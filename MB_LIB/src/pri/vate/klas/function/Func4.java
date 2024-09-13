/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pri.vate.klas.function;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class Func4 {

    public String convertImage(String file) {
        File f = new File(file);
        return convertImage(f);
    }

    public String convertImage(File imgFile) {
        byte[] bFile = new byte[(int) imgFile.length()];
        FileInputStream fileInputStream = null;
        try {
            //convert file into array of bytes
            fileInputStream = new FileInputStream(imgFile);
            fileInputStream.read(bFile);
            fileInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertImage(bFile);
    }

    public String convertImage(byte[] imgbt) {

//        try {
//            return new String(Base64.encodeBase64(ImagesProcess.resizeImageAsJPG(imgbt , 300)));
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        return "";
        return Base64.encodeBase64URLSafeString(imgbt);
    }

    public boolean convertStringToImage(String byteString, String userId, String path, String ext) {
        byte[] byteImg = Base64.decodeBase64(byteString.getBytes());
        File f = null;
        FileOutputStream fos = null;
        try {
            f = new File(path + "/" + userId + "." + ext);
            f.createNewFile();
            fos = new FileOutputStream(f);
            fos.write(byteImg);
            fos.close();
            return true;
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException : " + ex);
        } catch (IOException ioe) {
            System.out.println("IOException : " + ioe);
            ioe.printStackTrace();
        } finally {
            f = null;
            byteImg = null;
            fos = null;
        }
        return false;
    }

}
