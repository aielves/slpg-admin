package com.aielves.slpg.service.imp;

import com.aielves.slpg.dao.SlpgRoleResourceDAO;
import com.aielves.slpg.domain.SlpgRoleResource;
import com.aielves.slpg.service.SlpgRoleResourceService;
import com.soho.mybatis.crud.dao.MyBatisDAO;
import com.soho.mybatis.crud.service.imp.BaseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlpgRoleResourceServiceImp extends BaseServiceImp<SlpgRoleResource, Long> implements SlpgRoleResourceService {
    @Autowired
    private SlpgRoleResourceDAO slpgroleresourceDAO;

    public MyBatisDAO<SlpgRoleResource, Long> getMybatisDAO() {
        return slpgroleresourceDAO;
    }
}