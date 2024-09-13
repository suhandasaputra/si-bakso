/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bo.entity;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class TransactionLimit {

    private String limitID;
    private String trxCode;
    private String channelCode;
    private String trxGroup;
    private double singleAmountLimit;
    private int dailyItemLimit;
    private double dailyAmountLimit;
    private int monthlyItemLimit;
    private double monthlyAmountLimit;
    private double minAmountLimit;

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
     * @return the singleAmountLimit
     */
    public double getSingleAmountLimit() {
        return singleAmountLimit;
    }

    /**
     * @param singleAmountLimit the singleAmountLimit to set
     */
    public void setSingleAmountLimit(double singleAmountLimit) {
        this.singleAmountLimit = singleAmountLimit;
    }

    /**
     * @return the dailyItemLimit
     */
    public int getDailyItemLimit() {
        return dailyItemLimit;
    }

    /**
     * @param dailyItemLimit the dailyItemLimit to set
     */
    public void setDailyItemLimit(int dailyItemLimit) {
        this.dailyItemLimit = dailyItemLimit;
    }

    /**
     * @return the dailyAmountLimit
     */
    public double getDailyAmountLimit() {
        return dailyAmountLimit;
    }

    /**
     * @param dailyAmountLimit the dailyAmountLimit to set
     */
    public void setDailyAmountLimit(double dailyAmountLimit) {
        this.dailyAmountLimit = dailyAmountLimit;
    }

    /**
     * @return the monthlyItemLimit
     */
    public int getMonthlyItemLimit() {
        return monthlyItemLimit;
    }

    /**
     * @param monthlyItemLimit the monthlyItemLimit to set
     */
    public void setMonthlyItemLimit(int monthlyItemLimit) {
        this.monthlyItemLimit = monthlyItemLimit;
    }

    /**
     * @return the monthlyAmountLimit
     */
    public double getMonthlyAmountLimit() {
        return monthlyAmountLimit;
    }

    /**
     * @param monthlyAmountLimit the monthlyAmountLimit to set
     */
    public void setMonthlyAmountLimit(double monthlyAmountLimit) {
        this.monthlyAmountLimit = monthlyAmountLimit;
    }

    /**
     * @return the minAmountLimit
     */
    public double getMinAmountLimit() {
        return minAmountLimit;
    }

    /**
     * @param minAmountLimit the minAmountLimit to set
     */
    public void setMinAmountLimit(double minAmountLimit) {
        this.minAmountLimit = minAmountLimit;
    }

    /**
     * @return the trxGroup
     */
    public String getTrxGroup() {
        return trxGroup;
    }

    /**
     * @param trxGroup the trxGroup to set
     */
    public void setTrxGroup(String trxGroup) {
        this.trxGroup = trxGroup;
    }

    
    
}
