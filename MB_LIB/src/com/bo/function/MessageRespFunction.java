/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bo.function;

//import com.bo.database.DatabaseProcess;
import com.bo.entity.Tempmsg;
import org.apache.log4j.Logger;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class MessageRespFunction {
    private static Logger log = Logger.getLogger(MessageRespFunction.class);

//    public Tempmsg getMsgResponse(Tempmsg tempmsg) {
//        try {
//            Thread.sleep(1000);
//            DatabaseProcess dp = new DatabaseProcess();
//            int timeoutLoop = 14;
//            int loop = 0;
//            while (loop < timeoutLoop) {
//                tempmsg = dp.getMessageRespFromTempmsg(tempmsg);
//                if (tempmsg.isStatusreply()) {
//                    return tempmsg;
//                } else {
//                    loop++;
//                    Thread.sleep(2000);
//                }
//            }
//        } catch (InterruptedException ex) {
//            log.error(ex.getMessage());
//        }
//        return tempmsg;
//    }
}
