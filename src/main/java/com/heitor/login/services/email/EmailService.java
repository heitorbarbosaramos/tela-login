package com.heitor.login.services.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Date;

@Service
public class EmailService {

    @Autowired
    private  JavaMailSender javaMailSender;

    @Value("${email.from.address}")
    private  String fromAddress;

    private  final Logger LOG = LoggerFactory.getLogger(JavaMailSender.class);

    public  void enviaEmail(String toEmail, String subject, String message, File file) throws MessagingException {
        LOG.info("PREPARANDO ENVIO DE EMAIL");

        MimeMultipart mimeMultipart = new MimeMultipart();
        MimeBodyPart mimeBodyPart = new MimeBodyPart();

        mimeBodyPart.setContent(message,"text/html; charset=UTF-8");
        mimeMultipart.addBodyPart(mimeBodyPart);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(fromAddress);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setSentDate(new Date(System.currentTimeMillis()));

        if(file != null){helper.addAttachment(file.getName(), file);}

        mimeMessage.setContent(mimeMultipart);
        try {
            javaMailSender.send(mimeMessage);
            LOG.info("EMAIL ENVIADO");
        }catch (RuntimeException e){
            LOG.error("ERRO DE ENVIO DE EMAIL");
            LOG.error(e.getMessage());
        }

    }

    public  void sendMail(String toEmail, String subject, String message) {
        try {
            enviaEmail(toEmail, subject, message, null);
        } catch (MessagingException e) {
            LOG.error(e.getMessage());
        }
    }

    public  void sendMail(String toEmail, String subject, String message, File file) {
        try {
            enviaEmail(toEmail, subject, message, file);
        } catch (MessagingException e) {
            LOG.error(e.getMessage());
        }
    }
}
