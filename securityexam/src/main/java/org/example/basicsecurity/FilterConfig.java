package org.example.basicsecurity;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<CaramiFilter> caramiFilter() {
        FilterRegistrationBean<CaramiFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new CaramiFilter());
        registration.addUrlPatterns("/api/*");
        registration.setOrder(1);
        return registration;
    }
}
