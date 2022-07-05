package kr.co.common;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kr.co.member.model.vo.Mail;

@Component
public class MailSend {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Transactional
	public void mailSend(Mail mail) throws Exception {
		String subject = mail.getSubject();
		String content = mail.getContent();
		String from = mail.getFrom();
		String fromName = mail.getFromName();
		String to = mail.getTo();
		
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			
			messageHelper.setSubject(subject);
			messageHelper.setText(content, true);
			messageHelper.setFrom(from, fromName);
			messageHelper.setTo(to);
			
			mailSender.send(message);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
