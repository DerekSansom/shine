package shine.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import shine.app.mapper.PlayerMapper;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.objects.Player;
import com.sp.auth.AuthService;
import com.sp.entity.PlayerEntity;
import com.sp.player.PlayerDao;
import com.sp.security.PasswordHasher;

@Component
public class LoginManager {

	protected Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private PlayerMapper playerMapper;

	@Autowired
	private PasswordHasher passwordHasher;

	@Autowired
	private PlayerDao playerDao;

	@Autowired
	private AuthService authService;

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

				String token = authService.createTokenForUser(user.getId());
				user.setToken(token);
				return playerMapper.entityToUserDto(user);
			}
		} catch (ShineException e) {
			throw e;
		} catch (Exception e) {
			log.warn("login " + cred1 + " failed", e);
		}
		return null;

	}

}
