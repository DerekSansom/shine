package shine.app.track;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shine.boards.Advert;
import com.sp.entity.track.AdAction;
import com.sp.entity.track.AdSave;
import com.sp.entity.track.AdView;
import com.sp.entity.track.BoardView;
import com.sp.entity.track.Login;
import com.sp.entity.track.PrivilegeTracker;
import com.sp.entity.track.TrackObject;
import com.sp.spring.SpApplicationContext;

/**
 * Uses a singleton and a very polite background thread, that keeps sleeping and
 * yielding to log tracking records to database.
 * 
 */
public class TrackingManager {

	private static Queue<TrackObject> logOps;

	private static Logger log = LoggerFactory.getLogger(TrackingManager.class);
	protected boolean running = true;

	private TrackManager trackManager;

	static {
		// call constructor to instantiate objects and kick of thread.
		new TrackingManager();
	}

	private TrackingManager() {
		trackManager = SpApplicationContext.getBean(TrackManager.class);
		logOps = new LinkedList<TrackObject>();
		Thread t = new Thread(new ShineLogger());
		t.setPriority(Thread.MIN_PRIORITY);
		t.start();
		// new Thread(new ShineLogger()).start();
	}

	private class ShineLogger implements Runnable {

		@Override
		public void run() {

			while (running) {
				if (logOps != null && logOps.isEmpty()) {
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						log.warn("LogDao interrupted exception", e);
					}
				} else {
					trackToDb();
				}

			}
		}

		private void trackToDb() {

			trackManager.persistTrackingObjects(logOps);

		}

	}

	public static void trackBoardView(int userId, int boardId, List<Advert> ads, int client) {
		BoardView bv = new BoardView(userId, boardId, ads, client);
		logOps.add(bv);

	}

	public static void trackAdView(int userId, int adId, int client) {
		AdView bv = new AdView(userId, adId, client);
		logOps.add(bv);

	}

	public static void trackAdAction(int userId, int adId, int action, int client) {
		AdAction bv = new AdAction(userId, adId, action, client);
		logOps.add(bv);

	}

	@Override
	protected void finalize() throws Throwable {
		running = false;
//		sess.flush();
//		sess.close();
		super.finalize();
	}

	public static void trackSaveAd(int userid, int adid, int deleteid,
			int action, int client) {

		AdSave as = new AdSave(userid, adid, deleteid, action, client);
		logOps.add(as);
	}

	public static void tracklogin(String cred1, String phoneNo, int client) {
		Login l = new Login(cred1, "", phoneNo, client);
		logOps.add(l);
	}

	public static void trackPrivilege(int userid, int friendid, int privilege, int client) {
		PrivilegeTracker l = new PrivilegeTracker(userid, friendid, privilege, client);
		logOps.add(l);
	}

}
