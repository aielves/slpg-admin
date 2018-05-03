package com.aielves.slpg.service.imp;

import com.aielves.slpg.dao.SlpgUserDAO;
import com.aielves.slpg.domain.SlpgUser;
import com.aielves.slpg.service.SlpgUserService;
import com.soho.mybatis.crud.dao.MyBatisDAO;
import com.soho.mybatis.crud.service.imp.BaseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlpgUserServiceImp extends BaseServiceImp<SlpgUser, Long> implements SlpgUserService {
    @Autowired
    private SlpgUserDAO slpguserDAO;

    public MyBatisDAO<SlpgUser, Long> getMybatisDAO() {
        return slpguserDAO;
    }
}