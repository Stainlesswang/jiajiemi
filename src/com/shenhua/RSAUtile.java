package com.shenhua;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.io.File;
/**
 * create by 王建强
 * 使用RSA算法加密解密以及签名工具类
 * 使用方法：
 *   一、将相关类RSAEncrypt与本类放入同一包下
 *   二、将KEY_FOLDER_PATH 变量修改为本地存储key文件的目录，会自动生成公钥：publicKey.keystore，私钥：privateKey.keystore
 *   三、客户端请求公钥，将公钥公开出去，客户端使用公钥加密数据发送至服务器
 *   四、服务器接收到密文使用私钥解密
 * */
public class RSAUtile {
    //存放key的文件夹，建议存放在服务器磁盘（防止删除项目将key删除）
    //publicKey和privateKey都存放在该目录下，系统会根据目录自动加载key文件
    private static final String KEY_FOLDER_PATH ="E:/paperlessMeetingKey/";



    /**
     * 公钥加密方法
     * @param plainText 需要加密数据
     * @return String   加密后的字符串
     * */
    public static  String encryptByPublicKey(String plainText) throws Exception {
        //在项目根目录创建存放key的文件夹temp，并判断是否存在key，没有则创建
        createPathandKey();

        System.out.println("--------------公钥加密私钥解密过程-------------------");
        //公钥加密过程
        byte[] cipherData=RSAEncrypt.encrypt(RSAEncrypt.loadPublicKeyByStr(RSAEncrypt.loadPublicKeyByFile(KEY_FOLDER_PATH)),plainText.getBytes());
        String cipher= Base64.encode(cipherData);
        return cipher;
    }
    /**
     * 私钥解密方法
     * @param encodeData 密文
     * @return String 解密后的字符串
     * */
    public static  String decryptByPrivateKey(String encodeData) throws Exception {
        System.out.println("key存放目录："+KEY_FOLDER_PATH);
        byte[] res=RSAEncrypt.decrypt(RSAEncrypt.loadPrivateKeyByStr(RSAEncrypt.loadPrivateKeyByFile(KEY_FOLDER_PATH)), Base64.decode(encodeData));
        String restr=new String(res);
        return restr;
    }
    /**
     * 私钥加密
     * @param plainText 需要加密数据
     * @return String   加密后的字符串
     * */
    public static  String encryptByPrivateKey(String plainText) throws Exception {
        //私钥加密过程
        byte[] cipherData=RSAEncrypt.encrypt(RSAEncrypt.loadPrivateKeyByStr(RSAEncrypt.loadPrivateKeyByFile(KEY_FOLDER_PATH)),plainText.getBytes());
        String cipher=Base64.encode(cipherData);
        return cipher;
    }
    /**
     * 公钥解密
     * @param encodeData 密文
     * @return String   解密后的字符串
     * */
    public static  String decryptByPublicKey(String encodeData) throws Exception {
        System.out.println("key存放目录："+KEY_FOLDER_PATH);
        byte[] res=RSAEncrypt.decrypt(RSAEncrypt.loadPrivateKeyByStr(RSAEncrypt.loadPublicKeyByFile(KEY_FOLDER_PATH)), Base64.decode(encodeData));
        String rest=new String(res);
        return rest;
    }




    /**
     * 自动在项目根路径下创建存放key的文件夹temp
     * 并且第一次执行自动创建key文件
     * @return temp文件夹的绝对路径
     **/
    private static void createPathandKey(){

        //获取项目所在根目录并声明文件夹名称 temp
        File file =new File(KEY_FOLDER_PATH);
        System.out.println(KEY_FOLDER_PATH);
        //如果文件夹不存在则创建
        if  (!file .exists()  && !file .isDirectory())
        {
            System.out.println("不存在,创建文件夹 paperlessMeetingKey 存放公钥秘钥");
            file .mkdir();
            //创建key文件保存在该目录下
            RSAEncrypt.genKeyPair(KEY_FOLDER_PATH);
        } else
        {
            System.out.println("//目录已经存在");
            //首先判断key文件是否存在
            File file1=new File(KEY_FOLDER_PATH + "/publicKey.keystore");
            File file2=new File(KEY_FOLDER_PATH + "/privateKey.keystore");
            if(!file1.exists() || !file2.exists()) {
                RSAEncrypt.genKeyPair(KEY_FOLDER_PATH);
            }
            }
        }

    }
