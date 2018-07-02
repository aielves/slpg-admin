package com.aielves.platform.rbac.service.imp;

import com.aielves.platform.rbac.dao.RbacUserDAO;
import com.aielves.platform.rbac.domain.RbacUser;
import com.aielves.platform.rbac.service.RbacUserService;
import com.soho.mybatis.crud.dao.MyBatisDAO;
import com.soho.mybatis.crud.service.imp.BaseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RbacUserServiceImp extends BaseServiceImp<RbacUser, Long> implements RbacUserService {
    @Autowired
    private RbacUserDAO rbacuserDAO;

    public MyBatisDAO<RbacUser, Long> getMybatisDAO() {
        return rbacuserDAO;
    }
}