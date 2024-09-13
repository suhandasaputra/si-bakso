/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bo.function;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class SecurityFunction {
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(SecurityFunction.class);
    
    public static String generateMD5(String text){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(text.getBytes());
            byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            log.getLogger(ex.getMessage());
        }
        return null;
    }
}
