package com.haoze.javaweb.member;

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
@WebServlet({"/member","/member/delete","/member/update"})
public class MemberServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if ("/member".equals(servletPath)){
            doMember(request,response);
        }else if ("/member/delete".equals(servletPath)){
            doMemberDelete(request,response);
        }else if ("/member/update".equals(servletPath)){
            doMemberUpdate(request,response);
        }
    }
    private void doMemberUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Family> families = new ArrayList<>();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String newName = request.getParameter("newName");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            String sql = "UPDATE t_families SET names = ? WHERE names = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,newName);
            ps.setString(2,name);
            Family f = new Family(newName);
            request.getSession().setAttribute("userName",f);
            int count = ps.executeUpdate();
            if (count != 1){
                out.print("修改失败");
            }else {
                String sql2 = "select * from t_families";
                ps = conn.prepareStatement(sql2);
                rs = ps.executeQuery();
                while (rs.next()){
                    String name2 = rs.getString("names");
                    Family family = new Family(name2);
                    families.add(family);
                }
                request.setAttribute("families",families);
                request.getRequestDispatcher("/member.jsp?name="+newName).forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Method.close(conn,ps,rs);
        }
    }

    private void doMemberDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Family> families = new ArrayList<>();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            String sql = "DELETE FROM t_families WHERE names = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            out.print(name);
            int count = ps.executeUpdate();
            if (count != 1){
                out.print("删除失败");
            }else {
                String sql2 = "select * from t_families";
                ps = conn.prepareStatement(sql2);
                rs = ps.executeQuery();
                while (rs.next()){
                    String name2 = rs.getString("names");
                    Family family = new Family(name2);
                    families.add(family);
                }
                request.setAttribute("families",families);
                request.getRequestDispatcher("/member.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Method.close(conn,ps,rs);
        }
    }

    private void doMember(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Method.close(conn,ps,rs);
        }
        request.setAttribute("families",families);
        request.getRequestDispatcher("/member.jsp").forward(request,response);
    }
}
