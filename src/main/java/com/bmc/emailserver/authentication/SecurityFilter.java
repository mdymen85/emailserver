package com.bmc.emailserver.authentication;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class SecurityFilter implements Filter {

   @SuppressWarnings("unused")
   private FilterConfig filterConfig = null;
	   
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		 System.out.println(((HttpServletRequest) request).getRemoteAddr());
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
