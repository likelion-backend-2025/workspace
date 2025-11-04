package org.example.basicsecurity;

import jakarta.servlet.*;
import jakarta.servlet.FilterConfig;

import java.io.IOException;
//@Component
//@WebFilter(urlPatterns = "/api/*")
public class CaramiFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("CaramiFilter.doFilter() 실행전!!");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("CaramiFilter.doFilter() 실행후!!");
    }



    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
