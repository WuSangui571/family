package com.haoze.javaweb.login;

import com.haoze.javaweb.bean.Family;
import com.haoze.javaweb.mysql.Method;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import util.DbUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * @author 寿豪泽
 */
@WebServlet({"/login","/register","/exit"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if ("/login".equals(servletPath)) {
            doLogin(request,response);
        }else if ("/register".equals(servletPath)){
            doRegister(request,response);
        }else if ("/exit".equals(servletPath)){
            doExit(request,response);
        }
    }

    private void doExit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        HttpSession session = request.getSession(false);
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies){
                String cookieName= cookie.getName();
                if ("name".equals(cookieName)){
                    Cookie newCookie = new Cookie(cookieName, null);
                    newCookie.setMaxAge(0);
                    response.addCookie(newCookie);
                }
            }
        }
        if (session != null){
            session.invalidate();
            response.sendRedirect(request.getContextPath());
        }
    }

    private void doRegister(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            String sql = "INSERT INTO t_families VALUES (?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            int i = ps.executeUpdate();
            if (i == 1){
                request.getRequestDispatcher("/index.jsp").forward(request,response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            out.print("注册失败");
            Method.close(conn,ps,rs);
        }
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
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
                if (name.equals(request.getParameter("name"))) {
                    Family family = new Family(name);
                    request.getSession().setAttribute("userName",family);
                    String f = request.getParameter("f");
                    if ("1".equals(f)){
                        Cookie cookie = new Cookie("name",name);
                        cookie.setMaxAge(60 * 60 * 24 * 3);
                        cookie.setPath(request.getContextPath());
                        response.addCookie(cookie);
                    }
                    request.getRequestDispatcher("/member").forward(request,response);
                }
            }
            out.print("姓名错误！");

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Method.close(conn,ps,rs);
        }
    }
}
