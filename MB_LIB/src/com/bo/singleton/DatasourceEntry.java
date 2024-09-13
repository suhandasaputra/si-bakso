/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bo.singleton;

import javax.sql.DataSource;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class DatasourceEntry {

    private static DatasourceEntry log = null;
    private DataSource mbdatasource = null;
    private DataSource postgreDS = null;

    public DataSource getPostgreDS() {
        return postgreDS;
    }

    public void setPostgreDS(DataSource postgreDS) {
        this.postgreDS = postgreDS;
    }
    
    public DatasourceEntry() {
        
    }

    public static DatasourceEntry getInstance() {
        if (log == null) {
            log = new DatasourceEntry();
        }

        return log;
    }

    /**
     * @return the mbdatasource
     */
    public DataSource getMbdatasource() {
        return mbdatasource;
    }

    /**
     * @param cbddatasource the mbdatasource to set
     */
    public void setMbdatasource(DataSource cbddatasource) {
        this.mbdatasource = cbddatasource;
    }

}
