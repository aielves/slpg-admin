package com.aielves.slpg.admin.controller;

import com.aielves.slpg.admin.pojo.SlpgRoleVO;
import com.aielves.slpg.admin.service.RoleService;
import com.soho.mybatis.exception.BizErrorEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/list")
    public Object list(SlpgRoleVO vo) throws BizErrorEx {
        return roleService.list(vo);
    }

    @RequestMapping("/find")
    public Object get(SlpgRoleVO vo) throws BizErrorEx {
        return roleService.find(vo);
    }

    @ResponseBody
    @RequestMapping("/save")
    public Object save(SlpgRoleVO vo) throws BizErrorEx {
        return roleService.save(vo);
    }

}