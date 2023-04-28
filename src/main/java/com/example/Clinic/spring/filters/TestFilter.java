package com.example.Clinic.spring.filters;

import com.example.Clinic.spring.utils.TaskConstants;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.zkoss.zk.ui.Executions;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;




@WebFilter(filterName = "Test",urlPatterns = {"*.zul"})
public class TestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if (req.getRequestURI().equals("~./zul/login.zul"))
            filterChain.doFilter(servletRequest, servletResponse);

        //Check whether the user is logged in
        HttpSession session = req.getSession();
        String status = (String) session.getAttribute(TaskConstants.SessionKeys.SESSION_ATTR_USER_LOGGED);
        if (status == null || !status.equals("true")) {
            try {
                resp.sendRedirect("~./zul/login.zul");
            } catch (Exception e) {
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
