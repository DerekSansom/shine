package shine.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import shine.app.game.GameManager;
import shine.app.mapper.BoardMapper;
import shine.app.mapper.PlayerMapper;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.SharedConstants;
import com.shine.boards.NoticeBoard;
import com.shine.objects.Player;
import com.shine.objects.ShineLocation;
import com.shine.objects.ShineObject;
import com.sp.board.BoardDao;
import com.sp.entity.NoticeBoardEntity;
import com.sp.entity.PlayerEntity;
import com.sp.player.PlayerDao;

@Component
public class ObjectManager extends BaseHandler {

	private BoardMapper boardMapper = new BoardMapper();
	private PlayerMapper playerMapper = new PlayerMapper();
	private Player unknownUser = null;

	@Autowired
	private PlayerDao playerDao;

	@Autowired
	private BoardDao boardDao;

	@Autowired
	private GameManager gameManager;

	public double getRandomDouble(double lat, double maxDiff) {

		double round = lat - maxDiff;
		double rnd = Math.random() * 2;
		double ret = rnd * maxDiff;
		return ret + round;
	}

	public List<ShineObject> getNewUserLocObjects(int mode, ShineLocation userloc,
			ShineLocation viewloc, Player player, long secondsSince, double radius) throws ShineException {

		if (player == null && mode != SharedConstants.MODE_BOARDS) {
			throw new ShineException(SharedConstants.PLAYER_NOT_FOUND);
		} else if (player == null) {
			player = getUnknownUser();
		}

		if (userloc == null && viewloc == null) {
			throw new ShineException(GeneralError.PARAM_MISSING, "Location required");
		}
		if (secondsSince > 0) {
			secondsSince += 100;
		}

		List<ShineObject> list = new ArrayList<ShineObject>();
		if (mode == SharedConstants.MODE_BOTH || mode == SharedConstants.MODE_GAME) {
			if (userloc == null) {
				throw new ShineException(GeneralError.PARAM_MISSING, "user location is required in game mode");
			}

			gameManager.addStones(list, userloc, viewloc, player, secondsSince, radius);
			List<PlayerEntity> players = playerDao.getNearPlayers(userloc, player.getId(), radius);

			list.addAll(playerMapper.entityToDto(players));
		}

		if (mode == SharedConstants.MODE_BOTH || mode == SharedConstants.MODE_BOARDS) {

			List<NoticeBoard> boardDtos = getBoards(viewloc, secondsSince, radius, userloc);

			list.addAll(boardDtos);

		}
		return list;
	}

	public List<ShineObject> getBoards(ShineLocation userloc, ShineLocation viewloc, double radius)
			throws ShineException {

		List<ShineObject> objects = new ArrayList<>();

		objects.addAll(getBoards(viewloc, 0, radius, userloc));

		return objects;
	}

	private List<NoticeBoard> getBoards(ShineLocation viewloc, long secondsSince, double radius,
			ShineLocation sortByDistanceFrom) {
		List<NoticeBoardEntity> boards = boardDao.getNearBoards(viewloc, secondsSince, radius);

		List<NoticeBoard> boardDtos = boardMapper.entityToDto(boards);
		if (sortByDistanceFrom != null) {
			sortBoardsByDistance(boardDtos, sortByDistanceFrom);
		}

		return boardDtos;
	}

	private void sortBoardsByDistance(List<NoticeBoard> boards, ShineLocation userloc) {

		Collections.sort(boards, new BoardDistanceComparator(userloc));

	}

	private Player getUnknownUser() {
		if (unknownUser == null) {
			unknownUser = new Player();
			unknownUser.setId(0);
		}
		return unknownUser;
	}

}
