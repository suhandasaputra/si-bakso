/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bopro.database;

import com.bo.function.FunctionSupportMB;
import com.bo.function.JsonProcess;
import com.bo.function.StringFunction;
import com.bopro.singleton.DatasourceEntryBackend;
import com.bo.parameter.FieldParameterMatapos;
import com.bo.parameter.RuleNameParameter;
import com.bopro.model.brand;
import com.bopro.model.brand_sales;
import com.bopro.model.business;
import com.bopro.model.categoryname;
import com.bopro.model.detailreports;
import com.bopro.model.expense_list;
import com.bopro.model.income_list;
import com.bopro.model.item;
import com.bopro.model.itemdeleted;
import com.bopro.model.itemreports;
import com.bopro.model.jurnal_list;
import com.bopro.model.merchant;
import com.bopro.model.outlet;
import com.bopro.model.outlet_sales;
import com.bopro.model.summaryproduct;
import com.bopro.model.supplier;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class BackendDBProcess {

    private static final Logger log = Logger.getLogger(BackendDBProcess.class);

    private void clearStatment(PreparedStatement stat) {
//        log.info("stat 2 : " + stat);
        if (stat != null) {
            try {
//                log.info("stat A");
                stat.clearBatch();
//                log.info("stat B");
                stat.clearParameters();
//                log.info("stat C");
                stat.close();
//                log.info("stat D");
                stat = null;
//                log.info("stat E");
            } catch (SQLException ex) {
//                log.error("clearStatment : " +ex.getMessage());
//                ex.printStackTrace();
            }
        }
    }

    private void clearDBConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException ex) {
//                log.error("clearDBConnection : "+ex.getMessage());
            }
        }
    }

    private void clearResultset(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
                rs = null;
            } catch (SQLException ex) {
//                log.error("clearResultset : "+ex.getMessage());
            }
        }
    }

    private void clearAllConnStatRS(Connection conn, PreparedStatement stat, ResultSet rs) {
        clearResultset(rs);
        clearStatment(stat);
        clearDBConnection(conn);
    }

//    public String BrandSeq() {
//        Connection conn = null;
//        PreparedStatement stat = null;
//        ResultSet rs = null;
//        try {
//            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
//            stat = conn.prepareStatement("SELECT nextval('brand_seq'::regclass)");
//            rs = stat.executeQuery();
//            while (rs.next()) {
//                return StringFunction.pad(rs.getString("nextval"), 2, "0", "Right");
//            }
//
//        } catch (SQLException ex) {
//            log.error("getListOrderTransactionPOS : " + ex.getMessage());
//        } finally {
//            clearAllConnStatRS(conn, stat, rs);
//        }
//        return "00";
//    }
    public void setNextStan() {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("SELECT count(*) as count from stanmanage where standate = current_date");
            rs = stat.executeQuery();
            while (rs.next()) {
                if (rs.getInt("count") == 0) {
                    log.info("create next stan");
                    clearStatment(stat);
                    stat = conn.prepareStatement("INSERT INTO stanmanage(standate) VALUES (current_date)");
                    stat.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage());
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
    }

    public String getNextStan() {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        int currentStan = 1;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("SELECT stanno FROM stanmanage where standate = current_date");
            rs = stat.executeQuery();
            while (rs.next()) {
                currentStan = rs.getInt("stanno");
            }
            clearStatment(stat);
            stat = conn.prepareStatement("UPDATE stanmanage SET stanno=? WHERE standate=current_date;");
            stat.setInt(1, currentStan + 1);
            stat.executeUpdate();
        } catch (SQLException ex) {
            log.error("getNextStan : " + ex.getMessage());
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return String.valueOf(currentStan);
    }

    public String update_prod_plan(String rrn) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update production_plan set status = ? where rrn = ?");
            stat.setString(1, "3");
            stat.setString(2, rrn);
            stat.executeUpdate();

            stat.close();

            stat = conn.prepareStatement("update order_tbl set status = ? where noref = ?");
            stat.setString(1, "5");
            stat.setString(2, rrn);
            stat.executeUpdate();
        } catch (SQLException ex) {
            log.error("update_prod_plan : " + ex.getMessage());
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return "success";
    }

    public String activity(String user, String activity) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("insert into activity_log (user_id, activity) values (?, ?)");
            stat.setString(1, user);
            stat.setString(2, activity);
            stat.executeUpdate();
        } catch (SQLException ex) {
            log.error(ex.getMessage());
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return "00";
    }

    public HashMap validate(String user_id, String password) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        result.put(FieldParameterMatapos.resp_code, "0001");
        result.put(FieldParameterMatapos.resp_desc, "Failed");

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            if (user_id.equals("administrator")) {
                stat = conn.prepareStatement("SELECT * FROM am_administrator WHERE user_id = ? AND password = ?");
                stat.setString(1, user_id);
                stat.setString(2, password);
                rs = stat.executeQuery();
                while (rs.next()) {
                    result.put(FieldParameterMatapos.userlevel, "administrator");
                    result.put(FieldParameterMatapos.user_id, rs.getString("user_id"));
                    result.put(FieldParameterMatapos.resp_code, "0000");
                    result.put(FieldParameterMatapos.resp_desc, "Success");
                }
            } else {
//                stat = conn.prepareStatement("select * from");
//                stat.setString(1, user_id);
//                stat.setString(2, password);
//                rs = stat.executeQuery();
//                if (rs.next()) {
//
//                }
//                stat.close();
//                rs.close();

                stat = conn.prepareStatement("SELECT * FROM am_employee WHERE user_id = ? AND password = ? AND status = '1'");
                stat.setString(1, user_id);
                stat.setString(2, password);
                rs = stat.executeQuery();
                if (rs.next()) {
                    result.put(FieldParameterMatapos.user_id, rs.getString("user_id"));
                    result.put(FieldParameterMatapos.user_name, rs.getString("user_name"));
                    switch (rs.getString("userlevel")) {
                        case "1":
                            result.put(FieldParameterMatapos.userlevel, "sales");
                            break;
                        case "2":
                            result.put(FieldParameterMatapos.userlevel, "production");
                            break;
                        case "3":
                            result.put(FieldParameterMatapos.userlevel, "ppic");
                            break;
                        case "4":
                            result.put(FieldParameterMatapos.userlevel, "owner");
                            break;
                        default:
                            break;
                    }
                    result.put(FieldParameterMatapos.resp_code, "0000");
                    result.put(FieldParameterMatapos.resp_desc, "Success");
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap addcategory(String categoryname, String jenis) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("insert into product_category (category_id, category_name, jenis) values (?, ?, ?)");
            String bahan = StringFunction.getCurrentTimeHHmmsss();
            String jadi = categoryname.substring(0, 3).toUpperCase() + bahan;
            stat.setString(1, jadi);
            stat.setString(2, categoryname.toUpperCase());
            stat.setString(3, jenis.toUpperCase());
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Failed : " + e.getMessage());
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getlistorder() {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List list = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select a.*, b.user_name as pic_name, d.user_name as approvedname, c.name as cust_name, e.status_desc from order_tbl a left join am_employee b on a.pic = b.user_id left join am_customer c on a.customer = c.phonenumber left join am_employee d on a.approvedby = d.user_id left join order_status e on a.status = e.status_id where a.status not in ('3')");
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("requesttime", rs.getString("requesttime"));
                ab.put("noref", rs.getString("noref"));
                ab.put("pic", rs.getString("pic"));
                ab.put("pic_name", rs.getString("pic_name"));
                ab.put("customer", rs.getString("customer"));
                ab.put("cust_name", rs.getString("cust_name"));
                ab.put("product", rs.getString("product"));
                ab.put("status", rs.getString("status_desc"));
                ab.put("approvedname", rs.getString("approvedname"));
                ab.put("approveddate", rs.getString("approveddate"));
                list.add(ab);
                ab = null;
            }
            result.put("resp_code", "0000");
            result.put("resp_desc", "success");
            result.put("list", list);
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", e.getMessage());
            System.out.println("error : " + e.getMessage());
        } finally {
            list = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap createorder(String customer, String userlogin, String product) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("insert into order_tbl (noref, product, pic, customer) values (?, ?, ?, ?)");
            String bahan = StringFunction.getCurrentDateYYYYMMDDHHMMSS();
            stat.setString(1, bahan);
            stat.setString(2, product);
            stat.setString(3, userlogin);
            stat.setString(4, customer);
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Failed : " + e);
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap createbatchtoprocess(String tanggal, String batch, String userlogin) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from production_batch where status = '2' and tanggal = current_date");
            rs = stat.executeQuery();
            if (rs.next()) {
                result.put(RuleNameParameter.resp_code, "0001");
                result.put(RuleNameParameter.resp_desc, "another process is in progress, please wait for it to finish");
            } else {
                stat.close();
                stat = null;
                rs.close();
                rs = null;
                stat = conn.prepareStatement("update production_batch set status = 2, approveby = ?, approvedate = ? where tanggal = ? and batch = ?");
                stat.setString(1, userlogin);
                stat.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
                stat.setTimestamp(3, Timestamp.valueOf(tanggal));
                stat.setInt(4, Integer.parseInt(batch));
                stat.executeUpdate();
                getNextStan();
                result.put(RuleNameParameter.resp_code, "0000");
                result.put(RuleNameParameter.resp_desc, "Success");
            }
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Failed : " + e);
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getlistproduction() {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List list_plan = new ArrayList();
        List list_process = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select a.*, b.status_desc from production_plan a inner join production_status b on a.status = b.status_id where status not in ('0')");
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("orderdate", rs.getString("orderdate"));
                ab.put("rrn", rs.getString("rrn"));
                ab.put("product", rs.getString("product"));
                ab.put("qty", rs.getString("qty"));
                ab.put("processby", rs.getString("processby"));
                ab.put("processdate", rs.getString("processdate"));
                ab.put("status", rs.getString("status"));
                ab.put("status_desc", rs.getString("status_desc"));
                list_plan.add(ab);
                ab = null;
            }

            stat.close();
            stat = null;
            rs.close();
            rs = null;
            stat = conn.prepareStatement("select a.*, b.stock, b.std, c.status_desc from production_batch a left join product_item b on a.product = b.productcode left join production_status c on a.status = c.status_id where a.status in ('1', '2', '3')");
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap cd = new HashMap();
                cd.put("tanggal", rs.getString("tanggal"));
                cd.put("batch", rs.getString("batch"));
                cd.put("pesanan", rs.getString("pesanan"));
                cd.put("kebutuhan", rs.getString("kebutuhan"));
                cd.put("jumlah", rs.getString("jumlah"));
                cd.put("product", rs.getString("product"));
                cd.put("stock", rs.getString("stock"));
                cd.put("std", rs.getString("std"));
                cd.put("approveby", rs.getString("approveby"));
                cd.put("approvedate", rs.getString("approvedate"));
                cd.put("status_desc", rs.getString("status_desc"));
                list_process.add(cd);
                cd = null;
            }
            result.put("resp_code", "0000");
            result.put("resp_desc", "success");
            result.put("list_plan", list_plan);
            result.put("list_process", list_process);
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", e.getMessage());
            System.out.println("error : " + e.getMessage());
        } finally {
            list_plan = null;
            list_process = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getlistproductionprocess() {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List list_process = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select a.*, c.status_desc from production_batch a left join production_status c on a.status = c.status_id where a.status in ('2', '3')");
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap cd = new HashMap();
                cd.put("tanggal", rs.getString("tanggal"));
                cd.put("batch", rs.getString("batch"));
                cd.put("jumlah", rs.getString("jumlah"));
                cd.put("product", rs.getString("product"));
                cd.put("status_desc", rs.getString("status_desc"));
                cd.put("hm", rs.getString("hm"));
                cd.put("95", rs.getString("95"));
                cd.put("tr", rs.getString("tr"));
                cd.put("mdm", rs.getString("mdm"));
                cd.put("bld", rs.getString("bld"));
                cd.put("blp", rs.getString("blp"));
                cd.put("pot", rs.getString("pot"));
                cd.put("tap", rs.getString("tap"));
                cd.put("cs", rs.getString("cs"));
                cd.put("processby", rs.getString("processby"));
                cd.put("processdate", rs.getString("processdate"));
                list_process.add(cd);
                cd = null;
            }
            result.put("resp_code", "0000");
            result.put("resp_desc", "success");
            result.put("list_process", list_process);
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", e.getMessage());
            System.out.println("error : " + e.getMessage());
        } finally {
            list_process = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getspecificproductionprocess(String user, String tanggal, String batch) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List list_process = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            System.out.println("tanggalnya : " + tanggal);
            stat = conn.prepareStatement("select * from production_batch where tanggal = ? and batch = ?");
            stat.setTimestamp(1, Timestamp.valueOf(tanggal));
            stat.setInt(2, Integer.parseInt(batch));
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap cd = new HashMap();
                cd.put("jumlah", rs.getString("jumlah"));
                cd.put("product", rs.getString("product"));
                list_process.add(cd);
                cd = null;
            }
            result.put("resp_code", "0000");
            result.put("resp_desc", "success");
            result.put("list_process", list_process);
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", e.getMessage());
            System.out.println("error : " + e.getMessage());
        } finally {
            list_process = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

//    public HashMap signupowner(String brand_category, String user_name, String brand_name, String user_id, String phonenumber, String password) {
//        HashMap result = new HashMap();
//        Connection conn = null;
//        PreparedStatement stat = null;
//        PreparedStatement stat1 = null;
//        ResultSet rs = null;
//
//        result.put(RuleNameParameter.resp_code, "0001");
//        result.put(RuleNameParameter.resp_desc, "Failed");
//        try {
//            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
//            stat = conn.prepareStatement("select * from am_employee where user_id = ?");
//            stat.setString(1, user_id);
//            rs = stat.executeQuery();
//            if (rs.next()) {
//                result.put(RuleNameParameter.resp_code, "0002");
//                result.put(RuleNameParameter.resp_desc, "Duplicate Account");
//            } else {
//                stat1 = conn.prepareStatement("insert into am_employee (user_id, password, user_name, phonenumber, status, userlevel, brand_id, outlet_id) values (?, ?, ?, ?, ?, ?, ?, ?)");
//                stat1.setString(1, user_id);
//                stat1.setString(2, password);
//                stat1.setString(3, user_name);
//                stat1.setString(4, phonenumber);
//                stat1.setString(5, "1");
//                stat1.setInt(6, 4);
//                String brandid = StringFunction.getCurrentDateYYYYMMDDHHMMSS() + BrandSeq();
//                String outletid = "01";
//                stat1.setString(7, brandid);
//                stat1.setString(8, "01");
//                stat1.executeUpdate();
//                stat1.close();
//                stat1 = conn.prepareStatement("insert into am_brand (brand_id, brand_name, status, brand_level, business_id) values (?, ?, ?, ?, ?)");
//                stat1.setString(1, brandid);
//                stat1.setString(2, brand_name);
//                stat1.setString(3, "1");
//                stat1.setString(4, "0");
//                stat1.setString(5, brand_category);
//                stat1.executeUpdate();
//                stat1.close();
//                stat1 = conn.prepareStatement("insert into am_outlet (brand_id, outlet_id, outlet_name, alamat, header_receipt, footer_receipt, info, shifting) values (?, ?, ?, ?, ?, ?, ?, ?)");
//                stat1.setString(1, brandid);
//                stat1.setString(2, "01");
//                stat1.setString(3, "Sample Outlet Name");
//                stat1.setString(4, "Sample Alamat Outlet");
//                stat1.setString(5, "Sample Header Receipt Outlet");
//                stat1.setString(6, "Sample Footer Receipt Outlet");
//                stat1.setString(7, "Sample Info Outlet");
//                stat1.setString(8, "0");
//                stat1.executeUpdate();
//                stat.close();
//                stat1 = conn.prepareStatement("insert into am_balance (agent_id) values (?)");
//                stat1.setString(1, brandid + outletid);
//                stat1.executeUpdate();
//                result.put(RuleNameParameter.resp_code, "0000");
//                result.put(RuleNameParameter.resp_desc, "Success");
//
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//            return result;
//        } finally {
//            clearAllConnStatRS(conn, stat, rs);
//            clearStatment(stat1);
//        }
//        return result;
//    }
    public HashMap getlistcategory(String userlevel) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List listcate = new ArrayList();
        List listitem = new ArrayList();
        List listdeleteitem = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from product_category where status = '1'");
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("create_date", rs.getString("create_date"));
                ab.put("category_id", rs.getString("category_id"));
                ab.put("category_name", rs.getString("category_name"));
                ab.put("jenis", rs.getString("jenis"));
                listcate.add(ab);
                ab = null;
            }
            result.put("listcate", listcate);

            stat.close();
            stat = null;
            rs.close();
            rs = null;

            stat = conn.prepareStatement("select a.*, b.category_id, b.category_name from product_item a inner join product_category b on a.category = b.category_id where a.status = '1'");
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap cd = new HashMap();
                cd.put("createdate", rs.getString("createdate"));
                cd.put("img_url", rs.getString("img_url"));

                cd.put("productcode", rs.getString("productcode"));
                cd.put("productname", rs.getString("productname"));
                cd.put("category_id", rs.getString("category_id"));
                cd.put("category_name", rs.getString("category_name"));
                cd.put("std", rs.getString("std"));
                listitem.add(cd);
                cd = null;
            }
            result.put("listitem", listitem);

            stat.close();
            stat = null;
            rs.close();
            rs = null;

            stat = conn.prepareStatement("select * from product_item where status = '0'");
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ef = new HashMap();
                ef.put("createdate", rs.getString("createdate"));
                ef.put("deletedate", rs.getString("deletedate"));
                ef.put("productcode", rs.getString("productcode"));
                ef.put("productname", rs.getString("productname"));
                ef.put("category", rs.getString("category"));
                ef.put("std", rs.getString("std"));
                listdeleteitem.add(ef);
                ef = null;
            }
            result.put("listdeleteitem", listdeleteitem);

            result.put("resp_code", "0000");
            result.put("resp_desc", "success");
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", e);
            System.out.println("error : " + e);
        } finally {
            listitem = null;
            listdeleteitem = null;
            listcate = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public ArrayList<item> getlistitem(String brand_id, String outlet_id, String userlevel) {
        ArrayList<item> ppobList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            if (userlevel.equals("owner")) {
                stat = conn.prepareStatement("select * from product_master where brand_id = ? and available in ('0', '1')");
                stat.setString(1, brand_id);
                rs = stat.executeQuery();
                while (rs.next()) {

                    item ab = new item();
                    ab.setOutlet_id(rs.getString("outlet_id"));
                    ab.setProductcode(rs.getString("productcode"));
                    ab.setBarcode(rs.getString("code"));
                    if ("-".equals(rs.getString("img_url"))) {
                        ab.setImg_url("http://103.146.62.54:90/img/no_image.png");
                    } else {
                        ab.setImg_url(rs.getString("img_url"));
                    }
                    ab.setProductname(rs.getString("productname"));
                    ab.setCategory(rs.getString("category"));
                    ab.setStd(rs.getString("std"));
                    ab.setDetailproduct(rs.getString("detailproduct"));
                    ab.setReceive_stock(rs.getString("receive_stock"));
                    ab.setAvailable(rs.getString("available"));
                    ab.setStock(rs.getString("stock"));
                    ppobList.add(ab);
                }
            } else {
                stat = conn.prepareStatement("select * from product_master where brand_id = ? and outlet_id = ? and available in ('0', '1')");
                stat.setString(1, brand_id);
                stat.setString(2, outlet_id);
                rs = stat.executeQuery();
                while (rs.next()) {
                    item ab = new item();
                    ab.setOutlet_id(rs.getString("outlet_id"));
                    ab.setProductcode(rs.getString("productcode"));
                    ab.setBarcode(rs.getString("code"));
                    if ("-".equals(rs.getString("img_url"))) {
                        ab.setImg_url("http://103.146.62.54:90/img/no_image.png");
                    } else {
                        ab.setImg_url(rs.getString("img_url"));
                    }
                    ab.setProductname(rs.getString("productname"));
                    ab.setCategory(rs.getString("category"));
                    ab.setStd(rs.getString("std"));
                    ab.setDetailproduct(rs.getString("detailproduct"));
                    ab.setReceive_stock(rs.getString("receive_stock"));
                    ab.setAvailable(rs.getString("available"));
                    ab.setStock(rs.getString("stock"));
                    ppobList.add(ab);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            ppobList = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return ppobList;
    }

    public ArrayList<itemdeleted> getlistitemdeleted(String brand_id, String outlet_id, String userlevel) {
        ArrayList<itemdeleted> ppobList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            if (userlevel.equals("owner")) {
                stat = conn.prepareStatement("select * from product_master where brand_id = ? and available = ?");
                stat.setString(1, brand_id);
                stat.setString(2, "2");
                rs = stat.executeQuery();
                while (rs.next()) {
                    itemdeleted ab = new itemdeleted();
                    ab.setProductcode(rs.getString("productcode"));
                    ab.setBarcode(rs.getString("code"));
                    if ("-".equals(rs.getString("img_url"))) {
                        ab.setImg_url("http://103.146.62.54:90/img/no_image.png");
                    } else {
                        ab.setImg_url(rs.getString("img_url"));
                    }
                    ab.setProductname(rs.getString("productname"));
                    ab.setCategory(rs.getString("category"));
                    ab.setStd(rs.getString("std"));
                    ab.setDetailproduct(rs.getString("detailproduct"));
                    ab.setStock(rs.getString("stock"));
                    ppobList.add(ab);
                }
            } else {
                stat = conn.prepareStatement("select * from product_master where brand_id = ? and available = ? and outlet_id = ?");
                stat.setString(1, brand_id);
                stat.setString(2, "2");
                stat.setString(3, outlet_id);
                rs = stat.executeQuery();
                while (rs.next()) {
                    itemdeleted ab = new itemdeleted();
                    ab.setProductcode(rs.getString("productcode"));
                    ab.setBarcode(rs.getString("code"));
                    if ("-".equals(rs.getString("img_url"))) {
                        ab.setImg_url("http://103.146.62.54:90/img/no_image.png");
                    } else {
                        ab.setImg_url(rs.getString("img_url"));
                    }
                    ab.setProductname(rs.getString("productname"));
                    ab.setCategory(rs.getString("category"));
                    ab.setStd(rs.getString("std"));
                    ab.setDetailproduct(rs.getString("detailproduct"));
                    ab.setStock(rs.getString("stock"));
                    ppobList.add(ab);
                }
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            ppobList = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return ppobList;
    }

    public HashMap changestatusreceive(String brand_id, String outlet_id, String productcode) {
        HashMap result = new HashMap();
//        Connection conn = null;
//        PreparedStatement stat = null;
//        ResultSet rs = null;
//        PreparedStatement stat1 = null;
//
//        result.put(RuleNameParameter.resp_code, "0001");
//        result.put(RuleNameParameter.resp_desc, "Failed");
//        try {
//            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
//            stat = conn.prepareStatement("select receive_stock from product_master where brand_id = ? and outlet_id = ? and productcode = ?");
//            stat.setString(1, brand_id);
//            stat.setString(2, outlet_id);
//            stat.setString(3, productcode);
//            rs = stat.executeQuery();
//            while (rs.next()) {
//                String receive_stock = rs.getString("receive_stock");
//                if ("1".equals(receive_stock)) {
//                    stat1 = conn.prepareStatement("update product_master set receive_stock = ? where brand_id = ? and outlet_id = ? and productcode = ?");
//                    stat1.setString(1, "0");
//                    stat1.setString(2, brand_id);
//                    stat1.setString(3, outlet_id);
//                    stat1.setString(4, productcode);
//                    stat1.executeUpdate();
//                    result.put(RuleNameParameter.resp_code, "0000");
//                    result.put(RuleNameParameter.resp_desc, "Success");
//                } else if ("0".equals(receive_stock)) {
//                    stat1 = conn.prepareStatement("update product_master set receive_stock = ? where brand_id = ? and outlet_id = ? and productcode = ?");
//                    stat1.setString(1, "1");
//                    stat1.setString(2, brand_id);
//                    stat1.setString(3, outlet_id);
//                    stat1.setString(4, productcode);
//                    stat1.executeUpdate();
//                    result.put(RuleNameParameter.resp_code, "0000");
//                    result.put(RuleNameParameter.resp_desc, "Success");
//                }
//
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//            return result;
//        } finally {
//            clearAllConnStatRS(conn, stat, rs);
//            clearAllConnStatRS(conn, stat1, rs);
//        }
        return result;
    }

    public HashMap changestatusavailable(String brand_id, String outlet_id, String productcode) {
        HashMap result = new HashMap();
//        Connection conn = null;
//        PreparedStatement stat = null;
//        ResultSet rs = null;
//        PreparedStatement stat1 = null;
//
//        result.put(RuleNameParameter.resp_code, "0001");
//        result.put(RuleNameParameter.resp_desc, "Failed");
//        try {
//            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
//            stat = conn.prepareStatement("select available from product_master where brand_id = ? and outlet_id = ? and productcode = ?");
//            stat.setString(1, brand_id);
//            stat.setString(2, outlet_id);
//            stat.setString(3, productcode);
//            rs = stat.executeQuery();
//            while (rs.next()) {
//                String available = rs.getString("available");
//                if ("1".equals(available)) {
//                    stat1 = conn.prepareStatement("update product_master set available = ? where brand_id = ? and outlet_id = ? and productcode = ?");
//                    stat1.setString(1, "0");
//                    stat1.setString(2, brand_id);
//                    stat1.setString(3, outlet_id);
//                    stat1.setString(4, productcode);
//                    stat1.executeUpdate();
//                    result.put(RuleNameParameter.resp_code, "0000");
//                    result.put(RuleNameParameter.resp_desc, "Success");
//                } else if ("0".equals(available)) {
//                    stat1 = conn.prepareStatement("update product_master set available = ? where brand_id = ? and outlet_id = ? and productcode = ?");
//                    stat1.setString(1, "1");
//                    stat1.setString(2, brand_id);
//                    stat1.setString(3, outlet_id);
//                    stat1.setString(4, productcode);
//                    stat1.executeUpdate();
//                    result.put(RuleNameParameter.resp_code, "0000");
//                    result.put(RuleNameParameter.resp_desc, "Success");
//                }
//
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//            e.printStackTrace();
//            return result;
//        } finally {
//            clearAllConnStatRS(conn, stat, rs);
//            clearAllConnStatRS(conn, stat1, rs);
//        }
        return result;
    }

    public ArrayList<categoryname> getlistcategoryname(String brand_id) {
        ArrayList<categoryname> ppobList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select category_name from product_category where brand_id = ?");
            stat.setString(1, brand_id);
            rs = stat.executeQuery();
            while (rs.next()) {
                categoryname ab = new categoryname();
                ab.setCategory_name(rs.getString("category_name"));
                ppobList.add(ab);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            ppobList = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return ppobList;
    }

    public HashMap additem(String category, String product_code, String product_name, String image, String std) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("insert into product_item (category, productcode, productname, img_url, std) values (?, ?, ?, ?, ?)");
            stat.setString(1, category.toUpperCase());
            stat.setString(2, product_code.toUpperCase());
            stat.setString(3, product_name.toUpperCase());
            stat.setString(4, image);
            stat.setInt(5, Integer.parseInt(std));
            stat.executeUpdate();

            stat.close();

            stat = conn.prepareStatement("insert into bumbu (product) values (?)");
            stat.setString(1, product_code.toUpperCase());
            stat.executeUpdate();

            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, e.getMessage());
            System.out.println(e);
            return result;
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public ArrayList<outlet> getlistoutlet(String brand_id) {
        ArrayList<outlet> ppobList = new ArrayList<>();
        ppobList = null;
//        Connection conn = null;
//        PreparedStatement stat = null;
//        PreparedStatement stat1 = null;
//        ResultSet rs = null;
//        ResultSet rs1 = null;
//        try {
//            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
//            stat = conn.prepareStatement("select * from am_outlet where brand_id = ? and outlet_status = ?");
//            stat.setString(1, brand_id);
//            stat.setString(2, "1");
//
//            rs = stat.executeQuery();
//            while (rs.next()) {
//                outlet ab = new outlet();
//                ab.setOutlet_id(brand_id + rs.getString("outlet_id"));
//                ab.setOutlet_name(rs.getString("outlet_name"));
//                ab.setAlamat(rs.getString("alamat"));
////                ab.setEdit("-");
//                ppobList.add(ab);
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        } finally {
//            clearAllConnStatRS(conn, stat, rs);
//            clearAllConnStatRS(conn, stat1, rs1);
//        }
        return ppobList;
    }

    public ArrayList<outlet> getoutlet(String brand_id, String userlevel, String outlet_id) {
        ArrayList<outlet> ppobList = new ArrayList<>();
        ppobList = null;

//        Connection conn = null;
//        PreparedStatement stat = null;
//        PreparedStatement stat1 = null;
//        ResultSet rs = null;
//        ResultSet rs1 = null;
//        try {
//            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
//            if (userlevel.equals("owner")) {
//                stat = conn.prepareStatement("select * from am_outlet where brand_id = ? and outlet_status = ?");
//                stat.setString(1, brand_id);
//                stat.setString(2, "1");
//                rs = stat.executeQuery();
//                while (rs.next()) {
//                    outlet ab = new outlet();
//                    ab.setOutlet_id(brand_id + rs.getString("outlet_id"));
//                    ab.setOutlet_name(rs.getString("outlet_name"));
//                    ab.setAlamat(rs.getString("alamat"));
//                    ab.setBalance(rs.getString("total_trx"));
//                    ppobList.add(ab);
//                }
//            } else {
//                stat = conn.prepareStatement("select * from am_outlet where brand_id = ? and outlet_id = ? and outlet_status = ?");
//                stat.setString(1, brand_id);
//                stat.setString(2, outlet_id);
//                stat.setString(3, "1");
//                rs = stat.executeQuery();
//                while (rs.next()) {
//                    outlet ab = new outlet();
//                    ab.setOutlet_id(brand_id + rs.getString("outlet_id"));
//                    ab.setOutlet_name(rs.getString("outlet_name"));
//                    ab.setAlamat(rs.getString("alamat"));
//                    ab.setBalance(rs.getString("total_trx"));
//                    ppobList.add(ab);
//                }
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        } finally {
//            clearAllConnStatRS(conn, stat, rs);
//            clearAllConnStatRS(conn, stat1, rs1);
//        }
        return ppobList;
    }

    public ArrayList<detailreports> getlistdetailreport(String brand_id, String outlet_id, String tanggal_from, String tanggal_to) {
        ArrayList<detailreports> ppobList = new ArrayList<>();
        ppobList = null;
//        Connection conn = null;
//        PreparedStatement stat = null;
//        ResultSet rs = null;
//        try {
//            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
//            if (outlet_id.equals("")) {
//                stat = conn.prepareStatement("select outlet_id,status_void, detail, status_paid, noresi, dt_internal, item_purchase, customer_name,amount,tax,payment_method\n"
//                        + "from log_trx where brand_id = ? and status_paid = '1' and proc_code not in ('70', '71', '72') and TO_CHAR(dt_internal, 'YYYY-MM-DD') between ? and ? union all\n"
//                        + "select outlet_id,status_void, detail, status_paid, noresi,dt_internal,item_purchase,customer_name,amount,tax,payment_method\n"
//                        + "from log_trx_backup where brand_id = ? and status_paid = '1' and proc_code not in ('70', '71', '72') and TO_CHAR(dt_internal, 'YYYY-MM-DD') between ? and ?");
//                stat.setString(1, brand_id);
//                if (tanggal_from.equals("")) {
//                    Date today = new Date();
//                    String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(today);
//                    stat.setString(2, modifiedDate);
//                } else {
//                    stat.setString(2, tanggal_from);
//                }
//                if (tanggal_to.equals("")) {
//                    Date today = new Date();
//                    String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(today);
//                    stat.setString(3, modifiedDate);
//                } else {
//                    stat.setString(3, tanggal_to);
//                }
//                stat.setString(4, brand_id);
//                if (tanggal_from.equals("")) {
//                    Date today = new Date();
//                    String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(today);
//                    stat.setString(5, modifiedDate);
//                } else {
//                    stat.setString(5, tanggal_from);
//                }
//                if (tanggal_to.equals("")) {
//                    Date today = new Date();
//                    String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(today);
//                    stat.setString(6, modifiedDate);
//                } else {
//                    stat.setString(6, tanggal_to);
//                }
//            } else {
//                stat = conn.prepareStatement("select outlet_id,status_void,detail,status_paid, noresi, dt_internal,item_purchase,customer_name,amount,tax,payment_method\n"
//                        + "from log_trx where brand_id = ? and outlet_id = ? and status_paid = '1' and proc_code not in ('70', '71', '72') and TO_CHAR(dt_internal, 'YYYY-MM-DD') between ? and ? union all\n"
//                        + "select outlet_id,status_void, detail, status_paid, noresi,dt_internal,item_purchase,customer_name,amount,tax,payment_method\n"
//                        + "from log_trx_backup where brand_id = ? and outlet_id = ? and status_paid = '1' and proc_code not in ('70', '71', '72') and TO_CHAR(dt_internal, 'YYYY-MM-DD') between ? and ?");
//                stat.setString(1, brand_id);
//                stat.setString(2, outlet_id.substring(16));
//
//                if (tanggal_from.equals("")) {
//                    Date today = new Date();
//                    String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(today);
//                    stat.setString(3, modifiedDate);
//                } else {
//                    stat.setString(3, tanggal_from);
//                }
//                if (tanggal_to.equals("")) {
//                    Date today = new Date();
//                    String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(today);
//                    stat.setString(4, modifiedDate);
//                } else {
//                    stat.setString(4, tanggal_to);
//                }
//                stat.setString(5, brand_id);
//                stat.setString(6, outlet_id.substring(16));
//                if (tanggal_from.equals("")) {
//                    Date today = new Date();
//                    String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(today);
//                    stat.setString(7, modifiedDate);
//                } else {
//                    stat.setString(7, tanggal_from);
//                }
//                if (tanggal_to.equals("")) {
//                    Date today = new Date();
//                    String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(today);
//                    stat.setString(8, modifiedDate);
//                } else {
//                    stat.setString(8, tanggal_to);
//                }
//            }
//            rs = stat.executeQuery();
//            while (rs.next()) {
//                detailreports ab = new detailreports();
//                ab.setPlus("1");
//                ab.setNoresi(rs.getString("noresi"));
//                ab.setOutlet_id(rs.getString("outlet_id"));
//                ab.setDt_internal(rs.getString("dt_internal").substring(0, 19));
//                ab.setItem_purchase(rs.getString("item_purchase"));
//                ab.setCustomer_name(rs.getString("customer_name"));
//                ab.setAmount(rs.getString("amount"));
//                ab.setTax(rs.getString("tax"));
//                ab.setPayment_method(rs.getString("payment_method"));
//                ab.setDownload("");
//                ab.setPrint("");
//                if (rs.getString("status_void").equals("0")) {
//                    ab.setStatus_void("not");
//                } else if (rs.getString("status_void").equals("1")) {
//                    ab.setStatus_void("void");
//                } else {
//                    ab.setStatus_void("-");
//                }
//                ab.setDetail(rs.getString("detail"));
//                ppobList.add(ab);
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        } finally {
//            clearAllConnStatRS(conn, stat, rs);
//        }
        return ppobList;
    }

    //detailtrx    
    public HashMap getdetailtrx(String rrn) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        result.put(FieldParameterMatapos.resp_code, "0001");
        result.put(FieldParameterMatapos.resp_desc, "Failed");

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select detail from log_trx where noresi = ? union all\n"
                    + "select detail from log_trx_backup where noresi = ? ");
            stat.setString(1, rrn);
            stat.setString(2, rrn);
            rs = stat.executeQuery();
            while (rs.next()) {
                result.put(FieldParameterMatapos.detail, rs.getString("detail"));
                result.put(FieldParameterMatapos.resp_code, "0000");
                result.put(FieldParameterMatapos.resp_desc, "Success");
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public ArrayList<itemreports> getlistitemreport(String brand_id, String outlet_id, String tanggal_from, String tanggal_to) {
        ArrayList<itemreports> ppobList = new ArrayList<itemreports>();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        PreparedStatement stat1 = null;
        ResultSet rs1 = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            if (outlet_id.equals("")) {
                stat = conn.prepareStatement("select productcode, max(std) as std, sum(total) as total, sum(sum_amount) as sum_amount, sum(tax) as tax from itemsales_detail where brand_id = ? and TO_CHAR(datecreate, 'YYYY-MM-DD') between ? and ? group by productcode order by productcode desc");
                stat.setString(1, brand_id);
                if (tanggal_from.equals("")) {
                    Date today = new Date();
                    String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(today);
                    stat.setString(2, modifiedDate);
                } else {
                    stat.setString(2, tanggal_from);
                }
                if (tanggal_to.equals("")) {
                    Date today = new Date();
                    String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(today);
                    stat.setString(3, modifiedDate);
                } else {
                    stat.setString(3, tanggal_to);
                }
            } else {
                stat = conn.prepareStatement("select productcode, max(std) as std, sum(total) as total, sum(sum_amount) as sum_amount, sum(tax) as tax from itemsales_detail where brand_id = ? and outlet_id = ? and TO_CHAR(datecreate, 'YYYY-MM-DD') between ? and ? group by productcode order by productcode desc");
                stat.setString(1, brand_id);
                stat.setString(2, outlet_id.substring(16));
                if (tanggal_from.equals("")) {
                    Date today = new Date();
                    String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(today);
                    stat.setString(3, modifiedDate);
                } else {
                    stat.setString(3, tanggal_from);
                }
                if (tanggal_to.equals("")) {
                    Date today = new Date();
                    String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(today);
                    stat.setString(4, modifiedDate);
                } else {
                    stat.setString(4, tanggal_to);
                }
            }
            rs = stat.executeQuery();
            while (rs.next()) {
                stat1 = conn.prepareStatement("select productname, category from product_master where brand_id = ? and productcode = ?");
                stat1.setString(1, brand_id);
                stat1.setString(2, rs.getString("productcode"));
                rs1 = stat1.executeQuery();
                if (rs1.next()) {
                    itemreports ab = new itemreports();
                    ab.setProductname(rs1.getString("productname"));
                    ab.setProduct_id(rs.getString("productcode"));
                    ab.setCategory(rs1.getString("category"));
                    ab.setSell_price(rs.getString("std"));
                    ab.setQuantity(rs.getString("total"));
                    ab.setTotal(rs.getString("sum_amount"));
                    ab.setTax(rs.getString("tax"));
                    ppobList.add(ab);
                } else {
                    itemreports ab = new itemreports();
                    ab.setProductname("-");
                    ab.setProduct_id(rs.getString("productcode"));
                    ab.setCategory("-");
                    ab.setSell_price(rs.getString("std"));
                    ab.setQuantity(rs.getString("total"));
                    ab.setTotal(rs.getString("sum_amount"));
                    ab.setTax(rs.getString("tax"));
                    ppobList.add(ab);
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
            clearAllConnStatRS(conn, stat1, rs1);

        }
        return ppobList;
    }

    public String listItemSales() {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select brand_id, outlet_id, count(noresi)as order, sum(total) as product_sold, sum(sum_amount)as total from itemsales_detail group by brand_id, outlet_id WHERE datecreate >= NOW() - INTERVAL '6 months'");
            rs = stat.executeQuery();
            JSONArray ja = new JSONArray();
            while (rs.next()) {
                JSONObject jo = new JSONObject();
                jo.put("outlet_id", rs.getString("outlet_id"));
                jo.put("order", rs.getString("order"));
                jo.put("product_sold", rs.getString("product_sold"));
                jo.put("total", rs.getString("total"));
                ja.add(jo);

            }
            JSONObject mainObj = new JSONObject();
            mainObj.put("list", ja);
            return JsonProcess.generateJson(mainObj);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return "error";
    }

    public HashMap editbiodata(String brand_id, String user_id, String user_name) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        result.put(RuleNameParameter.resp_code, "0001");
        result.put(RuleNameParameter.resp_desc, "Failed");
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update am_employee set user_name = ? where brand_id = ? and user_id = ?");
            stat.setString(1, user_name);
            stat.setString(2, brand_id);
            stat.setString(3, user_id);
            stat.executeUpdate();

            result.put("user_name", user_name);
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
            return result;
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap editemail(String brand_id, String user_id, String email) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        PreparedStatement stat1 = null;

        result.put(RuleNameParameter.resp_code, "0001");
        result.put(RuleNameParameter.resp_desc, "Failed");
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select user_id from am_employee where user_id = ?");
            stat.setString(1, email);
            rs = stat.executeQuery();
            if (rs.next()) {
                result.put(RuleNameParameter.resp_code, "0002");
                result.put(RuleNameParameter.resp_desc, "Duplicate email");
            } else {
                stat1 = conn.prepareStatement("update am_employee set user_id = ? where brand_id = ? and user_id = ?");
                stat1.setString(1, email);
                stat1.setString(2, brand_id);
                stat1.setString(3, user_id);
                stat1.executeUpdate();
                result.put(RuleNameParameter.resp_code, "0000");
                result.put(RuleNameParameter.resp_desc, "Success");
            }
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
            return result;
        } finally {
            clearAllConnStatRS(conn, stat, rs);
            clearAllConnStatRS(conn, stat1, rs);
        }
        return result;
    }

    public HashMap editphonenumber(String brand_id, String user_id, String phonenumber) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        PreparedStatement stat1 = null;

        result.put(RuleNameParameter.resp_code, "0001");
        result.put(RuleNameParameter.resp_desc, "Failed");
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select phonenumber from am_employee where phonenumber = ?");
            stat.setString(1, phonenumber);
            rs = stat.executeQuery();
            if (rs.next()) {
                result.put(RuleNameParameter.resp_code, "0002");
                result.put(RuleNameParameter.resp_desc, "Duplicate email");
            } else {
                stat1 = conn.prepareStatement("update am_employee set phonenumber = ? where brand_id = ? and user_id = ?");
                stat1.setString(1, phonenumber);
                stat1.setString(2, brand_id);
                stat1.setString(3, user_id);
                stat1.executeUpdate();
                result.put(RuleNameParameter.resp_code, "0000");
                result.put(RuleNameParameter.resp_desc, "Success");
            }
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
            return result;
        } finally {
            clearAllConnStatRS(conn, stat, rs);
            clearAllConnStatRS(conn, stat1, rs);
        }
        return result;
    }

    public HashMap editpassword(String brand_id, String user_id, String oldPassword, String password) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        result.put(RuleNameParameter.resp_code, "0001");
        result.put(RuleNameParameter.resp_desc, "Failed");
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select password from am_employee where user_id = ? and password = ?");
            stat.setString(1, user_id);
            stat.setString(2, oldPassword);
            rs = stat.executeQuery();
            if (rs.next()) {
                stat = conn.prepareStatement("update am_employee set password = ? where brand_id = ? and user_id = ?");
                stat.setString(1, password);
                stat.setString(2, brand_id);
                stat.setString(3, user_id);
                stat.executeUpdate();
                result.put(RuleNameParameter.resp_code, "0000");
                result.put(RuleNameParameter.resp_desc, "Success");
            } else {
                result.put(RuleNameParameter.resp_code, "0003");
                result.put(RuleNameParameter.resp_desc, "Wrong Password");
            }
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
            return result;
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap addoutlet(String brand_id, String outlet_name, String outlet_address) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        PreparedStatement stat1 = null;
        ResultSet rs = null;

        result.put(RuleNameParameter.resp_code, "0001");
        result.put(RuleNameParameter.resp_desc, "Failed");
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select max(outlet_id) as max_outlet_id from am_outlet where brand_id = ?");
            stat.setString(1, brand_id);
            rs = stat.executeQuery();
            while (rs.next()) {
                int max_outlet_id = Integer.valueOf(rs.getString("max_outlet_id"));
                int next_id = max_outlet_id + 1;
                stat1 = conn.prepareStatement("insert into am_outlet (brand_id, outlet_id, outlet_name, alamat, header_receipt, footer_receipt, info, shifting) values (?, ?, ?, ?, ?, ?, ?, ?)");
                stat1.setString(1, brand_id);
                String hehe = "0" + String.valueOf(next_id);
                if (String.valueOf(next_id).length() == 1) {
                    stat1.setString(2, String.valueOf(hehe));
                } else if (String.valueOf(next_id).length() > 1) {
                    stat1.setString(2, String.valueOf(next_id));
                }
                stat1.setString(3, outlet_name);
                stat1.setString(4, outlet_address);
                stat1.setString(5, "Sample Header Receipt Outlet");
                stat1.setString(6, "Sample Footer Receipt Outlet");
                stat1.setString(7, "Sample Info Outlet");
                stat1.setString(8, "0");
                stat1.executeUpdate();
                stat1.close();
                stat1 = conn.prepareStatement("insert into am_balance (agent_id) values (?)");

                if (String.valueOf(next_id).length() == 1) {
                    stat1.setString(1, brand_id + hehe);
                } else if (String.valueOf(next_id).length() > 1) {
                    stat1.setString(1, brand_id + next_id);
                }
                stat1.executeUpdate();
                result.put(RuleNameParameter.resp_code, "0000");
                result.put(RuleNameParameter.resp_desc, "Success");
            }

        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
            return result;
        } finally {
            clearAllConnStatRS(conn, stat, rs);
            clearAllConnStatRS(conn, stat1, rs);
        }
        return result;
    }

    public HashMap editoutlet(String brand_id, String outlet_id, String outlet_name, String outlet_address) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        result.put(RuleNameParameter.resp_code, "0001");
        result.put(RuleNameParameter.resp_desc, "Failed");
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update am_outlet set outlet_name = ? , alamat = ? where brand_id = ? and outlet_id = ?");
            stat.setString(1, outlet_name);
            stat.setString(2, outlet_address);
            stat.setString(3, brand_id);
            stat.setString(4, outlet_id);
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
            return result;
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getlistuser() {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List list = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from am_employee where status = '1'");
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("user_name", rs.getString("user_name"));
                ab.put("user_id", rs.getString("user_id"));
                ab.put("phonenumber", rs.getString("phonenumber"));
                ab.put("userlevel", rs.getString("userlevel"));
                switch (rs.getString("userlevel")) {
                    case "1":
                        ab.put("userlevel", "sales");
                        break;
                    case "2":
                        ab.put("userlevel", "production");
                        break;
                    case "3":
                        ab.put("userlevel", "ppic");
                        break;
                    case "4":
                        ab.put("userlevel", "owner");
                        break;
                    default:
                        break;
                }
                ab.put("", rs.getString("status"));
                list.add(ab);
                ab = null;
            }
            result.put("resp_code", "0000");
            result.put("resp_desc", "success");

            result.put("list", list);
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", e);
            System.out.println("error : " + e);
        } finally {
            list = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap changestatususer(String user_id) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        PreparedStatement stat1 = null;

        result.put(RuleNameParameter.resp_code, "0001");
        result.put(RuleNameParameter.resp_desc, "Failed");
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select status from am_employee where user_id = ?");
            stat.setString(1, user_id);
            rs = stat.executeQuery();
            while (rs.next()) {
                String status = rs.getString("status");
                if ("1".equals(status)) {
                    stat1 = conn.prepareStatement("update am_employee set status = ? where user_id = ?");
                    stat1.setString(1, "0");
                    stat1.setString(2, user_id);
                    stat1.executeUpdate();
                    result.put(RuleNameParameter.resp_code, "0000");
                    result.put(RuleNameParameter.resp_desc, "Success");
                } else if ("0".equals(status)) {
                    stat1 = conn.prepareStatement("update am_employee set status = ? where user_id = ?");
                    stat1.setString(1, "1");
                    stat1.setString(2, user_id);
                    stat1.executeUpdate();
                    result.put(RuleNameParameter.resp_code, "0000");
                    result.put(RuleNameParameter.resp_desc, "Success");
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
            return result;
        } finally {
            clearAllConnStatRS(conn, stat, rs);
            clearAllConnStatRS(conn, stat1, rs);
        }
        return result;
    }

    public HashMap adduser(String user_name, String user_id, String phonenumber, String userlevel) throws UnsupportedEncodingException {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("insert into am_employee (user_id, password, user_name, phonenumber, status, userlevel) values (?, ?, ?, ?, ?, ?)");
            stat.setString(1, user_id);
            String password = StringFunction.generatePassword(8);
            stat.setString(2, FunctionSupportMB.encryptOneWayDataSave(password));
            stat.setString(3, user_name);
            stat.setString(4, phonenumber);
            stat.setString(5, "1");
            stat.setInt(6, Integer.valueOf(userlevel));
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");

            log.info("Try to Send Mail notif . . . .");
            System.out.println("Try to Send Mail notif . . . .");
            final String username = "noreply@matacash.co.id";
            final String pwd = "CY$6!5IK2dxB";
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "103.7.226.208");
            props.put("mail.smtp.port", "587");
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, pwd);
                }
            });
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username, "meatball"));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(user_id));
                String msg = "<body>\n<b>"
                        + "<pre>Hi " + user_name + ",\n"
                        + "Email Anda " + user_id + " telah didaftarkan  sebagai alamat email anda di MEATBALL.\n"
                        + "\n"
                        //                    + "Anda bisa mengakses halaman dashboard dan melakukan setup bisnis anda di \n"
                        //                    + "  URL        : " + StaticParameter.url_dashboard + "\n\ndengan cara memasukan :"
                        //                    + "\n"
                        + "  Email      : " + user_id + "\n"
                        + "  Password   : " + password + "\n" + " \n"
                        + "       \n"
                        + "Mohon untuk tidak membalas karena email ini dikirimkan secara otomatis oleh sistem.\n"
                        + "Demikian, Terimakasih.\n"
                        + "\n\n\n"
                        + "MEATBALL HelpDesk\n"
                        //                            + "Whatsapp (WA)	:  +6281284517330\n"
                        //                            + "Web		:  http://matacash.co.id/\n"
                        //                            + "Email		:  cs@matacash.co.id\n"
                        + "</pre>\n\n"
                        //                            + "<img src=\"https://matajari.co.id/logo/matapos.jpg\"  height=\"100\">\n"
                        + "\n"
                        + "</b></body>\n"
                        + "";
                message.setSubject("Selamat Bergabung di MEATBALL");

                message.setContent(msg, "text/html; charset=utf-8");
                Transport.send(message);
                System.out.println("Done");
                log.info("Send Trx to email : Done!");
            } catch (MessagingException e) {
                System.out.println("E : " + e);
                System.out.println("Fail");
                log.info("Send notif to email : Fail!");
            }
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Failed : " + e);
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap edituser(String user_name, String user_id, String phonenumber, String userlevel) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update am_employee set user_name = ? , phonenumber = ? , userlevel = ? where user_id = ?");
            stat.setString(1, user_name);
            stat.setString(2, phonenumber);
            stat.setInt(3, Integer.valueOf(userlevel));
            stat.setString(4, user_id);
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            System.out.println(e);
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Failed : " + e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap deleteuser(String user_id) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update am_employee set status='0' where user_id=?");
            stat.setString(1, user_id);
            stat.executeUpdate();

            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");

        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Failed : " + e);
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getlistcustomer() {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List list = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from am_customer where status = '1'");
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("name", rs.getString("name"));
                ab.put("phonenumber", rs.getString("phonenumber"));
                ab.put("email", rs.getString("email"));
                ab.put("address", rs.getString("address"));
                list.add(ab);
                ab = null;
            }
            result.put("resp_code", "0000");
            result.put("resp_desc", "success");
            result.put("list", list);
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", e);
            System.out.println("error : " + e);
        } finally {
            list = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap addcustomer(String customer_name, String customer_email, String customer_phone, String customer_address) throws UnsupportedEncodingException {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();

            stat = conn.prepareStatement("insert into am_customer (email, name, phonenumber, address) values (?, ?, ?, ?)");
            stat.setString(1, customer_email);
            stat.setString(2, customer_name);
            stat.setString(3, customer_phone);
            stat.setString(4, customer_address);
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Failed : " + e);
            System.out.println(e);
            return result;
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap editcustomer(String customer_name, String customer_phone, String customer_email, String customer_address) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update am_customer set name = ? , phonenumber = ? , address = ? where email = ?");
            stat.setString(1, customer_name);
            stat.setString(2, customer_phone);
            stat.setString(3, customer_address);
            stat.setString(4, customer_email);
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Failed : " + e);
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap deletecustomer(String email) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("DELETE FROM am_customer where email=?");
            stat.setString(1, email);
            stat.executeUpdate();

            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");

        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Failed : " + e);
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap deleteorder(String user, String rrn, String vlu) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update order_tbl set status = ? where noref = ?");
            stat.setString(1, vlu);
            stat.setString(2, rrn);
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            if (vlu.equals("2")) {
                result.put(RuleNameParameter.resp_desc, "Success reject order");
            } else {
                result.put(RuleNameParameter.resp_desc, "Success delete order");
            }
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Failed : " + e.getMessage());
            System.out.println(e.getMessage());
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap approveorder(String user, String rrn, String product) throws ParseException {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update order_tbl set status = ?, approvedby = ?, approveddate = ? where noref = ?");
            stat.setString(1, "4");
            stat.setString(2, user);
            stat.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            stat.setString(4, rrn);
            stat.executeUpdate();

            stat.close();
            stat = null;

            JSONParser parser = new JSONParser();
            JSONArray obj = (JSONArray) parser.parse(product);
            HashMap gg;
            for (Object jsonnya : obj) {
                gg = (HashMap) jsonnya;
                stat = conn.prepareStatement("insert into production_plan (rrn, product, qty) values (?, ?, ?)");
                stat.setString(1, rrn);
                stat.setString(2, (String) gg.get("product"));
                stat.setInt(3, Integer.parseInt(gg.get("qty").toString()));
                stat.executeUpdate();
            }

            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success send order to production");
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Failed : " + e.getMessage());
            System.out.println(e.getMessage());
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap approveproduction(String user, String tanggal, String batch) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        List l = new ArrayList();

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update production_batch set status = 3, processby = ?, processdate = ? where tanggal = ? and batch = ?");
            stat.setString(1, user);
            stat.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            stat.setTimestamp(3, Timestamp.valueOf(tanggal));
            stat.setInt(4, Integer.parseInt(batch));
            stat.executeUpdate();

            stat.close();
            stat = null;

            stat = conn.prepareStatement("select rrn from production_batch where tanggal = ? and batch = ?");
            stat.setTimestamp(1, Timestamp.valueOf(tanggal));
            stat.setInt(2, Integer.parseInt(batch));
            rs = stat.executeQuery();
            while (rs.next()) {
                String str = rs.getString("rrn").replace("[", "").replace("]", "");
                // split the string by comma
                String[] strParts = str.split(",");
                // convert array to the List using asList method
                List<String> listParts = Arrays.asList(strParts);
//                HashSet<String> hsetFromString = new HashSet<>(listParts);
                for (int i = 0; i < listParts.size(); i++) {
                    update_prod_plan(listParts.get(i));
                }
            }
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success send order to production");
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Failed : " + e.getMessage());
            System.out.println(e.getMessage());
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap insertorupdatebatch(String user, String rrn, String product, String qty) throws ParseException {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        PreparedStatement stat1 = null;
        ResultSet rs1 = null;

        List get_rrn = new ArrayList<>();

        int ord;
        int stk;
        int std;
        int new_ord;
        int new_kbt;
        Double new_jml;

        int stan = 0;
        Date tanggal = new Date();

        Double hm = 0.00;
        Double sembilanlima = 0.00;
        Double tr = 0.00;
        Double mdm = 0.00;
        Double bld = 0.00;
        Double blp = 0.00;
        Double pot = 0.00;
        Double tap = 0.00;
        Double cs = 0.00;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from stanmanage where standate = current_date");
            rs = stat.executeQuery();
            if (rs.next()) {
                stan = rs.getInt("stanno");
                tanggal = rs.getDate("standate");
            }

            stat.close();
            stat = null;
            rs.close();
            rs = null;

//            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select a.*, b.stock, b.std from production_batch a left join product_item b on a.product = b.productcode where a.product = ? and batch = ? and tanggal = current_date");
            stat.setString(1, product);
            stat.setInt(2, stan);
//            stat.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            rs = stat.executeQuery();
            if (rs.next()) {

                stat1 = conn.prepareStatement("select * from bumbu where product = ?");
                stat1.setString(1, product);
                rs1 = stat1.executeQuery();
                if (rs1.next()) {
                    hm = rs1.getDouble("hm");
                    sembilanlima = rs1.getDouble("95");
                    tr = rs1.getDouble("tr");
                    mdm = rs1.getDouble("mdm");
                    bld = rs1.getDouble("bld");
                    blp = rs1.getDouble("blp");
                    pot = rs1.getDouble("pot");
                    tap = rs1.getDouble("tap");
                    cs = rs1.getDouble("cs");
                }

                String rrn_nya = rs.getString("rrn").replace("[", "").replace("]", "");
                get_rrn.add(rrn_nya);
                get_rrn.add(rrn);
                ord = rs.getInt("pesanan");
                stk = rs.getInt("stock");
                std = rs.getInt("std");

                new_ord = ord + Integer.parseInt(qty);//pesanan
                new_kbt = new_ord - stk;//kebutuhan

                new_jml = Double.parseDouble(String.valueOf(new_kbt)) / Double.parseDouble(String.valueOf(std));//jumlah                

                stat = conn.prepareStatement("update production_batch set pesanan = ?, kebutuhan = ?, jumlah = ?, rrn = ?, "
                        + "hm = ?, \"95\" = ?, tr = ? , mdm = ?, bld = ?, blp = ?, pot = ?, tap = ?, cs = ? where product = ? and batch = ? and tanggal = current_date");
                stat.setInt(1, new_ord);
                stat.setInt(2, new_kbt);
                stat.setDouble(3, new_jml);
                stat.setString(4, get_rrn.toString());
                stat.setDouble(5, hm);
                stat.setDouble(6, new_jml * sembilanlima);
                stat.setDouble(7, new_jml * tr);
                stat.setDouble(8, new_jml * mdm);
                stat.setDouble(9, new_jml * bld);
                stat.setDouble(10, new_jml * blp);
                stat.setDouble(11, new_jml * pot);
                stat.setDouble(12, new_jml * tap);
                stat.setDouble(13, new_jml * cs);
                stat.setString(14, product);
                stat.setInt(15, stan);
//                stat.setTimestamp(16, Timestamp.valueOf(LocalDateTime.now()));

                stat.executeUpdate();

            } else {
                stat.close();
                stat = null;
                rs.close();
                rs = null;

                stat = conn.prepareStatement("select * from stanmanage where standate = current_date");
                rs = stat.executeQuery();
                if (rs.next()) {
                    stan = rs.getInt("stanno");
                    tanggal = rs.getDate("standate");
                }
                stat.close();
                stat = null;
                rs.close();
                rs = null;

                stat = conn.prepareStatement("select * from product_item where productcode = ?");
                stat.setString(1, product);
                rs = stat.executeQuery();
                if (rs.next()) {
                    stat1 = conn.prepareStatement("select * from bumbu where product = ?");
                    stat1.setString(1, product);
                    rs1 = stat1.executeQuery();
                    if (rs1.next()) {
                        hm = rs1.getDouble("hm");
                        sembilanlima = rs1.getDouble("95");
                        tr = rs1.getDouble("tr");
                        mdm = rs1.getDouble("mdm");
                        bld = rs1.getDouble("bld");
                        blp = rs1.getDouble("blp");
                        pot = rs1.getDouble("pot");
                        tap = rs1.getDouble("tap");
                        cs = rs1.getDouble("cs");
                    }

                    stk = rs.getInt("stock");
                    std = rs.getInt("std");
                    stat = conn.prepareStatement("insert into production_batch (tanggal, batch, pesanan, kebutuhan, jumlah, product, status, rrn,"
                            + "hm, \"95\", tr, mdm, bld, blp, pot, tap, cs) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    stat.setDate(1, (java.sql.Date) tanggal);
                    stat.setInt(2, stan);
                    stat.setInt(3, Integer.parseInt(qty));

                    new_kbt = Integer.parseInt(qty) - stk;//kebutuhan
                    new_jml = Double.parseDouble(String.valueOf(new_kbt)) / Double.parseDouble(String.valueOf(std));//jumlah

                    stat.setInt(4, new_kbt);
                    stat.setDouble(5, new_jml);
                    stat.setString(6, product);
                    stat.setString(7, "1");
                    get_rrn.add(rrn);
                    stat.setString(8, get_rrn.toString());

                    stat.setDouble(9, hm);
                    stat.setDouble(10, new_jml * sembilanlima);
                    stat.setDouble(11, new_jml * tr);
                    stat.setDouble(12, new_jml * mdm);
                    stat.setDouble(13, new_jml * bld);
                    stat.setDouble(14, new_jml * blp);
                    stat.setDouble(15, new_jml * pot);
                    stat.setDouble(16, new_jml * tap);
                    stat.setDouble(17, new_jml * cs);

                    stat.executeUpdate();
                }
            }

            stat.close();
            stat = null;
            rs.close();
            rs = null;

            stat = conn.prepareStatement("update production_plan set status = ?, processby = ?, processdate = ? where rrn = ? and product = ?");
            stat.setString(1, "2");
            stat.setString(2, user);
            stat.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            stat.setString(4, rrn);
            stat.setString(5, product);

            stat.executeUpdate();

            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success send order to production");
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Failed : " + e.getMessage());
            System.out.println(e.getMessage());
        } finally {
            clearAllConnStatRS(conn, stat, rs);
            clearAllConnStatRS(conn, stat1, rs1);
        }
        return result;
    }

//    public ArrayList<UserLog> getAllLog(String brand_id) {
//        ArrayList<UserLog> list_log = new ArrayList<UserLog>();
//        Connection conn = null;
//        PreparedStatement stat = null;
//        ResultSet rs = null;
//
//        try {
//            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
//            stat = conn.prepareStatement("select * from log_manage where brand_id = ? order by process_time desc limit 20");
//            stat.setString(1, brand_id);
//            rs = stat.executeQuery();
//            while (rs.next()) {
//                UserLog userlog = new UserLog();
//                userlog.setUserid(rs.getString("userid"));
//                userlog.setDetail_info(rs.getString("detail_info"));
//                userlog.setProcess_time(rs.getString("process_time").substring(0, 19));
//                list_log.add(userlog);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            clearAllConnStatRS(conn, stat, rs);
//        }
//        return list_log;
//    }
    public ArrayList<outlet_sales> getAlloutletsales(String brand_id, String outlet_id, String tanggal_from, String tanggal_to) {
        ArrayList<outlet_sales> listsales = new ArrayList<outlet_sales>();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        PreparedStatement stat1 = null;
        ResultSet rs1 = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            if (outlet_id.equals("all")) {
                stat = conn.prepareStatement("select brand_id, outlet_id, outlet_name from am_outlet where brand_id = ? and outlet_status = ?");
                stat.setString(1, brand_id);
                stat.setString(2, "1");
            } else {
                stat = conn.prepareStatement("select brand_id, outlet_id, outlet_name from am_outlet where brand_id = ? and outlet_id = ? and outlet_status = ?");
                stat.setString(1, brand_id);
                stat.setString(2, outlet_id.substring(16));
                stat.setString(3, "1");
            }
            rs = stat.executeQuery();
            while (rs.next()) {
//                outlet_sales userlog = new outlet_sales();
//                stat1 = conn.prepareStatement("select brand_id, outlet_id, count(distinct noresi)as order, sum(total) as product_sold, sum(sum_amount)as total, sum(tax)as pajak from itemsales_detail WHERE brand_id = ? and outlet_id = ? and TO_CHAR(datecreate, 'YYYY-MM-DD') = ? group by brand_id, outlet_id");                
                stat1 = conn.prepareStatement("select brand_id, outlet_id, count(distinct noresi)as order, sum(total) as product_sold, sum(sum_amount)as total, sum(tax)as pajak from itemsales_detail WHERE brand_id = ? and outlet_id = ? and TO_CHAR(datecreate, 'YYYY-MM-DD') between ? and ? group by brand_id, outlet_id");
                stat1.setString(1, rs.getString("brand_id"));
                stat1.setString(2, rs.getString("outlet_id"));
                stat1.setString(3, tanggal_from);
                stat1.setString(4, tanggal_to);

                rs1 = stat1.executeQuery();
                while (rs1.next()) {
                    outlet_sales userlog = new outlet_sales();
                    userlog.setTotal(rs1.getString("total"));
                    userlog.setOrder(rs1.getString("order"));
                    userlog.setProduct_sold(rs1.getString("product_sold"));
                    userlog.setOutlet_id(rs.getString("outlet_id"));
                    userlog.setOutlet_name(rs.getString("outlet_name"));
                    userlog.setPajak(rs1.getString("pajak"));
                    int harga = Integer.valueOf(rs1.getString("total"));
                    int pajak = Integer.valueOf(rs1.getString("pajak"));
                    int plus_pajak = harga + pajak;
                    userlog.setPlus_pajak(String.valueOf(plus_pajak));
                    listsales.add(userlog);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
            clearAllConnStatRS(conn, stat1, rs1);
        }
        return listsales;
    }

    public HashMap getsummaryreport(String brand_id, String tanggal_from, String tanggal_to, String outlet_id) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        PreparedStatement stat1 = null;
        ResultSet rs1 = null;
        result.put(RuleNameParameter.resp_code, "0001");
        result.put(RuleNameParameter.resp_desc, "Failed");
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            if (outlet_id.equals("")) {
                stat = conn.prepareStatement("select sum(sum_amount) as sum_amount, sum(sum_trx) as sum_trx, sum(sum_amount_cashonly) as sum_amount_cashonly, sum(opening_amount)as opening_amount, sum(closing_amount)as closing_amount from am_outlet_trx_day where brand_id = ? and TO_CHAR(trx_date, 'YYYY-MM-DD') between ? and ?");
                stat.setString(1, brand_id);
                stat.setString(2, tanggal_from);
                stat.setString(3, tanggal_to);
            } else {
                stat = conn.prepareStatement("select sum(sum_amount) as sum_amount, sum(sum_trx) as sum_trx, sum(sum_amount_cashonly) as sum_amount_cashonly, sum(opening_amount)as opening_amount, sum(closing_amount)as closing_amount from am_outlet_trx_day where outlet_id = ? and brand_id = ? and TO_CHAR(trx_date, 'YYYY-MM-DD') between ? and ?");
                stat.setString(1, outlet_id.substring(16));
                stat.setString(2, brand_id);
                stat.setString(3, tanggal_from);
                stat.setString(4, tanggal_to);
            }
            rs = stat.executeQuery();
            while (rs.next()) {
                int tswt = rs.getInt("sum_amount");

                result.put("opening_amount", rs.getInt("opening_amount"));
                result.put("closing_amount", rs.getInt("closing_amount"));
                result.put("total_sales_with_tax", tswt);
                result.put("number_of_transaction", rs.getInt("sum_trx"));
                int sa = rs.getInt("sum_amount");
                int st = rs.getInt("sum_trx");
                if (sa == 0) {
                    int apt = 0;
                    result.put("average_per_ticket", apt);
                } else {
                    int apt = sa / st;
                    result.put("average_per_ticket", apt);
                }
                int sac = rs.getInt("sum_amount_cashonly");
                result.put("cash", sac);
                int cl = sa - sac;
                result.put("cashless", cl);

                if (outlet_id.equals("")) {
                    stat1 = conn.prepareStatement("select sum(total) as number_of_item_sold, sum(tax) as tax from itemsales_detail where brand_id = ? and TO_CHAR(datecreate, 'YYYY-MM-DD') between ? and ?");
                    stat1.setString(1, brand_id);
                    stat1.setString(2, tanggal_from);
                    stat1.setString(3, tanggal_to);
                } else {
                    stat1 = conn.prepareStatement("select sum(total) as number_of_item_sold, sum(tax) as tax from itemsales_detail where outlet_id = ? and brand_id = ? and TO_CHAR(datecreate, 'YYYY-MM-DD') between ? and ?");
                    stat1.setString(1, outlet_id.substring(16));
                    stat1.setString(2, brand_id);
                    stat1.setString(3, tanggal_from);
                    stat1.setString(4, tanggal_to);
                }
                rs1 = stat1.executeQuery();
                while (rs1.next()) {
                    result.put("number_of_item_sold", rs1.getInt("number_of_item_sold"));
                    int tax = rs1.getInt("tax");
                    result.put("tax", tax);
                    int ts = tswt - tax;
                    result.put("total_sales", ts);
                }

                result.put(RuleNameParameter.resp_code, "0000");
                result.put(RuleNameParameter.resp_desc, "Success");
            }
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
            return result;
        } finally {
            clearAllConnStatRS(conn, stat, rs);
            clearAllConnStatRS(conn, stat1, rs1);
        }
        return result;
    }

    public HashMap getgrap(String brand_id, String outlet_id, String tanggal_from, String tanggal_to) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        PreparedStatement stat1 = null;
        ResultSet rs1 = null;
        PreparedStatement stat2 = null;
        ResultSet rs2 = null;
        PreparedStatement stat3 = null;
        ResultSet rs3 = null;
        PreparedStatement stat4 = null;
        ResultSet rs4 = null;

        result.put(RuleNameParameter.resp_code, "0001");
        result.put(RuleNameParameter.resp_desc, "Failed");
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            if (outlet_id.equals("all")) {
                stat = conn.prepareStatement("select distinct TO_CHAR(trx_date, 'YYYY-MM-DD') as tgl, sum(sum_amount)as amount, sum(sum_trx)as trx from am_outlet_trx_day where brand_id = ? and TO_CHAR(trx_date, 'YYYY-MM-DD') between ? and ? group by tgl order by TO_CHAR(trx_date, 'YYYY-MM-DD') desc");
                stat.setString(1, brand_id);
                stat.setString(2, tanggal_from);
                stat.setString(3, tanggal_to);
            } else {
                stat = conn.prepareStatement("select distinct TO_CHAR(trx_date, 'YYYY-MM-DD') as tgl, sum(sum_amount)as amount, sum(sum_trx)as trx from am_outlet_trx_day where brand_id = ? and outlet_id = ? and TO_CHAR(trx_date, 'YYYY-MM-DD') between ? and ? group by tgl order by TO_CHAR(trx_date, 'YYYY-MM-DD') desc");
                stat.setString(1, brand_id);
                stat.setString(2, outlet_id.substring(16));
                stat.setString(3, tanggal_from);
                stat.setString(4, tanggal_to);
            }
            rs = stat.executeQuery();
            ArrayList day_trx = new ArrayList();
            ArrayList day_amount = new ArrayList();
            ArrayList day_sumtrx = new ArrayList();
            while (rs.next()) {
                day_trx.add(rs.getString("tgl"));
                day_amount.add(rs.getInt("amount"));
                day_sumtrx.add(rs.getString("trx"));
                result.put("tgl_trx", day_trx);
                result.put("total_amount", day_amount);
                result.put("total_trx", day_sumtrx);

                if (outlet_id.equals("all")) {
                    stat1 = conn.prepareStatement("select count(distinct noresi) as total_sales from itemsales_detail where brand_id = ? and TO_CHAR(datecreate, 'YYYY-MM-DD') between ? and ?");
                    stat1.setString(1, brand_id);
                    stat1.setString(2, tanggal_from);
                    stat1.setString(3, tanggal_to);
                    rs1 = stat1.executeQuery();
                    while (rs1.next()) {
                        result.put(FieldParameterMatapos.total_sales, rs1.getString("total_sales"));
                    }

                    stat2 = conn.prepareStatement("select sum(sum_amount) as total_earnings from itemsales_detail where brand_id = ? and TO_CHAR(datecreate, 'YYYY-MM-DD') between ? and ?");
                    stat2.setString(1, brand_id);
                    stat2.setString(2, tanggal_from);
                    stat2.setString(3, tanggal_to);
                    rs2 = stat2.executeQuery();
                    while (rs2.next()) {
                        if (rs2.getString("total_earnings") == null) {
                            result.put(FieldParameterMatapos.total_earnings, "0");
                        } else {
                            result.put(FieldParameterMatapos.total_earnings, rs2.getString("total_earnings"));
                        }
                    }
                    stat3 = conn.prepareStatement("select count(*) as total_customers from am_customer where brand_id = ? and TO_CHAR(createdate, 'YYYY-MM-DD') between ? and ?");
                    stat3.setString(1, brand_id);
                    stat3.setString(2, tanggal_from);
                    stat3.setString(3, tanggal_to);
                    rs3 = stat3.executeQuery();
                    while (rs3.next()) {
                        result.put(FieldParameterMatapos.total_customers, rs3.getString("total_customers"));
                    }
                    stat4 = conn.prepareStatement("select sum(total) as total_item_sold from itemsales_detail where brand_id = ? and TO_CHAR(datecreate, 'YYYY-MM-DD') between ? and ?");
                    stat4.setString(1, brand_id);
                    stat4.setString(2, tanggal_from);
                    stat4.setString(3, tanggal_to);
                    rs4 = stat4.executeQuery();
                    while (rs4.next()) {
                        if (rs4.getString("total_item_sold") == null) {
                            result.put(FieldParameterMatapos.total_item_sold, "0");
                        } else {
                            result.put(FieldParameterMatapos.total_item_sold, rs4.getString("total_item_sold"));
                        }
                    }
                } else {
                    stat1 = conn.prepareStatement("select count(distinct noresi) as total_sales from itemsales_detail where brand_id = ? and outlet_id = ? and TO_CHAR(datecreate, 'YYYY-MM-DD') between ? and ?");
                    stat1.setString(1, brand_id);
                    stat1.setString(2, outlet_id.substring(16));
                    stat1.setString(3, tanggal_from);
                    stat1.setString(4, tanggal_to);
                    rs1 = stat1.executeQuery();
                    while (rs1.next()) {
                        result.put(FieldParameterMatapos.total_sales, rs1.getString("total_sales"));
                    }

                    stat2 = conn.prepareStatement("select sum(sum_amount) as total_earnings from itemsales_detail where brand_id = ? and outlet_id = ? and TO_CHAR(datecreate, 'YYYY-MM-DD') between ? and ?");
                    stat2.setString(1, brand_id);
                    stat2.setString(2, outlet_id.substring(16));
                    stat2.setString(3, tanggal_from);
                    stat2.setString(4, tanggal_to);
                    rs2 = stat2.executeQuery();
                    while (rs2.next()) {
                        if (rs2.getString("total_earnings") == null) {
                            result.put(FieldParameterMatapos.total_earnings, "0");
                        } else {
                            result.put(FieldParameterMatapos.total_earnings, rs2.getString("total_earnings"));
                        }
                    }
                    stat3 = conn.prepareStatement("select count(*) as total_customers from am_customer where brand_id = ? and outlet_id = ? and TO_CHAR(createdate, 'YYYY-MM-DD') between ? and ?");
                    stat3.setString(1, brand_id);
                    stat3.setString(2, outlet_id.substring(16));
                    stat3.setString(3, tanggal_from);
                    stat3.setString(4, tanggal_to);
                    rs3 = stat3.executeQuery();
                    while (rs3.next()) {
                        result.put(FieldParameterMatapos.total_customers, rs3.getString("total_customers"));
                    }
                    stat4 = conn.prepareStatement("select sum(total) as total_item_sold from itemsales_detail where brand_id = ? and outlet_id = ? and TO_CHAR(datecreate, 'YYYY-MM-DD') between ? and ?");
                    stat4.setString(1, brand_id);
                    stat4.setString(2, outlet_id.substring(16));
                    stat4.setString(3, tanggal_from);
                    stat4.setString(4, tanggal_to);
                    rs4 = stat4.executeQuery();
                    while (rs4.next()) {
                        if (rs4.getString("total_item_sold") == null) {
                            result.put(FieldParameterMatapos.total_item_sold, "0");
                        } else {
                            result.put(FieldParameterMatapos.total_item_sold, rs4.getString("total_item_sold"));
                        }
                    }
                }

                result.put(RuleNameParameter.resp_code, "0000");
                result.put(RuleNameParameter.resp_desc, "Success");
            }
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
            return result;
        } finally {
            clearAllConnStatRS(conn, stat, rs);
            clearAllConnStatRS(conn, stat1, rs1);
            clearAllConnStatRS(conn, stat2, rs2);
            clearAllConnStatRS(conn, stat3, rs3);
            clearAllConnStatRS(conn, stat4, rs4);
        }
        return result;
    }

    public HashMap editcategory(String user_id, String category_id, String category_name, String new_category, String jenis) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update product_category set category_name = ?, jenis = ?, where category_id = ?");
            stat.setString(1, new_category.toUpperCase());
            stat.setString(2, jenis.toUpperCase());
            stat.setString(3, category_id.toUpperCase());
            stat.executeUpdate();

            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Failed");
            System.out.println(e);
            return result;
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap deletecategory(String category_id) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update product_category set status = ? where category_id = ?");
            stat.setString(1, "0");
            stat.setString(2, category_id);
            stat.executeUpdate();

            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");

        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Failed");
            System.out.println(e);
            return result;
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap edititem(String user_id, String item_product_code_edit, String category_name_item, String item_name_edit_item, String item_std_edit_item, String img_item_edit_item) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            if (img_item_edit_item.equals("-")) {
                stat = conn.prepareStatement("update product_item set productname = ?, category = ?, std = ? where productcode = ?");
                stat.setString(1, item_name_edit_item.toUpperCase());
                stat.setString(2, category_name_item.toUpperCase());
                stat.setInt(3, Integer.valueOf(item_std_edit_item));
                stat.setString(4, item_product_code_edit.toUpperCase());
            } else {
                stat = conn.prepareStatement("update product_item set productname = ?, img_url = ?, category = ?, std = ? where productcode = ?");
                stat.setString(1, item_name_edit_item.toUpperCase());
                stat.setString(2, img_item_edit_item);
                stat.setString(3, category_name_item.toUpperCase());
                stat.setInt(4, Integer.valueOf(item_std_edit_item));
                stat.setString(5, item_product_code_edit.toUpperCase());
            }

            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            System.out.println(e);
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, e.getMessage());
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap deleteitem(String product_code) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update product_item set status = ?, deletedate = ? where productcode=?");
            stat.setString(1, "0");
            stat.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            stat.setString(3, product_code);
            stat.executeUpdate();

            stat.close();

            stat = conn.prepareStatement("update bumbu set status = ? where product=?");
            stat.setString(1, "0");
            stat.setString(2, product_code);
            stat.executeUpdate();

            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            result.put(RuleNameParameter.resp_code, "0001");
            result.put(RuleNameParameter.resp_desc, "Failed :" + e.getMessage());
            System.out.println("error " + e.getMessage());
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getaccount(String user_id, String brand_id) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        result.put(RuleNameParameter.resp_code, "0001");
        result.put(RuleNameParameter.resp_desc, "Failed");
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select user_id, user_name, userlevel, brand_id, phonenumber from am_employee where user_id = ? and brand_id = ?");
            stat.setString(1, user_id);
            stat.setString(2, brand_id);
            rs = stat.executeQuery();
            while (rs.next()) {
                result.put("user_id", rs.getString("user_id"));
                result.put("user_name", rs.getString("user_name"));
                result.put("userlevel", rs.getString("userlevel"));
                result.put("phonenumber", rs.getString("phonenumber"));

                result.put(RuleNameParameter.resp_code, "0000");
                result.put(RuleNameParameter.resp_desc, "Success");
            }
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
            return result;
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    //proses login    
    public HashMap getbrandname(String brand_id) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("SELECT brand_name from am_brand WHERE brand_id = ?");
            stat.setString(1, brand_id);
            rs = stat.executeQuery();
            while (rs.next()) {
                result.put(FieldParameterMatapos.brand_name, rs.getString("brand_name"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap editbrand(String brand_id, String brand_name) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        result.put(RuleNameParameter.resp_code, "0001");
        result.put(RuleNameParameter.resp_desc, "Failed");
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update am_brand set brand_name = ? where brand_id = ?");
            stat.setString(1, brand_name);
            stat.setString(2, brand_id);
            stat.executeUpdate();

            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
            return result;
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public ArrayList<summaryproduct> getsummaryinventory(String brand_id, String outlet_id, String userlevel) {
//        System.out.println("tes koneksi :" + brand_id);
        ArrayList<summaryproduct> ppobList = new ArrayList<summaryproduct>();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        PreparedStatement stat1 = null;
        ResultSet rs1 = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            if (userlevel.equals("owner")) {
                stat = conn.prepareStatement("select * from product_master where brand_id = ?");
                stat.setString(1, brand_id);
                rs = stat.executeQuery();
                while (rs.next()) {
                    summaryproduct ab = new summaryproduct();
                    ab.setBrand_id(rs.getString("brand_id"));
                    ab.setOutlet_id(rs.getString("outlet_id"));
                    ab.setProductcode(rs.getString("productcode"));
                    ab.setProductname(rs.getString("productname"));
                    ab.setStock(rs.getString("stock"));
                    ab.setBegining(rs.getString("begining"));
                    ab.setPo(rs.getString("po"));
                    ab.setSales(rs.getString("sales"));
                    ab.setTransfer(rs.getString("transfer"));
                    ab.setAdjusment(rs.getString("adjustment"));
                    ppobList.add(ab);
                }
            } else {
                stat = conn.prepareStatement("select * from product_master where brand_id = ? and outlet_id = ?");
                stat.setString(1, brand_id);
                stat.setString(2, outlet_id);
                rs = stat.executeQuery();
                while (rs.next()) {
                    summaryproduct ab = new summaryproduct();
                    ab.setBrand_id(rs.getString("brand_id"));
                    ab.setOutlet_id(rs.getString("outlet_id"));
                    ab.setProductcode(rs.getString("productcode"));
                    ab.setProductname(rs.getString("productname"));
                    ab.setStock(rs.getString("stock"));
                    ab.setBegining(rs.getString("begining"));
                    ab.setPo(rs.getString("po"));
                    ab.setSales(rs.getString("sales"));
                    ab.setTransfer(rs.getString("transfer"));
                    ab.setAdjusment(rs.getString("adjustment"));
                    ppobList.add(ab);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return ppobList;
    }

    public HashMap getoptioncategory() {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List listcate = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from product_category where status = '1'");
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("category_id", rs.getString("category_id"));
                ab.put("category_name", rs.getString("category_name"));
                listcate.add(ab);
                ab = null;
            }
            result.put("list", listcate);

            result.put("resp_code", "0000");
            result.put("resp_desc", "success");
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", e);
            System.out.println("error : " + e);
        } finally {
            listcate = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap getoptionproduct() {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap result = new HashMap();
        List listcate = new ArrayList();
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from product_item where status = '1' order by productname");
            rs = stat.executeQuery();
            while (rs.next()) {
                HashMap ab = new HashMap();
                ab.put("productcode", rs.getString("productcode"));
                ab.put("productname", rs.getString("productname"));
                listcate.add(ab);
                ab = null;
            }
            result.put("list", listcate);

            result.put("resp_code", "0000");
            result.put("resp_desc", "success");
        } catch (SQLException e) {
            result.put("resp_code", "0001");
            result.put("resp_desc", e.getMessage());
            System.out.println("error : " + e.getMessage());
        } finally {
            listcate = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public ArrayList<supplier> getoptionsupplier(String brand_id) {
        ArrayList<supplier> ppobList = new ArrayList<supplier>();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from am_brand_supplier where brand_id = ? and supplier_status = ?");
            stat.setString(1, brand_id);
            stat.setString(2, "1");
            rs = stat.executeQuery();
            while (rs.next()) {
                supplier ab = new supplier();
                ab.setSupplier_id(rs.getString("supplier_id"));
                ab.setSupplier_name(rs.getString("supplier_name"));
                ab.setSupplier_address(rs.getString("supplier_address"));
                ab.setSupplier_phone(rs.getString("supplier_phone"));
                ab.setSupplier_mail(rs.getString("supplier_mail"));
                ab.setSupplier_totaltrx(rs.getString("supplier_totaltrx"));
                ab.setSupplier_totalamounttrx(rs.getString("supplier_totalamounttrx"));
                ppobList.add(ab);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return ppobList;
    }

    public ArrayList<supplier> getlistsupplier(String brand_id) {
        ArrayList<supplier> ppobList = new ArrayList<supplier>();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from am_brand_supplier where brand_id = ? and supplier_status = ?");
            stat.setString(1, brand_id);
            stat.setString(2, "1");
            rs = stat.executeQuery();
            while (rs.next()) {
                supplier ab = new supplier();
                ab.setSupplier_id(rs.getString("supplier_id"));
                ab.setSupplier_name(rs.getString("supplier_name"));
                ab.setSupplier_address(rs.getString("supplier_address"));
                ab.setSupplier_phone(rs.getString("supplier_phone"));
                ab.setSupplier_mail(rs.getString("supplier_mail"));
                ab.setSupplier_totaltrx(rs.getString("supplier_totaltrx"));
                ab.setSupplier_totalamounttrx(rs.getString("supplier_totalamounttrx"));
                ppobList.add(ab);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return ppobList;
    }

    public HashMap addsupplier(String supplier_name, String supplier_address, String supplier_phone, String supplier_mail, String brand_id) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        result.put(RuleNameParameter.resp_code, "0001");
        result.put(RuleNameParameter.resp_desc, "Failed");
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("insert into am_brand_supplier (supplier_name, supplier_address, supplier_phone, supplier_mail, brand_id) values (?, ?, ?, ?, ?)");
            stat.setString(1, supplier_name);
            stat.setString(2, supplier_address);
            stat.setString(3, supplier_phone);
            stat.setString(4, supplier_mail);
            stat.setString(5, brand_id);
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
            return result;
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap editsupplier(String supplier_id, String supplier_name, String supplier_address, String supplier_phone, String supplier_mail, String brand_id) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        result.put(RuleNameParameter.resp_code, "0001");
        result.put(RuleNameParameter.resp_desc, "Failed");
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update am_brand_supplier set supplier_name = ? , supplier_address = ? , supplier_phone = ? , supplier_mail = ? where supplier_id = ? and brand_id = ?");
            stat.setString(1, supplier_name);
            stat.setString(2, supplier_address);
            stat.setString(3, supplier_phone);
            stat.setString(4, supplier_mail);
            stat.setInt(5, Integer.valueOf(supplier_id));
            stat.setString(6, brand_id);
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
            return result;
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap deletesupplier(String brand_id, String supplier_id) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        result.put(RuleNameParameter.resp_code, "0001");
        result.put(RuleNameParameter.resp_desc, "Failed");
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update am_brand_supplier set supplier_status = ? where supplier_id = ? and brand_id = ?");
            stat.setString(1, "0");
            stat.setInt(2, Integer.valueOf(supplier_id));
            stat.setString(3, brand_id);
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
            return result;
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public HashMap deleteoutlet(String brand_id, String outlet_id) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        result.put(RuleNameParameter.resp_code, "0001");
        result.put(RuleNameParameter.resp_desc, "Failed");
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("update am_outlet set outlet_status = ? where outlet_id = ? and brand_id = ?");
            stat.setString(1, "0");
            stat.setString(2, outlet_id);
            stat.setString(3, brand_id);
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
            return result;
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public ArrayList<business> getoptionbusiness() {
        ArrayList<business> ppobList = new ArrayList<business>();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from business_category");
            rs = stat.executeQuery();
            while (rs.next()) {
                business ab = new business();
                ab.setBusiness_id(rs.getString("business_id"));
                ab.setBusiness_name(rs.getString("business_name"));
                ppobList.add(ab);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return ppobList;
    }

    public ArrayList<merchant> getlistmerchant() {
        ArrayList<merchant> ppobList = new ArrayList<merchant>();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("SELECT am_brand.brand_id, am_brand.brand_name, am_brand.brand_level, am_brand.createdate, am_brand.tax_rate, business_category.business_name\n"
                    + "FROM am_brand\n"
                    + "INNER JOIN business_category ON am_brand.business_id=business_category.business_id");
            rs = stat.executeQuery();
            while (rs.next()) {
                merchant ab = new merchant();
                ab.setBrand_id(rs.getString("brand_id"));
                ab.setBrand_name(rs.getString("brand_name"));
                ab.setBrand_level(rs.getString("brand_level"));
                ab.setCreatedate(rs.getString("createdate"));
                ab.setTax_rate(rs.getString("tax_rate"));
                ab.setBusiness_id(rs.getString("business_name"));
                ppobList.add(ab);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            merchant ab = null;
            clearAllConnStatRS(conn, stat, rs);
        }
        return ppobList;
    }

//    public ArrayList<outlet_sales> getAlloutletsales(String brand_id, String outlet_id, String tanggal_from, String tanggal_to) {
//        ArrayList<outlet_sales> listsales = new ArrayList<outlet_sales>();
//        Connection conn = null;
//        PreparedStatement stat = null;
//        ResultSet rs = null;
//        PreparedStatement stat1 = null;
//        ResultSet rs1 = null;
//        outlet_sales userlog = new outlet_sales();
//
//        try {
//            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
//            if (outlet_id.equals("all")) {
//                stat = conn.prepareStatement("select brand_id, outlet_id, outlet_name from am_outlet where brand_id = ? and outlet_status = ?");
//                stat.setString(1, brand_id);
//                stat.setString(2, "1");
//            } else {
//                stat = conn.prepareStatement("select brand_id, outlet_id, outlet_name from am_outlet where brand_id = ? and outlet_id = ? and outlet_status = ?");
//                stat.setString(1, brand_id);
//                stat.setString(2, outlet_id.substring(16));
//                stat.setString(3, "1");
//            }
//            rs = stat.executeQuery();
//            while (rs.next()) {
////                stat1 = conn.prepareStatement("select brand_id, outlet_id, count(distinct noresi)as order, sum(total) as product_sold, sum(sum_amount)as total, sum(tax)as pajak from itemsales_detail WHERE brand_id = ? and outlet_id = ? and TO_CHAR(datecreate, 'YYYY-MM-DD') = ? group by brand_id, outlet_id");                
//                stat1 = conn.prepareStatement("select brand_id, outlet_id, count(distinct noresi)as order, sum(total) as product_sold, sum(sum_amount)as total, sum(tax)as pajak from itemsales_detail WHERE brand_id = ? and outlet_id = ? and TO_CHAR(datecreate, 'YYYY-MM-DD') between ? and ? group by brand_id, outlet_id");
//                stat1.setString(1, rs.getString("brand_id"));
//                stat1.setString(2, rs.getString("outlet_id"));
//                stat1.setString(3, tanggal_from);
//                stat1.setString(4, tanggal_to);
//
//                rs1 = stat1.executeQuery();
//                while (rs1.next()) {
//                    userlog.setTotal(rs1.getString("total"));
//                    userlog.setOrder(rs1.getString("order"));
//                    userlog.setProduct_sold(rs1.getString("product_sold"));
//                    userlog.setOutlet_id(rs.getString("outlet_id"));
//                    userlog.setOutlet_name(rs.getString("outlet_name"));
//                    userlog.setPajak(rs1.getString("pajak"));
//                    int harga = Integer.valueOf(rs1.getString("total"));
//                    int pajak = Integer.valueOf(rs1.getString("pajak"));
//                    int plus_pajak = harga + pajak;
//                    userlog.setPlus_pajak(String.valueOf(plus_pajak));
//                    listsales.add(userlog);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            clearAllConnStatRS(conn, stat, rs);
//            clearAllConnStatRS(conn, stat1, rs1);
//        }
//        return listsales;
//    }
    public ArrayList<brand_sales> getAllbrandsales(String brand_id, String tanggal_from, String tanggal_to) {
        ArrayList<brand_sales> listsales = new ArrayList<brand_sales>();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        PreparedStatement stat1 = null;
        ResultSet rs1 = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            if (brand_id.equals("all")) {
                stat = conn.prepareStatement("select brand_id, brand_name from am_brand");
            } else {
                stat = conn.prepareStatement("select brand_id, brand_name from am_brand where brand_id = ?");
                stat.setString(1, brand_id);
            }
            rs = stat.executeQuery();
            while (rs.next()) {
                stat1 = conn.prepareStatement("select brand_id, count(distinct noresi)as transaction, sum(sum_amount)as amount, sum(tax)as pajak from itemsales_detail WHERE brand_id = ? and TO_CHAR(datecreate, 'YYYY-MM-DD') between ? and ? group by brand_id");
                stat1.setString(1, rs.getString("brand_id"));
                stat1.setString(2, tanggal_from);
                stat1.setString(3, tanggal_to);

                rs1 = stat1.executeQuery();
                while (rs1.next()) {
                    brand_sales userlog = new brand_sales();
                    userlog.setTransaction(rs1.getString("transaction"));
                    userlog.setAmount(rs1.getString("amount"));
                    userlog.setBrand_id(rs.getString("brand_id"));
                    userlog.setBrand_name(rs.getString("brand_name"));

                    userlog.setPajak(rs1.getString("pajak"));
                    int harga = Integer.valueOf(rs1.getString("amount"));
                    int pajak = Integer.valueOf(rs1.getString("pajak"));
                    int plus_pajak = harga + pajak;
                    userlog.setPlus_pajak(String.valueOf(plus_pajak));

                    listsales.add(userlog);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
            clearAllConnStatRS(conn, stat1, rs1);
        }

        return listsales;
    }

    public ArrayList<brand> getlistbrand() {
        ArrayList<brand> ppobList = new ArrayList<brand>();
        Connection conn = null;
        PreparedStatement stat = null;
        PreparedStatement stat1 = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select * from am_brand");
            rs = stat.executeQuery();
            while (rs.next()) {
                brand ab = new brand();
                ab.setBrand_id(rs.getString("brand_id"));
                ab.setBrand_name(rs.getString("brand_name"));
                ppobList.add(ab);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
            clearAllConnStatRS(conn, stat1, rs1);
        }
        return ppobList;
    }

    public HashMap getbrandgrap(String brand_id, String tanggal_from, String tanggal_to) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
//        PreparedStatement stat1 = null;
//        ResultSet rs1 = null;
//        PreparedStatement stat2 = null;
//        ResultSet rs2 = null;
//        PreparedStatement stat3 = null;
//        ResultSet rs3 = null;
//        PreparedStatement stat4 = null;
//        ResultSet rs4 = null;

        result.put(RuleNameParameter.resp_code, "0001");
        result.put(RuleNameParameter.resp_desc, "Failed");
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            if (brand_id.equals("all")) {
                stat = conn.prepareStatement("select distinct TO_CHAR(trx_date, 'YYYY-MM-DD') as tgl, sum(sum_amount)as amount, sum(sum_trx)as trx from am_outlet_trx_day where TO_CHAR(trx_date, 'YYYY-MM-DD') between ? and ? group by tgl order by TO_CHAR(trx_date, 'YYYY-MM-DD') desc");
                stat.setString(1, tanggal_from);
                stat.setString(2, tanggal_to);
            } else {
                stat = conn.prepareStatement("select distinct TO_CHAR(trx_date, 'YYYY-MM-DD') as tgl, sum(sum_amount)as amount, sum(sum_trx)as trx from am_outlet_trx_day where brand_id = ? and TO_CHAR(trx_date, 'YYYY-MM-DD') between ? and ? group by tgl order by TO_CHAR(trx_date, 'YYYY-MM-DD') desc");
                stat.setString(1, brand_id);
                stat.setString(2, tanggal_from);
                stat.setString(3, tanggal_to);
            }
            rs = stat.executeQuery();
            ArrayList day_trx = new ArrayList();
            ArrayList day_amount = new ArrayList();
            ArrayList day_sumtrx = new ArrayList();
            while (rs.next()) {
                day_trx.add(rs.getString("tgl"));
                day_amount.add(rs.getInt("amount"));
                day_sumtrx.add(rs.getString("trx"));
                result.put("tgl_trx", day_trx);
                result.put("total_amount", day_amount);
                result.put("total_trx", day_sumtrx);

//                if (brand_id.equals("all")) {
//                    stat1 = conn.prepareStatement("select count(distinct noresi) as total_sales from itemsales_detail where brand_id = ? and TO_CHAR(datecreate, 'YYYY-MM-DD') between ? and ?");
//                    stat1.setString(1, brand_id);
//                    stat1.setString(2, tanggal_from);
//                    stat1.setString(3, tanggal_to);
//                    rs1 = stat1.executeQuery();
//                    while (rs1.next()) {
//                        result.put(FieldParameterMatapos.total_sales, rs1.getString("total_sales"));
//                    }
//
//                    stat2 = conn.prepareStatement("select sum(sum_amount) as total_earnings from itemsales_detail where brand_id = ? and TO_CHAR(datecreate, 'YYYY-MM-DD') between ? and ?");
//                    stat2.setString(1, brand_id);
//                    stat2.setString(2, tanggal_from);
//                    stat2.setString(3, tanggal_to);
//                    rs2 = stat2.executeQuery();
//                    while (rs2.next()) {
//                        if (rs2.getString("total_earnings") == null) {
//                            result.put(FieldParameterMatapos.total_earnings, "0");
//                        } else {
//                            result.put(FieldParameterMatapos.total_earnings, rs2.getString("total_earnings"));
//                        }
//                    }
//                    stat3 = conn.prepareStatement("select count(*) as total_customers from am_customer where brand_id = ? and TO_CHAR(createdate, 'YYYY-MM-DD') between ? and ?");
//                    stat3.setString(1, brand_id);
//                    stat3.setString(2, tanggal_from);
//                    stat3.setString(3, tanggal_to);
//                    rs3 = stat3.executeQuery();
//                    while (rs3.next()) {
//                        result.put(FieldParameterMatapos.total_customers, rs3.getString("total_customers"));
//                    }
//                    stat4 = conn.prepareStatement("select sum(total) as total_item_sold from itemsales_detail where brand_id = ? and TO_CHAR(datecreate, 'YYYY-MM-DD') between ? and ?");
//                    stat4.setString(1, brand_id);
//                    stat4.setString(2, tanggal_from);
//                    stat4.setString(3, tanggal_to);
//                    rs4 = stat4.executeQuery();
//                    while (rs4.next()) {
//                        if (rs4.getString("total_item_sold") == null) {
//                            result.put(FieldParameterMatapos.total_item_sold, "0");
//                        } else {
//                            result.put(FieldParameterMatapos.total_item_sold, rs4.getString("total_item_sold"));
//                        }
//                    }
//                } else {
//                    stat1 = conn.prepareStatement("select count(distinct noresi) as total_sales from itemsales_detail where brand_id = ? and outlet_id = ? and TO_CHAR(datecreate, 'YYYY-MM-DD') between ? and ?");
//                    stat1.setString(1, brand_id);
//                    stat1.setString(2, outlet_id.substring(16));
//                    stat1.setString(3, tanggal_from);
//                    stat1.setString(4, tanggal_to);
//                    rs1 = stat1.executeQuery();
//                    while (rs1.next()) {
//                        result.put(FieldParameterMatapos.total_sales, rs1.getString("total_sales"));
//                    }
//
//                    stat2 = conn.prepareStatement("select sum(sum_amount) as total_earnings from itemsales_detail where brand_id = ? and outlet_id = ? and TO_CHAR(datecreate, 'YYYY-MM-DD') between ? and ?");
//                    stat2.setString(1, brand_id);
//                    stat2.setString(2, outlet_id.substring(16));
//                    stat2.setString(3, tanggal_from);
//                    stat2.setString(4, tanggal_to);
//                    rs2 = stat2.executeQuery();
//                    while (rs2.next()) {
//                        if (rs2.getString("total_earnings") == null) {
//                            result.put(FieldParameterMatapos.total_earnings, "0");
//                        } else {
//                            result.put(FieldParameterMatapos.total_earnings, rs2.getString("total_earnings"));
//                        }
//                    }
//                    stat3 = conn.prepareStatement("select count(*) as total_customers from am_customer where brand_id = ? and outlet_id = ? and TO_CHAR(createdate, 'YYYY-MM-DD') between ? and ?");
//                    stat3.setString(1, brand_id);
//                    stat3.setString(2, outlet_id.substring(16));
//                    stat3.setString(3, tanggal_from);
//                    stat3.setString(4, tanggal_to);
//                    rs3 = stat3.executeQuery();
//                    while (rs3.next()) {
//                        result.put(FieldParameterMatapos.total_customers, rs3.getString("total_customers"));
//                    }
//                    stat4 = conn.prepareStatement("select sum(total) as total_item_sold from itemsales_detail where brand_id = ? and outlet_id = ? and TO_CHAR(datecreate, 'YYYY-MM-DD') between ? and ?");
//                    stat4.setString(1, brand_id);
//                    stat4.setString(2, outlet_id.substring(16));
//                    stat4.setString(3, tanggal_from);
//                    stat4.setString(4, tanggal_to);
//                    rs4 = stat4.executeQuery();
//                    while (rs4.next()) {
//                        if (rs4.getString("total_item_sold") == null) {
//                            result.put(FieldParameterMatapos.total_item_sold, "0");
//                        } else {
//                            result.put(FieldParameterMatapos.total_item_sold, rs4.getString("total_item_sold"));
//                        }
//                    }
//                }
                result.put(RuleNameParameter.resp_code, "0000");
                result.put(RuleNameParameter.resp_desc, "Success");
            }
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
            return result;
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public ArrayList<income_list> getlistincome(String brand_id, String outlet_id, String tanggal_from, String tanggal_to) {
        ArrayList<income_list> ppobList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            if (outlet_id.equals("")) {
                stat = conn.prepareStatement("select outlet_id,status_void, detail, status_paid, noresi, dt_internal, item_purchase, customer_name,amount,tax,payment_method\n"
                        + "from log_trx where brand_id = ? and status_paid = '1' and proc_code not in ('69', '70', '71') and status_void = '0' and TO_CHAR(dt_internal, 'YYYY-MM-DD') between ? and ? union all\n"
                        + "select outlet_id,status_void, detail, status_paid, noresi,dt_internal,item_purchase,customer_name,amount,tax,payment_method\n"
                        + "from log_trx_backup where brand_id = ? and status_paid = '1' and proc_code not in ('69', '70', '71') and status_void = '0' and TO_CHAR(dt_internal, 'YYYY-MM-DD') between ? and ?");
                stat.setString(1, brand_id);
                if (tanggal_from.equals("")) {
                    Date today = new Date();
                    String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(today);
                    stat.setString(2, modifiedDate);
                } else {
                    stat.setString(2, tanggal_from);
                }
                if (tanggal_to.equals("")) {
                    Date today = new Date();
                    String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(today);
                    stat.setString(3, modifiedDate);
                } else {
                    stat.setString(3, tanggal_to);
                }
                stat.setString(4, brand_id);
                if (tanggal_from.equals("")) {
                    Date today = new Date();
                    String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(today);
                    stat.setString(5, modifiedDate);
                } else {
                    stat.setString(5, tanggal_from);
                }
                if (tanggal_to.equals("")) {
                    Date today = new Date();
                    String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(today);
                    stat.setString(6, modifiedDate);
                } else {
                    stat.setString(6, tanggal_to);
                }
            } else {
                stat = conn.prepareStatement("select outlet_id,status_void,detail,status_paid, noresi, dt_internal,item_purchase,customer_name,amount,tax,payment_method\n"
                        + "from log_trx where brand_id = ? and outlet_id = ? and status_paid = '1' and proc_code not in ('69', '70', '71') and status_void = '0' and TO_CHAR(dt_internal, 'YYYY-MM-DD') between ? and ? union all\n"
                        + "select outlet_id,status_void, detail, status_paid, noresi,dt_internal,item_purchase,customer_name,amount,tax,payment_method\n"
                        + "from log_trx_backup where brand_id = ? and outlet_id = ? and status_paid = '1' and proc_code not in ('69', '70', '71') and status_void = '0' and TO_CHAR(dt_internal, 'YYYY-MM-DD') between ? and ?");
                stat.setString(1, brand_id);
                stat.setString(2, outlet_id.substring(16));

                if (tanggal_from.equals("")) {
                    Date today = new Date();
                    String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(today);
                    stat.setString(3, modifiedDate);
                } else {
                    stat.setString(3, tanggal_from);
                }
                if (tanggal_to.equals("")) {
                    Date today = new Date();
                    String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(today);
                    stat.setString(4, modifiedDate);
                } else {
                    stat.setString(4, tanggal_to);
                }
                stat.setString(5, brand_id);
                stat.setString(6, outlet_id.substring(16));
                if (tanggal_from.equals("")) {
                    Date today = new Date();
                    String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(today);
                    stat.setString(7, modifiedDate);
                } else {
                    stat.setString(7, tanggal_from);
                }
                if (tanggal_to.equals("")) {
                    Date today = new Date();
                    String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(today);
                    stat.setString(8, modifiedDate);
                } else {
                    stat.setString(8, tanggal_to);
                }
            }
            rs = stat.executeQuery();
            while (rs.next()) {
                income_list ab = new income_list();
                ab.setNoresi(rs.getString("noresi"));
                ab.setOutlet_id(rs.getString("outlet_id"));
                ab.setDt_internal(rs.getString("dt_internal").substring(0, 19));
                ab.setCustomer_name(rs.getString("customer_name"));
                ab.setAmount(rs.getString("amount"));
                ppobList.add(ab);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return ppobList;
    }

    public ArrayList<expense_list> getlistexpense(String brand_id, String outlet_id, String tanggal_from, String tanggal_to) {
        ArrayList<expense_list> ppobList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            if (outlet_id.equals("")) {
                stat = conn.prepareStatement("select * from acc_expense where brand_id = ? and TO_CHAR(exp_date, 'YYYY-MM-DD') between ? and ?");
                stat.setString(1, brand_id);
                if (tanggal_from.equals("")) {
                    Date today = new Date();
                    String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(today);
                    stat.setString(2, modifiedDate);
                } else {
                    stat.setString(2, tanggal_from);
                }
                if (tanggal_to.equals("")) {
                    Date today = new Date();
                    String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(today);
                    stat.setString(3, modifiedDate);
                } else {
                    stat.setString(3, tanggal_to);
                }
            } else {
                stat = conn.prepareStatement("select * from acc_expense where brand_id = ? and outlet_id = ? and TO_CHAR(exp_date, 'YYYY-MM-DD') between ? and ? ");
                stat.setString(1, brand_id);
                stat.setString(2, outlet_id.substring(16));
                if (tanggal_from.equals("")) {
                    Date today = new Date();
                    String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(today);
                    stat.setString(3, modifiedDate);
                } else {
                    stat.setString(3, tanggal_from);
                }
                if (tanggal_to.equals("")) {
                    Date today = new Date();
                    String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(today);
                    stat.setString(4, modifiedDate);
                } else {
                    stat.setString(4, tanggal_to);
                }
            }
            rs = stat.executeQuery();
            while (rs.next()) {
                expense_list ab = new expense_list();
                ab.setExp_id(rs.getString("exp_id"));
                ab.setOutlet_id(rs.getString("outlet_id"));
                ab.setBrand_id(rs.getString("brand_id"));
                ab.setExp_date(rs.getString("exp_date"));
                ab.setExp_category(rs.getString("exp_category"));
                ab.setExp_desc(rs.getString("exp_desc"));
                ab.setAmount(rs.getString("amount"));
                ab.setReceiver(rs.getString("receiver"));

                ppobList.add(ab);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return ppobList;
    }

    public HashMap addexpense(String brand_id, String user_id, String outlet_id, String user_name, String expense_subject, String expense_to, String expense_amount, String expense_desc) {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        result.put(RuleNameParameter.resp_code, "0001");
        result.put(RuleNameParameter.resp_desc, "Failed");
        try {
            int total_trx = 0;
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            stat = conn.prepareStatement("select total_trx from am_outlet where brand_id=? and outlet_id=? ");
            stat.setString(1, brand_id);
            stat.setString(2, outlet_id.substring(16));
            rs = stat.executeQuery();
            while (rs.next()) {
                total_trx = rs.getInt("total_trx");
            }

            stat = conn.prepareStatement("INSERT INTO log_trx(brand_id, outlet_id, proc_code, resp_code, req_msg, resp_msg, noresi, resp_desc, status_paid, executor_name, transaction_number, detail, amount, table_number, customer_name, payment_method, prev_bal, curr_bal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            //generate detail//
            HashMap detail = new HashMap();
            detail.put("product_code", "EXPENSE");
            detail.put("product_name", "EXPENSE");
            detail.put("barcode", "-");
            detail.put("std", expense_amount);
            detail.put("total", "1");
            detail.put("sum_amount", expense_amount);
            detail.put("tax", "0");
            detail.put("discount", "0");
            ArrayList listdetail = new ArrayList();
            listdetail.add(JsonProcess.generateJson(detail));

//            JsonProcess.generateJson(listdetail);
            stat.setString(1, brand_id);
            stat.setString(2, outlet_id.substring(16));
            stat.setString(3, "70");
            stat.setString(4, "0000");
            stat.setString(5, "");
            stat.setString(6, "");
            String no_resi = StringFunction.getCurrentDateYYYYMMDDHHMMSS() + StringFunction.generateOTP(4);
            stat.setString(7, no_resi);
            stat.setString(8, "SUCCESSFULL");
            stat.setString(9, "1");
            stat.setString(10, user_name);
            stat.setString(11, "0");
            stat.setString(12, listdetail.toString());
            stat.setInt(13, Integer.valueOf(expense_amount));
            stat.setString(14, "-");
            stat.setString(15, "-");
            stat.setString(16, "1");
            stat.setInt(17, total_trx);
            stat.setInt(18, total_trx - Integer.valueOf(expense_amount));
            stat.executeUpdate();

            stat = conn.prepareStatement("INSERT INTO acc_expense(exp_id, outlet_id, brand_id, exp_category, exp_desc, amount, receiver) VALUES (?, ?, ?, ?, ?, ?, ?)");
            stat.setString(1, no_resi);
            stat.setString(2, outlet_id.substring(16));
            stat.setString(3, brand_id);
            stat.setString(4, expense_subject);
            stat.setString(5, expense_desc);
            stat.setInt(6, Integer.valueOf(expense_amount));
            stat.setString(7, expense_to);
            stat.executeUpdate();

            stat = conn.prepareStatement("UPDATE am_outlet set total_trx = (select total_trx from am_outlet where brand_id = ? and outlet_id = ?)-? where brand_id = ? and outlet_id = ?");
            stat.setString(1, brand_id);
            stat.setString(2, outlet_id.substring(16));
            stat.setInt(3, Integer.valueOf(expense_amount));
            stat.setString(4, brand_id);
            stat.setString(5, outlet_id.substring(16));
            stat.executeUpdate();
            result.put(RuleNameParameter.resp_code, "0000");
            result.put(RuleNameParameter.resp_desc, "Success");
        } catch (SQLException e) {
            System.out.println(e);
            return result;
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public ArrayList<jurnal_list> getlistjurnal(String brand_id, String outlet_id, String tanggal_from, String tanggal_to) {
        ArrayList<jurnal_list> ppobList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntryBackend.getInstance().getBackendDS().getConnection();
            if (outlet_id.equals("")) {
                stat = conn.prepareStatement("select outlet_id, noresi, dt_internal, customer_name, amount, prev_bal, curr_bal, proc_code\n"
                        + "from log_trx where brand_id = ? and status_paid = '1' and TO_CHAR(dt_internal, 'YYYY-MM-DD') between ? and ? union all\n"
                        + "select outlet_id, noresi,dt_internal,customer_name,amount, prev_bal, curr_bal, proc_code\n"
                        + "from log_trx_backup where brand_id = ? and status_paid = '1' and TO_CHAR(dt_internal, 'YYYY-MM-DD') between ? and ?");
                stat.setString(1, brand_id);
                if (tanggal_from.equals("")) {
                    Date today = new Date();
                    String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(today);
                    stat.setString(2, modifiedDate);
                } else {
                    stat.setString(2, tanggal_from);
                }
                if (tanggal_to.equals("")) {
                    Date today = new Date();
                    String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(today);
                    stat.setString(3, modifiedDate);
                } else {
                    stat.setString(3, tanggal_to);
                }
                stat.setString(4, brand_id);
                if (tanggal_from.equals("")) {
                    Date today = new Date();
                    String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(today);
                    stat.setString(5, modifiedDate);
                } else {
                    stat.setString(5, tanggal_from);
                }
                if (tanggal_to.equals("")) {
                    Date today = new Date();
                    String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(today);
                    stat.setString(6, modifiedDate);
                } else {
                    stat.setString(6, tanggal_to);
                }
            } else {
                stat = conn.prepareStatement("select outlet_id, noresi, dt_internal, customer_name, amount, prev_bal, curr_bal, proc_code\n"
                        + "from log_trx where brand_id = ? and outlet_id = ? and status_paid = '1' and TO_CHAR(dt_internal, 'YYYY-MM-DD') between ? and ? union all\n"
                        + "select outlet_id, noresi,dt_internal,customer_name,amount, prev_bal, curr_bal, proc_code\n"
                        + "from log_trx_backup where brand_id = ? and outlet_id = ? and status_paid = '1' and TO_CHAR(dt_internal, 'YYYY-MM-DD') between ? and ?");
                stat.setString(1, brand_id);
                stat.setString(2, outlet_id.substring(16));

                if (tanggal_from.equals("")) {
                    Date today = new Date();
                    String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(today);
                    stat.setString(3, modifiedDate);
                } else {
                    stat.setString(3, tanggal_from);
                }
                if (tanggal_to.equals("")) {
                    Date today = new Date();
                    String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(today);
                    stat.setString(4, modifiedDate);
                } else {
                    stat.setString(4, tanggal_to);
                }
                stat.setString(5, brand_id);
                stat.setString(6, outlet_id.substring(16));
                if (tanggal_from.equals("")) {
                    Date today = new Date();
                    String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(today);
                    stat.setString(7, modifiedDate);
                } else {
                    stat.setString(7, tanggal_from);
                }
                if (tanggal_to.equals("")) {
                    Date today = new Date();
                    String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(today);
                    stat.setString(8, modifiedDate);
                } else {
                    stat.setString(8, tanggal_to);
                }
            }
            rs = stat.executeQuery();
            while (rs.next()) {
                jurnal_list ab = new jurnal_list();
                ab.setNoresi(rs.getString("noresi"));
                ab.setOutlet_id(rs.getString("outlet_id"));
                ab.setDt_internal(rs.getString("dt_internal").substring(0, 19));
                ab.setCustomer_name(rs.getString("customer_name"));
                ab.setAmount(rs.getString("amount"));
                ab.setPrev_bal(rs.getString("prev_bal"));
                ab.setCurr_bal(rs.getString("curr_bal"));
                String proc = rs.getString("proc_code");
                switch (proc) {
                    case "69":
                        ab.setStatus("D");
                        ab.setType("VOID");
                        break;
                    case "70":
                        ab.setStatus("D");
                        ab.setType("EXPENSE");
                        break;
                    case "71":
                        ab.setStatus("D");
                        ab.setType("PO");
                        break;
                    case "72":
                        ab.setStatus("C");
                        ab.setType("RETURN");
                        break;
                    default:
                        ab.setStatus("C");
                        ab.setType("SALES");
                        break;
                }
                ppobList.add(ab);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return ppobList;
    }
}
