/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */public class Messageout implements Serializable {
    private static final long serialVersionUID = 1L;
    private String message;
    private Date waktu;
    private Boolean status;
    private Long msgoutid;
    private String typemsg;
    private String tosocket;

    public Messageout() {
    }

    public Messageout(Long msgoutid) {
        this.msgoutid = msgoutid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getWaktu() {
        return waktu;
    }

    public void setWaktu(Date waktu) {
        this.waktu = waktu;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getMsgoutid() {
        return msgoutid;
    }

    public void setMsgoutid(Long msgoutid) {
        this.msgoutid = msgoutid;
    }

    public String getTypemsg() {
        return typemsg;
    }

    public void setTypemsg(String typemsg) {
        this.typemsg = typemsg;
    }

    public String getTosocket() {
        return tosocket;
    }

    public void setTosocket(String tosocket) {
        this.tosocket = tosocket;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (msgoutid != null ? msgoutid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Messageout)) {
            return false;
        }
        Messageout other = (Messageout) object;
        if ((this.msgoutid == null && other.msgoutid != null) || (this.msgoutid != null && !this.msgoutid.equals(other.msgoutid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.muamalat.entity.Messageout[msgoutid=" + msgoutid + "]";
    }

}
