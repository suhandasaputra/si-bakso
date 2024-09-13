/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bo.function;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class ByteFunction {

    public byte[] concatByte(byte[] header, byte[] msg) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(header);
        outputStream.write(msg);
        byte[] c = outputStream.toByteArray();
        outputStream = null;
        return c;
    }
}
