package com.bo.singleton;

import com.bo.entity.SocketConnectionEntity;
import com.bo.parameter.RuleNameParameter;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SocketConnectionSingleton {
//    private static Logger log = Logger.getLogger(SocketConnectionSingleton.class);

    private static SocketConnectionSingleton ref;

    /**
     * @return the ref
     */
    public static SocketConnectionSingleton getRef() {
        return ref;
    }
    private List<String> listOfAbdConnection = new ArrayList<String>();
    private List<String> listOfBankConnection = new ArrayList<String>();
    private HashMap<String, SocketConnectionEntity> abdConnectionMap = new HashMap<String, SocketConnectionEntity>();
    private HashMap<String, SocketConnectionEntity> BankConnectionMap = new HashMap<String, SocketConnectionEntity>();
    private int abdNextPort = 0;
    private int BankNextPort = 0;
    private int lastUsedBankConnectionOut = 0;
    private int lastUsedApigenConnectionOut = 0;
    private HashMap webConn;

    public static void setRef(SocketConnectionSingleton aRef) {
        ref = aRef;
    }

    public synchronized static SocketConnectionSingleton getInstance() {
        if (getRef() == null) {
            setRef(new SocketConnectionSingleton());
        }
        return getRef();
    }


    public DataOutputStream getOutStreamFromAbdConnetion() {
        SocketConnectionSingleton listConn = SocketConnectionSingleton.getInstance();
        boolean cekall = true;
        byte a = 1;
        if (listConn.getListOfAbdConnection().size() != 0) {
            while (cekall) {
                if (getAbdNextPort() == listConn.getListOfAbdConnection().size() - 1) {
                    setAbdNextPort(-1);
                }
                setAbdNextPort(getAbdNextPort() + 1);
                if (listConn.getAbdConnectionMap().get(listConn.getListOfAbdConnection().get(getAbdNextPort())).getOutStream() != null) {
//                    System.out.println("mengirim message ke mig melalui port " + listConn.getAbdConnectionMap().get(listConn.getListOfAbdConnection().get(getAbdNextPort())).getPort());
                    cekall = false;
                    return listConn.getAbdConnectionMap().get(listConn.getListOfAbdConnection().get(getAbdNextPort())).getOutStream();
                } else {
                    if (a == listConn.getListOfAbdConnection().size()) {
                        cekall = false;
                    } else {
                        a++;
                    }
                }
            }
        }
        return null;
    }



    /**
     * @return the listOfAbdConnection
     */
    public List<String> getListOfAbdConnection() {
        return listOfAbdConnection;
    }


    /**
     * @return the abdConnectionMap
     */
    public HashMap<String, SocketConnectionEntity> getAbdConnectionMap() {
        return abdConnectionMap;
    }


    /**
     * @return the abdNextPort
     */
    public int getAbdNextPort() {
        return abdNextPort;
    }

    /**
     * @param w4NextPort the abdNextPort to set
     */
    public void setAbdNextPort(int w4NextPort) {
        this.abdNextPort = w4NextPort;
    }

    /**
     * @param listOfAbdConnection the listOfAbdConnection to set
     */
    public void setListOfAbdConnection(List<String> listOfAbdConnection) {
        this.listOfAbdConnection = listOfAbdConnection;
    }

    /**
     * @return the listOfBankConnection
     */
    public List<String> getListOfBankConnection() {
        return listOfBankConnection;
    }

    /**
     * @param listOfBankConnection the listOfBankConnection to set
     */
    public void setListOfBankConnection(List<String> listOfBankConnection) {
        this.listOfBankConnection = listOfBankConnection;
    }

    /**
     * @param w4ConnectionMap the abdConnectionMap to set
     */
    public void setAbdConnectionMap(HashMap<String, SocketConnectionEntity> w4ConnectionMap) {
        this.abdConnectionMap = w4ConnectionMap;
    }

    /**
     * @return the BankConnectionMap
     */
    public HashMap<String, SocketConnectionEntity> getBankConnectionMap() {
        return BankConnectionMap;
    }

    /**
     * @param BankConnectionMap the BankConnectionMap to set
     */
    public void setBankConnectionMap(HashMap<String, SocketConnectionEntity> BankConnectionMap) {
        this.BankConnectionMap = BankConnectionMap;
    }

    /**
     * @return the BankNextPort
     */
    public int getBankNextPort() {
        return BankNextPort;
    }

    /**
     * @param BankNextPort the BankNextPort to set
     */
    public void setBankNextPort(int BankNextPort) {
        this.BankNextPort = BankNextPort;
    }

    /**
     * @return the lastUsedBankConnectionOut
     */
    public int getLastUsedBankConnectionOut() {
        return lastUsedBankConnectionOut;
    }

    /**
     * @param lastUsedBankConnectionOut the lastUsedBankConnectionOut to set
     */
    public void setLastUsedBankConnectionOut(int lastUsedBankConnectionOut) {
        this.lastUsedBankConnectionOut = lastUsedBankConnectionOut;
    }

    /**
     * @return the lastUsedApigenConnectionOut
     */
    public int getLastUsedApigenConnectionOut() {
        return lastUsedApigenConnectionOut;
    }

    /**
     * @param lastUsedApigenConnectionOut the lastUsedApigenConnectionOut to set
     */
    public void setLastUsedApigenConnectionOut(int lastUsedApigenConnectionOut) {
        this.lastUsedApigenConnectionOut = lastUsedApigenConnectionOut;
    }

    public SocketConnectionEntity getSceConnection(String partner, String termID) {
        if (partner.equals(RuleNameParameter.yabes)){
            return getAbdConnectionMap().get(termID);
        }else if (partner.equals(RuleNameParameter.bank)){
            return getBankConnectionMap().get(termID);
        }
        return null;
    }

    public HashMap getSceHashmapConnection(String partner) {
        if (partner.equals(RuleNameParameter.yabes)){
            return getAbdConnectionMap();
        }else if (partner.equals(RuleNameParameter.bank)){
            return getBankConnectionMap();
        }
        return null;
    }

    public void destroy(){
        setRef(null);
    }

    /**
     * @return the webConn
     */
    public HashMap getWebConn() {
        return webConn;
    }

    /**
     * @param webConn the webConn to set
     */
    public void setWebConn(HashMap webConn) {
        this.webConn = webConn;
    }
}
