/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bo.singleton;


/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class InitParameterSingleton {

    private boolean statusThread;

    public static InitParameterSingleton getInstance() {
        if (getRef() == null) {
//            System.out.println("getInstance");
            setRef(new InitParameterSingleton());
        }
        return getRef();
    }

    /**
     * @return the ref
     */
    public static InitParameterSingleton getRef() {
        return ref;
    }

    /**
     * @param aRef the ref to set
     */
    public static void setRef(InitParameterSingleton aRef) {
        ref = aRef;
    }

    public InitParameterSingleton(){
        
    }

   
    private static InitParameterSingleton ref = null;
    private String postgresDatabaseIP;
    private String postgresDatabasePort;
    private String postgresDatabaseSID;
    private String postgresDatabaseUsername;
    private String postgresDatabasePassword;
    private String postgresDatabaseDriver;
    private String oracleDatabaseIP;
    private String oracleDatabasePort;
    private String oracleDatabaseSID;
    private String oracleDatabaseUsername;
    private String oracleDatabasePassword;
    private String oracleDatabaseDriver;
    private String gatewayType;
    private int wdrOnusLimit;
    private int wdrOffusLimit;
    private int trfOnusLimit;
    private int trfOffusLimit;
    private int stanSignon = 0;

    /**
     * @return the postgresDatabaseIP
     */
    public String getPostgresDatabaseIP() {
        return postgresDatabaseIP;
    }

    /**
     * @param postgresDatabaseIP the postgresDatabaseIP to set
     */
    public void setPostgresDatabaseIP(String postgresDatabaseIP) {
        this.postgresDatabaseIP = postgresDatabaseIP;
    }

    /**
     * @return the postgresDatabasePort
     */
    public String getPostgresDatabasePort() {
        return postgresDatabasePort;
    }

    /**
     * @param postgresDatabasePort the postgresDatabasePort to set
     */
    public void setPostgresDatabasePort(String postgresDatabasePort) {
        this.postgresDatabasePort = postgresDatabasePort;
    }

    /**
     * @return the postgresDatabaseSID
     */
    public String getPostgresDatabaseSID() {
        return postgresDatabaseSID;
    }

    /**
     * @param postgresDatabaseSID the postgresDatabaseSID to set
     */
    public void setPostgresDatabaseSID(String postgresDatabaseSID) {
        this.postgresDatabaseSID = postgresDatabaseSID;
    }

    /**
     * @return the postgresDatabaseUsername
     */
    public String getPostgresDatabaseUsername() {
        return postgresDatabaseUsername;
    }

    /**
     * @param postgresDatabaseUsername the postgresDatabaseUsername to set
     */
    public void setPostgresDatabaseUsername(String postgresDatabaseUsername) {
        this.postgresDatabaseUsername = postgresDatabaseUsername;
    }

    /**
     * @return the postgresDatabasePassword
     */
    public String getPostgresDatabasePassword() {
        return postgresDatabasePassword;
    }

    /**
     * @param postgresDatabasePassword the postgresDatabasePassword to set
     */
    public void setPostgresDatabasePassword(String postgresDatabasePassword) {
        this.postgresDatabasePassword = postgresDatabasePassword;
    }

    /**
     * @return the postgresDatabaseDriver
     */
    public String getPostgresDatabaseDriver() {
        return postgresDatabaseDriver;
    }

    /**
     * @param postgresDatabaseDriver the postgresDatabaseDriver to set
     */
    public void setPostgresDatabaseDriver(String postgresDatabaseDriver) {
        this.postgresDatabaseDriver = postgresDatabaseDriver;
    }

    /**
     * @return the oracleDatabaseIP
     */
    public String getOracleDatabaseIP() {
        return oracleDatabaseIP;
    }

    /**
     * @param oracleDatabaseIP the oracleDatabaseIP to set
     */
    public void setOracleDatabaseIP(String oracleDatabaseIP) {
        this.oracleDatabaseIP = oracleDatabaseIP;
    }

    /**
     * @return the oracleDatabasePort
     */
    public String getOracleDatabasePort() {
        return oracleDatabasePort;
    }

    /**
     * @param oracleDatabasePort the oracleDatabasePort to set
     */
    public void setOracleDatabasePort(String oracleDatabasePort) {
        this.oracleDatabasePort = oracleDatabasePort;
    }

    /**
     * @return the oracleDatabaseSID
     */
    public String getOracleDatabaseSID() {
        return oracleDatabaseSID;
    }

    /**
     * @param oracleDatabaseSID the oracleDatabaseSID to set
     */
    public void setOracleDatabaseSID(String oracleDatabaseSID) {
        this.oracleDatabaseSID = oracleDatabaseSID;
    }

    /**
     * @return the oracleDatabaseUsername
     */
    public String getOracleDatabaseUsername() {
        return oracleDatabaseUsername;
    }

    /**
     * @param oracleDatabaseUsername the oracleDatabaseUsername to set
     */
    public void setOracleDatabaseUsername(String oracleDatabaseUsername) {
        this.oracleDatabaseUsername = oracleDatabaseUsername;
    }

    /**
     * @return the oracleDatabasePassword
     */
    public String getOracleDatabasePassword() {
        return oracleDatabasePassword;
    }

    /**
     * @param oracleDatabasePassword the oracleDatabasePassword to set
     */
    public void setOracleDatabasePassword(String oracleDatabasePassword) {
        this.oracleDatabasePassword = oracleDatabasePassword;
    }

    /**
     * @return the oracleDatabaseDriver
     */
    public String getOracleDatabaseDriver() {
        return oracleDatabaseDriver;
    }

    /**
     * @param oracleDatabaseDriver the oracleDatabaseDriver to set
     */
    public void setOracleDatabaseDriver(String oracleDatabaseDriver) {
        this.oracleDatabaseDriver = oracleDatabaseDriver;
    }

    /**
     * @return the statusThread
     */
    public boolean isStatusThread() {
        return statusThread;
    }

    /**
     * @param statusThread the statusThread to set
     */
    public void setStatusThread(boolean statusThread) {
        this.statusThread = statusThread;
    }

    /**
     * @return the gatewayType
     */
    public String getGatewayType() {
        return gatewayType;
    }

    /**
     * @param gatewayType the gatewayType to set
     */
    public void setGatewayType(String gatewayType) {
        this.gatewayType = gatewayType;
    }

    /**
     * @return the wdrOnusLimit
     */
    public int getWdrOnusLimit() {
        return wdrOnusLimit;
    }

    /**
     * @param wdrOnusLimit the wdrOnusLimit to set
     */
    public void setWdrOnusLimit(int wdrOnusLimit) {
        this.wdrOnusLimit = wdrOnusLimit;
    }

    /**
     * @return the wdrOffusLimit
     */
    public int getWdrOffusLimit() {
        return wdrOffusLimit;
    }

    /**
     * @param wdrOffusLimit the wdrOffusLimit to set
     */
    public void setWdrOffusLimit(int wdrOffusLimit) {
        this.wdrOffusLimit = wdrOffusLimit;
    }

    /**
     * @return the trfOnusLimit
     */
    public int getTrfOnusLimit() {
        return trfOnusLimit;
    }

    /**
     * @param trfOnusLimit the trfOnusLimit to set
     */
    public void setTrfOnusLimit(int trfOnusLimit) {
        this.trfOnusLimit = trfOnusLimit;
    }

    /**
     * @return the trfOffusLimit
     */
    public int getTrfOffusLimit() {
        return trfOffusLimit;
    }

    /**
     * @param trfOffusLimit the trfOffusLimit to set
     */
    public void setTrfOffusLimit(int trfOffusLimit) {
        this.trfOffusLimit = trfOffusLimit;
    }

    /**
     * @return the stanSignon
     */
    public String getStanSignon() {
        stanSignon++;
        return String.valueOf(stanSignon);
    }  
}
