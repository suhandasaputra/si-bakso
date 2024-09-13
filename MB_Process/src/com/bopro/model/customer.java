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
public class customer {

    private String name;
    private String birthdate;
    private String phonenumber;
    private String email;
    private String address;
    private String amount_trx;
    private String total_trx;
    private String brand_id;
    private String outlet_id;
    
    
    private String createdate;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAmount_trx() {
        return amount_trx;
    }

    public void setAmount_trx(String amount_trx) {
        this.amount_trx = amount_trx;
    }

    public String getTotal_trx() {
        return total_trx;
    }

    public void setTotal_trx(String total_trx) {
        this.total_trx = total_trx;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getOutlet_id() {
        return outlet_id;
    }

    public void setOutlet_id(String outlet_id) {
        this.outlet_id = outlet_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }
}
