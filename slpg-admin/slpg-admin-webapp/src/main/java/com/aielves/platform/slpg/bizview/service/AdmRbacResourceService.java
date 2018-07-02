package com.aielves.platform.slpg.bizview.service;

import com.aielves.platform.slpg.bizview.pojo.RbacResourceVO;
import com.soho.mybatis.exception.BizErrorEx;

/**
 * @author shadow
 * @create 2018.5.6
 */
public interface AdmRbacResourceService {

    // 列表
    public Object list(RbacResourceVO vo) throws BizErrorEx;

    // 查询
    public Object find(RbacResourceVO vo) throws BizErrorEx;

    // 新增
    public Object save(RbacResourceVO vo) throws BizErrorEx;

    // 删除
    public Object delete(RbacResourceVO vo) throws BizErrorEx;

    // 获取资源列表
    public Object findByPid(RbacResourceVO vo) throws BizErrorEx;

}
