package com.haoze.javaweb.bill;

import com.haoze.javaweb.bean.Bill;
import com.haoze.javaweb.bean.Family;
import com.haoze.javaweb.mysql.Method;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.DbUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 寿豪泽
 */
@WebServlet({"/bill/list","/bill/search","/bill/member","/bill/insert","/bill/update","/bill/delete"})
public class BillServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if ("/bill/list".equals(servletPath)) {
            doBillList(request, response);
        } else if ("/bill/search".equals(servletPath)) {
            doBillSearch(request, response);
        } else if ("/bill/member".equals(servletPath)) {
            doBillMember(request, response);
        } else if ("/bill/insert".equals(servletPath)) {
            doBillInsert(request, response);
        } else if ("/bill/update".equals(servletPath)) {
            doBillUpdate(request, response);
        } else if ("/bill/delete".equals(servletPath)) {
            doBillDelete(request, response);
        }
    }

    private void doBillDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        Family family = (Family) request.getSession().getAttribute("userName");
        int billID = Integer.parseInt(request.getParameter("billID"));
        List<Family> families = new ArrayList<>();
        List<Bill> bills = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;
        try {
            conn = DbUtil.getConnection();
            sql = "DELETE FROM t_bills WHERE billID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,billID);
            int a = ps.executeUpdate();
            if (a == 1){
                String sql3 = "SELECT * FROM t_bills";
                ps = conn.prepareStatement(sql3);
                rs = ps.executeQuery();
                while (rs.next()){
                    Bill bill = new Bill();
                    bill.setBillID(rs.getInt("billID"));
                    bill.setBillDate(rs.getDate("billDate"));
                    bill.setConsumptionType(rs.getString("consumptionType"));
                    bill.setConsumptionAmount(rs.getFloat("consumptionAmount"));
                    bill.setConsumer(rs.getString("consumer"));
                    bills.add(bill);
                }
                request.setAttribute("bills",bills);
                String sql2 = "select * from t_families";
                ps = conn.prepareStatement(sql2);
                rs = ps.executeQuery();
                while (rs.next()){
                    String names = rs.getString("names");
                    Family family2 = new Family(names);
                    families.add(family2);
                }
                request.setAttribute("families",families);
                request.getRequestDispatcher("/bill/list?name=" + family.getName()).forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Method.close(conn,ps,null);
        }
    }

    private void doBillUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        Family family = (Family) request.getSession().getAttribute("userName");
        int billID = Integer.parseInt(request.getParameter("billID"));
        Date billDate = Date.valueOf(request.getParameter("billDate"));
        String consumptionType = request.getParameter("consumptionType");
        float consumptionAmount = Float.parseFloat(request.getParameter("consumptionAmount"));
        String consumer = request.getParameter("consumer");
        List<Family> families = new ArrayList<>();
        List<Bill> bills = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;
        try {
            conn = DbUtil.getConnection();
            sql = "UPDATE t_bills SET billDate = ?,consumptionType = ?," +
                    "consumptionAmount = ?,consumer = ? WHERE billID = ?";
            ps = conn.prepareStatement(sql);
            int count = 1;
            ps.setDate(count++,billDate);
            ps.setString(count++,consumptionType);
            ps.setFloat(count++,consumptionAmount);
            ps.setString(count++,consumer);
            ps.setInt(count,billID);
            int a = ps.executeUpdate();
            if (a == 1){
                String sql3 = "SELECT * FROM t_bills";
                ps = conn.prepareStatement(sql3);
                rs = ps.executeQuery();
                while (rs.next()){
                    Bill bill = new Bill();
                    bill.setBillID(rs.getInt("billID"));
                    bill.setBillDate(rs.getDate("billDate"));
                    bill.setConsumptionType(rs.getString("consumptionType"));
                    bill.setConsumptionAmount(rs.getFloat("consumptionAmount"));
                    bill.setConsumer(rs.getString("consumer"));
                    bills.add(bill);
                }
                request.setAttribute("bills",bills);
                String sql2 = "select * from t_families";
                ps = conn.prepareStatement(sql2);
                rs = ps.executeQuery();
                while (rs.next()){
                    String names = rs.getString("names");
                    Family family2 = new Family(names);
                    families.add(family2);
                }
                request.setAttribute("families",families);
                request.getRequestDispatcher("/bill/list?name=" + family.getName()).forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Method.close(conn,ps,null);
        }
    }

    private void doBillInsert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        Family family = (Family) request.getSession().getAttribute("userName");
        if (family == null){
            family = (Family) request.getAttribute("name");
        }
        List<Family> families = new ArrayList<>();
        List<Bill> bills = new ArrayList<>();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Date billDate = Date.valueOf(request.getParameter("billDate"));
        String consumptionType = request.getParameter("consumptionType");
        float consumptionAmount = Float.parseFloat(request.getParameter("consumptionAmount"));
        String consumer = request.getParameter("consumer");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;
        try {
            conn = DbUtil.getConnection();
            sql = "INSERT INTO t_bills VALUES(NULL,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            int count = 1;
            ps.setDate(count++,billDate);
            ps.setString(count++,consumptionType);
            ps.setFloat(count++,consumptionAmount);
            ps.setString(count,consumer);
            int a = ps.executeUpdate();
            if (a == 1){
                String sql3 = "SELECT * FROM t_bills";
                ps = conn.prepareStatement(sql3);
                rs = ps.executeQuery();
                while (rs.next()){
                    Bill bill = new Bill();
                    bill.setBillID(rs.getInt("billID"));
                    bill.setBillDate(rs.getDate("billDate"));
                    bill.setConsumptionType(rs.getString("consumptionType"));
                    bill.setConsumptionAmount(rs.getFloat("consumptionAmount"));
                    bill.setConsumer(rs.getString("consumer"));
                    bills.add(bill);
                }
                request.setAttribute("bills",bills);
                String sql2 = "select * from t_families";
                ps = conn.prepareStatement(sql2);
                rs = ps.executeQuery();
                while (rs.next()){
                    String names = rs.getString("names");
                    Family family2 = new Family(names);
                    families.add(family2);
                }
                request.setAttribute("families",families);
                request.getRequestDispatcher("/list.jsp?name=" + family.getName()).forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Method.close(conn,ps,rs);
            out.print("录入失败！");
        }
    }

    private void doBillMember(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        List<Family> families = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            String sql = "select * from t_families";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                String name = rs.getString("names");
                Family family = new Family(name);
                families.add(family);
            }
            request.setAttribute("families",families);
            request.getRequestDispatcher("/list.jsp").forward(request,response);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Method.close(conn,ps,rs);
        }
    }

    private void doBillSearch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String userName = ((Family) request.getSession().getAttribute("userName")).getName();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String consumer = request.getParameter("consumer");
        String consumptionType = request.getParameter("consumptionType");
        out.print("year:" + year + "<br>");
        out.print("month:" + month + "<br>");
        out.print("consumer:" + consumer + "<br>");
        out.print("consumptionType:" + consumptionType + "<br>");
        List<Bill> bills = new ArrayList<>();
        List<Family> families = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            String sql;
            if ("whole".equals(month)){
                if ("whole".equals(consumer)){
                    if ("whole".equals(consumptionType)){
                        sql = "SELECT * FROM t_bills WHERE year(billDate) = " + year;

                    }else {
                        sql = "SELECT * FROM t_bills WHERE year(billDate) = "+ year +" and consumptionType = \"" + consumptionType + "\"";
                    }
                }else {
                    if ("whole".equals(consumptionType)){
                        sql = "SELECT * FROM t_bills WHERE year(billDate) = "+ year +" and consumer = \"" + consumer + "\"";
                    }else {
                        sql = "SELECT * FROM t_bills WHERE year(billDate) = " + year + " and consumer = \"" + consumer + "\" and consumptionType = \"" + consumptionType + "\"";
                    }
                }
            }else {
                if ("whole".equals(consumer)){
                    if ("whole".equals(consumptionType)){
                        sql = "SELECT * FROM t_bills WHERE year(billDate) = " + year + " and month(billDate) = " + month;
                    }else {
                        sql = "SELECT * FROM t_bills WHERE year(billDate) = " + year + " and month(billDate) = " + month + " and consumptionType = \"" + consumptionType + "\"";
                    }
                }else {
                    if ("whole".equals(consumptionType)){
                        sql = "SELECT * FROM t_bills WHERE year(billDate) = " + year + " and month(billDate) = " + month + " and consumer = \"" + consumer + "\"";
                    }else {
                        sql = "SELECT * FROM t_bills WHERE year(billDate) = " + year + " and month(billDate) = " + month + " and consumer = \"" + consumer + "\" and consumptionType = \"" + consumptionType + "\"";
                    }
                }
            }
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                int billID = Integer.parseInt(rs.getString("billID"));
                Date billDate = Date.valueOf(rs.getString("billDate"));
                consumptionType = rs.getString("consumptionType");
                float consumptionAmount = Float.parseFloat(rs.getString("consumptionAmount"));
                consumer = rs.getString("consumer");
                Bill bill = new Bill(billID,billDate,consumptionType,consumptionAmount,consumer);
                bills.add(bill);
            }
            request.setAttribute("bills",bills);

            String sql2 = "select * from t_families";
            ps = conn.prepareStatement(sql2);
            rs = ps.executeQuery();
            while (rs.next()){
                String name = rs.getString("names");
                Family family = new Family(name);
                families.add(family);
            }
            request.setAttribute("families",families);
            request.getRequestDispatcher("/list.jsp?name=" + userName).forward(request,response);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Method.close(conn,ps,rs);
        }
    }

    private void doBillList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        //String name = (String) request.getSession().getAttribute("userName");
        List<Bill> bills = new ArrayList<>();
        List<Family> families = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            String sql = "SELECT * FROM t_bills";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Bill bill = new Bill();
                bill.setBillID(rs.getInt("billID"));
                bill.setBillDate(rs.getDate("billDate"));
                bill.setConsumptionType(rs.getString("consumptionType"));
                bill.setConsumptionAmount(rs.getFloat("consumptionAmount"));
                bill.setConsumer(rs.getString("consumer"));
                bills.add(bill);
            }
            request.setAttribute("bills",bills);
            String sql2 = "select * from t_families";
            ps = conn.prepareStatement(sql2);
            rs = ps.executeQuery();
            while (rs.next()){
                String names = rs.getString("names");
                Family family = new Family(names);
                families.add(family);
            }
            request.setAttribute("families",families);
            request.getRequestDispatcher("/list.jsp").forward(request,response);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Method.close(conn,ps,rs);
        }
    }
}
