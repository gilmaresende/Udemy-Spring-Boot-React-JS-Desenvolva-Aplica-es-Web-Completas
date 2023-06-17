package com.condelar.cursomc.services;

import com.condelar.cursomc.domain.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbsetractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void serndOrderConfirmationEmail(Pedido obj) {
        SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(obj.getCliente().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Pedido Confirmado");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(obj.toString());
        return sm;
    }

    protected String htmlFromTempletePedido(Pedido p) {
        Context context = new Context();
        context.setVariable("pedido", p);
        return templateEngine.process("email/confirmacaoPedido", context);
    }

    @Override
    public void sendOrderConfirmationHtmlEmail(Pedido pedido) {
        try {
            MimeMessage mm = prepareMimeMessageFromPedido(pedido);
            sendHtmlEmail(mm);
        } catch (MessagingException e) {
            sendOrderConfirmationHtmlEmail(pedido);
        }

    }

    protected MimeMessage prepareMimeMessageFromPedido(Pedido p) throws MessagingException {
        MimeMessage mm = javaMailSender.createMimeMessage();
        MimeMessageHelper mmh = new MimeMessageHelper(mm, true);
        mmh.setTo(p.getCliente().getEmail());
        mmh.setFrom(sender);
        mmh.setSubject("Pedido confirmado! Código: " + p.getId());
        mmh.setSentDate(new Date(System.currentTimeMillis()));
        mmh.setText(htmlFromTempletePedido(p), true);
        return mm;
    }
}
