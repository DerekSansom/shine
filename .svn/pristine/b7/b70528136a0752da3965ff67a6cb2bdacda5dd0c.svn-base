package shine.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sp.mail.MailDao;

@Component
public class MailReadManager extends BaseHandler {

	@Autowired
	private MailDao dao;

	@Transactional
	public void markMailAsRead(int mailId) {
		if (mailId > 0) {
			dao.readMail(mailId);
		}

	}

}
