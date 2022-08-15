package com.haoze.javaweb.welcome;

import com.haoze.javaweb.bean.Family;
import com.haoze.javaweb.mysql.Method;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import util.DbUtil;

import java.io.IOException;
import java.sql.*;

/**
 * @author 寿豪泽
 */
@WebServlet({"/welcome"})
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        boolean flag = false;
        String userName = null;
        if (cookies != null){
            for (Cookie cookie : cookies){
                if ("name".equals(cookie.getName())){
                    userName = cookie.getValue();
                }
            }
        }
        if (userName != null){
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                conn = DbUtil.getConnection();
                String sql = "select * from t_families where names = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1,userName);
                rs = ps.executeQuery();
                if (rs.next()){
                    flag = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                Method.close(conn,ps,rs);
            }
            if (flag){
                Family f = new Family(userName);
                request.getSession().setAttribute("userName",f);
                response.sendRedirect(request.getContextPath() + "/member");
            }else {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
        }else {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }
}
