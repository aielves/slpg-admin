package com.aielves.slpg.admin.controller;

import com.soho.spring.mvc.model.FastView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error/")
public class ErrorController {

    @RequestMapping("404")
    public Object error404() {
        return new FastView("/error/404").done();
    }

    @RequestMapping("500")
    public Object error500() {
        return new FastView("/error/500").done();
    }

}