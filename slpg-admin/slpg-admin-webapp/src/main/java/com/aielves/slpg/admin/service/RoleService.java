package com.aielves.slpg.admin.service;

import com.aielves.slpg.domain.SlpgRole;
import com.soho.mybatis.exception.BizErrorEx;
import com.soho.spring.model.ReqData;

/**
 * @author shadow
 * @create 2018.5.6
 */
public interface RoleService {

    // 列表
    public Object list(ReqData reqData) throws BizErrorEx;

    // 查询
    public Object get(SlpgRole model) throws BizErrorEx;

    // 新增
    public Object add(SlpgRole model) throws BizErrorEx;

    // 修改
    public Object edit(SlpgRole model) throws BizErrorEx;

    // 删除
    public Object delete(SlpgRole model) throws BizErrorEx;

}
