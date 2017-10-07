package mdd.me.web.user;

import com.google.gson.Gson;
import mdd.me.core.annotation.*;
import mdd.me.core.common.Constants;
import mdd.me.core.common.enums.CodeEnums;
import mdd.me.core.common.vo.ResponseVo;
import mdd.me.core.common.vo.Token;
import mdd.me.core.common.vo.TokenVo;
import mdd.me.core.exception.MddException;
import mdd.me.core.uitls.MD5Util;
import mdd.me.core.uitls.RSAUtils;
import mdd.me.core.uitls.ValidatorUtils;
import mdd.me.domain.user.User;
import mdd.me.service.common.validator.ValidatorService;
import mdd.me.service.token.TokenService;
import mdd.me.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.security.PrivateKey;

/**
 * Created by 猫大东 on 2017/4/7.
 */
@Controller
@RequestMapping("user")
public class UserController {

    final int login_flow = 5;
    final int login_flow_verify = 3;
    @Resource
    Gson gson;
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
    @Access
    @CheckToken(value = false)
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register() {
        return "user/register";
    }

    /**
     * 登入view
     *
     * @return
     * @throws MddException
     */
    @CheckToken(value = false)
    @RequestMapping(value = "login", method = RequestMethod.GET)
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
    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo register(@CurrentToken Token token,
                               @RequestParam(value = "loginName", required = true) String loginName,
                               @RequestParam(value = "code", required = true) String code,
                               @RequestParam(value = "pwd", required = true) String pwd) throws MddException {

        User user = new User();
        PrivateKey key = token.getKeyPair().getPrivate();
        loginName = RSAUtils.decryptString(key, loginName);
        pwd = RSAUtils.decryptString(key, pwd);
        code = RSAUtils.decryptString(key, code);
        if (!validatorService.checkRegisterInfo(token, loginName, pwd, code)) {
            return new ResponseVo(CodeEnums.REGISTER_WAS_FAILED);
        }
        if(ValidatorUtils.judgeLoginName(loginName) == Constants.IS_EMAIL){
            user.setEmail(loginName);
        }
        user.setPwd(MD5Util.encrypt(MD5Util.encrypt(pwd)));
        userService.register(user);
        token.setUid(user.getId());
        token.setExpiresIn(Token.ACC_TOKEN_EXP_LOGIN);
        token.setType(Token.TOKEN_TYPE_LOGIN);
        tokenService.saveToken(token);
        return new ResponseVo(new TokenVo(token));
    }

    /**
     * 登入form
     *
     * @param token
     * @param loginName
     * @param pwd
     * @return
     */
    @Access(time = login_flow)
    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseVo login(@CurrentToken Token token,
                            @FlowTimes int times,
                            @Verify String vc,
                            @RequestParam(value = "loginName", required = true) String loginName,
                            @RequestParam(value = "pwd", required = true) String pwd,
                            @RequestParam(value = "remember", required = true) int remember) throws MddException {

        PrivateKey key = token.getKeyPair().getPrivate();
        loginName = RSAUtils.decryptString(key, loginName);
        pwd = RSAUtils.decryptString(key, pwd);
        User user = userService.getUserByLoginName(loginName);
        if (user == null || !MD5Util.encrypt(MD5Util.encrypt(pwd)).equals(user.getPwd())) {
            if(times >= login_flow_verify){
                return new ResponseVo(CodeEnums.ACCESS_FLOW_NEED_VERIFY);
            }
            return new ResponseVo(CodeEnums.USER_PASSWORD_ERROR);
        }
        if(times >= login_flow_verify){
            if(validatorService.checkVerifyCode(token.getAccessToken(),vc) != Constants.SUCCESS_CODE){
                return new ResponseVo(CodeEnums.VERIFY_IS_ERROR);
            }
        }
        if (remember == 1) {//自动登入
            token.setExpiresIn(Token.ACC_TOKEN_EXP_REMEMBER);
            token.setType(Token.TOKEN_TYPE_REMEMBER);
        } else {
            token.setExpiresIn(Token.ACC_TOKEN_EXP_LOGIN);
            token.setType(Token.TOKEN_TYPE_LOGIN);
        }
        tokenService.saveToken(token);
        return new ResponseVo(new TokenVo(token));
    }

    /**
     * 判断是否存在
     *
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
        if (user != null) {
            result = 1;
        }
        return new ResponseVo(result);
    }

    /**
     * 登出
     * @param token
     * @return
     */
    @Access
    @RequestMapping("/logout")
    public String logout(@CurrentToken Token token,
                         Model model){
        token.setExpiresIn(Token.ACC_TOKEN_EXP_TEMP);
        token.setType(Token.TOKEN_TYPE_TEMP);
        tokenService.saveToken(token);
        model.addAttribute("tokenJson",gson.toJson(new TokenVo(token)));
        return "user/logout";
    }

    /**
     * 修改密码第一步
     * @return
     * @throws MddException
     */
    @LoginRequired(value = false)
    @RequestMapping(value = "cp1",method = RequestMethod.GET)
    public String changePwdOne() throws MddException {
            return "user/cp1";
    }

    /**
     * 修改密码第二步
     * @return
     * @throws MddException
     */
    @LoginRequired(value = false)
    @RequestMapping(value = "cp2",method = RequestMethod.GET)
    public String changePwdTwo(@CurrentToken Token token,
                               @RequestParam(value = "key", required = true) String key,
                               Model model) throws MddException {
        if(userService.checkKey(token.getAccessToken(),key) == Constants.SUCCESS_CODE){
            model.addAttribute("key",key);
            model.addAttribute("tokenJson",gson.toJson(new TokenVo(token)));
            return "user/cp2";
        }else {
            throw new MddException(CodeEnums.INVALID_REQUEST);
        }

    }

    /**
     * 修改密码
     * @param token
     * @param pwd
     * @param key
     * @return
     * @throws MddException
     */
    @LoginRequired(value = false)
    @ResponseBody
    @RequestMapping(value = "alterPwd",method = RequestMethod.POST)
    public ResponseVo alterPwd(@CurrentToken Token token,
                                 @RequestParam(value = "pwd", required = true) String pwd,
                                 @RequestParam(value = "key", required = true) String key) throws MddException {
        if(userService.checkKey(token.getAccessToken(),key) == Constants.SUCCESS_CODE){
            PrivateKey pk = token.getKeyPair().getPrivate();
            pwd = RSAUtils.decryptString(pk, pwd);
            User user = userService.getUserById(token.getUid());
            user.setPwd(MD5Util.encrypt(MD5Util.encrypt(pwd)));
            userService.updateUser(user);
            token.setExpiresIn(Token.ACC_TOKEN_EXP_LOGIN);
            token.setType(Token.TOKEN_TYPE_LOGIN);
            tokenService.saveToken(token);
            return new ResponseVo(token);
        }else {
            throw new MddException(CodeEnums.INVALID_REQUEST);
        }

    }
   /* *//**
     * 修改密码第三步
     * @return
     * @throws MddException
     *//*
    @LoginRequired(value = false)
    @RequestMapping(value = "cp3",method = RequestMethod.GET)
    public String changePwdThree(@CurrentToken Token token,
                                 @RequestParam(value = "pwd", required = true) String pwd,
                                 @RequestParam(value = "key", required = true) String key,
                                 Model model) throws MddException {
        if(userService.checkKey(token.getAccessToken(),key) == Constants.SUCCESS_CODE){
            PrivateKey pk = token.getKeyPair().getPrivate();
            pwd = RSAUtils.decryptString(pk, pwd);
            User user = userService.getUserById(token.getUid());
            user.setPwd(MD5Util.encrypt(MD5Util.encrypt(pwd)));
            token.setExpiresIn(Token.ACC_TOKEN_EXP_LOGIN);
            token.setType(Token.TOKEN_TYPE_LOGIN);
            tokenService.saveToken(token);
            return "user/cp3";
        }else {
            throw new MddException(CodeEnums.INVALID_REQUEST);
        }

    }*/

    @LoginRequired(value = false)
    @CheckToken(value = false)
    @RequestMapping("test")
    public ResponseVo test(
            @RequestParam(value = "token", required = true) String token,
            @RequestParam(value = "pwd", required = true) String pwd) {
        Token tb = tokenService.getToken(token);
        String pwds = RSAUtils.decryptString(tb.getKeyPair().getPrivate(), pwd);
        System.out.println(pwds);
        return new ResponseVo();

    }
}
