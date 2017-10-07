package mdd.me.service.user.impl;

import mdd.me.core.common.Constants;
import mdd.me.core.common.enums.CodeEnums;
import mdd.me.core.exception.MddException;
import mdd.me.core.properties.ConfigProperty;
import mdd.me.core.uitls.StringUtils;
import mdd.me.core.uitls.ValidatorUtils;
import mdd.me.dao.user.UserDao;
import mdd.me.domain.user.User;
import mdd.me.redis.ValueRedisDao;
import mdd.me.service.user.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/4/13.
 */
@Service
public class UserServiceImpl implements UserService {
    final String find_pwd = "find_pwd";
    final long expire_time = 0x927C0;
    @Resource
    UserDao userDao;
    @Resource
    ValueRedisDao valueRedisDao;

    @Override
    public User getUserById(@Param("id") Long id) {
        return userDao.getByPrimary(id);
    }

    @Override
    public int register(@Param("User") User user) {
        return userDao.insert(user);
    }


    @Override
    public User getUserByLoginName(String loginName) throws MddException {
        if(ValidatorUtils.judgeLoginName(loginName)==0){
            return  getUserByEmail(loginName);
        }else {
            throw new MddException(CodeEnums.USER_NAME_ERROR);
        }
    }

    @Override
    public User getUserByEmail(String email) throws MddException {
        if(ValidatorUtils.isEmail(email)){
            return userDao.getByEmail(email);
        }else {
            throw new MddException(CodeEnums.EMAIL_IS_ERROR);
        }
    }

    @Override
    public String getFindUrl(String accessToken) {
        String uri = (String) ConfigProperty.getProperty("server.uri");
        String key = StringUtils.randomUUID();
        uri += "/user/cp2?tid="+accessToken+"&key="+ key;
        valueRedisDao.save(find_pwd+accessToken,key,expire_time);
        return uri;
    }

    @Override
    public int checkKey(String accessToken, String key) {
        String bak = (String) valueRedisDao.get(find_pwd+accessToken);
        if(bak == null){
            return Constants.INVALID_CODE;
        }
        if(bak.equals(key)){
            return Constants.SUCCESS_CODE;
        }
        return Constants.ERROR_CODE;
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateByPrimary(user);
    }
}
