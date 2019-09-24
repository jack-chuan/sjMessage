package cn.com.sparknet.sjMessage.common.util;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class FilterConfig {
	@Bean
    public FilterRegistrationBean registFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new SessionFilter());
        registration.addUrlPatterns("/index");
        registration.setName("LogCostFilter");
        registration.setOrder(1);
        return registration;
    }
}
