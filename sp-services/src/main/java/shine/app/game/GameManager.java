package shine.app.game;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import shine.app.BaseHandler;
import shine.app.PlayerManager;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.SharedConstants;
import com.shine.Util;
import com.shine.objects.Diamond;
import com.shine.objects.Emerald;
import com.shine.objects.Ruby;
import com.shine.objects.Sapphire;
import com.shine.objects.ShineLocation;
import com.shine.objects.ShineObject;
import com.shine.objects.Stone;
import com.shine.objects.Player;
import com.sp.entity.PlayerEntity;
import com.sp.entity.game.PlayerDiamond;
import com.sp.entity.game.PlayerEmerald;
import com.sp.entity.game.PlayerRuby;
import com.sp.entity.game.PlayerSapphire;
import com.sp.entity.game.PlayerStone;
import com.sp.player.PlayerDao;

@Component
public class GameManager extends BaseHandler {

	public static final int sapphiresPerDay = 6;
	private static final double maxDiff = 0.004;
	// distance in meters from which one can pick up an object
	private static final double pickupDiff = 50;
	public static final int PICKUP = 8;
	private static final int COST_OF_EMERALD = 1;
	private String nextRuby;

	@Autowired
	private com.sp.game.GameDao gameDao;

	@Autowired
	private PlayerDao pdao;

	public void addStones(List<ShineObject> list, ShineLocation userloc, ShineLocation viewloc, Player player,
			long secondsSince, double radius) {

		List<Sapphire> sapphires = gameDao.getSapphires(userloc, secondsSince, radius);
		list.addAll(sapphires);
		if (secondsSince == 0) {
			list.addAll(generateNewSapps(userloc, player, sapphires));
		}
		if (viewloc != null) {
			list.addAll(gameDao.getSapphires(viewloc, secondsSince, radius));
		}
		if (secondsSince == 0) {
			List<Emerald> emeralds = gameDao.getEmeralds(userloc, viewloc, player, radius);
			list.addAll(emeralds);
		} else {
			List<Emerald> emeralds = gameDao.getEmeralds(userloc, secondsSince, radius);
			list.addAll(emeralds);
		}
		if (secondsSince == 0) {
			List<Ruby> rubies = gameDao.getRubies(userloc, viewloc, player, radius);
			list.addAll(rubies);
		} else {
			List<Ruby> rubies = gameDao.getRubies(userloc, secondsSince, radius);
			list.addAll(rubies);

		}
		if (secondsSince == 0) {

			List<Diamond> diamonds = gameDao.getDiamonds(userloc, viewloc, player, radius);
			list.addAll(diamonds);
		} else {
			List<Diamond> diamonds = gameDao.getDiamonds(userloc, secondsSince, radius);
			list.addAll(diamonds);
		}

	}

	private List<Sapphire> generateNewSapps(ShineLocation loc, Player player, List<Sapphire> current) {

		int currentSapphires = 0;

		double currentLat = loc.getLat();
		double currentLng = loc.getLng();

		for (Sapphire sapphire : current) {
			double sLat = sapphire.getLat();
			double sLng = sapphire.getLng();

			if (Math.abs(sLat - currentLat) < maxDiff
					&& Math.abs(sLng - currentLng) < maxDiff) {
				currentSapphires++;
			}

		}

		int toCreate = Math.max(0, sapphiresPerDay - currentSapphires);

		List<Sapphire> list = new ArrayList<Sapphire>();

		for (int i = toCreate; i > 0; i--) {

			double lat = getRandomDouble(loc.getLat(), maxDiff);
			double lng = getRandomDouble(loc.getLng(), maxDiff);
			Sapphire d = new Sapphire();
			d.setLocation(new ShineLocation(lat, lng));
			d.setCreatorId(player.getId());
			gameDao.createSapphire(d);
			list.add(d);

		}

		return list;
	}

	public double getRandomDouble(double lat, double maxDiff) {

		double round = lat - maxDiff;
		double rnd = Math.random() * 2;
		double ret = rnd * maxDiff;
		return ret + round;
	}

	/**
	 * This has to be synchronised because each sapphire can be picked up by
	 * only one player.
	 * 
	 * @param objectId
	 * @param userid
	 * @param loc
	 * @return
	 * @throws ShineException
	 */
	public synchronized int pickUpSapphire(int sappId, int userid, ShineLocation loc) throws ShineException {

		try {
			PlayerSapphire pe = gameDao.getPlayerStone(PlayerSapphire.class, userid, sappId);
			if (pe != null) {
				throw new ShineException(SharedConstants.ALREADY_ACTIONED);
			}
			Sapphire s = gameDao.getSapphire(sappId);
			if (s == null) {
				throw new ShineException(GeneralError.NOT_FOUND);
			}
			PlayerEntity p = pdao.getPlayerById(userid);
			if (p == null) {
				throw new ShineException(SharedConstants.PLAYER_NOT_FOUND);
			}
			if (s.getOwnerId() != null && s.getOwnerId() != 0) {
				if (s.getOwnerId() == userid) {
					pe = new PlayerSapphire();
					pe.setStoneId(sappId);
					pe.setUserId(userid);
					gameDao.save(pe);
					throw new ShineException(SharedConstants.ALREADY_ACTIONED);
				}
				throw new ShineException(SharedConstants.SOMEONE_ELSES_SAPPHIRE);
			}

			if (!isInRange(loc, s.getLocation(), pickupDiff)) {
				throw new ShineException(SharedConstants.TOO_FAR);
			}

			s.setOwnerId(userid);
			pickUpStone(new PlayerSapphire(), p, s, loc);
			return SharedConstants.SUCCESS;

		} catch (ShineException e) {
			throw e;
		} catch (Exception e) {
			log.warn("Failed to pickup sapphire", e);
			throw new ShineException(GeneralError.SYSTEM_ERROR);
		}

	}

	public String getNextRubyXml() {
		return nextRuby;
	}

	private boolean isInRange(ShineLocation loc1, ShineLocation loc2, double maxMetres) {
		return Util.calculateDistance(loc1.getLat(), loc1.getLng(), loc2.getLat(), loc2.getLng()) < maxMetres;
	}

	@Transactional
	public boolean deleteDiamond(int objectId) {
		gameDao.deleteDiamond(objectId);
		return true;

	}

	@Transactional
	public boolean deleteDiamondsByLocation(ShineLocation loc) {

		int ret = gameDao.deleteDiamond(loc);
			return ret > 0;

	}

	@Transactional
	public void createUserEmerald(Emerald emerald, ShineLocation loc) throws ShineException {

		//retrieve user through a player manager for error handling and tracking loc
		PlayerManager pm = new PlayerManager();
		Player user = pm.getUser(emerald.getCreatorId(), loc);
		if (user == null) {
			throw new ShineException(SharedConstants.PLAYER_NOT_FOUND);
		}

		if (user.getScore() < COST_OF_EMERALD) {
			throw new ShineException(SharedConstants.NOT_ENOUGH_POINTS);
		}
		user.setScore(user.getScore() - COST_OF_EMERALD);
		gameDao.createUserEmerald(emerald, user);
	}

	@Transactional
	public boolean pickUpEmerald(ShineLocation loc, int emaraldid, int playerid, int answerId) throws ShineException {

		PlayerEmerald pe = gameDao.getPlayerStone(PlayerEmerald.class, playerid, emaraldid);
		if (pe != null) {
			throw new ShineException(SharedConstants.ALREADY_ACTIONED);
		}
		Emerald e = gameDao.getEmerald(emaraldid);
		if (e == null) {
			throw new ShineException(GeneralError.NOT_FOUND);
		}
		PlayerEntity p = pdao.getPlayerById(playerid);
		if (p == null) {
			throw new ShineException(SharedConstants.PLAYER_NOT_FOUND);
		}
		if (p.getScore() < e.getPoints()) {
			throw new ShineException(SharedConstants.NOT_ENOUGH_POINTS);
		}
		if (!isInRange(e.getLocation(), loc, pickupDiff)) {
			throw new ShineException(SharedConstants.TOO_FAR);
		}
		if (e.getCorrectAnswer() != answerId) {
			if (e.getCreatorId() != null && e.getCreatorId() > 0) {
				PlayerEntity creator = pdao.getPlayerById(e.getCreatorId());
				if (creator != null) {
					creator.addScore(e.getPoints());
					p.setScore(p.getScore() - e.getPoints());
					pdao.save(creator);
					pdao.save(p);
				} else {
					log.warn("Picking up emerald: " + e.getId() + ", creator " + e.getCreatorId() + " not found");
				}
			}
			throw new ShineException(SharedConstants.WRONG_ANSWER);
		}

		pickUpStone(new PlayerEmerald(), p, e, loc);
		return true;
	}

	@Transactional
	public boolean pickUpDiamond(ShineLocation loc, int diamondid, int playerid) throws ShineException {
		PlayerDiamond pe = gameDao.getPlayerStone(PlayerDiamond.class, playerid, diamondid);
		if (pe != null) {
			throw new ShineException(SharedConstants.ALREADY_ACTIONED);
		}
		Diamond e = gameDao.getDiamond(diamondid);
		if (e == null) {
			throw new ShineException(GeneralError.NOT_FOUND);
		}
		PlayerEntity p = pdao.getPlayerById(playerid);
		if (p == null) {
			throw new ShineException(SharedConstants.PLAYER_NOT_FOUND);
		}
		if (!isInRange(e.getLocation(), loc, pickupDiff)) {
			throw new ShineException(SharedConstants.TOO_FAR);
		}

		pickUpStone(new PlayerDiamond(), p, e, loc);
		return true;
	}

	/**
	 * 
	 * @param loc
	 * @param rubyid
	 * @param playerid
	 * @return error code or id of next ruby in trail
	 * @throws ShineException
	 */
	@Transactional
	public int pickUpRuby(ShineLocation loc, int rubyid, int playerid) throws ShineException {

		PlayerRuby pe = gameDao.getPlayerStone(PlayerRuby.class, playerid, rubyid);
		if (pe != null) {
			throw new ShineException(SharedConstants.ALREADY_ACTIONED);
		}
		Ruby ruby = gameDao.getRuby(rubyid);
		if (ruby == null) {
			throw new ShineException(GeneralError.NOT_FOUND);
		}
		PlayerEntity p = pdao.getPlayerById(playerid);
		if (p == null) {
			throw new ShineException(SharedConstants.PLAYER_NOT_FOUND);
		}
		if (!isInRange(ruby.getLocation(), loc, pickupDiff)) {
			throw new ShineException(SharedConstants.TOO_FAR);
		}
		if (ruby.getParentId() != null) {
			PlayerRuby parent = gameDao.getPlayerStone(PlayerRuby.class, playerid, ruby.getParentId());
			if (parent == null) {
				throw new ShineException(SharedConstants.PARENT_NOT_COLLECTED);
			}

		}

		pickUpStone(new PlayerRuby(), p, ruby, loc);

		if (ruby.getChildId() != null) {
			return ruby.getChildId();
		}

		return SharedConstants.TRAIL_FINISHED;

	}

	@Transactional
	public Ruby getRuby(int rubyid, int playerid) throws ShineException {

		Ruby ruby = gameDao.getRuby(rubyid);
			if (ruby == null) {
				throw new ShineException(GeneralError.NOT_FOUND);
			}
			PlayerEntity p = pdao.getPlayerById(playerid);
			if (p == null) {
				throw new ShineException(SharedConstants.PLAYER_NOT_FOUND);
			}
			if (ruby.getParentId() == null) {
				return ruby;
			}
			if (ruby.getParentId() != null) {
			PlayerRuby parent = gameDao.getPlayerStone(PlayerRuby.class, playerid, ruby.getParentId());
				if (parent == null) {
					throw new ShineException(SharedConstants.PARENT_NOT_COLLECTED);
				}
			}
			return ruby;

	}

	/**
	 * Does standard ops of populating the PlayerStone object, adding points and
	 * saving all objects in a transaction.
	 * 
	 * @param ps
	 * @param p
	 * @param e
	 * @param loc
	 * @param dao
	 * @param pdao
	 * @throws ShineException
	 */

	private void pickUpStone(PlayerStone ps, PlayerEntity p, Stone e, ShineLocation loc) throws ShineException {
		p.addScore(e.getPoints());

		ps.setUserId(p.getId());
		ps.setStoneId(e.getId());

		try {
			gameDao.save(ps);
			if (e instanceof Sapphire) {
				gameDao.save(e);
			}
			pdao.updateUserWithLoc(p, loc);
		} catch (Exception ex) {
			log.error("Failed to pickup " + e.getClass().getSimpleName() + " : " + e.getId(), ex);
			throw new ShineException(ex);
		}

	}
}
