package edu.egg.libreria.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServicio {

    @Autowired
    private JavaMailSender sender;

    @Value("${spring.mail.username}")
    private String from;


    private static String SUBJECT = "correo electrónico de bienvenida";

    private static String TEXT = "Bienvenido a nuestra página. ¡Gracias por registrarte!";

    @Async
    public void send(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom(from);
        message.setSubject(SUBJECT);
        message.setText(TEXT);
        sender.send(message);
    }
}
