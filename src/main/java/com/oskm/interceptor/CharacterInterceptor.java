package com.oskm.interceptor;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CharacterInterceptor implements HandlerInterceptor, InitializingBean {

    private static final Logger LOG = Logger.getLogger(CharacterInterceptor.class.getCanonicalName());

    @Override
    public void afterCompletion(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Object paramObject, Exception paramException) throws Exception {
        // TODO Auto-generated method stub

        LOG.debug("aferCompletion...");

    }

    @Override
    public void postHandle(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Object paramObject, ModelAndView paramModelAndView) throws Exception {
        // TODO Auto-generated method stub
        LOG.debug("postHandle...");
    }

    @Override
    public boolean preHandle(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Object paramObject) throws Exception {
        // TODO Auto-generated method stub
        LOG.debug("preHandle...");
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub
        LOG.debug("afterPropertiesSet...");

    }

}
