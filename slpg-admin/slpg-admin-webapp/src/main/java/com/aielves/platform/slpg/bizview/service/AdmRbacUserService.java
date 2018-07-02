package com.aielves.platform.slpg.bizview.service;

import com.aielves.platform.slpg.bizview.pojo.RbacUserVO;
import com.soho.mybatis.exception.BizErrorEx;

/**
 * @author shadow
 * @create 2018.5.6
 */
public interface AdmRbacUserService {

    // 用户账号密码登录
    public Object login(RbacUserVO vo) throws BizErrorEx;

    // 修改个人账号信息
    public Object editMy(RbacUserVO vo) throws BizErrorEx;

    // 列表
    public Object list(RbacUserVO vo) throws BizErrorEx;

    // 查询
    public Object find(RbacUserVO vo) throws BizErrorEx;

    // 新增
    public Object save(RbacUserVO vo) throws BizErrorEx;

    // 删除
    public Object delete(RbacUserVO vo) throws BizErrorEx;

}
