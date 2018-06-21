package com.aielves.slpg.admin.service;

import com.aielves.slpg.admin.pojo.SlpgResourceVO;
import com.soho.mybatis.exception.BizErrorEx;

/**
 * @author shadow
 * @create 2018.5.6
 */
public interface AdmSlpgResourceService {

    // 列表
    public Object list(SlpgResourceVO vo) throws BizErrorEx;

    // 查询
    public Object find(SlpgResourceVO vo) throws BizErrorEx;

    // 新增
    public Object save(SlpgResourceVO vo) throws BizErrorEx;

    // 删除
    public Object delete(SlpgResourceVO vo) throws BizErrorEx;

    // 获取资源列表
    public Object findByPid(SlpgResourceVO vo) throws BizErrorEx;

}
