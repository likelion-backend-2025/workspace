package org.example.basicsecurity;

import jakarta.servlet.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("UserFilter doFilter() filterChain.doFilter() 실행전!!");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("UserFilter doFilter() filterChain.doFilter() 실행후!!");
    }


    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("UserFilter init()");
    }

    @Override
    public void destroy() {
        System.out.println("UserFilter destroy()");
    }
}
