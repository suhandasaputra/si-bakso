/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bo.function;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class JsonProcess {
    private static Logger log = Logger.getLogger(JsonProcess.class);
    public static String generateJson (HashMap data){        
        return new JSONObject(data).toJSONString();        
    }
    
    public static HashMap decodeJson(String msg){
        HashMap obj = null;
        try {
            JSONParser parser = new JSONParser();
            obj = (HashMap) parser.parse(msg);
            new JsonProcess().printmsg(obj);
        } catch (ParseException ex) {
            log.error(ex.getMessage());
        }
        return obj;
    }
    
    
    
    public void printmsg(HashMap obj) {
        Iterator iterator = obj.keySet().iterator();
        String loginfomsg = "";
        while (iterator.hasNext()) {
            String item = iterator.next().toString();
            if (castjsonobj(obj, item)) {
                HashMap subobj = (HashMap) obj.get(item);
                printmsg(subobj);
            } else {
            }
        }
    }
        
    private boolean castjsonobj(HashMap obj, String key) {
        try {
            if (obj.get(key) == null) return false;
            
            obj = (HashMap) obj.get(key);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
    
//    public static void main(String[] args) throws ParseException {
//        String text = "{\"rq_uuid\":\"MATAJARI141410000000141410\",\"rs_datetime\":\"2015-05-26 18:45:10\",\"error_code\":\"0000\",\"error_desc\":\"COMPLETED SUCCESFULLY\",\"order_id\":\"1880105198100\",\"ref_id\":\"MATAJARI141410000000141410\",\"amount\":\"140100000\",\"data\":{\"order_id\":\"1880105198100\",\"airline_code\":\"09\",\"airline_code2\":\"0002\",\"total_flight\":\"2\",\"passenger_name\":\"JOE STRUMMER\",\"pnr_code\":\"FWFXQY\",\"info\":[{\"number_of_passengers\":\"01\",\"carrier\":\"JT\",\"class\":\"V\",\"from\":\"CGK\",\"to\":\"DPS\",\"flight_number\":\"34\",\"depart_date\":\"3105\",\"depart_time\":\"0430\"},{\"number_of_passengers\":\"01\",\"carrier\":\"JT\",\"class\":\"T\",\"from\":\"DPS\",\"to\":\"CGK\",\"flight_number\":\"11\",\"depart_date\":\"2406\",\"depart_time\":\"0630\"}]}}";
//        JSONParser parser = new JSONParser();
//        
////        JSONObject obj = (JSONObject) parser.parse(text);
//        HashMap obj = JsonProcess.decodeJson(text);
//        HashMap data = (HashMap) obj.get("data");
//        List info = (List) data.get("info");
//        for (int i=0;i<info.size();i++){
//            System.out.println(info.get(i));
//        }
//    
//    
//    }
    
}
