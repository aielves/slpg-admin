package com.aielves.platform.rbac.dao.imp;

import com.aielves.platform.rbac.dao.RbacRoleResourceDAO;
import com.aielves.platform.rbac.domain.RbacRoleResource;
import com.soho.mybatis.crud.dao.imp.MyBatisDAOImp;
import org.springframework.stereotype.Repository;

@Repository
public class RbacRoleResourceDAOImp extends MyBatisDAOImp<RbacRoleResource> implements RbacRoleResourceDAO {
}