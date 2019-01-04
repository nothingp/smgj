package com.szkingdom.utils;

import com.szkingdom.entity.Users;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lange
 * @date 2018-12-13 15:31
 */
public class CommonUtils {

    private static SnowflakeIdWorker SW = new SnowflakeIdWorker(0, 0);

    /**
     * 生成主键
     * @return
     */
    public static Long generatePK(){
        return SW.nextId();
    }

    public static String md5Password(String password, String salt){
        SimpleHash hash = new SimpleHash("md5", password,  salt, 2);
        return hash.toHex();
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if(ip.contains(",")){
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    public static void main(String[] args) {

        System.out.println(new SimpleHash("md5", "123456", "zsf7a879fe419a7b78109b1d3c47949db85", 2).toHex());
    }


}
