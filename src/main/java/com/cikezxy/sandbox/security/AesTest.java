package com.cikezxy.sandbox.security;

import javax.crypto.*;
import java.security.SecureRandom;
import java.util.Base64;

public class AesTest {

    private static final String ALG = "AES";
    private static final String ENCODING="UTF-8";

    public static String encrypt(String key, String rawText) throws Exception {

        SecretKey secretKey = getKey(key);
        Cipher cipher = Cipher.getInstance(ALG);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(rawText.getBytes(ENCODING));
        return new String(Base64.getEncoder().encode(encrypted));
    }

    public static String decrypt(String key, String encrypted) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(encrypted);
        SecretKey secretKey = getKey(key);
        Cipher cipher = Cipher.getInstance(ALG);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(bytes),ENCODING);
    }

    private static SecretKey getKey(String keySeed){
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(keySeed.getBytes(ENCODING));
            KeyGenerator generator = KeyGenerator.getInstance(ALG);
            generator.init(secureRandom);
            return generator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception{
        String key = "AAAAA";
        String encrypted = encrypt(key,"hello world");
        System.out.println(encrypted);

        String decrypted = decrypt(key,encrypted);
        System.out.println(decrypted);
    }
}
