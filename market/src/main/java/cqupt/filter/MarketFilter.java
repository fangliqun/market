package cqupt.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import ch.qos.logback.core.boolex.Matcher;

//@WebFilter(urlPatterns = "/*", filterName = "indexFilter")
//@Configuration
public class MarketFilter implements Filter {
	
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("ssssssssssssssssssssss");
	}

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		System.out.println("filter doFilter method");
        HttpSession session = httpRequest.getSession();
//    	if(session.getAttribute("hq")!=null){
//    		chain.doFilter(httpRequest, httpResponse);
//    	}else{
//    		httpResponse.sendRedirect("/login.html");//req.getContextPath()
//    	}
        String path = httpRequest.getContextPath();
        String basePath = httpRequest.getScheme()+"://"+httpRequest.getServerName()+":"+httpRequest.getServerPort()+path;
		String username=(String) session.getAttribute("username");
    	if (username == null || "".equals(username)) {
    		httpResponse.setHeader("Cache-Control", "no-store");
    		httpResponse.setDateHeader("Expires", 0);
    		httpResponse.setHeader("Prama", "no-cache");
            //此处设置了访问静态资源类
    		httpResponse.sendRedirect(basePath+"/login.html");
        } else {
            // Filter 只是链式处理，请求依然转发到目的地址。 
        	chain.doFilter(httpRequest, httpResponse);
        }
	}

	
	
}
