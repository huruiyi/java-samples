package com.example.demo.salary.infrastructure.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InterceptRequestResponseFilter implements Filter {

	private final static Logger LOG = LoggerFactory.getLogger(InterceptRequestResponseFilter.class);

	@Override
	public void init(final FilterConfig filterConfig) {
		LOG.info("Initializing filter :{}", this);
	}

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		logInfoAboutRequestAndResponse(request, response, chain, req, res);

	}

	private void logInfoAboutRequestAndResponse(ServletRequest request, ServletResponse response, FilterChain chain, HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		LOG.info("Logging Request  {} : {}", req.getMethod(), req.getRequestURI());
		chain.doFilter(request, response);
		LOG.info("Logging Response :{}", res.getContentType());
	}

	@Override
	public void destroy() {
		LOG.warn("Destructing filter :{}", this);
	}
}
