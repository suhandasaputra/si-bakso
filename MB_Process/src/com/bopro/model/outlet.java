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
public class outlet {
    
    private String brand_id;
    private String outlet_id;
    private String outlet_name;
    private String alamat;
    private String edit;
    private String balance;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getEdit() {
        return edit;
    }

    public void setEdit(String edit) {
        this.edit = edit;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    private String createdate;
    private String header_receipt;
    private String footer_receipt;
    private String info;
    private String shifting;

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

    public String getOutlet_name() {
        return outlet_name;
    }

    public void setOutlet_name(String outlet_name) {
        this.outlet_name = outlet_name;
    }

    

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getHeader_receipt() {
        return header_receipt;
    }

    public void setHeader_receipt(String header_receipt) {
        this.header_receipt = header_receipt;
    }

    public String getFooter_receipt() {
        return footer_receipt;
    }

    public void setFooter_receipt(String footer_receipt) {
        this.footer_receipt = footer_receipt;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getShifting() {
        return shifting;
    }

    public void setShifting(String shifting) {
        this.shifting = shifting;
    }
}
