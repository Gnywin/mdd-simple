package mdd.me.web.sys;

import mdd.me.core.annotation.CheckToken;
import mdd.me.core.common.vo.ResponseVo;
import mdd.me.core.common.vo.Token;
import mdd.me.core.common.vo.TokenVo;
import mdd.me.service.token.TokenService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by 猫大东 on 2017/4/7.
 */
@RestController
@RequestMapping("token")
public class TokenController {
    @Resource
    private TokenService tokenService;

    /**
     * 生成token
     *
     * @param fingerPrint
     * @return
     */
    @RequestMapping(value = "get", method = RequestMethod.GET)
    @CheckToken(value = false)
    public ResponseVo<Token> getToken(@RequestParam(value = "fingerPrint", required = true) String fingerPrint) {
        Token token = tokenService.createToken(fingerPrint);
        return new ResponseVo(new TokenVo(token));
    }

    /**
     * 修改token
     *
     * @param token
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ResponseVo updateToken(@RequestParam(value = "token", required = true) Token token) {
        return new ResponseVo();
    }
}
