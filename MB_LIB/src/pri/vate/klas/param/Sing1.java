/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pri.vate.klas.param;


/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class Sing1 {


    public static Sing1 getInstance() {
        if (getRef() == null) {
//            System.out.println("getInstance");
            setRef(new Sing1());
        }
        return getRef();
    }

    /**
     * @return the ref
     */
    public static Sing1 getRef() {
        return ref;
    }

    /**
     * @param aRef the ref to set
     */
    public static void setRef(Sing1 aRef) {
        ref = aRef;
    }

    public Sing1(){
        
    }

   
    private static Sing1 ref = null;
    private String ak = "m4t4J4r!";
    private String bk = "@-P0S-@-";
    private String ck = "*45~123^";

    /**
     * @return the ak
     */
    public String getAk() {
        return ak;
    }

    /**
     * @param ak the ak to set
     */
    public void setAk(String ak) {
        this.ak = ak;
    }

    /**
     * @return the bk
     */
    public String getBk() {
        return bk;
    }

    /**
     * @param bk the bk to set
     */
    public void setBk(String bk) {
        this.bk = bk;
    }

    /**
     * @return the ck
     */
    public String getCk() {
        return ck;
    }

    /**
     * @param ck the ck to set
     */
    public void setCk(String ck) {
        this.ck = ck;
    }

   
}
