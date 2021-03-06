package shine.app;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import shine.app.utils.ShineProperties;
import shine.dao.exception.ShineException;
import shine.dao.msg.MailMapper;

import com.shine.SharedConstants;
import com.shine.Util;
import com.shine.mail.Mail;
import com.shine.mail.MsgThread;
import com.shine.xml.MailXml;
import com.sp.entity.PlayerEntity;
import com.sp.entity.msg.MailEntity;
import com.sp.mail.MailDao;
import com.sp.player.PlayerDao;

@Component
public class MailHandler extends BaseHandler {

	protected Logger log = LoggerFactory.getLogger(getClass());
	private MailMapper mapper = new MailMapper();

	@Autowired
	private MailDao mailDao;

	@Autowired
	private PlayerDao playerDao;

	@Transactional
	public String getMessagesAsThreads(int userId, boolean newMsgs, int pageSize) throws ShineException {

		try {
			if (newMsgs) {
				return getNewMsgs(userId);
			} else {
				return getMessagesAsThreads(userId, pageSize);
			}
		} catch (Exception e) {
			log.error("Failed to get mails", e);
			throw new ShineException(e.getMessage());
		}

	}

	@Transactional
	public String getMessagesAsThreads(int userId, int pageSize) throws ShineException, IOException {
		List<MailEntity> in = mailDao.retrieveMailByRecipient(userId, null);
		List<MailEntity> firstDelivery = markFirstDelivered(in);
		List<MailEntity> out = mailDao.retrieveMailBySender(userId, null);

		List<MsgThread> threads = organiseMailsToThread(mapper.entityToDto(in), mapper.entityToDto(out));
		markAsDelivered(firstDelivery);
		return threadsToJson(threads);

	}

	String getNewMsgs(int userId) throws IOException {
		List<MailEntity> in = mailDao.retrieveNewMailByRecipient(userId);

		List<MsgThread> threads = organiseMailsToThread(mapper.entityToDto(in), null);
		String json = threadsToJson(threads);

		sortAndMarkAsDelivered(in);
		return json;
	}

	private String threadsToJson(List<MsgThread> threads) throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		JsonFactory f = new JsonFactory();
		JsonGenerator jgen = f.createJsonGenerator(os);
		jgen.writeStartArray();
		for (MsgThread thread : threads) {
			jgen.writeStartObject();
			jgen.writeNumberField("from", thread.getCorrespondentId());
			jgen.writeStringField("name", thread.getCorrespodentName());
			jgen.writeBooleanField("unread", thread.isHasUnread());
			jgen.writeStringField("last", Util.datadf.format(thread.getRecent()));
			jgen.writeArrayFieldStart("msgs");
			for (MsgThread.Message msg : thread.getMsgs()) {
				jgen.writeStartObject();
				jgen.writeBooleanField("f", msg.isFrom());
				jgen.writeBooleanField("n", msg.isNew());
				jgen.writeStringField("txt", msg.getMessage());
				jgen.writeStringField("date", Util.datadf.format(msg.getCreated()));
				if (msg.getSysType() > 0) {
					jgen.writeNumberField("st", msg.getSysType());
					jgen.writeNumberField("x", msg.getIntx());
				}
				jgen.writeEndObject();

			}
			jgen.writeEndArray();

			jgen.writeEndObject();

		}
		jgen.writeEndArray();
		jgen.flush();
		os.toString();

		return os.toString();
	}

	private List<MsgThread> organiseMailsToThread(List<Mail> in, List<Mail> out) {

		Map<Integer, List<Mail>> map = new HashMap<Integer, List<Mail>>();
		if (out != null) {
			for (Mail mail : out) {
				List<Mail> thread = map.get(mail.getRecipientId());
				if (thread == null) {
					thread = new ArrayList<Mail>();
					map.put(mail.getRecipientId(), thread);
				}
				thread.add(mail);
			}
		}
		for (Mail mail : in) {
			List<Mail> thread = map.get(mail.getCreatorId());
			if (thread == null) {
				thread = new ArrayList<Mail>();
				map.put(mail.getCreatorId(), thread);
			}
			thread.add(mail);

		}

		Comparator<Mail> mcomp = new Comparator<Mail>() {

			@Override
			public int compare(Mail o1, Mail o2) {
				return o1.getCreated().compareTo(o2.getCreated());
			}
		};

		List<MsgThread> threads = new ArrayList<MsgThread>();
		for (Integer i : map.keySet()) {
			String playerName = null;
			if (i != 1) {
				PlayerEntity p = playerDao.getPlayerById(i);
				if (p != null) {
					playerName = p.getUsername();
				}
			} else {
				playerName = "sp";
			}
			List<Mail> mails = map.get(i);
			Collections.sort(mails, mcomp);
			MsgThread thread = createThread(i, mails, playerName);
			threads.add(thread);
		}

		Comparator<MsgThread> threadComp = new Comparator<MsgThread>() {

			@Override
			public int compare(MsgThread o1, MsgThread o2) {
				return o1.getRecent().compareTo(o2.getRecent());
			}
		};

		Collections.sort(threads, threadComp);

		return threads;
	}

	private MsgThread createThread(int correspondentId, List<Mail> mails, String playerName) {
		MsgThread thread = new MsgThread();
		thread.setCorrespondentId(correspondentId);
		thread.setCorrespodentName(playerName);
		List<MsgThread.Message> messages = new ArrayList<MsgThread.Message>();

		for (Mail mail : mails) {
			if (thread.getRecent() == null || thread.getRecent().before(mail.getCreated())) {
				thread.setRecent(mail.getCreated());
			}
			String message = mail.getSubject();
			if (mail.getMessage() != null) {
				message += " -" + mail.getMessage();
			}
			boolean isFromPlayer = mail.getRecipientId() == correspondentId;
			MsgThread.Message msg = thread.new Message(mail.getCreated(), mail.isRead(), message,
					isFromPlayer, mail.getSystemType(), mail.getIntExtra());
			if (!mail.isRead()) {
				thread.setHasUnread(true);
			}
			messages.add(msg);
		}
		thread.setMsgs(messages);
		return thread;
	}

	@Transactional
	public String getMail(int box, int userId, int start, int count) throws ShineException {
		try {
			if (box == SharedConstants.NEW_MAILS) {
				return getNewMail(userId);
			} else if (box == SharedConstants.JSON_NEW) {
				return getNewMsgs(userId);
			} else {
				return getAllMail(box, userId, start, count);
			}
		} catch (Exception e) {
			log.error("Failed to get mails", e);
			throw new ShineException(e);
		}

	}

	/**
	 * Allows a DAO to be passed in to return emails, allowing a session to be
	 * shared. This then leaves the caller responsible for handling exceptions
	 * and closing session.
	 * 
	 * 
	 * @param dao
	 * @param box
	 * @param userId
	 * @param start
	 * @param count
	 * @return
	 * @throws ShineException
	 */
	private String getAllMail(int box, int userId, int start, int count)
			throws ShineException {

		StringBuilder xml = new StringBuilder("<mailboxes>");
		if (box == SharedConstants.JSON_MSGS) {
			try {
				String msgs = getMessagesAsThreads(userId, count);
				xml.append(msgs);
			} catch (IOException e) {
				// do nothing except log and fix
				log.error("error getting msg json", e);
			}
		} else {
			List<MailEntity> inbox = null;
			if (box == SharedConstants.IN_BOX || box == SharedConstants.BOTH_BOXES) {
				int unread = mailDao.unreadMails(userId);
				inbox = getInbox(userId, start, count);
				sortAndMarkAsDelivered(inbox);
				List<Mail> inboxDtos = mapper.entityToDto(inbox);
				xml.append("<inbox unread=\"" + unread + "\">");
				for (Mail mail : inboxDtos) {
					PlayerEntity p = playerDao.getPlayerById(mail.getCreatorId());
					if (p != null) {
						mail.setSender(p.getUsername());
					}
					xml.append(new MailXml(mail).toXml());
				}
				xml.append("</inbox>");
			}
			if (box == SharedConstants.OUT_BOX || box == SharedConstants.BOTH_BOXES) {
				List<MailEntity> outbox = getOutbox(userId, start, count);
				xml.append("<outbox>");
				List<Mail> outboxDtos = mapper.entityToDto(outbox);

				for (Mail mail : outboxDtos) {
					PlayerEntity p = playerDao.getPlayerById(mail.getRecipientId());
					if (p != null) {
						mail.setStringExtra(p.getUsername());
					}
					xml.append(new MailXml(mail).toSentXml());
				}
				xml.append("</outbox>");
			}
		}

		xml.append("</mailboxes>");
		return xml.toString();
	}

	private List<MailEntity> markFirstDelivered(List<MailEntity> mail) {
		if (mail != null) {
			List<MailEntity> firstDelivered = new ArrayList<MailEntity>();
			Date now = new Date();
			for (MailEntity m : mail) {
				if (m.getDelivered() == null) {
					m.setNewMail(true);
					firstDelivered.add(m);
				}
			}
			return firstDelivered;
		}
		return null;
	}

	private void markAsDelivered(List<MailEntity> firstDelivered) {
		mailDao.setDelivered(firstDelivered);
	}

	private List<MailEntity> getOutbox(int userId, int start, int count) {
		return mailDao.retrieveMailBySender(userId, start, count);
	}

	private List<MailEntity> getInbox(int userId, int start, int count) {

		return mailDao.retrieveMailByRecipient(userId, start,
				ShineProperties.getDEFAULT_MAILS_TO_RETRIEVE());
	}

	@Transactional
	public String getNewMail(int userId) throws ShineException {
		List<MailEntity> inbox = mailDao.retrieveNewMailByRecipient(userId);
		sortAndMarkAsDelivered(inbox);
		List<Mail> inboxDtos = mapper.entityToDto(inbox);

		StringBuilder xml = new StringBuilder();
		xml.append("<inbox>");
		for (Mail mail : inboxDtos) {
			PlayerEntity p = playerDao.getPlayerById(mail.getCreatorId());
			if (p != null) {
				mail.setSender(p.getUsername());
			}
			xml.append(new MailXml(mail).toXml());
		}
		xml.append("</inbox>");
		return xml.toString();
	}

	private void sortAndMarkAsDelivered(List<MailEntity> inbox) {
		List<MailEntity> firstDelivery = markFirstDelivered(inbox);
		markAsDelivered(firstDelivery);
	}

}
