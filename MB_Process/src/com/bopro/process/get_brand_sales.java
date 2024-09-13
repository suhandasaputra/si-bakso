/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bopro.process;

import com.bopro.database.BackendDBProcess;
import com.bo.parameter.FieldParameter;
import com.bo.parameter.FieldParameterMatapos;
import com.bopro.model.brand_sales;
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
public class get_brand_sales {

    private static Logger log = Logger.getLogger(get_brand_sales.class);

    public HashMap process(HashMap input) {
        BackendDBProcess dp = new BackendDBProcess();
        Gson gson = new Gson();
        JsonElement element = new JsonArray();
        ArrayList<brand_sales> listsales = new ArrayList<brand_sales>();
        try {
            listsales = dp.getAllbrandsales(input.get(FieldParameterMatapos.brand_id).toString(),input.get(FieldParameterMatapos.tanggal_from).toString(), input.get(FieldParameterMatapos.tanggal_to).toString());
            element = gson.toJsonTree(listsales);
            input.put("list_brand_sales", element);
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
