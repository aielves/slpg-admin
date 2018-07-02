package com.aielves.platform.rbac.service.imp;

import com.aielves.platform.rbac.dao.RbacRoleResourceDAO;
import com.aielves.platform.rbac.domain.RbacRoleResource;
import com.aielves.platform.rbac.service.RbacRoleResourceService;
import com.soho.mybatis.crud.dao.MyBatisDAO;
import com.soho.mybatis.crud.service.imp.BaseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RbacRoleResourceServiceImp extends BaseServiceImp<RbacRoleResource, Long> implements RbacRoleResourceService {
    @Autowired
    private RbacRoleResourceDAO rbacroleresourceDAO;

    public MyBatisDAO<RbacRoleResource, Long> getMybatisDAO() {
        return rbacroleresourceDAO;
    }
}