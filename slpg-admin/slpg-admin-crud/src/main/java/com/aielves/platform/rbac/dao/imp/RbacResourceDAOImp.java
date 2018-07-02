package com.aielves.platform.rbac.dao.imp;

import com.aielves.platform.rbac.dao.RbacResourceDAO;
import com.aielves.platform.rbac.domain.RbacResource;
import com.soho.mybatis.crud.dao.imp.MyBatisDAOImp;
import org.springframework.stereotype.Repository;

@Repository
public class RbacResourceDAOImp extends MyBatisDAOImp<RbacResource> implements RbacResourceDAO {
}