package com.aielves.slpg.admin.controller;

import com.aielves.slpg.admin.service.RoleService;
import com.soho.mybatis.exception.BizErrorEx;
import com.soho.spring.model.ReqData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/list")
    public Object list(ReqData reqData) throws BizErrorEx {
        return roleService.list(reqData);
    }

}