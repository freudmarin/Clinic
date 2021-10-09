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
    // it's better to externalize the keys



      /*  if (session.getAttribute(TaskConstants.SessionKeys.SESSION_ATTR_USER_LOGGED) == null) {
            String desiredUrl = req.getRequestURL().toString();
            session.setAttribute(TaskConstants.SessionKeys.SESSION_ATTR_REQUEST_URL, desiredUrl);

            resp.sendRedirect("/zkau/web/zul/login.zul"); // No logged-in user found, so redirect to login page.

        } else {

            ///      resp.sendRedirect(req.getHeader("referer"));
            filterChain.doFilter(req, resp);
        }
    }*/

   /* @Bean
    public FilterRegistrationBean<TestFilter> filterRegistrationBean() {
        FilterRegistrationBean<TestFilter> registrationBean = new FilterRegistrationBean<>();


        registrationBean.setFilter(new TestFilter());
        registrationBean.addUrlPatterns("/private/*");

        return registrationBean;
    }*/


}
