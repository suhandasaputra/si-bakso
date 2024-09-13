package com.bo.function;

/*
 * Created on Dec 15, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import org.apache.log4j.Logger;

/**
 * @author adi
 *
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Periode {

    private static Logger log = Logger.getLogger(Periode.class);
    private static Random rand = new Random();

    public static String today() {
        Date today = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        DateFormat sdf = new SimpleDateFormat("yyyyMM");
        return sdf.format(today);
    }

        public static String todayTimestamp() {
        Date today = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        2017-09-26 23:59:59.999
        return sdf.format(today)+" 23:59:59.999";
    }
    
    
    public static String lastDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        Date lastDayOfMonth = calendar.getTime();
        DateFormat sdf = new SimpleDateFormat("yyyyMM");
        return sdf.format(lastDayOfMonth);
    }
    public static String lastDayOfMonthTimestamp() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        Date lastDayOfMonth = calendar.getTime();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM");
        return sdf.format(lastDayOfMonth)+"-01 00:00:00";
    }
    
//    public static void main(String[] args) {
//        System.out.println("to "+ todayTimestamp());
//        System.out.println("from "+ lastDayOfMonthTimestamp());
//    }
}
