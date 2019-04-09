package com.cikezxy.sandbox.java.security;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

public class RsaUtil {

    public static final String RSA = "RSA";

    public static KeyPair initKey(String alogrithm, int keySize) throws NoSuchAlgorithmException {

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(alogrithm);
        keyPairGenerator.initialize(keySize);
        return keyPairGenerator.generateKeyPair();
    }

    public static byte[] decryptByPrivateKey(byte[] data, String privateKeyStr) throws Exception {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyStr));
        PrivateKey privateKey = KeyFactory.getInstance(RSA).generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    public static byte[] encryptByPrivateKey(byte[] data, String privateKeyStr) throws Exception {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyStr));
        PrivateKey privateKey = KeyFactory.getInstance(RSA).generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    public static byte[] decryptByPublicKey(byte[] data, String publicKeyStr) throws Exception {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyStr));
        PublicKey publicKey = KeyFactory.getInstance(RSA).generatePublic(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    public static byte[] encryptByPublicKey(byte[] data, String publicKeyStr) throws Exception {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyStr));
        PublicKey publicKey = KeyFactory.getInstance(RSA).generatePublic(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    public static void main(String[] args) throws Exception {
        // 生成公钥私钥
        KeyPair keyPair = initKey(RSA, 512);
        String publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
        String privateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());

        System.out.println("------public key------");
        System.out.println(publicKey);

        System.out.println("------private key------");
        System.out.println(privateKey);

        String plainText = "明文1234abcd";
        System.out.println("------plain text------");
        System.out.println(plainText);

        System.out.println("------encrypt by public key------");
        byte[] encryptData = encryptByPublicKey(plainText.getBytes(), publicKey);
        System.out.println(Base64.getEncoder().encodeToString(encryptData));

        System.out.println("------decrypt by private key------");
        byte[] decryptData = decryptByPrivateKey(encryptData, privateKey);
        System.out.println(new String(decryptData));

        System.out.println("------encrypt by private key------");
        encryptData = encryptByPrivateKey(plainText.getBytes(), privateKey);
        System.out.println(Base64.getEncoder().encodeToString(encryptData));

        System.out.println("------decrypt by public key------");
        decryptData = decryptByPublicKey(encryptData, publicKey);
        System.out.println(new String(decryptData));
    }
}
