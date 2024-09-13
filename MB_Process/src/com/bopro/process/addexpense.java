/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bopro.process;

import com.bopro.database.BackendDBProcess;
import com.bo.parameter.FieldParameterMatapos;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * @author suhanda
 */
public class addexpense {

    private static Logger log = Logger.getLogger(addexpense.class);

    public HashMap process(HashMap input) throws UnsupportedEncodingException {
        BackendDBProcess dp = new BackendDBProcess();

        HashMap status = dp.addexpense(
                input.get("brand_id").toString(), 
                input.get("user_id").toString(), 
                input.get("outlet_id").toString(), 
                input.get("user_name").toString(), 
                input.get("expense_subject").toString(),
                input.get("expense_to").toString(), 
                input.get("expense_amount").toString(), 
                input.get("expense_desc").toString()
        );
        return status;
    }
}
