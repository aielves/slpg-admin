package com.aielves.slpg.dao.imp;

import com.aielves.slpg.dao.SlpgUserRoleDAO;
import com.aielves.slpg.domain.SlpgUserRole;
import com.soho.mybatis.crud.dao.imp.MyBatisDAOImp;
import org.springframework.stereotype.Repository;

@Repository
public class SlpgUserRoleDAOImp extends MyBatisDAOImp<SlpgUserRole> implements SlpgUserRoleDAO {
}