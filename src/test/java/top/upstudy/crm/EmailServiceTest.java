package top.upstudy.crm;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.upstudy.crm.service.impl.EmailServiceImpl;

import java.io.File;

@RunWith(SpringRunner.class)
    @SpringBootTest
    public class EmailServiceTest{

        @Autowired
        private EmailServiceImpl emailService;

        @Test
        public void sendSimpleEmail(){
            String content = "你好，恭喜你...";
            emailService.sendSimpleMail("663524190@qq.com","祝福邮件",content);
        }

        @Test
        public void sendMimeEmail(){
            String content = "<a href='https://blog.csdn.net/'>你好，欢迎注册网站，请点击链接激活</a>";
            emailService.sendHtmlMail("663524190@qq.com","激活邮件",content);
        }

        @Test
        public void sendAttachment(){
            File file=new File("D:/app20201025_16522986.jpg");
            emailService.sendAttachmentsMail("663524190@qq.com","发送附件","这是Java体系图",file);
        }
    }

