package shine.app;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.boards.BoardCategory;
import com.shine.objects.ShineLocation;
import com.sp.board.BoardCreationDao;
import com.sp.entity.NoticeBoardEntity;
import com.sp.entity.PlayerEntity;
import com.sp.locations.BoardLocationsMapper;
import com.sp.player.PlayerDao;

@Component
public class BoardCreator {

	private static Logger log = LoggerFactory.getLogger(BoardManager.class);

	@Autowired
	private DefaultNoticeCreator defaultNoticeCreator;

	@Autowired
	private PlayerDao playerDao;

	@Autowired
	private BoardCreationDao boardCreationDao;

	@Autowired
	private BoardLocationsMapper boardLocator;

	@Transactional
	public int createBoard(String name, String locname, int creatorId, ShineLocation loc, BoardCategory category)
			throws ShineException {

		PlayerEntity creator = playerDao.getPlayerById(creatorId);

		NoticeBoardEntity nb = new NoticeBoardEntity();
		nb.setName(name);
		nb.setLocationName(locname);
		nb.setPlayer(creator);
		nb.setLat(loc.getLat());
		nb.setLng(loc.getLng());
		nb.setActive(true);
		nb.setCategory(category);

		try {
			boardCreationDao.createNoticeBoard(nb);
			startLocationingThread(nb.getId());
			defaultNoticeCreator.createDefaultNotices(nb.getId(), nb.getCreated(), Locale.UK);
			return nb.getId();

		} catch (Exception e) {
			log.error("failed to create new board: " + name + ", " + locname, e);

			return GeneralError.SYSTEM_ERROR.getCode();
		}


	}

	/**
	 * Looks up a new boards location in a thread so as not to block returning
	 * new board.
	 * 
	 * @param id
	 */
	private void startLocationingThread(final int id) {

		Runnable runner = new Runnable() {

			@Override
			public void run() {

				try {

					boardLocator.updateBoardLocation(id);
				} catch (Exception e) {
					log.error("Error setting loc for new Board id=" + id, e);
				}

			}
		};

		new Thread(runner).start();
	}

}
