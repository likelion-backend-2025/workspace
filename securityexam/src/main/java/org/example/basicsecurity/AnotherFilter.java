package org.example.basicsecurity;

import jakarta.servlet.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
@Order(2)
public class AnotherFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("AnotherFilter 실행 "+ UserContext.getUser().getName());
        filterChain.doFilter(servletRequest, servletResponse);


    }
}
