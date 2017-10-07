package mdd.me.web.email;

import mdd.me.core.annotation.Access;
import mdd.me.core.annotation.CurrentToken;
import mdd.me.core.annotation.LoginRequired;
import mdd.me.core.common.enums.CodeEnums;
import mdd.me.core.common.vo.ResponseVo;
import mdd.me.core.common.vo.Token;
import mdd.me.core.exception.MddException;
import mdd.me.core.properties.ConfigProperty;
import mdd.me.core.uitls.ValidatorUtils;
import mdd.me.domain.user.User;
import mdd.me.service.email.EmailService;
import mdd.me.service.token.TokenService;
import mdd.me.service.user.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by 猫大东 on 2017/6/5.
 */
@RestController
@RequestMapping("email")
public class EmailRest {
    final String server_uri = (String) ConfigProperty.getProperty("server.uri");
    @Resource
    EmailService emailService;
    @Resource
    UserService userService;
    @Resource
    TokenService tokenService;


    /**
     * 发送邮件
     *
     * @param token
     * @param email
     * @return
     */
    @LoginRequired(value = false)
    @Access()
    @RequestMapping(value = "send", method = RequestMethod.GET)
    public ResponseVo send(@CurrentToken Token token,
                           @RequestParam("email") String email,
                           @RequestParam(value = "type", required = false ,defaultValue = "0") int type) throws MddException {
        ResponseVo rv = new ResponseVo();
        if (ValidatorUtils.isEmail(email)) {
            String subject = "猫大东的榕树下邮箱验证";
            String sendHtml = "";
            switch (type) {//注册
                case 0:
                    sendHtml = "<div style='width: 500px;margin: 10px auto;background: #2f2f2f;padding: 20px;" +
                            "border-radius: 20px;color: #f9f9f9;'><p>喵，您正在【猫大东的榕树下】使用该邮箱进行注册:</p> " +
                            "<em>如果不是小主本人操作，请忽略这个邮件!</em>"+
                            "</br><strong>魔咒口令:#code#</strong>"+
                            "<p>这段咒语将在6分钟内失效，请尽快使用</p> " +
                            "</br><a style='color: #2281f1;' href='"+server_uri+"'>猫大东的榕树下>>></a>" +
                            "</div>";
                    sendHtml = sendHtml.replace("#code#",emailService.saveEmailCode(email));
                    break;
                case 1:{//找回密码
                    sendHtml = "<div style='width: 500px;margin: 10px auto;background: #2f2f2f;padding: 20px;" +
                            "border-radius: 20px;color: #f9f9f9;'><p>喵，您正在【猫大东的榕树下】找回密码:</p> " +
                            "<em>如果不是小主本人操作，请忽略这个邮件!</em>"+
                            "</br><strong>点击找回密码:<a href='#uri#'>#uri#</a></strong>"+
                            "<p>这个神秘链接将在6分钟内失效，请尽快使用</p> " +
                            "</br><a style='color: #2281f1;' href='"+server_uri+"'>猫大东的榕树下>>></a>" +
                            "</div>";
                    User user = userService.getUserByLoginName(email);
                    if(user == null){
                        throw  new MddException(CodeEnums.EMAIL_IS_ERROR);
                    }else {
                        token.setUid(user.getId());
                        tokenService.saveToken(token);
                    }
                    String findUri = userService.getFindUrl(token.getAccessToken());
                    sendHtml = sendHtml.replace("#uri#",findUri);
                }
            }
            emailService.doSendHtmlEmail(subject, sendHtml, email);
        } else {
            rv.setCode(CodeEnums.EMAIL_IS_ERROR.getCode());
            rv.setMsg(CodeEnums.EMAIL_IS_ERROR.getMsg());
        }
        return rv;
    }
}
