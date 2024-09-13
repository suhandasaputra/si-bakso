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
public class addsupplier {

    private static Logger log = Logger.getLogger(addsupplier.class);

    public HashMap process(HashMap input) throws UnsupportedEncodingException {
        BackendDBProcess dp = new BackendDBProcess();
        HashMap status = dp.addsupplier(input.get(FieldParameterMatapos.supplier_name).toString(), input.get(FieldParameterMatapos.supplier_address).toString(), input.get(FieldParameterMatapos.supplier_phone).toString(), input.get(FieldParameterMatapos.supplier_mail).toString(), input.get(FieldParameterMatapos.brand_id).toString());
        return status;
    }
}
