package com.aielves.slpg.dao.imp;

import com.aielves.slpg.dao.SlpgUserDAO;
import com.aielves.slpg.domain.SlpgUser;
import com.soho.mybatis.crud.dao.imp.MyBatisDAOImp;
import org.springframework.stereotype.Repository;

@Repository
public class SlpgUserDAOImp extends MyBatisDAOImp<SlpgUser> implements SlpgUserDAO {
}