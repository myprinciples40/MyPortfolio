package org.example.controller;

import org.example.annotation.Controller;
import org.example.annotation.RequestMapping;
import org.example.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Purpose: Practice for understanding Reflection API
 * Features: Find and print all classes that have the @Controller annotation set.
 *
 * Author: Jinhwan Kim (Jin)
 * Date created: 2023-07-02
 * Modification Date:
 */

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(HttpServletRequest request, HttpServletResponse response) {
         return "home";
    }
}
