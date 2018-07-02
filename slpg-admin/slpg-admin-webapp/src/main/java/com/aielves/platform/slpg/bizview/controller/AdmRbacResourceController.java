package com.aielves.platform.slpg.bizview.controller;

import com.aielves.platform.slpg.bizview.pojo.RbacResourceVO;
import com.aielves.platform.slpg.bizview.service.AdmRbacResourceService;
import com.soho.mybatis.exception.BizErrorEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/resource")
public class AdmRbacResourceController {

    @Autowired
    private AdmRbacResourceService admRbacResourceService;

    @RequestMapping("/list")
    public Object list(RbacResourceVO vo) throws BizErrorEx {
        return admRbacResourceService.list(vo);
    }

    @RequestMapping("/find")
    public Object find(RbacResourceVO vo) throws BizErrorEx {
        return admRbacResourceService.find(vo);
    }

    @ResponseBody
    @RequestMapping("/save")
    public Object save(RbacResourceVO vo) throws BizErrorEx {
        return admRbacResourceService.save(vo);
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(RbacResourceVO vo) throws BizErrorEx {
        return admRbacResourceService.delete(vo);
    }

    @ResponseBody
    @RequestMapping("/findByPid")
    public Object findByPid(RbacResourceVO vo) throws BizErrorEx {
        return admRbacResourceService.findByPid(vo);
    }

}