package mdd.me.web.email;

import mdd.me.core.annotation.Access;
import mdd.me.core.annotation.LoginRequired;
import mdd.me.core.common.enums.CodeEnums;
import mdd.me.core.common.vo.ResponseVo;
import mdd.me.core.common.vo.Token;
import mdd.me.core.uitls.ValidatorUtils;
import mdd.me.service.email.EmailService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by 猫大东 on 2017/6/5.
 */
@RestController
@RequestMapping("email")
public class EmailRest {
    @Resource
    EmailService emailService;

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
    public ResponseVo send(@ModelAttribute("token") Token token,
                           @RequestParam("email") String email,
                           @RequestParam(value = "type", required = false ,defaultValue = "0") int type) {
        ResponseVo rv = new ResponseVo();
        if (ValidatorUtils.isEmail(email)) {
            String subject = "猫大东的榕树下邮箱验证";
            String sendHtml = "";
            switch (type) {
                case 0:
                    sendHtml = "<div style='width: 500px;margin: 10px auto;background: #2bb2d0;padding: 20px;" +
                            "border-radius: 20px;color: #f9f9f9;'><p>喵，您正在【猫大东的榕树下】使用该邮箱进行注册:</p> " +
                            "<em>如果不是小主本人操作，请忽略这个邮件!</em>"+
                            "</br><strong>魔咒口令:#code#</strong>"+
                            "<p>这段咒语将在6分钟内失效，请尽快使用</p> " +
                            "</br><a href='http://www.qq.com'>猫大东的榕树下>>></a>" +
                            "</div>";
                    sendHtml = sendHtml.replace("#code#",emailService.saveEmailCode(email));
                    break;
            }
            emailService.doSendHtmlEmail(subject, sendHtml, email);
        } else {
            rv.setCode(CodeEnums.EMAIL_IS_ERROR.getCode());
            rv.setMsg(CodeEnums.EMAIL_IS_ERROR.getMsg());
        }
        return rv;
    }
}
