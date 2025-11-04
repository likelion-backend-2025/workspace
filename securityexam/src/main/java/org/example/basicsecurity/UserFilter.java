package org.example.basicsecurity;

import jakarta.servlet.*;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
@Order(1)
public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("UserFilter doFilter() filterChain.doFilter() 실행전!!");
        System.out.println(Thread.currentThread().getName());

        //이때 이 필터가 복잡한 일을 수행 할 수 도 있을꺼예요.
        //복잡한 일을 해서 가져온 값을!!!   이 쓰레드가 사용되는 동안에 어디에서든 다 쓸 수 있게 하고 싶어요.
        //이 예제에서는 간단하게 리퀘스트에서 값을 꺼내서 사용할께요. (실제로는 리퀘스트는 요청에서 모두 사용하므로 의미는 없다.)
        try {
            String name = servletRequest.getParameter("name");
            name = name.toUpperCase();
            User user = new User(name);
//        ThreadLocal 에다가 User를 맡겨둠!!
            UserContext.setUser(user);


            filterChain.doFilter(servletRequest, servletResponse);
            System.out.println("UserFilter doFilter() filterChain.doFilter() 실행후!!");
            System.out.println(Thread.currentThread().getName());


        }finally {
            UserContext.clear();
        }


    }


}
