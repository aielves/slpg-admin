package com.aielves.platform.rbac.dao.imp;

import com.aielves.platform.rbac.dao.RbacUserDAO;
import com.aielves.platform.rbac.domain.RbacUser;
import com.soho.mybatis.crud.dao.imp.MyBatisDAOImp;
import org.springframework.stereotype.Repository;

@Repository
public class RbacUserDAOImp extends MyBatisDAOImp<RbacUser> implements RbacUserDAO {
}