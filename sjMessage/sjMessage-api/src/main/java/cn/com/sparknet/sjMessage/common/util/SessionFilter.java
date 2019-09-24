package cn.com.sparknet.sjMessage.common.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.sparknet.sjMessage.app.entity.RUser;

public class SessionFilter implements Filter{

	@Override
	public void doFilter(ServletRequest Servletrequest, ServletResponse Servletresponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) Servletrequest;
        HttpServletResponse response = (HttpServletResponse) Servletresponse;
        RUser rUser = (RUser) request.getSession().getAttribute("rUser");
        System.out.println("过滤器执行了");
        if(rUser ==null){
        	System.out.println("rUser"+rUser);
        	response.sendRedirect("/login");
        }else{
        	filterChain.doFilter(request, response);
        }
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
