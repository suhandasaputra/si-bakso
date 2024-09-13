/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bo.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class BERTLV {

    private static String pad(String s, int pad, String padString, String position) {
        StringBuffer a = new StringBuffer(pad);
        for (int i = 0; i < pad; i++) {
            a = a.append(padString);
        }
        if (position.equals("Right")) {
            return a.substring(s.length()) + s;
        }
        return s + a.substring(s.length());
    }

    private String createC(String msg, int urutan) {
        StringBuffer result = new StringBuffer();
        result.append("C" + urutan);
        int lengthMsg = msg.length() + 1;
        String hexaLengthMsg = Integer.toHexString(lengthMsg);
        if (lengthMsg > 127) {
            hexaLengthMsg = pad(hexaLengthMsg, 4, "0", "Right");
            result.append("82");
        } else {
            hexaLengthMsg = pad(hexaLengthMsg, 2, "0", "Right");
        }
        result.append(hexaLengthMsg.toUpperCase());
        result.append("T");
        result.append(msg);
        return result.toString();
    }

    private String createCv2(String msg, String title) {
        StringBuffer result = new StringBuffer();
        result.append(title);
        int lengthMsg = msg.length() + 1;
        String hexaLengthMsg = Integer.toHexString(lengthMsg);
        if (lengthMsg > 127 && lengthMsg <= 255) {
            hexaLengthMsg = pad(hexaLengthMsg, 2, "0", "Right");
            result.append("81");
        } else if (lengthMsg > 255) {
            hexaLengthMsg = pad(hexaLengthMsg, 4, "0", "Right");
            result.append("82");
        } else {
            hexaLengthMsg = pad(hexaLengthMsg, 2, "0", "Right");
        }
        result.append(hexaLengthMsg.toUpperCase());
        result.append("T");
        result.append(msg);
        return result.toString();
    }

    private String createF(String msg) {
        StringBuffer result = new StringBuffer();
        result.append("F0");
        int lengthMsg = msg.length();
        String hexaLengthMsg = Integer.toHexString(lengthMsg);
        if (lengthMsg > 127 && lengthMsg <= 255) {
            hexaLengthMsg = pad(hexaLengthMsg, 2, "0", "Right");
            result.append("81");
        } else if (lengthMsg > 255) {
            hexaLengthMsg = pad(hexaLengthMsg, 4, "0", "Right");
            result.append("82");
        }
        result.append(hexaLengthMsg.toUpperCase());
        result.append(msg);
        return result.toString();
    }

    public List getDataBertlv(String msg) {
        int count = 0;
        int lengthCount = 0;
        List result = new ArrayList();
        HashMap resultC = new HashMap();
        String lengthTotal = msg.substring(2, 4);
        int lengthTotalInt = Integer.parseInt(lengthTotal, 16);
        lengthCount = 4;
        if (lengthTotalInt > 127) {
            lengthTotal = msg.substring(4, 8);
            lengthTotalInt = Integer.parseInt(lengthTotal, 16);
            lengthCount = 8;
        }
        try {
            while (lengthCount < msg.length()) {
                resultC = getDataC(msg.substring(lengthCount));
                lengthCount = lengthCount + Integer.parseInt(resultC.get("length").toString());
                count++;
                result.add(resultC.get("value").toString());
//                System.out.println(resultC.get("value").toString());
//                System.out.println(Integer.parseInt(resultC.get("length").toString()));
            }

        } catch (Exception e) {

        }
        return result;
    }

    private HashMap getDataC(String msg) throws Exception {
        HashMap result = new HashMap();
        int lengthCount = 4;
//        System.out.println(msg);
        String lengthTotal = msg.substring(2, 4);        
        int lengthTotalInt = 0;
        if (lengthTotal.equals("81")) {
            lengthTotalInt = Integer.parseInt(msg.substring(4, 6), 16);
            lengthCount = 6;
        } else if (lengthTotal.equals("82")) {
            lengthTotalInt = Integer.parseInt(msg.substring(4, 8), 16);
            lengthCount = 8;
        } else {
            lengthTotalInt = Integer.parseInt(lengthTotal, 16);
            lengthCount = 4;
        }
//        System.out.println("C lengthTotalint 1 : " + lengthTotalInt);
        result.put("title", msg.substring(0, 2).toUpperCase());
//        if (lengthTotalInt > 127) {
//            lengthTotal = msg.substring(4, 8);
//            lengthTotalInt = Integer.parseInt(lengthTotal, 16);
//            System.out.println("C lengthTotalint 2 : " + lengthTotalInt);
//            lengthCount = 8;
//        }
        result.put("value", msg.substring(lengthCount + 1, lengthCount + lengthTotalInt));
        result.put("length", lengthCount + lengthTotalInt);

        return result;
    }

    public String generateBertlv(List content) {
        String result = "";
        for (int i = 0; i < content.size(); i++) {
            result = result + createC(content.get(i).toString(), i + 1);
        }

        return createF(result);
    }

    public String generateBertlv2(HashMap content) {
        Iterator iter = content.keySet().iterator();
        String result = "";
        String item = "";

//        while(iter.hasNext())
//        {
//            item = iter.next().toString();
//            result = result + createCv2(content.get(item).toString(), item);
//        }
        SortedSet<String> keys = new TreeSet<String>(content.keySet());
        for (String key : keys) {
            result = result + createCv2(content.get(key).toString(), key);
        }

        return createF(result);
    }

    public HashMap getDataBertlv2(String msg) throws Exception {
//        int count = 0;
        int lengthCount = 0;
        HashMap resultC = new HashMap();
        HashMap result = new HashMap();
        String lengthTotal = msg.substring(2, 4);
        int lengthTotalInt = 0;
        if (lengthTotal.equals("81")) {
            lengthTotalInt = Integer.parseInt(msg.substring(4, 6), 16);
            lengthCount = 6;
        } else if (lengthTotal.equals("82")) {
            lengthTotalInt = Integer.parseInt(msg.substring(4, 8), 16);
            lengthCount = 8;
        } else {
            lengthTotalInt = Integer.parseInt(lengthTotal, 16);
            lengthCount = 4;
        }

//        System.out.println("lengthTotalInt 1 : " + lengthTotalInt);
//        if (lengthTotalInt > 127) {
//            lengthTotal = msg.substring(4, 8);
//            lengthTotalInt = Integer.parseInt(lengthTotal, 16);
//            System.out.println("lengthTotalInt 2 : " + lengthTotalInt);
//            lengthCount = 8;
//        }
        while (lengthCount < lengthTotalInt) {
            resultC = getDataC(msg.substring(lengthCount));
            lengthCount = lengthCount + Integer.parseInt(resultC.get("length").toString());
            result.put(resultC.get("title"), resultC.get("value").toString());
        }

        return result;
    }

//    public static void main(String[] args) throws Exception {
////        String msg = "F054C104T001C201TC30DT122217100099C407T003001C51BTMATAJARI012347210528183505C907T012347";
//        String msg = "F0820155C104T001C30DT122217100099C407T003001C51BTMATAJARI012047210528183555C907T012047CA24T122817300039|ASEP SULAEMAN|02|0007|CB0ET2|504A|354250CC0CT505A|338000CD81B7T--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
////        System.out.println(msg);
////        String msg = "F08198C104T001C30DT122217100099C407T003001C51BTMATAJARI012047210528183555C907T012047CA24T122817300039|ASEP SULAEMAN|02|0007|CB0ET2|504A|354250CC0CT505A|338000";
//        HashMap result = new BERTLV().getDataBertlv2(msg);
//        Iterator iter = result.keySet().iterator();
//
//        while (iter.hasNext()) {
//            String item = iter.next().toString();
//            System.out.println(item + " - " + result.get(item));
//        }
//
////        HashMap hm = new HashMap();
////        hm.put("C1", "001");
////        hm.put("C3", "122217100099");
////        hm.put("C4", "003001");
////        hm.put("C5", "MATAJARI012047210528183555");
////        hm.put("C9", "012047");
////        hm.put("CA", "122817300039|ASEP SULAEMAN|02|0007|");
////        hm.put("CB", "2|504A|354250");
////        hm.put("CC", "505A|338000");
////        hm.put("CD", "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//////        
////        System.out.println(new BERTLV().generateBertlv2(hm));
//    }
}
