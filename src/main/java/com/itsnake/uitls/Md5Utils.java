package com.itsnake.uitls;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;


public class Md5Utils {

        public static String md5(String src) {
            //将字符串信息采用MD5处理
            MessageDigest md;
            try {
                md = MessageDigest.getInstance("MD5");
                byte[] output = md.digest(src.getBytes());
                String s = Base64.encodeBase64String(output);
                return s;
            } catch (Exception e) {
                return e.getMessage();
            }
        }

    public static void main(String[] args) throws Exception{
        System.out.println("123的加密密码为："+md5("123"));
        System.out.println("huangzishun的加密密码为："+md5("huangzishun"));
    }
}
