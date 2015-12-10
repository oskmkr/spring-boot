package com.oskm.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.util.Enumeration;

public class CharacterFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(CharacterFilter.class);

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        LOG.debug("doFilter...");

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        StringBuilder sbHeaderInfo = new StringBuilder();

        sbHeaderInfo.append("\nAddress: ").append(httpServletRequest.getRequestURL());
        sbHeaderInfo.append("\nEncoding: ").append(httpServletRequest.getCharacterEncoding());
        sbHeaderInfo.append("\nHttp-Method: ").append(httpServletRequest.getMethod());
        sbHeaderInfo.append("\nContent-Type: ").append(httpServletRequest.getContentType());
        sbHeaderInfo.append("\nRemoteAddr: ").append(httpServletRequest.getRemoteAddr());
        sbHeaderInfo.append("\nX-Forward-For: ").append(httpServletRequest.getHeader("X-Forwarded-For"));

        Enumeration headerNames = httpServletRequest.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String headerName = (String) headerNames.nextElement();

            sbHeaderInfo.append(headerName);
            sbHeaderInfo.append("=[").append(httpServletRequest.getHeader(headerName)).append("], ");
        }

        LOG.debug(sbHeaderInfo.toString());

        StringBuilder sbResponse = new StringBuilder();
        sbResponse.append("\nresponse Encoding: ").append(httpServletResponse.getCharacterEncoding());
        sbResponse.append("\nresponse Content-Type: ").append(httpServletResponse.getContentType());

        LOG.debug(sbResponse.toString());

        chain.doFilter(request, response);

        HttpServletResponseWrapper httpServletResponseWrapper = new HttpServletResponseWrapper(httpServletResponse);

        //LOG.debug(new PrintWriter(httpServletResponseWrapper.getOutputStream()).toString());

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }


}
