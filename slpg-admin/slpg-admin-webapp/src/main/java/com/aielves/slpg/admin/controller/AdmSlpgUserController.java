package com.aielves.slpg.admin.controller;

import com.aielves.slpg.admin.pojo.SlpgUserVO;
import com.aielves.slpg.admin.service.AdmSlpgUserService;
import com.soho.mybatis.exception.BizErrorEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class AdmSlpgUserController {

    @Autowired
    private AdmSlpgUserService admSlpgUserService;

    @RequestMapping("/list")
    public Object list(SlpgUserVO vo) throws BizErrorEx {
        return admSlpgUserService.list(vo);
    }

    @RequestMapping("/find")
    public Object find(SlpgUserVO vo) throws BizErrorEx {
        return admSlpgUserService.find(vo);
    }

    @ResponseBody
    @RequestMapping("/save")
    public Object save(SlpgUserVO vo) throws BizErrorEx {
        return admSlpgUserService.save(vo);
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(SlpgUserVO vo) throws BizErrorEx {
        return admSlpgUserService.delete(vo);
    }

}