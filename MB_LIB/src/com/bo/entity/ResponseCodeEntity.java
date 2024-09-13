/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bo.entity;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class ResponseCodeEntity {

    private String code;
    private String desc;

    public ResponseCodeEntity(String respCode, String respDesc){
        code=respCode;
        desc=respDesc;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }
}
