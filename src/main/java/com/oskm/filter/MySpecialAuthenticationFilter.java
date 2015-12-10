package com.oskm.filter;

import org.apache.log4j.Logger;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class MySpecialAuthenticationFilter extends GenericFilterBean {

    private static final Logger LOG = Logger.getLogger(MySpecialAuthenticationFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOG.debug(">>>>>");
        chain.doFilter(request, response);

    }

}
