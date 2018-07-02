package com.aielves.platform.rbac.dao.imp;

import com.aielves.platform.rbac.dao.RbacUserRoleDAO;
import com.aielves.platform.rbac.domain.RbacUserRole;
import com.soho.mybatis.crud.dao.imp.MyBatisDAOImp;
import org.springframework.stereotype.Repository;

@Repository
public class RbacUserRoleDAOImp extends MyBatisDAOImp<RbacUserRole> implements RbacUserRoleDAO {
}