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
public class edititem {

    private static Logger log = Logger.getLogger(edititem.class);

    public HashMap process(HashMap input) {
        
        BackendDBProcess dp = new BackendDBProcess();
        HashMap status = dp.edititem(
                input.get("user_id").toString(), 
                input.get("item_product_code_edit").toString(), 
                input.get("category_name_item").toString(), 
                input.get("item_name_edit_item").toString(), 
                input.get("item_std_edit_item").toString(),
                input.get("img_item_edit_item").toString());
        return status;
    }
}
