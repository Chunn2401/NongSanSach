package poly.store.service;

import javax.mail.MessagingException;

import poly.store.model.MailInfo;
import poly.store.model.Mailform;

public interface MailerService {
	void send(MailInfo mail) throws MessagingException;

	void sendfrom(Mailform mail) throws MessagingException;

	void send(String to, String subject, String body) throws MessagingException;

	void sendfrom(String to, String subject, String body) throws MessagingException;

	void queue(MailInfo mail);

	void queue(String to, String subject, String body);
}
