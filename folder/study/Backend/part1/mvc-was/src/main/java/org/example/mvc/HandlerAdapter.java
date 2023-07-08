package org.example.mvc;

import org.example.mvc.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Interface to find adapters that support the handler
public interface HandlerAdapter {
    // Check if it supports the handler passed in
    boolean supports(Object handler);

    // Execute the handler and get the ModelAndView return if it supports handlers
    ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;
}
