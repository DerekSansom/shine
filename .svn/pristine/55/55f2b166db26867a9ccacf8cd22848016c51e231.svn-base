package com.sp.mail;

import java.util.Date;
import java.util.List;

import com.sp.entity.msg.MailEntity;

public interface MailDao {

	int createMail(MailEntity mail);

	List<MailEntity> retrieveMailBySender(int userId, Date since);

	List<MailEntity> retrieveMailBySender(int userId, int numberToRetrieve, int start);

	List<MailEntity> retrieveMailByRecipient(int userId, Date since);

	List<MailEntity> retrieveMailByRecipient(int userId, int numberToRetrieve, int start);

	List<MailEntity> retrieveNewMailByRecipient(int userId);

	void setDelivered(List<MailEntity> mail);

	int unreadMails(int userId);

	void readMail(int mailId);

}
