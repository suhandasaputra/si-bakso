/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bo.function;

import com.bo.parameter.RuleNameParameter;
import java.util.HashMap;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class ISOFunction {

    public static String createMessageIDFromISOMessage(ISOMsg msg) throws ISOException {
        if (msg.getMTI().equals("0200") || msg.getMTI().equals("0210")) {
            return msg.getString(7) + msg.getString(11) + msg.getString(12) + msg.getString(13) + msg.getString(37) + "0200";
        } else if (msg.getMTI().equals("0400") || msg.getMTI().equals("0410")) {
            return msg.getString(7) + msg.getString(11) + msg.getString(12) + msg.getString(13) + msg.getString(37) + "0400";
        } else if (msg.getMTI().equals("0220") || msg.getMTI().equals("0230")) {
            return msg.getString(7) + msg.getString(11) + msg.getString(12) + msg.getString(13) + msg.getString(37) + "0220";
        } else if (msg.getMTI().equals("0100") || msg.getMTI().equals("0110")) {
            return msg.getString(7) + msg.getString(11) + msg.getString(12) + msg.getString(13) + msg.getString(37) + "0100";
        } else if (msg.getMTI().equals("9700") || msg.getMTI().equals("9710")) {
            return msg.getString(7) + msg.getString(11) + msg.getString(12) + msg.getString(13) + msg.getString(37) + "9700";
        } else if (msg.getMTI().equals("0800") || msg.getMTI().equals("0810")) {
            return msg.getString(7) + msg.getString(11) + msg.getString(70) + "0800";
        }
        return null;
    }
    
    public static String createMessageIDFromJsonRequest(HashMap input){
        return input.get(RuleNameParameter.agent_id).toString() +
                input.get(RuleNameParameter.rrn).toString() +
                input.get(RuleNameParameter.req_datetime).toString();
    }
    
    public static String setMTIISOResponse(String mti) {
        if (mti.equals("0200")) {
            return "0210";
        } else if (mti.equals("0400")) {
            return "0410";
        } else if (mti.equals("0420")) {
            return "0430";
        } else if (mti.equals("0220")) {
            return "0230";
        } else if (mti.equals("9700")) {
            return "9710";
        } else if (mti.equals("0100")) {
            return "0110";
        }
        return mti;
    }
}
