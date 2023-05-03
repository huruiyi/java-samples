package com.example.demo.salary.infrastructure.filter;

import com.example.demo.salary.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class TransactionFilter implements Filter {

	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TransactionService transactionService;

	@Override
	public void init(final FilterConfig filterConfig) {
		log.info("Initializing filter :{}", this);
	}

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		transactionService.startTransaction(req);
		chain.doFilter(request, response);
		transactionService.commitTransaction(req);

	}

	@Override
	public void destroy() {
		log.warn("Destructing filter :{}", this);
	}
}
