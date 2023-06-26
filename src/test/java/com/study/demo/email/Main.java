package com.study.demo.email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author caonan
 * @createtime 2023/6/13 15:29
 * @Description TODO
 * @Version 1.0
 */
public class Main {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.qq.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("1692430681@qq.com", "owzkhnqgxmazdbba");
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("1692430681@qq.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("18515878076@163.com"));
            message.setSubject("This is the subject of the email");
            message.setText("This is the content of the email");

            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

}
