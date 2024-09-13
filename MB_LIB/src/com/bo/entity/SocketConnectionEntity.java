/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bo.entity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.log4j.Logger;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class SocketConnectionEntity {

    private static Logger log = Logger.getLogger(SocketConnectionEntity.class);
    private String ipAddress;
    private String termID;
    private int port;
    private DataOutputStream outStream = null;
    private DataInputStream inStream = null;
    private Socket requestSocket;
    private ServerSocket providerSocket;
    private boolean start;
    private boolean openSocket;
    private String type;
    private String partner;
    private int headerMessageType;
    private boolean statusStart;
    private int urutan;
    private String jenismsg;
    private String jenis;
    private String bankCode;
    private boolean lengthIncl;
    private boolean signon;
    private boolean autosignon;
    private String signOnMessage;
    private String packageName;

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public Socket getRequestSocket() {
        return requestSocket;
    }

    public void setRequestSocket(Socket requestSocket) {
        this.requestSocket = requestSocket;
    }

    public ServerSocket getProviderSocket() {
        return providerSocket;
    }

    public void setProviderSocket(ServerSocket providerSocket) {
        this.providerSocket = providerSocket;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getTermID() {
        return termID;
    }

    public void setTermID(String termID) {
        this.termID = termID;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public DataOutputStream getOutStream() {
        return outStream;
    }

    public void setOutStream(DataOutputStream outStream) {
        this.outStream = outStream;
    }

    public DataInputStream getInStream() {
        return inStream;
    }

    public void setInStream(DataInputStream inStream) {
        this.inStream = inStream;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the partner
     */
    public String getPartner() {
        return partner;
    }

    /**
     * @param partner the partner to set
     */
    public void setPartner(String partner) {
        this.partner = partner;
    }

    /**
     * @return the openSocket
     */
    public boolean isOpenSocket() {
        return openSocket;
    }

    /**
     * @param openSocket the openSocket to set
     */
    public void setOpenSocket(boolean openSocket) {
        this.openSocket = openSocket;
    }

    /**
     * @return the urutan
     */
    public int getUrutan() {
        return urutan;
    }

    /**
     * @param urutan the urutan to set
     */
    public void setUrutan(int urutan) {
        this.urutan = urutan;
    }

    /**
     * @return the jenismsg
     */
    public String getJenismsg() {
        return jenismsg;
    }

    /**
     * @param jenismsg the jenismsg to set
     */
    public void setJenismsg(String jenismsg) {
        this.jenismsg = jenismsg;
    }

    /**
     * @return the jenis
     */
    public String getJenis() {
        return jenis;
    }

    /**
     * @param jenis the jenis to set
     */
    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    /**
     * @return the statusStart
     */
    public boolean isStatusStart() {
        return statusStart;
    }

    /**
     * @param statusStart the statusStart to set
     */
    public void setStatusStart(boolean statusStart) {
        this.statusStart = statusStart;
    }

    /**
     * @return the headerMessageType
     */
    public int getHeaderMessageType() {
        return headerMessageType;
    }

    /**
     * @param headerMessageType the headerMessageType to set
     */
    public void setHeaderMessageType(int headerMessageType) {
        this.headerMessageType = headerMessageType;
    }

    /**
     * @return the bankCode
     */
    public String getBankCode() {
        return bankCode;
    }

    /**
     * @param bankCode the bankCode to set
     */
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    /**
     * @return the lengthIncl
     */
    public boolean isLengthIncl() {
        return lengthIncl;
    }

    /**
     * @param lengthIncl the lengthIncl to set
     */
    public void setLengthIncl(boolean lengthIncl) {
        this.lengthIncl = lengthIncl;
    }

    /**
     * @return the signon
     */
    public boolean isSignon() {
        return signon;
    }

    /**
     * @param signon the signon to set
     */
    public void setSignon(boolean signon) {
        this.signon = signon;
    }

    /**
     * @return the autosignon
     */
    public boolean isAutosignon() {
        return autosignon;
    }

    /**
     * @param autosignon the autosignon to set
     */
    public void setAutosignon(boolean autosignon) {
        this.autosignon = autosignon;
    }

    /**
     * @return the signOnMessage
     */
    public String getSignOnMessage() {
        return signOnMessage;
    }

    /**
     * @param signOnMessage the signOnMessage to set
     */
    public void setSignOnMessage(String signOnMessage) {
        this.signOnMessage = signOnMessage;
    }

    /**
     * @return the packageName
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * @param packageName the packageName to set
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
