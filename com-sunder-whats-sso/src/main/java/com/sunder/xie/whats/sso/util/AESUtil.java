package com.sunder.xie.whats.sso.util;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by xieshengrong on 2017/5/16.
 */
public class AESUtil {
    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    public AESUtil() {
    }

    public static byte[] initSecretKey() {
        KeyGenerator kg = null;

        try {
            kg = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException var2) {
            var2.printStackTrace();
            return new byte[0];
        }

        kg.init(128);
        SecretKey secretKey = kg.generateKey();
        return secretKey.getEncoded();
    }

    static Key toKey(byte[] key) {
        return new SecretKeySpec(key, "AES");
    }

    public static byte[] encrypt(byte[] data, Key key) throws Exception {
        return encrypt(data, key, "AES/ECB/PKCS5Padding");
    }

    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        return encrypt(data, key, "AES/ECB/PKCS5Padding");
    }

    public static byte[] encrypt(byte[] data, byte[] key, String cipherAlgorithm) throws Exception {
        Key k = toKey(key);
        return encrypt(data, k, cipherAlgorithm);
    }

    public static byte[] encrypt(byte[] data, Key key, String cipherAlgorithm) throws Exception {
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        cipher.init(1, key);
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        return decrypt(data, key, "AES/ECB/PKCS5Padding");
    }

    public static byte[] decrypt(byte[] data, Key key) throws Exception {
        return decrypt(data, key, "AES/ECB/PKCS5Padding");
    }

    public static byte[] decrypt(byte[] data, byte[] key, String cipherAlgorithm) throws Exception {
        Key k = toKey(key);
        return decrypt(data, k, cipherAlgorithm);
    }

    public static byte[] decrypt(byte[] data, Key key, String cipherAlgorithm) throws Exception {
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        cipher.init(2, key);
        return cipher.doFinal(data);
    }
}

}
