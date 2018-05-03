package com.aielves.slpg.dao.imp;

import com.aielves.slpg.dao.SlpgRoleDAO;
import com.aielves.slpg.domain.SlpgRole;
import com.soho.mybatis.crud.dao.imp.MyBatisDAOImp;
import org.springframework.stereotype.Repository;

@Repository
public class SlpgRoleDAOImp extends MyBatisDAOImp<SlpgRole> implements SlpgRoleDAO {
}