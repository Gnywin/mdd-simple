package mdd.me.web.user;

import mdd.me.core.annotation.CheckToken;
import mdd.me.core.annotation.CurrentToken;
import mdd.me.core.annotation.LoginRequired;
import mdd.me.core.common.enums.CodeEnums;
import mdd.me.core.common.vo.ResponseVo;
import mdd.me.core.common.vo.Token;
import mdd.me.core.exception.MddException;
import mdd.me.core.uitls.MD5Util;
import mdd.me.core.uitls.RSAUtils;
import mdd.me.domain.user.User;
import mdd.me.service.common.validator.ValidatorService;
import mdd.me.service.token.TokenService;
import mdd.me.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.PrivateKey;

/**
 * Created by 猫大东 on 2017/4/7.
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Resource
    UserService userService;
    @Resource
    TokenService tokenService;
    @Resource
    ValidatorService validatorService;

    /**
     * 注册view
     *
     * @return
     */
    @CheckToken(value = false)
    @RequestMapping(value = "register",method = RequestMethod.GET)
    public String register() {
        return "user/register";
    }

    /**
     * 登入view

     * @return
     * @throws MddException
     */
    @CheckToken(value = false)
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login() {
        return "user/login";
    }
    /**
     * 注册form
     *
     * @param token
     * @param loginName
     * @param pwd
     * @return
     */
    @RequestMapping(value = "register",method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo register(@CurrentToken Token token,
                               @RequestParam(value = "loginName", required = true) String loginName,
                               @RequestParam(value = "code", required = true) String code,
                               @RequestParam(value = "pwd", required = true) String pwd) throws MddException {

        User user = new User();
        PrivateKey key = token.getKeyPair().getPrivate();
        loginName = RSAUtils.decryptString(key,loginName);
        pwd = RSAUtils.decryptString(key,pwd);
        code = RSAUtils.decryptString(key,code);
        if(!validatorService.checkRegisterInfo(token,loginName, pwd,code)){
            return new ResponseVo(CodeEnums.REGISTER_WAS_FAILED);
        }
        user.setLoginName(loginName);
        user.setPwd(MD5Util.encrypt(pwd));
        userService.register(user);
        token.setUid(user.getId());
        token.setExpiresIn(Token.ACC_TOKEN_EXP_LOGIN);
        token.setType(Token.TOKEN_TYPE_LOGIN);
        tokenService.saveToken(token);
        return new ResponseVo(token);
    }

    /**
     * 登入form
     *
     * @param token
     * @param loginName
     * @param password
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public ResponseVo login(@RequestHeader(value = "token", required = true) Token token,
                            @RequestParam(value = "loginName", required = true) String loginName,
                            @RequestParam(value = "password", required = true) String password,
                            @RequestParam(value = "remember", required = true) int remember) throws MddException {

        User user = userService.getUserByLoginName(loginName);
        if(!MD5Util.encrypt(password).equals(user.getPwd())){
            return new ResponseVo(CodeEnums.USER_PASSWORD_ERROR);
        }
        if (remember == 0) {
            token.setExpiresIn(Token.ACC_TOKEN_EXP_LOGIN);
            token.setType(Token.TOKEN_TYPE_LOGIN);
        } else {
            token.setExpiresIn(Token.ACC_TOKEN_EXP_REMEMBER);
            token.setType(Token.TOKEN_TYPE_REMEMBER);
        }
        tokenService.saveToken(token);
        return new ResponseVo();
    }

    /**
     * 判断是否存在
     * @param loginName
     * @return
     * @throws MddException
     */
    @CheckToken(value = false)
    @LoginRequired(value = false)
    @ResponseBody
    @RequestMapping("loginNameExist")
    public ResponseVo emailExist(@RequestParam(value = "loginName", required = true) String loginName) throws MddException {
        User user = userService.getUserByLoginName(loginName);
        int result = 0;
        if(user != null){
            result = 1;
        }
        return new ResponseVo(result);
    }


    @LoginRequired(value = false)
    @CheckToken(value = false)
    @RequestMapping("test")
    public ResponseVo test(
            @RequestParam(value = "token", required = true) String token,
            @RequestParam(value = "pwd", required = true) String pwd){
        Token tb = tokenService.getToken(token);
        String pwds =  RSAUtils.decryptString(tb.getKeyPair().getPrivate(),pwd);
        System.out.println(pwds);
        return new ResponseVo();

    }
}
