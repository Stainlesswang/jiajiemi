package com.shenhua;

import com.shenhua.MD5Util2;
import com.sun.jmx.mbeanserver.JmxMBeanServer;

import java.security.MessageDigest;

/**
 * 采用MD5加密
 */
public class MD5Util {
    /***
     * MD5加密 生成32位md5码
     * @param  inStr 待加密字符串
     * @return 返回32位md5码
     */
    public static String md5Encode(String inStr) throws Exception {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    // 可逆的加密算法
    public static String KL(String inStr) {
        // String s = new String(inStr);
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;
    }

    // 加密后解密
    public static String JM(String inStr ) {
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String k = new String(a);
        return k;
    }
    /**
     * 测试主函数
     * @param args
     * @throws Exception
     */
//    public static void main(String args[]) throws Exception {
//        String str = new String("WSRdasasa");
//
//
//
////        String md5=MD5Util2.encrypt(str,MD5Util2.MD5_KEY);
//        System.out.println("原始：" + str);
//        System.out.println("MD5后：" + KL(str));
//        System.out.println("MD5后：" + JM(KL(str)));
//    }
}

