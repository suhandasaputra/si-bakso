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
public class addcustomer {

    private static Logger log = Logger.getLogger(addcustomer.class);

    public HashMap process(HashMap input) throws UnsupportedEncodingException {
        BackendDBProcess dp = new BackendDBProcess();
        HashMap status = dp.addcustomer(input.get(FieldParameterMatapos.customer_name).toString(), input.get(FieldParameterMatapos.customer_email).toString(), input.get(FieldParameterMatapos.customer_phone).toString(), input.get(FieldParameterMatapos.customer_address).toString());
        dp = null;
        return status;
    }
}
