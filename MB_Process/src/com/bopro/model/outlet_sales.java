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
public class outlet_sales {

    private String outlet_id;
    private String outlet_name;
    private String order;
    private String product_sold;
    private String total;
    private String brand_id;
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

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getProduct_sold() {
        return product_sold;
    }

    public void setProduct_sold(String product_sold) {
        this.product_sold = product_sold;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

}
