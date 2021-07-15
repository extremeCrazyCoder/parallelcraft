package com.parallelcraft.Networking;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class for handling all the encryption Stuff....
 * 
 * @author extremeCrazyCoder
 */
public class MCEncryption {
    private static Logger logger = LogManager.getLogger("MCEncryption");
    
    public static PublicKey construct(byte[] byteArray) {
        try {
            EncodedKeySpec specification = new X509EncodedKeySpec(byteArray);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            
            return factory.generatePublic(specification);
        } catch(NoSuchAlgorithmException | InvalidKeySpecException e) {
            logger.error("Unable to decode the Key", e);
        }
        
        return null;
    }
    
    public static byte[] toByteArray(PublicKey key) {
        return key.getEncoded();
    }
    
    public static byte[] decrypt(PrivateKey key, byte[] data) {
        try {
            Cipher instance = Cipher.getInstance(key.getAlgorithm());
            instance.init(2, key);
            return instance.doFinal(data);
        } catch (NoSuchAlgorithmException e) {
            logger.warn("Unexpected error in encryption", e);
        } catch (NoSuchPaddingException e) {
            logger.warn("Unexpected error in encryption", e);
        } catch (InvalidKeyException e) {
            logger.warn("Unexpected error in encryption", e);
        } catch (IllegalBlockSizeException e) {
            logger.warn("Unexpected error in encryption", e);
        } catch (BadPaddingException e) {
            logger.warn("Unexpected error in encryption", e);
        }
        return null;
    }
    
    public static SecretKey generateSharedKey(byte [] asBytes) {
        return new SecretKeySpec(asBytes, "AES");
    }
    
    /**
     * Get the Cipher for the Shared key
     * 
     * @param mode can be Cipher.ENCRYPT_MODE or Cipher.DECRYPT_MODE
     * @param key is the SecretKey that will be used
     */
    public static Cipher createSharedCipher(int mode, SecretKey key) {
        try {
            Cipher retval = Cipher.getInstance("AES/CFB8/NoPadding");
            retval.init(mode, key, new IvParameterSpec(key.getEncoded()));
            return retval;
        } catch (NoSuchAlgorithmException e) {
            logger.warn("Unexpected error in encryption", e);
        } catch (NoSuchPaddingException e) {
            logger.warn("Unexpected error in encryption", e);
        } catch (InvalidKeyException e) {
            logger.warn("Unexpected error in encryption", e);
        } catch (InvalidAlgorithmParameterException e) {
            logger.warn("Unexpected error in encryption", e);
        }
        return null;
    }
}
