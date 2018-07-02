package com.aielves.platform.rbac.service.imp;

import com.aielves.platform.rbac.dao.RbacRoleDAO;
import com.aielves.platform.rbac.domain.RbacRole;
import com.aielves.platform.rbac.service.RbacRoleService;
import com.soho.mybatis.crud.dao.MyBatisDAO;
import com.soho.mybatis.crud.service.imp.BaseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RbacRoleServiceImp extends BaseServiceImp<RbacRole, Long> implements RbacRoleService {
    @Autowired
    private RbacRoleDAO rbacroleDAO;

    public MyBatisDAO<RbacRole, Long> getMybatisDAO() {
        return rbacroleDAO;
    }
}