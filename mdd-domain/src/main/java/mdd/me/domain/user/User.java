package mdd.me.domain.user;

import java.util.Date;

public class User {
    /**
     * Id
     */
    private Long id;

    /**
     * 登入账号
     */
    private String loginName;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 
     */
    private String mobile;

    /**
     * 头像
     */
    private Long avatar;

    /**
     * 是否可用（0不可用，1可用）
     */
    private Boolean isUsed;

    /**
     * 注册时间
     */
    private Date registerTime;

    /**
     * 近期登入时间
     */
    private Date loginTime;

    /**
     * 
     * @return id 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 登入账号
     * @return login_name 登入账号
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 登入账号
     * @param loginName 登入账号
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    /**
     * 密码
     * @return pwd 密码
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * 密码
     * @param pwd 密码
     */
    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    /**
     * 邮箱
     * @return email 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 
     * @return mobile 
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 
     * @param mobile 
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 头像
     * @return avatar 头像
     */
    public Long getAvatar() {
        return avatar;
    }

    /**
     * 头像
     * @param avatar 头像
     */
    public void setAvatar(Long avatar) {
        this.avatar = avatar;
    }

    /**
     * 是否可用（0不可用，1可用）
     * @return is_used 是否可用（0不可用，1可用）
     */
    public Boolean getIsUsed() {
        return isUsed;
    }

    /**
     * 是否可用（0不可用，1可用）
     * @param isUsed 是否可用（0不可用，1可用）
     */
    public void setIsUsed(Boolean isUsed) {
        this.isUsed = isUsed;
    }

    /**
     * 注册时间
     * @return register time 注册时间
     */
    public Date getRegisterTime() {
        return registerTime;
    }

    /**
     * 注册时间
     * @param registerTime 注册时间
     */
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * 近期登入时间
     * @return login_time 近期登入时间
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 近期登入时间
     * @param loginTime 近期登入时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}