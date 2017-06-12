package mdd.me.service.token.impl;

import mdd.me.core.common.vo.Token;
import mdd.me.redis.ValueRedisDao;
import mdd.me.service.token.TokenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 猫大东 on 2017/4/21.
 */
@Service
public class TokenServiceImpl implements TokenService {
    @Resource
    ValueRedisDao redisDao;

    @Override
    public void saveToken(Token token) {
        redisDao.save(token.getAccessToken(), token, token.getExpiresIn());
    }

    @Override
    public Token getToken(String accessToken) {
        return (Token) redisDao.get(accessToken);
    }

    @Override
    public Token createToken(String finger) {
        Token token = new Token(finger);
        saveToken(token);
        return token;
    }

}
