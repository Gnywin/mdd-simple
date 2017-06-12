package mdd.me.service.token;

import mdd.me.core.common.vo.Token;

/**
 * Created by 猫大东 on 2017/4/21.
 */
public interface TokenService {
    /**
     * 保存token
     * @param token
     */
    void saveToken(Token token);

    /**
     * 获取token
     * @param accessToken
     * @return
     */
    Token getToken(String accessToken);

    /**
     *创建token并保存
     * @param finger
     * @return
     */
    Token createToken(String finger);

}
