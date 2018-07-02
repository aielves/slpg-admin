package com.aielves.platform.rbac.dao.imp;

import com.aielves.platform.rbac.dao.RbacRoleDAO;
import com.aielves.platform.rbac.domain.RbacRole;
import com.soho.mybatis.crud.dao.imp.MyBatisDAOImp;
import org.springframework.stereotype.Repository;

@Repository
public class RbacRoleDAOImp extends MyBatisDAOImp<RbacRole> implements RbacRoleDAO {
}