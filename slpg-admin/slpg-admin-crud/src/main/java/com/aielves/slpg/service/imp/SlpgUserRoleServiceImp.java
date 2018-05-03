package com.aielves.slpg.service.imp;

import com.aielves.slpg.dao.SlpgUserRoleDAO;
import com.aielves.slpg.domain.SlpgUserRole;
import com.aielves.slpg.service.SlpgUserRoleService;
import com.soho.mybatis.crud.dao.MyBatisDAO;
import com.soho.mybatis.crud.service.imp.BaseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlpgUserRoleServiceImp extends BaseServiceImp<SlpgUserRole, Long> implements SlpgUserRoleService {
    @Autowired
    private SlpgUserRoleDAO slpguserroleDAO;

    public MyBatisDAO<SlpgUserRole, Long> getMybatisDAO() {
        return slpguserroleDAO;
    }
}