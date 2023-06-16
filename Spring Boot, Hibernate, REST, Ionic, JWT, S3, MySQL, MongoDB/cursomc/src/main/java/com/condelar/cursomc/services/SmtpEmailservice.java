package com.condelar.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SmtpEmailservice extends AbsetractEmailService {

    private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailservice.class);

    @Autowired
    private MailSender mainSender;

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        LOG.info("Simulando envio de e-mail...");
        mainSender.send(msg);
        LOG.info("Email enviado!");
    }
}
