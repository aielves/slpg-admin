package com.aielves.slpg.service.imp;

import com.aielves.slpg.dao.SlpgRoleDAO;
import com.aielves.slpg.domain.SlpgRole;
import com.aielves.slpg.service.SlpgRoleService;
import com.soho.mybatis.crud.dao.MyBatisDAO;
import com.soho.mybatis.crud.service.imp.BaseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlpgRoleServiceImp extends BaseServiceImp<SlpgRole, Long> implements SlpgRoleService {
    @Autowired
    private SlpgRoleDAO slpgroleDAO;

    public MyBatisDAO<SlpgRole, Long> getMybatisDAO() {
        return slpgroleDAO;
    }
}