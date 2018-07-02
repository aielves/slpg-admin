package com.aielves.platform.slpg.bizview.controller;

import com.aielves.platform.slpg.bizview.pojo.RbacRoleVO;
import com.aielves.platform.slpg.bizview.service.AdmRbacRoleService;
import com.soho.mybatis.exception.BizErrorEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/role")
public class AdmRbacRoleController {

    @Autowired
    private AdmRbacRoleService admRbacRoleService;

    @RequestMapping("/list")
    public Object list(RbacRoleVO vo) throws BizErrorEx {
        return admRbacRoleService.list(vo);
    }

    @RequestMapping("/find")
    public Object find(RbacRoleVO vo) throws BizErrorEx {
        return admRbacRoleService.find(vo);
    }

    @ResponseBody
    @RequestMapping("/save")
    public Object save(RbacRoleVO vo) throws BizErrorEx {
        return admRbacRoleService.save(vo);
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(RbacRoleVO vo) throws BizErrorEx {
        return admRbacRoleService.delete(vo);
    }

}