package com.aielves.platform.rbac.service.imp;

import com.aielves.platform.rbac.dao.RbacResourceDAO;
import com.aielves.platform.rbac.domain.RbacResource;
import com.aielves.platform.rbac.service.RbacResourceService;
import com.soho.mybatis.crud.dao.MyBatisDAO;
import com.soho.mybatis.crud.service.imp.BaseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RbacResourceServiceImp extends BaseServiceImp<RbacResource, Long> implements RbacResourceService {
    @Autowired
    private RbacResourceDAO rbacresourceDAO;

    public MyBatisDAO<RbacResource, Long> getMybatisDAO() {
        return rbacresourceDAO;
    }
}