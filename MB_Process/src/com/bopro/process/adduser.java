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
public class adduser {

    private static Logger log = Logger.getLogger(adduser.class);

    public HashMap process(HashMap input) throws UnsupportedEncodingException {
        BackendDBProcess dp = new BackendDBProcess();
        HashMap status = dp.adduser(input.get(FieldParameterMatapos.user_name).toString(), input.get(FieldParameterMatapos.user_id).toString(), input.get(FieldParameterMatapos.phonenumber).toString(), input.get(FieldParameterMatapos.userlevel).toString());
        return status;
    }
}
