package kh.test.jdbckh.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import kh.test.jdbckh.common.filter.wrapper.PasswordWrapper;

public class PasswordFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
//		PasswordWrapper pwrapper = new PasswordWrapper(request);

//		chain.doFilter(pwrapper, response);
		
		
	}

}
