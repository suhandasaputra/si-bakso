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
 */public class Tempmsg implements Serializable {
    private static long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
    private String msgid;
    private String abdmsg;
    private String reqisobank;
    private String reqbiller;
    private String respisobank = "";
    private String noref;
    private String cardno;

    public String getReqbiller() {
        return reqbiller;
    }

    public void setReqbiller(String reqbiller) {
        this.reqbiller = reqbiller;
    }
    private String bankcode;
    private String bankcodefrom;
    private String fromaccount;
    private String toaccount;
    private String amount;
    private float prev_bal;
    private float curr_bal;
    private float prev_biller_bal;

    public float getPrev_biller_bal() {
        return prev_biller_bal;
    }

    public void setPrev_biller_bal(float prev_biller_bal) {
        this.prev_biller_bal = prev_biller_bal;
    }

    public float getCurr_biller_bal() {
        return curr_biller_bal;
    }

    public void setCurr_biller_bal(float curr_biller_bal) {
        this.curr_biller_bal = curr_biller_bal;
    }
    private float curr_biller_bal;
    private String proccode;
    private String productcode;
    private String responsecode;
    private String transactionid;
    private String withdrawalCode;

    public String getWithdrawalCode() {
        return withdrawalCode;
    }

    public void setWithdrawalCode(String widrawalCode) {
        this.withdrawalCode = widrawalCode;
    }

    
    public float getPrev_bal() {
        return prev_bal;
    }

    public void setPrev_bal(float prev_bal) {
        this.prev_bal = prev_bal;
    }

    public float getCurr_bal() {
        return curr_bal;
    }

    public void setCurr_bal(float curr_bal) {
        this.curr_bal = curr_bal;
    }
    private boolean statusreply;
    private Date requesttime;
    private Date responsetime;
    private String trxidbackend;
    private String fromSocket;
    private String rcInternal;
    private String custNo;
    private String merchantType;

   
    
    
    public Tempmsg() {
    }

    public Tempmsg(String msgid) {
        this.msgid = msgid;
    }

    public Tempmsg(String msgid, boolean statusreply, Date requesttime, String respisobank, String responsecode) {
        this.msgid = msgid;
        this.statusreply = statusreply;
        this.requesttime = requesttime;
        this.respisobank = respisobank;
        this.responsecode = responsecode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getMsgid() != null ? getMsgid().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tempmsg)) {
            return false;
        }
        Tempmsg other = (Tempmsg) object;
        if ((this.getMsgid() == null && other.getMsgid() != null) || (this.getMsgid() != null && !this.msgid.equals(other.msgid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.muamalat.entity.Tempmsg[msgid=" + getMsgid() + "]";
    }

    /**
     * @return the msgid
     */
    public String getMsgid() {
        return msgid;
    }

    /**
     * @param msgid the msgid to set
     */
    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    /**
     * @return the abdmsg
     */
    public String getAbdmsg() {
        return abdmsg;
    }

    /**
     * @param abdmsg the abdmsg to set
     */
    public void setAbdmsg(String abdmsg) {
        this.abdmsg = abdmsg;
    }

    /**
     * @return the reqisobank
     */
    public String getReqisobank() {
        return reqisobank;
    }

    /**
     * @param reqisobank the reqisobank to set
     */
    public void setReqisobank(String reqisobank) {
        this.reqisobank = reqisobank;
    }

    /**
     * @return the respisobank
     */
    public String getRespisobank() {
        return respisobank;
    }

    /**
     * @param respisobank the respisobank to set
     */
    public void setRespisobank(String respisobank) {
        this.respisobank = respisobank;
    }

    /**
     * @return the noref
     */
    public String getNoref() {
        return noref;
    }

    /**
     * @param noref the noref to set
     */
    public void setNoref(String noref) {
        this.noref = noref;
    }

    /**
     * @return the cardno
     */
    public String getCardno() {
        return cardno;
    }

    /**
     * @param cardno the cardno to set
     */
    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    /**
     * @return the bankcode
     */
    public String getBankcode() {
        return bankcode;
    }

    /**
     * @param bankcode the bankcode to set
     */
    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }

    /**
     * @return the fromaccount
     */
    public String getFromaccount() {
        return fromaccount;
    }

    /**
     * @param fromaccount the fromaccount to set
     */
    public void setFromaccount(String fromaccount) {
        this.fromaccount = fromaccount;
    }

    /**
     * @return the toaccount
     */
    public String getToaccount() {
        return toaccount;
    }

    /**
     * @param toaccount the toaccount to set
     */
    public void setToaccount(String toaccount) {
        this.toaccount = toaccount;
    }

    /**
     * @return the amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * @return the proccode
     */
    public String getProccode() {
        return proccode;
    }

    /**
     * @param proccode the proccode to set
     */
    public void setProccode(String proccode) {
        this.proccode = proccode;
    }

    /**
     * @return the responsecode
     */
    public String getResponsecode() {
        return responsecode;
    }

    /**
     * @param responsecode the responsecode to set
     */
    public void setResponsecode(String responsecode) {
        this.responsecode = responsecode;
    }

    /**
     * @return the statusreply
     */
    public boolean isStatusreply() {
        return statusreply;
    }

    /**
     * @param statusreply the statusreply to set
     */
    public void setStatusreply(boolean statusreply) {
        this.statusreply = statusreply;
    }

    /**
     * @return the requesttime
     */
    public Date getRequesttime() {
        return requesttime;
    }

    /**
     * @param requesttime the requesttime to set
     */
    public void setRequesttime(Date requesttime) {
        this.requesttime = requesttime;
    }

    /**
     * @return the responsetime
     */
    public Date getResponsetime() {
        return responsetime;
    }

    /**
     * @param responsetime the responsetime to set
     */
    public void setResponsetime(Date responsetime) {
        this.responsetime = responsetime;
    }

    /**
     * @return the transactionid
     */
    public String getTransactionid() {
        return transactionid;
    }

    /**
     * @param transactionid the transactionid to set
     */
    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

    /**
     * @return the productcode
     */
    public String getProductcode() {
        return productcode;
    }

    /**
     * @param productcode the productcode to set
     */
    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }

    /**
     * @return the trxidbackend
     */
    public String getTrxidbackend() {
        return trxidbackend;
    }

    /**
     * @param trxidbackend the trxidbackend to set
     */
    public void setTrxidbackend(String trxidbackend) {
        this.trxidbackend = trxidbackend;
    }

    /**
     * @return the fromSocket
     */
    public String getFromSocket() {
        return fromSocket;
    }

    /**
     * @param fromSocket the fromSocket to set
     */
    public void setFromSocket(String fromSocket) {
        this.fromSocket = fromSocket;
    }

    /**
     * @return the bankcodefrom
     */
    public String getBankcodefrom() {
        return bankcodefrom;
    }

    /**
     * @param bankcodefrom the bankcodefrom to set
     */
    public void setBankcodefrom(String bankcodefrom) {
        this.bankcodefrom = bankcodefrom;
    }

    /**
     * @return the rcInternal
     */
    public String getRcInternal() {
        return rcInternal;
    }

    /**
     * @param rcInternal the rcInternal to set
     */
    public void setRcInternal(String rcInternal) {
        this.rcInternal = rcInternal;
    }

    /**
     * @return the custNo
     */
    public String getCustNo() {
        return custNo;
    }

    /**
     * @param custNo the custNo to set
     */
    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }
 public String getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(String merchantType) {
        this.merchantType = merchantType;
    }
}
