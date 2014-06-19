package shine.app.track;

import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.shine.boards.Advert;
import com.sp.entity.track.AdServe;
import com.sp.entity.track.BoardView;
import com.sp.entity.track.TrackObject;
import com.sp.track.TrackingDaoImpl;

@Component
public class TrackManager {
	private static Logger log = LoggerFactory.getLogger(TrackManager.class);

	@Autowired
	private TrackingDaoImpl trackingDaoImpl;

	@Transactional
	public void persistTrackingObjects(Queue<TrackObject> logOps) {

		TrackObject trackingObj = null;
		try {
			trackingObj = logOps.poll();
			while (trackingObj != null) {
				if (trackingObj instanceof BoardView) {
					track((BoardView) trackingObj);
				} else {
					trackingDaoImpl.save(trackingObj);
				}
				// yield after each object to allow higher level threads to
				// run
				Thread.yield();
				trackingObj = logOps.poll();
			}
		} catch (Throwable e) {
			log.warn("Failed to log " + trackingObj, e);
		}
	}

	private void track(BoardView boardView) {

		trackingDaoImpl.save(boardView);
		int i = 1;
		if (boardView.getAds() != null) {
			for (Advert ad : boardView.getAds()) {
				AdServe as = new AdServe(boardView.getId(), ad.getId(), i++);
				trackingDaoImpl.save(as);
			}
		}
	}

}
