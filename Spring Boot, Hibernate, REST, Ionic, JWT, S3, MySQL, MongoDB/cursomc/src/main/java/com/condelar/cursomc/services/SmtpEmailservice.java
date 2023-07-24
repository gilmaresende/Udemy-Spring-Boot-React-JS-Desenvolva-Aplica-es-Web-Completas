package com.condelar.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;

public class SmtpEmailservice extends AbsetractEmailService {

    private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailservice.class);

    @Autowired
    private MailSender mainSender;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        LOG.info("Enviando e-mail...");
        mainSender.send(msg);
        LOG.info("Email enviado!");
    }

    @Override
    public void sendHtmlEmail(MimeMessage msg) {
        LOG.info("Enviando e-mail HTML...");
        javaMailSender.send(msg);
        LOG.info("Email enviado!");
    }
}
