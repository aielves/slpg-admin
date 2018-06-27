package com.aielves.slpg.admin.service;

import com.aielves.slpg.admin.pojo.SlpgUserVO;
import com.soho.mybatis.exception.BizErrorEx;

/**
 * @author shadow
 * @create 2018.5.6
 */
public interface AdmSlpgUserService {

    // 列表
    public Object list(SlpgUserVO vo) throws BizErrorEx;

    // 查询
    public Object find(SlpgUserVO vo) throws BizErrorEx;

    // 新增
    public Object save(SlpgUserVO vo) throws BizErrorEx;

    // 删除
    public Object delete(SlpgUserVO vo) throws BizErrorEx;

}
