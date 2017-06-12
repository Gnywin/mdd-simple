package mdd.me.service.user.impl;

import mdd.me.core.common.enums.CodeEnums;
import mdd.me.core.exception.MddException;
import mdd.me.core.uitls.ValidatorUtils;
import mdd.me.dao.user.UserDao;
import mdd.me.domain.user.User;
import mdd.me.service.user.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/4/13.
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserDao userDao;

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
}
