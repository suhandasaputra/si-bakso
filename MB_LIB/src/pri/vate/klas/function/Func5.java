/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pri.vate.klas.function;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import org.apache.commons.codec.binary.Hex;
import pri.vate.klas.param.Sing1;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class Func5 {

    public static String proen(String message) {
        try {

            SecretKey key = readKey(Sing1.getInstance().getBk() + Sing1.getInstance().getCk() + Sing1.getInstance().getAk());
            String encResult = encrypt(key, message.trim().getBytes());
            return encResult.trim();
        } catch (Exception e) {
            System.err.println(e);
            System.err.println("Usage: java " + Func5.class.getName() + " -d|-e|-g <keyfile>");
        }
        return null;
    }
    
    public static String prode(String message) {
        try {

            SecretKey key = readKey(Sing1.getInstance().getBk()+Sing1.getInstance().getCk()+Sing1.getInstance().getAk());            
            String decResult = decrypt(key, message);
            return decResult;
        } catch (Exception e) {
            System.err.println(e);
            System.err.println("Usage: java " + Func5.class.getName() + " -d|-e|-g <keyfile>");
        }
        return null;
    }


    public static SecretKey readKey(String keyString) throws IOException,
            NoSuchAlgorithmException, InvalidKeyException,
            InvalidKeySpecException {
        // Read the raw bytes from the keyfile
        byte[] rawkey = keyString.getBytes();
        // Convert the raw bytes to a secret key like this
        DESedeKeySpec keyspec = new DESedeKeySpec(rawkey);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");
        SecretKey key = keyfactory.generateSecret(keyspec);
        return key;
    }

    /**
     * Use the specified TripleDES key to encrypt bytes from the input stream
     * and write them to the output stream. This method uses CipherOutputStream
     * to perform the encryption and write bytes at the same time.
     */
    public static void encrypt(SecretKey key, InputStream in, OutputStream out)
            throws NoSuchAlgorithmException, InvalidKeyException,
            NoSuchPaddingException, IOException {
        // Create and initialize the encryption engine
        Cipher cipher = Cipher.getInstance("DESede");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        // Create a special output stream to do the work for us
        CipherOutputStream cos = new CipherOutputStream(out, cipher);

        // Read from the input and write to the encrypting output stream
        byte[] buffer = new byte[2048];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            cos.write(buffer, 0, bytesRead);
        }
        cos.close();

        // For extra security, don't leave any plaintext hanging around memory.
        java.util.Arrays.fill(buffer, (byte) 0);
    }

    
    /**
     * Use the specified TripleDES key to decrypt bytes ready from the input
     * stream and write them to the output stream. This method uses uses Cipher
     * directly to show how it can be done without CipherInputStream and
     * CipherOutputStream.
     */
    public static void decrypt(SecretKey key, InputStream in, OutputStream out)
            throws NoSuchAlgorithmException, InvalidKeyException, IOException,
            IllegalBlockSizeException, NoSuchPaddingException,
            BadPaddingException {
        // Create and initialize the decryption engine
        Cipher cipher = Cipher.getInstance("DESede");
        cipher.init(Cipher.DECRYPT_MODE, key);

        // Read bytes, decrypt, and write them out.
        byte[] buffer = new byte[2048];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(cipher.update(buffer, 0, bytesRead));
        }

        // Write out the final bunch of decrypted bytes
        out.write(cipher.doFinal());
        out.flush();
    }

    public static String encrypt(SecretKey key,byte[] plain ) throws Exception
    {
        Cipher cipher = Cipher.getInstance("DESede");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(plain);
        return new String(Hex.encodeHex(encrypted));
    }
    public static String decrypt(SecretKey key,String decString ) throws Exception
    {
        byte[] cipher = Hex.decodeHex(decString.toCharArray());
        Cipher dcipher = Cipher.getInstance("DESede");
        dcipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypted = dcipher.doFinal(cipher);
        return new String(decrypted).trim();
    }
}
