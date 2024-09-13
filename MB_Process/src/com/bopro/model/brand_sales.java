/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bopro.model;

/**
 *
 * @author matajari
 */
public class brand_sales {

    private String brand_id;
    private String brand_name;
    private String transaction;
    private String amount;
    private String pajak;
    private String plus_pajak;

    public String getPlus_pajak() {
        return plus_pajak;
    }

    public void setPlus_pajak(String plus_pajak) {
        this.plus_pajak = plus_pajak;
    }

    public String getPajak() {
        return pajak;
    }

    public void setPajak(String pajak) {
        this.pajak = pajak;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}
