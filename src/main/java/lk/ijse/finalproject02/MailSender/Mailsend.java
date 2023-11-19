package lk.ijse.finalproject02.MailSender;

import javafx.event.ActionEvent;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

public class Mailsend extends Thread{
    public Mailsend(String email, String subject, String text) {
        this.email = email;
        this.subject = subject;
        this.text = text;
    }

    private String email;
    private String subject ;
    private String text;

    @Override
    public void run(){
        sendMail(email,subject,text);
    }
    public static void sendMail(String email,String subject ,String text){

            String to = email;
            String from = "amxdhnanditha@gmail.com";
            String host = "smtp.gmail.com";
            String username = "amxdhnanditha@gmail.com";
            String password = "gnhv lcmt oogq nppk";

            Properties properties = System.getProperties();
            properties.setProperty("mail.smtp.host", host);
            properties.setProperty("mail.smtp.port", "587");
            properties.setProperty("mail.smtp.starttls.enable", "true");
            properties.setProperty("mail.smtp.auth", "true");

            Session session = Session.getInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                message.setSubject(subject);
                message.setText(text);


                Transport.send(message);
                System.out.println("Email sent successfully.");
            } catch (MessagingException mex) {
                mex.printStackTrace();
            }


    }
}
