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
public class getlistcategory {

    private static Logger log = Logger.getLogger(getlistcategory.class);

    public HashMap process(HashMap input) {
        BackendDBProcess dp = new BackendDBProcess();

        HashMap result = dp.getlistcategory(input.get(FieldParameterMatapos.userlevel).toString());
        dp = null;
        return result;
    }
}
