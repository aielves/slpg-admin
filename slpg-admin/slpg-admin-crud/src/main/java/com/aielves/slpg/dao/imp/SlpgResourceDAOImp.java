package com.aielves.slpg.dao.imp;

import com.aielves.slpg.dao.SlpgResourceDAO;
import com.aielves.slpg.domain.SlpgResource;
import com.soho.mybatis.crud.dao.imp.MyBatisDAOImp;
import org.springframework.stereotype.Repository;

@Repository
public class SlpgResourceDAOImp extends MyBatisDAOImp<SlpgResource> implements SlpgResourceDAO {
}