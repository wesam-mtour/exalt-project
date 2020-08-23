package com.exalt.sparepartsmanagement.interceptor;

import com.exalt.sparepartsmanagement.error.exceptions.InvalidHeaderFieldException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestHeaderInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        if (request.getHeader("trust-api") == null) {

            throw new InvalidHeaderFieldException("missing header");

        }
        return super.preHandle(request, response, handler);
    }
}
