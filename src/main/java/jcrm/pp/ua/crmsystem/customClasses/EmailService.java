//package jcrm.pp.ua.crmsystem.customClasses;
//
//import com.google.common.hash.Hashing;
//import jcrm.pp.ua.crmsystem.services.MailContentBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//import java.nio.charset.StandardCharsets;
//
//@Service
//public class EmailService {
//
//    private final JavaMailSender javaMailSender;
//    private final MailContentBuilder mailContentBuilder;
//
//    @Autowired
//    public EmailService(JavaMailSender sender, MailContentBuilder mailContentBuilder) {
//        this.javaMailSender = sender;
//        this.mailContentBuilder = mailContentBuilder;
//    }
//
//    public void send(String emailTo, String subject, String body) throws MessagingException {
//        System.out.println("Sending email...");
//
//        long timestamp = System.currentTimeMillis();
//
//        String sha256hex = Hashing.sha256().hashString(String.valueOf(timestamp),StandardCharsets.UTF_8).toString();
//        System.out.println(sha256hex);
//
//        MimeMessage message = javaMailSender.createMimeMessage();
//
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//        helper.setTo(emailTo);
//        helper.setSubject(subject);
//        String content = mailContentBuilder.build(sha256hex);
//        helper.setText(content, true);
//
//
////        FileSystemResource file
////                = new FileSystemResource(new File(pathToAttachment));
////        helper.addAttachment("Invoice", file);
//
//        javaMailSender.send(message);
//
//        System.out.println("Email Send!");
//    }
//
//
//}