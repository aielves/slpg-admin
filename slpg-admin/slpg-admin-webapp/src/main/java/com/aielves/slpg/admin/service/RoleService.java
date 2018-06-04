package com.aielves.slpg.admin.service;

import com.aielves.slpg.admin.pojo.SlpgRoleVO;
import com.soho.mybatis.exception.BizErrorEx;

/**
 * @author shadow
 * @create 2018.5.6
 */
public interface RoleService {

    // 列表
    public Object list(SlpgRoleVO vo) throws BizErrorEx;

    // 查询
    public Object find(SlpgRoleVO vo) throws BizErrorEx;

    // 新增
    public Object save(SlpgRoleVO vo) throws BizErrorEx;

    // 删除
    public Object delete(SlpgRoleVO vo) throws BizErrorEx;

}
