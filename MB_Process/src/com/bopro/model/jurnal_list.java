/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bopro.model;

/**
 *
 * @author suhanda
 */
public class jurnal_list {

    private String noresi;
    private String dt_internal;
    private String customer_name;
    private String amount;
    private String outlet_id;
    
    private String prev_bal;
    private String curr_bal;
    private String status;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    

    public String getPrev_bal() {
        return prev_bal;
    }

    public void setPrev_bal(String prev_bal) {
        this.prev_bal = prev_bal;
    }

    public String getCurr_bal() {
        return curr_bal;
    }

    public void setCurr_bal(String curr_bal) {
        this.curr_bal = curr_bal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getNoresi() {
        return noresi;
    }

    public void setNoresi(String noresi) {
        this.noresi = noresi;
    }

    public String getDt_internal() {
        return dt_internal;
    }

    public void setDt_internal(String dt_internal) {
        this.dt_internal = dt_internal;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getOutlet_id() {
        return outlet_id;
    }

    public void setOutlet_id(String outlet_id) {
        this.outlet_id = outlet_id;
    }

    
}
