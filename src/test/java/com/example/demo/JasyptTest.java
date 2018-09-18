package com.example.demo;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.junit.Test;

public class JasyptTest {
    @Test
    public void encrypt() {
        // 创建加密器
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        // 配置
        EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
        config.setAlgorithm("PBEWithMD5AndDES");// 加密算法
        config.setPassword("xxxx");// 密码
        encryptor.setConfig(config);

        String plaintext = "root"; //明文
        String ciphertext = encryptor.encrypt(plaintext); // 加密
        System.out.println(plaintext + " : " + ciphertext);// 运行结果：root : root : zLdyNB+Dj3iw+J+TXZiv5g==
    }

    @Test
    public void decrypt() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setPassword("xxxx");
        encryptor.setConfig(config);
        String ciphertext = "zLdyNB+Dj3iw+J+TXZiv5g==";// 密文

        //解密
        String plaintext = encryptor.decrypt(ciphertext); // 解密
        System.out.println(ciphertext + " : " + plaintext);// 运行结果：zLdyNB+Dj3iw+J+TXZiv5g== : root
    }
}
