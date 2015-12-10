package com.oskm.filter;

import com.googlecode.htmlcompressor.compressor.HtmlCompressor;
import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ?compress=true 인 경우 response content 압축 처리
 *
 * @see http://www.byteslounge.com/tutorials/how-to-compress-response-html-in-java-web-application
 * @ Created by oskmkr on 2015-06-19.
 */
@WebFilter(filterName = "CompressResponseFilter", urlPatterns = {"/app/*"})
public class CompressResponseFilter implements Filter {

    private HtmlCompressor compressor;

    /**
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        compressor = new HtmlCompressor();
        compressor.setCompressCss(true);
        compressor.setCompressJavaScript(true);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        CharResponseWrapper responseWrapper = new CharResponseWrapper((HttpServletResponse) servletResponse);

        filterChain.doFilter(servletRequest, responseWrapper);

        String response = new String(responseWrapper.toString());

        if (StringUtils.defaultString(servletRequest.getParameter("compress"), "").equals("true")) {
            servletResponse.getWriter().write(compressor.compress(response));
        } else {
            servletResponse.getWriter().write(response);
        }
    }

    @Override
    public void destroy() {

    }
}
