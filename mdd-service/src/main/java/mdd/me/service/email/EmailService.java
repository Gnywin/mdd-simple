package mdd.me.service.email;

/**
 * Created by 猫大东 on 2016/7/12.
 */
public interface EmailService {

    /**
     * 获取邮箱验证码
     * @param email
     * @return
     */
    String getEmailCode(String email);

    /**
     * 存储邮箱验证码
     * @param email
     * @return
     */
    String saveEmailCode(String email);

    /**
     * 发送邮件
     * @param subject
     * @param sendHtml
     * @param receiveUser
     */
    void doSendHtmlEmail(String subject, String sendHtml,
                         String receiveUser);

}
