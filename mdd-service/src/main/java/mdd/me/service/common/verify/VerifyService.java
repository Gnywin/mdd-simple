package mdd.me.service.common.verify;

/**
 * Created by 猫大东 on 2017/10/6.
 */

public interface VerifyService {
    /**
     * 存储验证码
     * @param accessToken
     * @param code
     */
    void saveVerify(String accessToken , String code);

    /**
     * 校验验证码
     * @param accessToken
     * @return
     */
    String getVerify(String accessToken);
}
