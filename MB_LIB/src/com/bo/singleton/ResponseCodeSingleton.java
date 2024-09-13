/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bo.singleton;

import com.bo.entity.ResponseCodeEntity;
import java.util.HashMap;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class ResponseCodeSingleton {

    private static ResponseCodeSingleton log = null;
    private HashMap responseCode;

    public static ResponseCodeSingleton getInstance() {
        if (log == null) {
            log = new ResponseCodeSingleton();
        }

        return log;
    }
    public ResponseCodeSingleton() {
        responseCode = new HashMap();
        responseCode.put("00", new ResponseCodeEntity("0000", "Successful"));
        responseCode.put("01", new ResponseCodeEntity("8318", "Refer to issuer"));
        responseCode.put("02", new ResponseCodeEntity("9999", ""));
        responseCode.put("2", new ResponseCodeEntity("0001", "failed"));
        responseCode.put("03", new ResponseCodeEntity("8037", "Merchant  is not recognized"));
        responseCode.put("04", new ResponseCodeEntity("8001", "Capture card / pick up card"));
        responseCode.put("05", new ResponseCodeEntity("8001", "Do not honor"));
        responseCode.put("06", new ResponseCodeEntity("8001", "System Error"));
        responseCode.put("07", new ResponseCodeEntity("9999", ""));
        responseCode.put("08", new ResponseCodeEntity("9999", ""));
        responseCode.put("09", new ResponseCodeEntity("9999", ""));
        responseCode.put("10", new ResponseCodeEntity("9999", ""));
        responseCode.put("11", new ResponseCodeEntity("9999", ""));
        responseCode.put("12", new ResponseCodeEntity("8001", "Unknown journal code in liability transaction!"));
        responseCode.put("13", new ResponseCodeEntity("8803", "Exceed transaction amount limit"));
        responseCode.put("14", new ResponseCodeEntity("0014", "Unkown Subcriber"));
        responseCode.put("15", new ResponseCodeEntity("8001", "No such issuer"));
        responseCode.put("16", new ResponseCodeEntity("9999", ""));
        responseCode.put("17", new ResponseCodeEntity("9999", ""));
        responseCode.put("18", new ResponseCodeEntity("9999", ""));
        responseCode.put("19", new ResponseCodeEntity("9999", ""));
        responseCode.put("20", new ResponseCodeEntity("8001", "Invalid response"));
        responseCode.put("21", new ResponseCodeEntity("9999", ""));
        responseCode.put("22", new ResponseCodeEntity("9999", ""));
        responseCode.put("23", new ResponseCodeEntity("9999", ""));
        responseCode.put("24", new ResponseCodeEntity("9999", ""));
        responseCode.put("25", new ResponseCodeEntity("9999", ""));
        responseCode.put("26", new ResponseCodeEntity("9999", ""));
        responseCode.put("27", new ResponseCodeEntity("9999", ""));
        responseCode.put("28", new ResponseCodeEntity("9999", ""));
        responseCode.put("29", new ResponseCodeEntity("9999", ""));
        responseCode.put("30", new ResponseCodeEntity("8302", "Format salah"));
        responseCode.put("31", new ResponseCodeEntity("8002", "Destination bank  is not registered"));
        responseCode.put("32", new ResponseCodeEntity("9999", ""));
        responseCode.put("33", new ResponseCodeEntity("8316", "Expired card"));
        responseCode.put("34", new ResponseCodeEntity("9999", ""));
        responseCode.put("36", new ResponseCodeEntity("8001", "Restricted card"));
        responseCode.put("35", new ResponseCodeEntity("9999", ""));
        responseCode.put("38", new ResponseCodeEntity("8001", "Allowable PIN tries exceeded"));
        responseCode.put("39", new ResponseCodeEntity("8001", "No credit card"));
        responseCode.put("40", new ResponseCodeEntity("8001", "Unregistered product"));
        responseCode.put("41", new ResponseCodeEntity("8312", "Lost card"));
        responseCode.put("42", new ResponseCodeEntity("9999", ""));
        responseCode.put("43", new ResponseCodeEntity("8315", "Stolen card"));
        responseCode.put("44", new ResponseCodeEntity("9999", ""));
        responseCode.put("45", new ResponseCodeEntity("9999", ""));
        responseCode.put("46", new ResponseCodeEntity("9999", ""));
        responseCode.put("47", new ResponseCodeEntity("9999", ""));
        responseCode.put("48", new ResponseCodeEntity("9999", ""));
        responseCode.put("49", new ResponseCodeEntity("9999", ""));
        responseCode.put("50", new ResponseCodeEntity("9999", ""));
        responseCode.put("51", new ResponseCodeEntity("8801", "Insufficient fund"));
        responseCode.put("52", new ResponseCodeEntity("8001", "No chequing account"));
        responseCode.put("53", new ResponseCodeEntity("8042", "Invalid from saving account"));
        responseCode.put("54", new ResponseCodeEntity("8316", "Expired card"));
        responseCode.put("55", new ResponseCodeEntity("8410", "Invalid PIN"));
        responseCode.put("56", new ResponseCodeEntity("9999", ""));
        responseCode.put("57", new ResponseCodeEntity("8003", "Transaction not permitted to cardholder"));
        responseCode.put("58", new ResponseCodeEntity("8001", "Transaction not permitted to terminal"));
        responseCode.put("59", new ResponseCodeEntity("9999", ""));
        responseCode.put("60", new ResponseCodeEntity("9999", ""));
        responseCode.put("61", new ResponseCodeEntity("8802", "Exceed withdrawl / transfer amount limit"));
        responseCode.put("62", new ResponseCodeEntity("9302", "Sender Card is already registered"));
        responseCode.put("63", new ResponseCodeEntity("8001", "Security violation"));
        responseCode.put("64", new ResponseCodeEntity("9999", ""));
        responseCode.put("65", new ResponseCodeEntity("8812", "Exceed withdrawl / transfer frequency limit"));
        responseCode.put("66", new ResponseCodeEntity("9999", ""));
        responseCode.put("67", new ResponseCodeEntity("8001", "Hard capture"));
        responseCode.put("68", new ResponseCodeEntity("8001", "Time out"));
        responseCode.put("69", new ResponseCodeEntity("8001", "Waiting"));
        responseCode.put("70", new ResponseCodeEntity("8001", "Reserved for ISO use"));
//        responseCode.put("71", new ResponseCodeEntity("8001", "Reserved for ISO use"));
        responseCode.put("72", new ResponseCodeEntity("72", "Voucher Not Available"));
        responseCode.put("73", new ResponseCodeEntity("8001", "Reserved for ISO use"));
        responseCode.put("74", new ResponseCodeEntity("8001", "Reserved for ISO use"));
        responseCode.put("75", new ResponseCodeEntity("8411", "Invalid PIN last retry"));
        responseCode.put("76", new ResponseCodeEntity("8035", "Invalid to account"));
        responseCode.put("77", new ResponseCodeEntity("8038", "Invalid account"));
        responseCode.put("78", new ResponseCodeEntity("8039", "Closed account"));
        responseCode.put("79", new ResponseCodeEntity("8001", "Reserved for private use"));
        responseCode.put("80", new ResponseCodeEntity("8001", "Reserved for private use"));
        responseCode.put("81", new ResponseCodeEntity("8001", "Reserved for private use"));
        responseCode.put("82", new ResponseCodeEntity("8001", "Reserved for private use"));
        responseCode.put("83", new ResponseCodeEntity("8001", "Reserved for private use"));
        responseCode.put("84", new ResponseCodeEntity("8001", "Reserved for private use"));
        responseCode.put("85", new ResponseCodeEntity("8001", "Reserved for private use"));
        responseCode.put("86", new ResponseCodeEntity("8001", "Reserved for private use"));
        responseCode.put("87", new ResponseCodeEntity("8001", "Reserved for private use"));
        responseCode.put("88", new ResponseCodeEntity("8001", "Reserved for private use"));
        responseCode.put("89", new ResponseCodeEntity("8001", "Reserved for private use"));
        responseCode.put("90", new ResponseCodeEntity("9999", ""));
        responseCode.put("91", new ResponseCodeEntity("8001", "issuer or switch is inoperative"));
        responseCode.put("92", new ResponseCodeEntity("8001", "Unable to route transaction"));
        responseCode.put("93", new ResponseCodeEntity("9999", ""));
        responseCode.put("94", new ResponseCodeEntity("8001", "Duplicate transmision / request message"));
        responseCode.put("95", new ResponseCodeEntity("9999", ""));
        responseCode.put("96", new ResponseCodeEntity("8001", "System malfunction / system error"));
        responseCode.put("97", new ResponseCodeEntity("8001", "Reserved for national use"));
        responseCode.put("98", new ResponseCodeEntity("8001", "Reserved for national use"));
        responseCode.put("99", new ResponseCodeEntity("8001", "Reserved for national use"));
        responseCode.put("0000", new ResponseCodeEntity("0000", "Successful"));
        responseCode.put("9999", new ResponseCodeEntity("0068", "Time out"));
        responseCode.put("300", new ResponseCodeEntity("0053", "Invalid from saving account"));
        responseCode.put("404", new ResponseCodeEntity("0040", "PRODUCT NOT AVAILABLE"));
        responseCode.put("405", new ResponseCodeEntity("0040", "PRODUCT NOT AVAILABLE"));
        responseCode.put("040", new ResponseCodeEntity("0041", "BILLER SYSTEM MALFUNCTION"));
        responseCode.put("403", new ResponseCodeEntity("0013", "Invalid transaction amount"));
        responseCode.put("210", new ResponseCodeEntity("0094", "Duplicate request message"));
        responseCode.put("205", new ResponseCodeEntity("0030", "Invalid message"));
        responseCode.put("0", new ResponseCodeEntity("0000", "Successful"));
        responseCode.put("1", new ResponseCodeEntity("0001", "failed"));
        //------------------ Indomog -----------------------
        responseCode.put("00", new ResponseCodeEntity("0000", "Successful"));
        responseCode.put("100", new ResponseCodeEntity("0001", "Failed"));
        responseCode.put("101", new ResponseCodeEntity("0040", "Product not available"));
        responseCode.put("103", new ResponseCodeEntity("0040", "Your Unique Request ID is not Found"));
        responseCode.put("105", new ResponseCodeEntity("0040", "Invalid Request Code"));
        responseCode.put("106", new ResponseCodeEntity("0040", "Indomog Member ID is not Registered"));
        responseCode.put("116", new ResponseCodeEntity("0040", "Invalid EmailHP"));
        responseCode.put("117", new ResponseCodeEntity("0040", "It must be filled by HP"));
        responseCode.put("118", new ResponseCodeEntity("0040", "Quantity must be 1 or more "));
        responseCode.put("119", new ResponseCodeEntity("0040", "QID must be filled"));
        responseCode.put("120", new ResponseCodeEntity("0040", "QID had been existing"));
        responseCode.put("302", new ResponseCodeEntity("0040", "Invalid Signature"));
        responseCode.put("304", new ResponseCodeEntity("0040", "Invalid RMID"));
        responseCode.put("901", new ResponseCodeEntity("0040", "Voucher or Stock is not Available"));
        responseCode.put("904", new ResponseCodeEntity("0040", "Balance is not Enough"));
        //------------------ MKM -----------------------
        responseCode.put("0001", new ResponseCodeEntity("0001", "failed"));
        responseCode.put("0000", new ResponseCodeEntity("0000", "Succesful"));
        responseCode.put("0002", new ResponseCodeEntity("0001", "failed"));
        responseCode.put("0003", new ResponseCodeEntity("0001", "failed"));
        responseCode.put("0004", new ResponseCodeEntity("0001", "failed"));
        responseCode.put("0005", new ResponseCodeEntity("0001", "failed"));
        responseCode.put("0006", new ResponseCodeEntity("0001", "failed"));
        responseCode.put("0008", new ResponseCodeEntity("0008", "TAGIHAN SUDAH TERBAYAR"));
        responseCode.put("0011", new ResponseCodeEntity("0011", "Need to sign-on"));
        responseCode.put("0012", new ResponseCodeEntity("0012", "Settlement had been done"));
        responseCode.put("0013", new ResponseCodeEntity("0013", "Invalid transaction amount"));
        responseCode.put("0014", new ResponseCodeEntity("0014", "Unknown subscriber"));
        responseCode.put("0015", new ResponseCodeEntity("0015", "NOMOR METER YANG ANDA MASUKKAN SALAH, MOHON TELITI KEMBALI"));
        responseCode.put("0016", new ResponseCodeEntity("0016", "Customer ID is blocked"));
        responseCode.put("0020", new ResponseCodeEntity("0020", "Invalid Response"));
        responseCode.put("0021", new ResponseCodeEntity("0001", "failed"));
        responseCode.put("0022", new ResponseCodeEntity("0001", "failed"));
        responseCode.put("0023", new ResponseCodeEntity("0001", "failed"));
        responseCode.put("0025", new ResponseCodeEntity("0001", "failed"));
        responseCode.put("0026", new ResponseCodeEntity("0001", "failed"));
        responseCode.put("0030", new ResponseCodeEntity("0030", "Invalid message"));
        responseCode.put("0031", new ResponseCodeEntity("0031", "Unregistered bank code"));
        responseCode.put("0032", new ResponseCodeEntity("0032", "Unregistered switching"));
        responseCode.put("0033", new ResponseCodeEntity("0040", "Unregistered product"));
        responseCode.put("0040", new ResponseCodeEntity("0041", "Unregistered Product"));
        responseCode.put("0047", new ResponseCodeEntity("0047", "Total kWh Melebihi Batas Maksimum"));
        responseCode.put("0063", new ResponseCodeEntity("0063", "No payment"));
        responseCode.put("0068", new ResponseCodeEntity("0068", "Timeout"));
        responseCode.put("0069", new ResponseCodeEntity("0068", "Waiting"));
        responseCode.put("0076", new ResponseCodeEntity("0076", "No payment"));
        responseCode.put("0077", new ResponseCodeEntity("0077", "Unknown ID"));
        responseCode.put("0078", new ResponseCodeEntity("0078", "No payment"));
        responseCode.put("0079", new ResponseCodeEntity("0079", "No payment"));
        responseCode.put("0080", new ResponseCodeEntity("0080", "No payment"));
        responseCode.put("0081", new ResponseCodeEntity("0081", "No payment"));
        responseCode.put("0083", new ResponseCodeEntity("0083", "Nomor pelanggan bermasalah"));
        responseCode.put("0088", new ResponseCodeEntity("0088", "Tagihan Sudah Lunas"));
        responseCode.put("0089", new ResponseCodeEntity("0089", "Current bill is not available"));
        responseCode.put("0090", new ResponseCodeEntity("0090", "Cut-off is in progress"));
        responseCode.put("0091", new ResponseCodeEntity("0091", "No payment"));
        responseCode.put("0092", new ResponseCodeEntity("0092", "Switcher receipt reference number is not available"));
        responseCode.put("0093", new ResponseCodeEntity("0093", "Invalid switcher reference number"));
        responseCode.put("0094", new ResponseCodeEntity("0094", "Advice had been done"));
        responseCode.put("0095", new ResponseCodeEntity("0095", "No payment"));
        responseCode.put("0096", new ResponseCodeEntity("0096", "No payment"));
        responseCode.put("0097", new ResponseCodeEntity("0097", "Switching ID / Bank Code is not identical with inquiry"));
        responseCode.put("0098", new ResponseCodeEntity("0098", "PLN ref number is not valid"));
        responseCode.put("0104", new ResponseCodeEntity("0004", "Gagal generate SessionId"));
        responseCode.put("0105", new ResponseCodeEntity("0005", "Database biller bermasalah"));
        responseCode.put("0112", new ResponseCodeEntity("0012", "Parameter payment tidak sesuai dengan hasil inquiry"));
        responseCode.put("0113", new ResponseCodeEntity("0013", "Rupiah tagihan tidak sesuai dengan hasil inquiry"));
        responseCode.put("0115", new ResponseCodeEntity("0015", "Parameter tidak lengkap"));
        responseCode.put("0116", new ResponseCodeEntity("0016", "Gagal saat inquiry, tidak dapat melakukan payment"));
        responseCode.put("0163", new ResponseCodeEntity("0063", "Transaksi sebelumnya gagal, silahkan lakukan pembayaran kembali"));
        responseCode.put("0167", new ResponseCodeEntity("0067", "Parameter input tidak lengkap"));
        responseCode.put("0168", new ResponseCodeEntity("0068", "Connection timeout Biller"));
        responseCode.put("0169", new ResponseCodeEntity("0069", "Kode action tidak dikenal"));
        responseCode.put("0170", new ResponseCodeEntity("0040", "PRODUCT NOT AVAILABLE"));
        responseCode.put("0171", new ResponseCodeEntity("0171", "Client ID tidak terdaftar"));
        responseCode.put("0172", new ResponseCodeEntity("0072", "Quota tidak mencukupi"));
        responseCode.put("0173", new ResponseCodeEntity("0073", "Akses untuk produk ini diblok"));
        responseCode.put("0174", new ResponseCodeEntity("0074", "Akses ke H2H diblok"));
        responseCode.put("0180", new ResponseCodeEntity("0080", "Server sedang cut-off, tidak dapat payment saat ini"));
        responseCode.put("0181", new ResponseCodeEntity("0081", "Tidak dapat melakukan advice untuk nomor pelanggan ini"));
        responseCode.put("0184", new ResponseCodeEntity("0084", "Proses pelunasan sedang berlangsung"));
        responseCode.put("0185", new ResponseCodeEntity("0085", "Ada tagihan tetapi pernah tercatat lunas"));
        responseCode.put("0186", new ResponseCodeEntity("0086", "Pembayaran belum dilakukan"));
        responseCode.put("0187", new ResponseCodeEntity("0087", "Advice berhasil, Pembayaran sudah dilakukan sebelumnya"));
        responseCode.put("0188", new ResponseCodeEntity("0088", "Tagihan sudah tercatat lunas"));
        responseCode.put("0189", new ResponseCodeEntity("0089", "Detail tagihan untuk periode [YYYYmm] tidak ditemukan"));
        responseCode.put("0190", new ResponseCodeEntity("0090", "Data pelanggan tidak ditemukan"));
        responseCode.put("0191", new ResponseCodeEntity("0091", "Tanggal transaksi berbeda dengan tanggal inquiry"));
        responseCode.put("0192", new ResponseCodeEntity("0092", "SessionId tidak ditemukan"));
        responseCode.put("0193", new ResponseCodeEntity("0093", "Transaksi bermasalah, proses pembatalan sedang berlangsung"));
        responseCode.put("0194", new ResponseCodeEntity("0094", "Transaksi bermasalah dan telah dibatalkan secara otomatis"));
        responseCode.put("0195", new ResponseCodeEntity("0095", "Transaksi bermasalah, status pembatalan tidak diketahui"));
        responseCode.put("0198", new ResponseCodeEntity("0014", "Gagal, harap cek kembali data anda"));
        responseCode.put("0199", new ResponseCodeEntity("0099", "Request transaksi kembar"));
        //------------------ AVANTANA -----------------------
        responseCode.put("-1", new ResponseCodeEntity("0000", "Succesful"));
        //------------------ PESONA -----------------------
        responseCode.put("002", new ResponseCodeEntity("0003", "sucess, delivered, wait for operator status"));
        responseCode.put("000", new ResponseCodeEntity("0000", "sucess"));
        responseCode.put("201", new ResponseCodeEntity("0201", "Gagal, gangguan	operator"));
        responseCode.put("202", new ResponseCodeEntity("0202", "Gagal, gangguan server"));
        responseCode.put("203", new ResponseCodeEntity("0203", "Transaksi gagal"));
        responseCode.put("204", new ResponseCodeEntity("0204", "Gagal, account number atau nomor tujuan top up salah"));
    }

    /**
     * @return the responseCode
     */
    public HashMap getResponseCode() {
        return responseCode;
    }

    public String getResponseCode(String errcode) {

        if (responseCode.get(errcode) == null || responseCode.get(errcode) == "") {
            return "0099";
        } else {
            if (errcode.length() != 2) {
                return ((ResponseCodeEntity) responseCode.get(errcode)).getCode();
            } else {
                return errcode;
            }
        }
    }
    public String getResponseCodeDesc(String errcode) {

        if (responseCode.get(errcode) == null || responseCode.get(errcode) == "") {
            return "0099";
        } else {
            if (errcode.length() != 2) {
                return ((ResponseCodeEntity) responseCode.get(errcode)).getDesc();
            } else {
                return errcode;
            }
        }
    }
}
