/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bopro.process;

import com.bopro.database.BackendDBProcess;
import java.util.HashMap;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;

/**
 *
 * @author suhanda
 */
public class processprod {

    private static Logger log = Logger.getLogger(processprod.class);

    public HashMap process(HashMap input) throws ParseException {
        BackendDBProcess dp = new BackendDBProcess();
        HashMap status = dp.insertorupdatebatch(              
                input.get("user_id").toString(), 
                input.get("rrn").toString(), 
                input.get("product").toString(),
                input.get("qty").toString());
        dp = null;
        return status;
    }
}
