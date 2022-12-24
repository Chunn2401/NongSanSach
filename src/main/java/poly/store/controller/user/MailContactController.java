package poly.store.controller.user;

import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.store.common.Constants;
import poly.store.service.impl.MailerServiceImpl;

@Controller
@RequestMapping("/api")
public class MailContactController {

    @Autowired
    JavaMailSender mailSender;
    @Autowired
    MailerServiceImpl mail;

    @GetMapping("/send-mail")
    public String succeString(@RequestParam("to") String to,
            @RequestParam("subject") String subject,
            @RequestParam("body") String body) throws MessagingException {

        mail.sendfrom(to, subject, body);
        return "redirect:/contact";
    }

    // public void sendmail(String from, String to, String subject, String text) {
    // SimpleMailMessage mailMessage = new SimpleMailMessage();
    // mailMessage.setFrom(from);
    // mailMessage.setTo(to);
    // mailMessage.setSubject(subject);
    // mailMessage.setText(text);

    // // String[] cc = mail1.getCc();
    // // if (cc != null && cc.length > 0) {
    // // helper.setCc(cc);
    // // }

    // // String[] bcc = mail1.getBcc();
    // // if (bcc != null && bcc.length > 0) {
    // // helper.setBcc(bcc);
    // // }

    // // String[] attachments = mail1.getAttachment();
    // // if (attachments != null && attachments.length > 0) {
    // // for (String path : attachments) {
    // // File file = new File(path);
    // // helper.addAttachment(file.getName(), file);
    // // }
    // // }

    // mailSender.send(mailMessage);
    // }

}
