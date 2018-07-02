package com.aielves.platform.slpg.bizview.service.imp;

import com.aielves.platform.rbac.dao.RbacRoleDAO;
import com.aielves.platform.rbac.domain.RbacRole;
import com.aielves.platform.slpg.bizview.pojo.RbacRoleVO;
import com.aielves.platform.slpg.bizview.service.AdmRbacRoleService;
import com.soho.mybatis.exception.BizErrorEx;
import com.soho.mybatis.exception.MybatisDAOEx;
import com.soho.mybatis.sqlcode.condition.Cnd;
import com.soho.mybatis.sqlcode.condition.imp.SQLCnd;
import com.soho.spring.model.RetCode;
import com.soho.spring.mvc.model.FastMap;
import com.soho.spring.mvc.model.FastView;
import com.soho.spring.utils.NumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by shadow on 2018/5/16.
 */
@Component
public class AdmRbacRoleServiceImp implements AdmRbacRoleService {

    @Autowired
    private RbacRoleDAO rbacRoleDAO;

    @Override
    public Object list(RbacRoleVO vo) throws BizErrorEx {
        try {
            Cnd sql = new SQLCnd().limit(vo.getPageNo(), vo.getPageSize());
            List<RbacRole> list = rbacRoleDAO.findByCnd(sql);
            return new FastView("role/list").add("models", list).add("limit", sql.getPagination()).add("vo", vo).done();
        } catch (MybatisDAOEx ex) {
            ex.printStackTrace();
        }
        throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, RetCode.BIZ_ERROR_MESSAGE);
    }

    @Override
    public Object find(RbacRoleVO vo) throws BizErrorEx {
        RbacRole model = vo.getModel();
        try {
            if (model != null && model.getId() != null) {
                model = rbacRoleDAO.findById(model.getId());
            }
            if (model == null || model.getId() == null) {
                model = new RbacRole();
            }
        } catch (MybatisDAOEx ex) {
            throw new BizErrorEx(ex.getErrorCode(), ex.getMessage());
        }
        return new FastView("role/edit").add("model", model).done();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Object save(RbacRoleVO vo) throws BizErrorEx {
        RbacRole model = vo.getModel();
        if (model == null) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "参数不能为空");
        }
        Long id = model.getId();
        String name = model.getName();
        Integer state = model.getState();
        if (StringUtils.isEmpty(name)) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "");
        }
        if (StringUtils.isEmpty(name) || name.length() > 8) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "角色名称不能为空,并小于8个字");
        }
        if (state == null || (state < 1 || state > 2)) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "状态类型错误");
        }
        try {
            if (id == null) {
                String code = NumUtils.getIntSN(6);
                RbacRole newData = rbacRoleDAO.findOneByCnd(new SQLCnd().eq("code", code));
                if (newData != null) {
                    throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "系统正繁忙");
                }
                newData = new RbacRole();
                newData.setName(name);
                newData.setCode(code);
                newData.setState(state);
                newData.setCtime(System.currentTimeMillis());
                rbacRoleDAO.insert(newData);
                return new FastMap<>().add("result", "添加成功").add("callurl", "/role/find").done();
            } else {
                RbacRole oldData = rbacRoleDAO.findById(id);
                if (oldData == null) {
                    throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "没有找到数据");
                }
                oldData.setName(name);
                oldData.setState(state);
                oldData.setUtime(System.currentTimeMillis());
                rbacRoleDAO.update(oldData);
                return new FastMap<>().add("result", "修改成功").add("callurl", "/role/find?pojo[id]=" + id).done();
            }
        } catch (MybatisDAOEx ex) {
            ex.printStackTrace();
        }
        throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, RetCode.BIZ_ERROR_MESSAGE);
    }

    @Override
    public Object delete(RbacRoleVO vo) throws BizErrorEx {
        List<Long> idArr = vo.getModelIdList();
        if (idArr == null) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "参数ID不能为空");
        }
        try {
            rbacRoleDAO.delete(new SQLCnd().in("id", idArr));
            return new FastMap<>().add("result", "删除成功").done();
        } catch (MybatisDAOEx ex) {
            ex.printStackTrace();
        }
        throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, RetCode.BIZ_ERROR_MESSAGE);
    }

}
