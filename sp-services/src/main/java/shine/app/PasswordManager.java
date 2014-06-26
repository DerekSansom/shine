package shine.app;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import shine.app.mapper.PlayerMapper;
import shine.app.utils.ShineProperties;
import shine.dao.exception.ShineException;
import shine.mailsender.MailSender;

import com.shine.GeneralError;
import com.shine.SharedConstants;
import com.shine.objects.Player;
import com.sp.auth.TokenGenerator;
import com.sp.entity.PlayerEntity;
import com.sp.entity.auth.PasswordResetToken;
import com.sp.player.PlayerDao;
import com.sp.security.PasswordHasher;
import com.sp.security.PasswordResetDao;

@Component
public class PasswordManager extends BaseHandler {

	private PlayerMapper playerMapper = new PlayerMapper();
	private PasswordHasher passwordHasher = new PasswordHasher();

	@Autowired
	private PlayerDao playerDao;

	@Autowired
	private PasswordResetDao passwordResetDao;

	@Autowired
	private TokenGenerator tokenGenerator;


	// @Transactional
	// public void sendPasswordEmail(String cred1) throws ShineException {
	//
	// try {
	//
	// PlayerEntity user = playerDao.getUserByCredential(cred1);
	// if (user == null) {
	// throw new ShineException(SharedConstants.PLAYER_NOT_FOUND);
	// }
	//
	// String email = user.getEmail();
	// String name = user.getForename();
	// String text = "Your StreetPin password is " + user.getPassword() +
	// ", happy posting";
	// String title = "StreetPin password reminder";
	//
	// MailSender.sendMessage(name, email, text, title);
	// } catch (ShineException e) {
	// throw e;
	// } catch (Exception e) {
	// log.warn("login " + cred1 + " failed", e);
	// throw new ShineException(SharedConstants.PLAYER_NOT_FOUND);
	// }
	//
	// }

	@Transactional
	public void sendPasswordEmail(String cred1) throws ShineException {

		try {

			PlayerEntity user = playerDao.getUserByCredential(cred1);
			if (user == null) {
				throw new ShineException(SharedConstants.PLAYER_NOT_FOUND);
			}

			String token = tokenGenerator.generateToken();

			PasswordResetToken resetToken =
					passwordResetDao.createToken(user.getId(), token);

			sendResetEmail(user, resetToken);
		} catch (ShineException e) {
			throw e;
		} catch (Exception e) {
			log.warn("login " + cred1 + " failed", e);
			throw new ShineException(SharedConstants.PLAYER_NOT_FOUND);
		}

	}

	@Transactional
	public boolean isTokenValid(String token) throws ShineException {

		PasswordResetToken resetToken = passwordResetDao.getResetToken(token);
		if (resetToken != null) {
			DateTime created = new DateTime(resetToken.getCreated());
			if (created.plusDays(1).isAfterNow()) {
				return true;
			}
		}

		return false;
	}


	private void sendResetEmail(PlayerEntity user, PasswordResetToken resetToken) throws ShineException {
		String email = user.getEmail();
		String name = user.getForename();
		String text = "Dear " + name + ",\n\n Please use the following link to reset your StreetPin password.\n" + createLink(resetToken.getToken())
				+ "\n\n this is valid for 24 hours only, happy posting";
		String title = "StreetPin password reminder";

		MailSender.sendMessage(name, email, text, title);
	}

	private String createLink(String token) {

		return ShineProperties.getDomainUrl() + "/shine/mw/passwordreset?token=" + token;
	}

	@Transactional
	public void resetPassword(String cred1, String oldPassword, String newPassword) throws ShineException {

		PlayerEntity user = logInOldAndNew(cred1, oldPassword);

		resetPassword(user, newPassword);
	}

	private PlayerEntity logInOldAndNew(String cred1, String password) {
		try {
			String hashedPassword = passwordHasher.hashPassword(PasswordHasher.SALT, password);
			PlayerEntity user = playerDao.doLogin(cred1, hashedPassword);
			if (user != null) {
				return user;
			}
		} catch (Exception e) {

		}
		PlayerEntity user = playerDao.doLogin(cred1, password);

		return user;
	}

	@Transactional
	public Player doLogin(String cred1, String password, String platform, String os, String version)
			throws ShineException {

		try {

			PlayerEntity user = logInOldAndNew(cred1, password);

			if (user != null) {
				if (user.getSuspended() != null) {
					throw new ShineException(GeneralError.SUSPENDED);
				}
				user.setPlatform(platform);
				user.setOs(os);
				user.setVersion(version);

				// set new platform details to user
				playerDao.save(user);

				return playerMapper.entityToUserDto(user);
			}
		} catch (ShineException e) {
			throw e;
		} catch (Exception e) {
			log.warn("login " + cred1 + " failed", e);
		}
		return null;

	}

	@Transactional
	public void resetPassword(String token, String newPassword) throws ShineException {

		Long id = passwordResetDao.getIdByToken(token);
		if (id == null) {
			throw new ShineException(GeneralError.NOT_FOUND);
		}

		PlayerEntity user = playerDao.getPlayerById(id.intValue());

		if (user == null) {
			throw new ShineException(GeneralError.NOT_FOUND);
		}

		if (user.getSuspended() != null) {
			throw new ShineException(GeneralError.SUSPENDED);
		}
		passwordResetDao.deleteToken(token);
		resetPassword(user, newPassword);

	}

	private void resetPassword(PlayerEntity user, String newPassword) throws ShineException {

		if (user != null) {
			user.setPassword(passwordHasher.hashPassword(PasswordHasher.SALT, newPassword));
			playerDao.save(user);
		}
	}

}
