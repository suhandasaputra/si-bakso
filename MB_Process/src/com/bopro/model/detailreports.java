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
public class detailreports {

    private String plus;

    private String noresi;
    private String dt_internal;
    private String item_purchase;
    private String customer_name;
    private String amount;
    private String tax;
    private String payment_method;
    private String download;
    private String print;
    private String detail;
    private String status_void;
    private String dt_void;
    private String outlet_id;

    public String getOutlet_id() {
        return outlet_id;
    }

    public void setOutlet_id(String outlet_id) {
        this.outlet_id = outlet_id;
    }

    public String getStatus_void() {
        return status_void;
    }

    public void setStatus_void(String status_void) {
        this.status_void = status_void;
    }

    public String getDt_void() {
        return dt_void;
    }

    public void setDt_void(String dt_void) {
        this.dt_void = dt_void;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public String getPrint() {
        return print;
    }

    public void setPrint(String print) {
        this.print = print;
    }

    public String getPlus() {
        return plus;
    }

    public void setPlus(String plus) {
        this.plus = plus;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getItem_purchase() {
        return item_purchase;
    }

    public void setItem_purchase(String item_purchase) {
        this.item_purchase = item_purchase;
    }

    public String getDt_internal() {
        return dt_internal;
    }

    public void setDt_internal(String dt_internal) {
        this.dt_internal = dt_internal;
    }

    public String getNoresi() {
        return noresi;
    }

    public void setNoresi(String noresi) {
        this.noresi = noresi;
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

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }
}
