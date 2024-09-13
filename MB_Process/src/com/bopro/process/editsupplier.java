/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bopro.process;

import com.bopro.database.BackendDBProcess;
import com.bo.parameter.FieldParameterMatapos;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * @author suhanda
 */
public class editsupplier {

    private static Logger log = Logger.getLogger(editsupplier.class);

    public HashMap process(HashMap input) {
        BackendDBProcess dp = new BackendDBProcess();
        HashMap status = dp.editsupplier(input.get(FieldParameterMatapos.supplier_id).toString(), input.get(FieldParameterMatapos.supplier_name).toString(), input.get(FieldParameterMatapos.supplier_address).toString(), input.get(FieldParameterMatapos.supplier_phone).toString(), input.get(FieldParameterMatapos.supplier_mail).toString(), input.get(FieldParameterMatapos.brand_id).toString());
        return status;
    }
}
