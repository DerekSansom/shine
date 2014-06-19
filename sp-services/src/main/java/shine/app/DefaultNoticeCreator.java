package shine.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import shine.app.utils.ShineProperties;

import com.shine.boards.NoticeCategory;
import com.sp.entity.NoticeEntity;
import com.sp.notice.NoticeDao;

@Component
public class DefaultNoticeCreator {

	private static Date defaultExpiry = new GregorianCalendar(2020, 12, 31).getTime();

	private static Logger log = LoggerFactory.getLogger(DefaultNoticeCreator.class);

	@Autowired
	private NoticeDao noticeDao;

	/**
	 * Creates 3 default notices for a board. This should never throw an
	 * exception, rather if it fails it is silent
	 * 
	 * @param dao
	 * @param nb
	 */
	List<NoticeEntity> createDefaultNotices(int boardId, Date created, Locale locale) {
		List<NoticeEntity> defaults = new ArrayList<NoticeEntity>();
		if (boardId > 0) {

			try {
				NoticeEntity n1 = getDefaultNotice1(boardId, locale);
				n1.setCreated(created);
				noticeDao.createNotice(n1);
				defaults.add(n1);
				NoticeEntity n2 = getDefaultNotice2(boardId, locale);
				noticeDao.createNotice(n2);
				n2.setCreated(created);
				defaults.add(n2);
				NoticeEntity n3 = getDefaultNotice3(boardId, locale);
				noticeDao.createNotice(n3);
				n3.setCreated(created);
				defaults.add(n3);

			} catch (Exception e) {
				log.debug("Failed to create a default notice", e);
			}
		}
		return defaults;

	}

	private NoticeEntity getDefaultNotice1(int boardid, Locale locale) {
		String title = ShineProperties.getProperty("defaultNotice1.title", "What's occurring right here, right now?");
		int catid = ShineProperties.getIntProperty("defaultNotice1.category.id", NoticeCategory.HERE_AND_NOW.getId());
		NoticeEntity n = createDefaultNotice(boardid, title, catid, defaultExpiry);
		return n;
	}

	private NoticeEntity createDefaultNotice(int boardid, String title, int catid, Date defaultExpiry) {
		NoticeEntity defaultNotice = new NoticeEntity();
		defaultNotice.setBoardId(boardid);
		defaultNotice.setTitle(title);
		defaultNotice.setCategoryId(catid);
		defaultNotice.setExpires(defaultExpiry);
		return defaultNotice;
	}

	private NoticeEntity getDefaultNotice2(int boardid, Locale locale) {

		String title = ShineProperties.getProperty("defaultNotice2.title", "Get it off your chest – what’s frustrated you here?");
		int catid = ShineProperties.getIntProperty("defaultNotice2.category.id", NoticeCategory.RANT.getId());

		NoticeEntity n = createDefaultNotice(boardid, title, catid, defaultExpiry);
		return n;
	}

	private NoticeEntity getDefaultNotice3(int boardid, Locale locale) {
		String title = ShineProperties.getProperty("defaultNotice3.title", "Anyone bored?");
		int catid = ShineProperties.getIntProperty("defaultNotice3.category.id", NoticeCategory.MEETING_UP.getId());
		NoticeEntity n = createDefaultNotice(boardid, title, catid, defaultExpiry);
		return n;
	}

}
