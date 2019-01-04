package com.szkingdom.redis;

/**
 * @author Lange
 * @date 2018-12-15 22:59
 */
public class KeyPrefix {
    private String prefix;
    private Long timeout;//0或负数或空是表示永不过期

    public KeyPrefix(String prefix, Long timeout) {
        this.prefix = prefix;
        this.timeout = timeout;
    }

    public static KeyPrefix LOGIN_USER_KP = new KeyPrefix("login_user_",10000L);//过期10时间s，登录时设置用户信息缓存，用于登录成功完后取出存入session

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }
}
