package com.aielves.platform.rbac.service.imp;

import com.aielves.platform.rbac.dao.RbacUserRoleDAO;
import com.aielves.platform.rbac.domain.RbacUserRole;
import com.aielves.platform.rbac.service.RbacUserRoleService;
import com.soho.mybatis.crud.dao.MyBatisDAO;
import com.soho.mybatis.crud.service.imp.BaseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RbacUserRoleServiceImp extends BaseServiceImp<RbacUserRole, Long> implements RbacUserRoleService {
    @Autowired
    private RbacUserRoleDAO rbacuserroleDAO;

    public MyBatisDAO<RbacUserRole, Long> getMybatisDAO() {
        return rbacuserroleDAO;
    }
}