/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pri.vate.klas.function;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class Func2 {

    public String sendHttpRequest(String urlpath, String message) {
//        System.setProperty("sun.net.client.defaultReadTimeout", "3000");
        String result = "";
        message = "req="+message;
        message = message.replaceAll(" ", "%20");
        byte[] postData = message.getBytes(Charset.forName("UTF-8"));
        try {
//            System.out.println(urlpath + "?" + message);
            URL url = new URL(urlpath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setReadTimeout(63000);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("Content-Length", Integer.toString(postData.length));
            conn.setUseCaches(false);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.write(postData);
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                result += inputLine;
            }
            in.close();
            in = null;
            inputLine = null;

        } catch (IOException ex) {
            ex.printStackTrace();
            return "timeout";
        } 
//        System.out.println("result : " + result);
        return result;
    }
}