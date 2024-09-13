/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bo.function;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class CheckSumFunction {

    public static int getCheckSum(String a) {
        int checksum = 0;
        for (int i = 0; i < a.length(); i++) {
//            System.out.print(a.substring(a.length()-(i+1), a.length()-i));
//            System.out.print(" : ");
//            System.out.print("2^" + i + " = " + (int)Math.pow(2, i));
//            System.out.print(" : ");
//            System.out.println((Integer.parseInt(a.substring(a.length()-(i+1), a.length()-i)))*((int)Math.pow(2, i)));
            checksum += (Integer.parseInt(a.substring(a.length() - (i + 1), a.length() - i))) * ((int) Math.pow(2, i));
        }
        return checksum;
    }

    public static boolean verifyCheckSum(String a, String b) {
        int checksum = 0;
        for (int i = 0; i < a.length(); i++) {
//            System.out.print(a.substring(a.length()-(i+1), a.length()-i));
//            System.out.print(" : ");
//            System.out.print("2^" + i + " = " + (int)Math.pow(2, i));
//            System.out.print(" : ");
//            System.out.println((Integer.parseInt(a.substring(a.length()-(i+1), a.length()-i)))*((int)Math.pow(2, i)));
            checksum += (Integer.parseInt(a.substring(a.length() - (i + 1), a.length() - i))) * ((int) Math.pow(2, i));
        }
        return checksum == Integer.valueOf(b);
    }
//    public static void main(String[] args) {
//        String a = "1100";
//        int checksum = 0;
//        for (int i = 0; i < a.length(); i++) {
//            System.out.print(a.substring(a.length() - (i + 1), a.length() - i));
//            System.out.print(" * ");
//            System.out.print("2^" + i + " = " + (int) Math.pow(2, i));
//            System.out.print(" = ");
//            System.out.println((Integer.parseInt(a.substring(a.length() - (i + 1), a.length() - i))) * ((int) Math.pow(2, i)));
//
//        }
//    }

//    public static void main(String[] args) {
//        System.out.println(CheckSumFunction.getCheckSum("100"));
//    }
}
