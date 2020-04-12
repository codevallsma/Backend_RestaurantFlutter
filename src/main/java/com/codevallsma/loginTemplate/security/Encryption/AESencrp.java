package com.codevallsma.loginTemplate.security.Encryption;/*
 * 
 * Source code from: http://www.code2learn.com/2011/06/encryption-and-decryption-of-data-using.html
 * 
 */

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;


/*
 * 
 * AES 128 algorithm
 * 
 */
public class AESencrp {
	
	private static final String ALGO = "AES";

    public static byte[] encrypt(Object data, String secretKey) throws Exception
    {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        KeySpec spec = new PBEKeySpec("password".toCharArray(), salt, 65536, 256); // AES-256
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        final byte[]  pass = "47e7717f0f37ee72cb226278279aebef".getBytes("UTF-8");
        final MessageDigest sha = MessageDigest.getInstance("SHA-256");

        byte[] key2 = sha.digest(pass);
        // use only first 128 bit (16 bytes). By default Java only supports AES 128 bit key sizes for encryption.
        // Updated jvm policies are required for 256 bit.
        key2 = Arrays.copyOf(key2, 16);
        SecretKeySpec keySpec = new SecretKeySpec(key2, "AES");

        byte[] ivBytes = new byte[16];
        random.nextBytes(ivBytes);
        IvParameterSpec iv = new IvParameterSpec(ivBytes);

        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        Cipher.getMaxAllowedKeyLength("AES");
        c.init(Cipher.ENCRYPT_MODE, keySpec, iv);

        byte[] encValue = c.doFinal(ObjectToByteArray(data));

        byte[] finalCiphertext = new byte[encValue.length+2*16];
        System.arraycopy(ivBytes, 0, finalCiphertext, 0, 16);
        System.arraycopy(salt, 0, finalCiphertext, 16, 16);
        System.arraycopy(encValue, 0, finalCiphertext, 32, encValue.length);

        return finalCiphertext;
    }

    public static String decrypt(String encryptedData, String secretKey) throws Exception 
    {
        /*SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        KeySpec spec = new PBEKeySpec("password".toCharArray(), salt, 65536, 256); // AES-256
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        final byte[]  pass = "47e7717f0f37ee72cb226278279aebef".getBytes("UTF-8");
        final MessageDigest sha = MessageDigest.getInstance("SHA-256");

        byte[] key2 = sha.digest(pass);
        // use only first 128 bit (16 bytes). By default Java only supports AES 128 bit key sizes for encryption.
        // Updated jvm policies are required for 256 bit.
        key2 = Arrays.copyOf(key2, 16);
        SecretKeySpec keySpec = new SecretKeySpec(key2, "AES");

        byte[] ivBytes = new byte[16];
        random.nextBytes(ivBytes);
        IvParameterSpec iv = new IvParameterSpec(ivBytes);

        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        Cipher.getMaxAllowedKeyLength("AES");
        c.init(Cipher.DECRYPT_MODE, keySpec, iv);

        byte[] encValue = c.doFinal(ObjectToByteArray(data));

        byte[] finalCiphertext = new byte[encValue.length+2*16];
        System.arraycopy(ivBytes, 0, finalCiphertext, 0, 16);
        System.arraycopy(salt, 0, finalCiphertext, 16, 16);
        System.arraycopy(encValue, 0, finalCiphertext, 32, encValue.length);

        return finalCiphertext;
        */
        return secretKey;
    }
    
    /*
     * Method to generate a secret key for AES algorithm with a given secret key.
     */
    private static Key generateKey(String secretKey) throws Exception
    {
        Key key = new SecretKeySpec(secretKey.getBytes(), ALGO);
        return key;
    }
    private static byte[] ObjectToByteArray(Object o) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(o);
        oos.flush();
        byte [] data = bos.toByteArray();
        return data;
    }
	
}