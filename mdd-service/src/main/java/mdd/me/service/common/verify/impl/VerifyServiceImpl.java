package mdd.me.service.common.verify.impl;

import mdd.me.redis.ValueRedisDao;
import mdd.me.service.common.verify.VerifyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 猫大东 on 2017/10/6.
 */
@Service
public class VerifyServiceImpl implements VerifyService{
    @Resource
    ValueRedisDao<String, String> redisDao;

    private final long code_time = 600 * 1000;
    private final String verify_code_key = "verify_code_key";

    @Override
    public void saveVerify(String accessToken, String code) {
        redisDao.save(accessToken+verify_code_key,code,code_time);
    }

    @Override
    public String getVerify(String accessToken) {
        return redisDao.get(accessToken+verify_code_key );
    }
}
