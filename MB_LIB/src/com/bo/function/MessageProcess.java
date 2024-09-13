/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bo.function;

import java.io.File;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public interface MessageProcess {
    public String encryptMessage(HashMap input);
    public HashMap decryptMessage(String input);
    public String generateUserIDandPin(String name, Date birthdate, String level);
    public String generateUserIDandPin(String name, GregorianCalendar birthdate, String level);
    public String generateUserIDandPin(String name, long birthdate, String level);
    public boolean convertStringToImage(String byteString, String userId, String path, String ext);
    public String convertImage(byte[] imgbt);
    public String convertImage(File imgFile);
    public String convertImage(String file);
    
}
