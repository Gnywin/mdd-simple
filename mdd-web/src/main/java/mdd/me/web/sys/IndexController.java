package mdd.me.web.sys;

import mdd.me.core.annotation.CheckToken;
import mdd.me.core.annotation.CurrentToken;
import mdd.me.core.annotation.LoginRequired;
import mdd.me.core.annotation.Verify;
import mdd.me.core.common.vo.ResponseVo;
import mdd.me.core.common.vo.Token;
import mdd.me.core.uitls.VerifyCodeUtils;
import mdd.me.service.common.validator.ValidatorService;
import mdd.me.service.common.verify.VerifyService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/4/23.
 */
@Controller
public class IndexController {
    private final Logger logger = Logger.getLogger(IndexController.class);

    private final int vs = 4, w = 100, h = 30;//验证码字符数，图片宽度、高度

    @Resource
    VerifyService verifyService;
    @Resource
    ValidatorService validatorService;

    @RequestMapping("/")
    @CheckToken(value = false)
    @LoginRequired(value = false)
    public String index(){
        return "index";
    }


    /**
     * 获取验证码
     * @param token
     * @param response
     */
    @RequestMapping(value = "/verify",method = RequestMethod.GET)
    @LoginRequired(value = false)
    public void verify(@CurrentToken Token token, HttpServletResponse response){
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        //生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(vs);
        verifyService.saveVerify(token.getAccessToken(),verifyCode);
        //生成图片
        try {
            VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
        } catch (IOException e) {
            logger.error(e);
        }

    }

    /**
     * 验证码是否匹配
     * @param token
     * @param verify
     * @return
     */
    @RequestMapping(value = "/verifyCode",method = RequestMethod.POST)
    @ResponseBody
    @LoginRequired(value = false)
    public ResponseVo verifyCode(@CurrentToken Token token,
                                 @Verify String verify){
        return new ResponseVo(validatorService.checkVerifyCode(token.getAccessToken(),verify));
    }
}
