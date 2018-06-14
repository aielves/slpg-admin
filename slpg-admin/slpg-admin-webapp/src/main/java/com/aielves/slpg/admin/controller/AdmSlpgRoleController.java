package com.aielves.slpg.admin.controller;

import com.aielves.slpg.admin.pojo.SlpgRoleVO;
import com.aielves.slpg.admin.service.AdmSlpgRoleService;
import com.soho.mybatis.exception.BizErrorEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/role")
public class AdmSlpgRoleController {

    @Autowired
    private AdmSlpgRoleService admSlpgRoleService;

    @RequestMapping("/list")
    public Object list(SlpgRoleVO vo) throws BizErrorEx {
        vo.setPageSize(5);
        return admSlpgRoleService.list(vo);
    }

    @RequestMapping("/find")
    public Object find(SlpgRoleVO vo) throws BizErrorEx {
        return admSlpgRoleService.find(vo);
    }

    @ResponseBody
    @RequestMapping("/save")
    public Object save(SlpgRoleVO vo) throws BizErrorEx {
        return admSlpgRoleService.save(vo);
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(SlpgRoleVO vo) throws BizErrorEx {
        return admSlpgRoleService.delete(vo);
    }

}