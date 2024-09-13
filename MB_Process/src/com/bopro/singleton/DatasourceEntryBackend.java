/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bopro.singleton;

import javax.sql.DataSource;

/**
 *
 * @author herry.suganda
 */
public class DatasourceEntryBackend {

    private static DatasourceEntryBackend log = null;
    private DataSource BackendDS = null;
    
    public DatasourceEntryBackend() {
        
    }

    public static DatasourceEntryBackend getInstance() {
        if (log == null) {
            log = new DatasourceEntryBackend();
        }

        return log;
    }

    /**
     * @return the BackendDS
     */
    public DataSource getBackendDS() {
        return BackendDS;
    }

    /**
     * @param BackendDS the BackendDS to set
     */
    public void setBackendDS(DataSource BackendDS) {
        this.BackendDS = BackendDS;
    }


}
