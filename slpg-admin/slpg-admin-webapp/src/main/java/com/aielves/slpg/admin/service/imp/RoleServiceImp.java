package com.aielves.slpg.admin.service.imp;

import com.aielves.slpg.admin.pojo.SlpgRoleVO;
import com.aielves.slpg.admin.service.RoleService;
import com.aielves.slpg.dao.SlpgRoleDAO;
import com.aielves.slpg.domain.SlpgRole;
import com.soho.mybatis.exception.BizErrorEx;
import com.soho.mybatis.exception.MybatisDAOEx;
import com.soho.mybatis.sqlcode.condition.imp.SQLCnd;
import com.soho.spring.model.RetCode;
import com.soho.spring.mvc.model.FastMap;
import com.soho.spring.mvc.model.FastView;
import com.soho.spring.utils.NumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Created by shadow on 2018/5/16.
 */
@Component
public class RoleServiceImp implements RoleService {

    @Autowired
    private SlpgRoleDAO slpgRoleDAO;

    @Override
    public Object list(SlpgRoleVO vo) throws BizErrorEx {
        return new FastView("role/list").done();
    }

    @Override
    public Object find(SlpgRoleVO vo) throws BizErrorEx {
        SlpgRole model = vo.getModel();
        try {
            if (model != null && model.getId() != null) {
                model = slpgRoleDAO.findById(model.getId());
            }
            if (model == null || model.getId() == null) {
                model = new SlpgRole();
            }
        } catch (MybatisDAOEx ex) {
            throw new BizErrorEx(ex.getErrorCode(), ex.getMessage());
        }
        return new FastView("role/edit").add("model", model).done();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Object save(SlpgRoleVO vo) throws BizErrorEx {
        SlpgRole model = vo.getModel();
        if (model == null) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "参数不能为空");
        }
        Long id = model.getId();
        String name = model.getName();
        Integer state = model.getState();
        if (StringUtils.isEmpty(name)) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "角色名称不能为空");
        }
        if (name.length() > 8) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "角色名称最多8个字符");
        }
        if (StringUtils.isEmpty(state)) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "状态类型不能为空");
        }
        if (state < 1 || state > 2) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "状态类型异常");
        }
        try {
            if (id == null) {
                String code = NumUtils.getIntSN(6);
                SlpgRole newData = slpgRoleDAO.findOneByCnd(new SQLCnd().eq("code", code));
                if (newData != null) {
                    throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "系统正繁忙");
                }
                newData = new SlpgRole();
                newData.setName(name);
                newData.setCode(code);
                newData.setState(state);
                newData.setCtime(System.currentTimeMillis());
                slpgRoleDAO.insert(newData);
                return new FastMap<>().add("result", "添加成功").add("callurl", "/role/find").done();
            } else {
                SlpgRole oldData = slpgRoleDAO.findById(id);
                if (oldData == null) {
                    throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "没有找到数据");
                }
                oldData.setName(name);
                oldData.setState(state);
                oldData.setUtime(System.currentTimeMillis());
                slpgRoleDAO.update(oldData);
                return new FastMap<>().add("result", "修改成功").add("callurl", "/role/find?pojo[id]=" + id).done();
            }
        } catch (MybatisDAOEx ex) {
            ex.printStackTrace();
        }
        throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, RetCode.BIZ_ERROR_MESSAGE);
    }

    @Override
    public Object delete(SlpgRoleVO vo) throws BizErrorEx {
        return null;
    }
}
