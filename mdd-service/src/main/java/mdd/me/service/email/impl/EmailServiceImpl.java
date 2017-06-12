package mdd.me.service.email.impl;

import com.sun.mail.util.MailSSLSocketFactory;
import mdd.me.core.uitls.StringUtils;
import mdd.me.redis.ValueRedisDao;
import mdd.me.service.email.EmailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * Created by 猫大东 on 2016/7/12.
 */
@Service("emailService")
public class EmailServiceImpl implements EmailService {
    @Resource
    ValueRedisDao<String, String> redisDao;

    private final static long code_time = 600 * 1000;
    private final static String EMAIL_CODE_KEY = "email_code_key";


    @Override
    public String getEmailCode(String email) {
        return redisDao.get(email.trim() + EMAIL_CODE_KEY);
    }

    @Override
    public String saveEmailCode(String email) {
        String code = StringUtils.getRandomNum(6);
        redisDao.save(email.trim() + EMAIL_CODE_KEY, code, code_time);
        return code;
    }

    @Override
    public void doSendHtmlEmail(String subject, String sendHtml, String receiveUser) {
        new Thread(() -> {
            Transport transport = null;
            String mailHost = "";
            String mailPort = "25";
            String sender_username = "";
            String sender_password = "";
            Properties properties = new Properties();
            InputStream in = this.getClass().getResourceAsStream("/properties/mailServer.properties");
            try {
                MailSSLSocketFactory sf = new MailSSLSocketFactory();
                sf.setTrustAllHosts(true);

                properties.put("mail.smtp.ssl.socketFactory", sf);
                properties.load(in);
                mailHost = properties.getProperty("mail.smtp.host");
                mailPort = properties.getProperty("mail.smtp.port");
                sender_username = properties.getProperty("mail.sender.username");
                sender_password = properties.getProperty("mail.sender.password");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }

            Session session = Session.getInstance(properties);
            session.setDebug(true);//开启后有调试信息
            MimeMessage message = new MimeMessage(session);

            try {
                // 发件人
                //InternetAddress from = new InternetAddress(sender_username);
                // 下面这个是设置发送人的Nick name
                InternetAddress from = new InternetAddress(MimeUtility.encodeWord("猫大东") + " <" + sender_username + ">");
                message.setFrom(from);

                // 收件人
                InternetAddress to = new InternetAddress(receiveUser);
                message.setRecipient(Message.RecipientType.TO, to);//还可以有CC、BCC

                // 邮件主题
                message.setSubject(subject);

                String content = sendHtml.toString();
                // 邮件内容,也可以使纯文本"text/plain"
                message.setContent(content, "text/html;charset=UTF-8");

                // 保存邮件
                message.saveChanges();

                transport = session.getTransport("smtp");
                // smtp验证，就是你用来发邮件的邮箱用户名密码
                transport.connect(mailHost, Integer.parseInt(mailPort), sender_username, sender_password);
                // 发送

                transport.sendMessage(message, message.getAllRecipients());
                //System.out.println("send success!");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (transport != null) {
                    try {
                        transport.close();
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
