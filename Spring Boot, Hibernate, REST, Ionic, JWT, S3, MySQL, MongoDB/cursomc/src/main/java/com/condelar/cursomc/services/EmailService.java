package com.condelar.cursomc.services;

import com.condelar.cursomc.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void serndOrderConfirmationEmail(Pedido obj);

    void sendEmail(SimpleMailMessage msg);
}
