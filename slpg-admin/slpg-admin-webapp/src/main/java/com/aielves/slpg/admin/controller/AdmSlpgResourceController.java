package com.aielves.slpg.admin.controller;

import com.aielves.slpg.admin.pojo.SlpgResourceVO;
import com.aielves.slpg.admin.service.AdmSlpgResourceService;
import com.soho.mybatis.exception.BizErrorEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/resource")
public class AdmSlpgResourceController {

    @Autowired
    private AdmSlpgResourceService admSlpgResourceService;

    @RequestMapping("/list")
    public Object list(SlpgResourceVO vo) throws BizErrorEx {
        return admSlpgResourceService.list(vo);
    }

    @RequestMapping("/find")
    public Object find(SlpgResourceVO vo) throws BizErrorEx {
        return admSlpgResourceService.find(vo);
    }

    @ResponseBody
    @RequestMapping("/save")
    public Object save(SlpgResourceVO vo) throws BizErrorEx {
        return admSlpgResourceService.save(vo);
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(SlpgResourceVO vo) throws BizErrorEx {
        return admSlpgResourceService.delete(vo);
    }

    @ResponseBody
    @RequestMapping("/findByPid")
    public Object findByPid(SlpgResourceVO vo) throws BizErrorEx {
        return admSlpgResourceService.findByPid(vo);
    }

}