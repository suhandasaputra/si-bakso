/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bo.entity;

/**
 *
 * @author herrysuganda
 */


public class LimitProcCode {

    private String limitID;
    private String trxCode;
    private String channelCode;
    private String procCode;

    /**
     * @return the limitID
     */
    public String getLimitID() {
        return limitID;
    }

    /**
     * @param limitID the limitID to set
     */
    public void setLimitID(String limitID) {
        this.limitID = limitID;
    }

    /**
     * @return the trxCode
     */
    public String getTrxCode() {
        return trxCode;
    }

    /**
     * @param trxCode the trxCode to set
     */
    public void setTrxCode(String trxCode) {
        this.trxCode = trxCode;
    }

    /**
     * @return the channelCode
     */
    public String getChannelCode() {
        return channelCode;
    }

    /**
     * @param channelCode the channelCode to set
     */
    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    /**
     * @return the procCode
     */
    public String getProcCode() {
        return procCode;
    }

    /**
     * @param procCode the procCode to set
     */
    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }


}
