package com.ms.email.services;

import com.ms.email.enums.StatusEmail;
import com.ms.email.models.EmailModel;
import com.ms.email.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    EmailRepository repository;

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String emailFrom;

    public EmailModel sendEmail(EmailModel emailModel){

        try{
            emailModel.setEmailFrom(emailFrom);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());

            javaMailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.SENT);
        }catch (MailException e){
            emailModel.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return repository.save(emailModel);
        }
    }
}
