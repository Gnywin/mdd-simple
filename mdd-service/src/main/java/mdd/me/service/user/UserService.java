package mdd.me.service.user;

import mdd.me.core.exception.MddException;
import mdd.me.domain.user.User;

/**
 * Created by 猫大东 on 2017/4/13.
 */
public interface UserService {
    /**
     * 根据uid获取用户
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * 注册
     * @param user
     * @return
     */
    int register(User user);

    /**
     * 根据登录名登入
     * @param loginName
     * @return
     */
    User getUserByLoginName(String loginName) throws MddException;

    /**
     * 根据email
     * @param email
     * @return
     */
    User getUserByEmail(String email) throws MddException;

}
