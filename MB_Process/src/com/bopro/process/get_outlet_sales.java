/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bopro.process;

import com.bopro.database.BackendDBProcess;
import com.bo.parameter.FieldParameter;
import com.bo.parameter.FieldParameterMatapos;
import com.bopro.model.outlet_sales;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * @author suhanda
 */
public class get_outlet_sales {

    private static Logger log = Logger.getLogger(get_outlet_sales.class);

    public HashMap process(HashMap input) {
        BackendDBProcess dp = new BackendDBProcess();
        Gson gson = new Gson();
        JsonElement element = new JsonArray();
        ArrayList<outlet_sales> listsales = new ArrayList<outlet_sales>();
        try {
            listsales = dp.getAlloutletsales(input.get(FieldParameterMatapos.brand_id).toString(), input.get(FieldParameterMatapos.outlet_id).toString(), input.get(FieldParameterMatapos.tanggal_from).toString(), input.get(FieldParameterMatapos.tanggal_to).toString());
            element = gson.toJsonTree(listsales);
            input.put(FieldParameterMatapos.list_outlet_sales, element);
            input.put(FieldParameter.resp_code, "0000");
            input.put(FieldParameter.resp_desc, "Success");
        } catch (Exception e) {
            input.put(FieldParameter.resp_code, "0099");
            input.put(FieldParameter.resp_code, "unknown error");
        } finally {
            dp = null;
            gson = null;
            element = null;
            listsales = null;
        }
        return input;
    }
}
