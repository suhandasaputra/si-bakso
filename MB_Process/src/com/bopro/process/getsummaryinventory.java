/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bopro.process;

//import com.bopro.database.BackendDBProcess;
import com.bo.parameter.FieldParameter;
import com.bo.parameter.FieldParameterMatapos;
import com.bopro.database.BackendDBProcess;
import com.bopro.model.summaryproduct;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author suhanda
 */
public class getsummaryinventory {

//    private static Logger log = Logger.getLogger(getdashboard.class);
    public HashMap process(HashMap input) {

        BackendDBProcess dp = new BackendDBProcess();
        Gson gson = new Gson();
        ArrayList<summaryproduct> listcustomer = dp.getsummaryinventory(input.get(FieldParameterMatapos.brand_id).toString(),input.get(FieldParameterMatapos.outlet_id).toString(),input.get(FieldParameterMatapos.userlevel).toString());
        try {
            if (listcustomer != null) {
                JsonElement element = gson.toJsonTree(listcustomer);
                input.put(FieldParameterMatapos.listsummaryproduct, element);
                input.put(FieldParameter.resp_code, "0000");
                input.put(FieldParameter.resp_desc, "Success");
                return input;
            } else {
                input.put(FieldParameter.resp_code, "0001");
                input.put(FieldParameter.resp_code, "Failed");
                return input;
            }
        } catch (Exception e) {
            input.put(FieldParameter.resp_code, "0099");
            input.put(FieldParameter.resp_code, "unknown error");
            return input;
        } finally {
            dp = null;
            gson = null;
            listcustomer = null;
        }
    }
}
