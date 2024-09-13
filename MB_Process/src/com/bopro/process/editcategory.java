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
public class editcategory {

    private static Logger log = Logger.getLogger(editcategory.class);

    public HashMap process(HashMap input) {
        
        BackendDBProcess dp = new BackendDBProcess();
        HashMap status = dp.editcategory(input.get("user_id").toString(), input.get("category_id").toString(), input.get("category_name").toString(), input.get("new_category").toString(), input.get("jenis").toString());
        return status;
    }
}
