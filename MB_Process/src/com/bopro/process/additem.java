/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bopro.process;

import com.bopro.database.BackendDBProcess;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * @author suhanda
 */
public class additem {

    private static Logger log = Logger.getLogger(additem.class);

    public HashMap process(HashMap input) {
        BackendDBProcess dp = new BackendDBProcess();
        HashMap status = dp.additem(
                input.get("category").toString(),
                input.get("product_code").toString(),
                input.get("product_name").toString(),
                input.get("image").toString(),
                input.get("std").toString());
        return status;
    }
}
