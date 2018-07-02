package com.aielves.platform.slpg.bizview.service;

import com.aielves.platform.slpg.bizview.pojo.RbacRoleVO;
import com.soho.mybatis.exception.BizErrorEx;

/**
 * @author shadow
 * @create 2018.5.6
 */
public interface AdmRbacRoleService {

    // 列表
    public Object list(RbacRoleVO vo) throws BizErrorEx;

    // 查询
    public Object find(RbacRoleVO vo) throws BizErrorEx;

    // 新增
    public Object save(RbacRoleVO vo) throws BizErrorEx;

    // 删除
    public Object delete(RbacRoleVO vo) throws BizErrorEx;

}
