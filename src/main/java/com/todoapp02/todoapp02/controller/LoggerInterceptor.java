package com.todoapp02.todoapp02.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
class LoggerInterceptor implements HandlerInterceptor {

    public static final Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);

    @Override
    public boolean preHandle(final HttpServletRequest request,final HttpServletResponse response,final Object handler) throws Exception {
        logger.info("[preHandle] " + request.getMethod() + " " + request.getRequestURI());
        return true;
    }
/*
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("[postHandle]");
    }

 **/
}
