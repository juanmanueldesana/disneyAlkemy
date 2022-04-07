package com.alkemy.disney.utils;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    
    @Value("${app.sendgrid.templateId}")
    private String templateId;

    @Autowired
    SendGrid sendGrid;


    public String sendEmail(String email) {

        Mail mail = prepareMail(email);

        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
            if (response != null) {
                System.out.println(response.getStatusCode());
                System.out.println(response.getBody());
                System.out.println(response.getHeaders());
            } else {
                System.out.println("Response is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Email sent successfully";
    }

    public Mail prepareMail(String email) {

        Mail mail = new Mail();

        Email fromEmail = new Email();

        fromEmail.setEmail("desanajm@gmail.com");

        mail.setFrom(fromEmail);
        Email to = new Email();
        to.setEmail(email);

        Personalization personalization = new Personalization();

        personalization.addTo(to);
        mail.addPersonalization(personalization);

        mail.setTemplateId(templateId);

        return mail;
    }

}
