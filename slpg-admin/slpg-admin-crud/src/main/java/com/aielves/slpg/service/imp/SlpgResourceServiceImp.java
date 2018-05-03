package com.aielves.slpg.service.imp;

import com.aielves.slpg.dao.SlpgResourceDAO;
import com.aielves.slpg.domain.SlpgResource;
import com.aielves.slpg.service.SlpgResourceService;
import com.soho.mybatis.crud.dao.MyBatisDAO;
import com.soho.mybatis.crud.service.imp.BaseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlpgResourceServiceImp extends BaseServiceImp<SlpgResource, Long> implements SlpgResourceService {
    @Autowired
    private SlpgResourceDAO slpgresourceDAO;

    public MyBatisDAO<SlpgResource, Long> getMybatisDAO() {
        return slpgresourceDAO;
    }
}