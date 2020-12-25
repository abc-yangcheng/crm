package top.upstudy.crm.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailServiceImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender javaMailSender;

    //注入配置文件中配置的信息——>from
    @Value("${spring.mail.username}")
    private String username;

    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        //发件人
        message.setFrom(username);
        //收件人
        message.setTo(to);
        //邮件主题
        message.setSubject(subject);
        //邮件内容
        message.setText(content);
        //发送邮件
        javaMailSender.send(message);
    }

    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(message,true);
            messageHelper.setFrom(username);
            messageHelper.setTo(to);
            message.setSubject(subject);
            messageHelper.setText(content,true);
            javaMailSender.send(message);
            logger.info("邮件已经发送！");
        } catch (MessagingException e) {
            logger.error("发送邮件时发生异常："+e);
        }
    }

    public void sendAttachmentsMail(String sendto, String title, String content, File file) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            //发件人
            messageHelper.setFrom(username);
            messageHelper.setTo(sendto);
            messageHelper.setSubject(title);
            messageHelper.setText(content);

            System.out.println("发件人：" + username + "\n" +
                    "收件人：" + sendto + "\n" +
                    "标题：" + title + "\n" +
                    "内容：" + content);

            //附件
            FileSystemResource fileSystemResource = new FileSystemResource(file);
            System.out.println(fileSystemResource);

            String fileName = file.getName();
            System.out.println(fileName);
            messageHelper.addAttachment(fileName, fileSystemResource);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mimeMessage);
    }

}

