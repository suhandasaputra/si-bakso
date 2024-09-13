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
public class expense_list {

    private String exp_id;
    private String outlet_id;
    private String brand_id;
    private String exp_date;
    private String exp_category;
    private String exp_desc;
    private String amount;
    private String receiver;

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }


    public String getExp_id() {
        return exp_id;
    }

    public void setExp_id(String exp_id) {
        this.exp_id = exp_id;
    }

    public String getOutlet_id() {
        return outlet_id;
    }

    public void setOutlet_id(String outlet_id) {
        this.outlet_id = outlet_id;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getExp_date() {
        return exp_date;
    }

    public void setExp_date(String exp_date) {
        this.exp_date = exp_date;
    }

    public String getExp_category() {
        return exp_category;
    }

    public void setExp_category(String exp_category) {
        this.exp_category = exp_category;
    }

    public String getExp_desc() {
        return exp_desc;
    }

    public void setExp_desc(String exp_desc) {
        this.exp_desc = exp_desc;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
    
}
