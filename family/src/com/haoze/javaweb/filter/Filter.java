package com.haoze.javaweb.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * @author 寿豪泽
 */
@WebFilter("/abc")
public class Filter implements jakarta.servlet.Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        String servletPath = request.getServletPath();
        if ("/index.jsp".equals(servletPath) || "/welcome".equals(servletPath) || "/login".equals(servletPath)
        || "/register".equals(servletPath) || session != null && session.getAttribute("userName")!= null){
            request.setAttribute("name",session.getAttribute("userName"));
            chain.doFilter(request,response);
        }else {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }
}
