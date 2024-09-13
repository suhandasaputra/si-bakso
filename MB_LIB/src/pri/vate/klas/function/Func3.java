/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pri.vate.klas.function;

import com.bo.function.FunctionSupportMB;
import com.bo.parameter.FieldParameter;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class Func3 extends Func4 {
    private Func1 f1 = new Func1();
    private Func5 f5 = new Func5();
    public String encryptMessage(HashMap input){
        if (input.containsKey(FieldParameter.password)){
            input.put(FieldParameter.password, FunctionSupportMB.encryptOneWayDataSave(input.get(FieldParameter.password).toString()));
//            System.out.println("ENKRIP LIB password : "+input.get(FieldParameter.password).toString());
        }
        if (input.containsKey(FieldParameter.oldPassword)){
            input.put(FieldParameter.oldPassword, FunctionSupportMB.encryptOneWayDataSave(input.get(FieldParameter.oldPassword).toString()));
//            System.out.println("ENKRIP LIB old password: "+input.get(FieldParameter.oldPassword).toString());
        }
        if (input.containsKey(FieldParameter.pin)){
            input.put(FieldParameter.pin, FunctionSupportMB.encryptOneWayDataSave(input.get(FieldParameter.pin).toString()));
        }
        String messageOut = null;
        messageOut = f1.generateJson(input);
        messageOut = f5.proen(messageOut);
        return messageOut;
    }
    
    public HashMap decryptMessage(String input){
        input = f5.prode(input);
        HashMap out = null;
        out = f1.decodeJson(input);
        return out;
    }
    
    public String generateUserIDandPin(String name, Date birthdate, String level) {
        if (birthdate == null) {
            return "01;birthdate is null";
        }
        return generateUserIDandPin(name, birthdate.getTime(), level);
    }

    public String generateUserIDandPin(String name, GregorianCalendar birthdate, String level) {
        if (birthdate == null) {
            return "01;birthdate is null";
        }
        return generateUserIDandPin(name, birthdate.getTimeInMillis(), level);
    }

    public String generateUserIDandPin(String name, long birthdate, String level) {
        if (name == null) {
            return "02;name is null";
        }
        if (level == null) {
            return "03;level is null";
        }
        String prefix;
        if ("MBO".equals(level.toUpperCase())) {
            prefix = "M";
        } else if ("AGENT".equals(level.toUpperCase())) {
            prefix = "A";
        } else if ("CUSTOMER".equals(level.toUpperCase())) {
            prefix = "C";
        } else {
            return "04;level is not defined";
        }
        String response = "00;success;";
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTimeInMillis(birthdate);
        String[] nama = validate(name).split(" ");
        if (nama.length == 1) {
            if (nama[0].length() > 9) {
                nama[0] = nama[0].substring(0, 9);
            } else {
                nama[0] = pad(nama[0], 9, "0", "Left");
            }
            response += (prefix + nama[0] + pad(String.valueOf(cal.get(GregorianCalendar.DAY_OF_MONTH)), 2, "0", "Right") + pad(String.valueOf((cal.get(GregorianCalendar.MONTH) + 1)), 2, "0", "Right")).toUpperCase();
        } else if (nama.length == 2) {
            if (nama[0].length() > 5) {
                nama[0] = nama[0].substring(0, 5);
            }
            if (nama[1].length() > 4) {
                nama[1] = nama[1].substring(0, 4);
            }
            response += (prefix + pad(nama[0] + nama[1], 9, "0", "Left") + pad(String.valueOf(cal.get(GregorianCalendar.DAY_OF_MONTH)), 2, "0", "Right") + pad(String.valueOf((cal.get(GregorianCalendar.MONTH) + 1)), 2, "0", "Right")).toUpperCase();

        } else {
            if (nama[0].length() > 5) {
                nama[0] = nama[0].substring(0, 5);
            }
            if (nama[nama.length - 1].length() > 4) {
                nama[nama.length - 1] = nama[nama.length - 1].substring(0, 4);
            }
            response += (prefix + pad(nama[0] + nama[nama.length - 1], 9, "0", "Left") + pad(String.valueOf(cal.get(GregorianCalendar.DAY_OF_MONTH)), 2, "0", "Right") + pad(String.valueOf((cal.get(GregorianCalendar.MONTH) + 1)), 2, "0", "Right")).toUpperCase();

        }
        String pin = pad(String.valueOf(new Random().nextInt(999999)), 6, "0", "Right");
        response += ";" + pin;
        response += ";" + FunctionSupportMB.encryptOneWayDataSave(pin);
        return response;
    }

    private String validate(String input) {
        StringBuffer sb = new StringBuffer();
        String charset = " ABCDEFGH012IJKLMNOPQRSTUVWXYZ569abcdefgh78ijklmnopqrstu34vwxyz.";

        try {
            for (int i = 0; i < input.length(); i++) {
                if (charset.indexOf(input.charAt(i)) < 0) {
                    sb.append("");
                } else {
                    sb.append(input.charAt(i));
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            sb = new StringBuffer(input);
        } finally {

        }
        return sb.toString();
    }

    private String pad(String s, int pad, String padString, String position) {
        StringBuffer a = new StringBuffer(pad);
        for (int i = 0; i < pad; i++) {
            a = a.append(padString);
        }
        if (position.equals("Right")) {
            return a.substring(s.length()) + s;
        }
        return s + a.substring(s.length());
    }
    
}
