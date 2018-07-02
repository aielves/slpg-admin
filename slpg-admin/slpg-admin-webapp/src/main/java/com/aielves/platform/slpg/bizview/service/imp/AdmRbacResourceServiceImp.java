package com.aielves.platform.slpg.bizview.service.imp;

import com.aielves.platform.rbac.dao.RbacResourceDAO;
import com.aielves.platform.rbac.domain.RbacResource;
import com.aielves.platform.slpg.bizview.pojo.RbacResourceVO;
import com.aielves.platform.slpg.bizview.service.AdmRbacResourceService;
import com.soho.mybatis.exception.BizErrorEx;
import com.soho.mybatis.exception.MybatisDAOEx;
import com.soho.mybatis.sqlcode.aconst.SortBy;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by shadow on 2018/5/16.
 */
@Component
public class AdmRbacResourceServiceImp implements AdmRbacResourceService {

    @Autowired
    private RbacResourceDAO rbacResourceDAO;

    @Override
    public Object list(RbacResourceVO vo) throws BizErrorEx {
        try {
            Cnd sql = new SQLCnd().limit(vo.getPageNo(), vo.getPageSize());
            if (vo.getPid2() != null && vo.getPid2() != -1) {
                sql.eq("pid", vo.getPid2());
            } else if (vo.getPid1() != null && vo.getPid1() != -1) {
                sql.eq("pid", vo.getPid1());
            }
            List<RbacResource> resources = rbacResourceDAO.findByCnd(sql);
            List<Map<String, Object>> retList = new ArrayList<>(resources.size());
            for (RbacResource resource : resources) {
                FastMap map = new FastMap<>()
                        .add("model", resource);
                if (resource.getType() == 2 && resource.getPid() != null) {
                    RbacResource parent1 = rbacResourceDAO.findOneByCnd(new SQLCnd().eq("id", resource.getPid()));
                    if (parent1 != null) {
                        map.add("parent1", parent1.getName());
                    }
                }
                if (resource.getType() == 3 && resource.getPid() != null) {
                    RbacResource parent2 = rbacResourceDAO.findOneByCnd(new SQLCnd().eq("id", resource.getPid()));
                    if (parent2 != null) {
                        map.add("parent2", parent2.getName());
                        if (parent2.getPid() != null) {
                            RbacResource parent1 = rbacResourceDAO.findOneByCnd(new SQLCnd().eq("id", parent2.getPid()));
                            if (parent1 != null) {
                                map.add("parent1", parent1.getName());
                            }
                        }
                    }
                }
                retList.add(map.done());
            }
            List<RbacResource> level1 = rbacResourceDAO.findByCnd(new SQLCnd().eq("pid", 0).orderby("orderno", SortBy.A));
            List<RbacResource> level2 = null;
            if (vo.getPid1() != null && vo.getPid1() != -1) {
                level2 = rbacResourceDAO.findByCnd(new SQLCnd().eq("pid", vo.getPid1()).orderby("orderno", SortBy.A));
            } else {
                level2 = new ArrayList<>();
            }
            return new FastView("resource/list").add("models", retList).add("limit", sql.getPagination()).add("vo", vo).add("level1", level1).add("level2", level2).done();
        } catch (MybatisDAOEx ex) {
            ex.printStackTrace();
        }
        throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, RetCode.BIZ_ERROR_MESSAGE);
    }

    @Override
    public Object find(RbacResourceVO vo) throws BizErrorEx {
        RbacResource model = vo.getModel();
        FastMap map = new FastMap();
        try {
            if (model != null && model.getId() != null) {
                model = rbacResourceDAO.findById(model.getId());
                if (model.getType() == 2 && model.getPid() != null) {
                    RbacResource parent1 = rbacResourceDAO.findOneByCnd(new SQLCnd().eq("id", model.getPid()));
                    if (parent1 != null) {
                        map.add("parent1", parent1.getName());
                    }
                }
                if (model.getType() == 3 && model.getPid() != null) {
                    RbacResource parent2 = rbacResourceDAO.findOneByCnd(new SQLCnd().eq("id", model.getPid()));
                    if (parent2 != null) {
                        map.add("parent2", parent2.getName());
                        if (parent2.getPid() != null) {
                            RbacResource parent1 = rbacResourceDAO.findOneByCnd(new SQLCnd().eq("id", parent2.getPid()));
                            if (parent1 != null) {
                                map.add("parent1", parent1.getName());
                            }
                        }
                    }
                }
            }
            if (model == null || model.getId() == null) {
                model = new RbacResource();
            }
        } catch (MybatisDAOEx ex) {
            throw new BizErrorEx(ex.getErrorCode(), ex.getMessage());
        }
        return new FastView("resource/edit").add("model", model).add("pid", map.done()).done();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Object save(RbacResourceVO vo) throws BizErrorEx {
        RbacResource model = vo.getModel();
        if (model == null) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "参数不能为空");
        }
        Long id = model.getId();
        String name = model.getName();
        String resume = model.getResume();
        String url = model.getUrl();
        Integer orderno = model.getOrderno();
        Integer type = model.getType();
        Integer state = model.getState();
        if (StringUtils.isEmpty(name) || name.length() > 10) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "资源名称不能为空,并小于10个字");
        }
        if (StringUtils.isEmpty(orderno)) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "优先级不能为空");
        }
        if (state == null || (state < 1 || state > 2)) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "状态类型错误");
        }
        if (type == null || (type < 1 || type > 3)) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "资源类型错误");
        }
        if (type == 3 && StringUtils.isEmpty(url)) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "节点资源访问地址不能为空");
        } else {
            url = url.replaceAll(" ", "");
        }
        if (!StringUtils.isEmpty(resume) && resume.length() > 50) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "资源简介小于50个字");
        }
        try {
            if (id == null) {
                Long pid1 = vo.getPid1();
                Long pid2 = vo.getPid2();
                if (type == 2 && pid1 == null) {
                    throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "请选择所属大模块栏目");
                }
                if (type == 3 && pid2 == null) {
                    throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "请选择所属小模块栏目");
                }
                String code = NumUtils.getIntSN(6);
                RbacResource newData = rbacResourceDAO.findOneByCnd(new SQLCnd().eq("code", code));
                if (newData != null) {
                    throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "系统正繁忙");
                }
                newData = new RbacResource();
                if (type == 2) {
                    newData.setPid(pid1);
                } else if (type == 3) {
                    newData.setPid(pid2);
                    newData.setUrl(url);
                }
                newData.setType(type);
                newData.setName(name);
                newData.setCode(code);
                newData.setOrderno(orderno);
                newData.setResume(resume);
                newData.setState(state);
                newData.setCtime(System.currentTimeMillis());
                rbacResourceDAO.insert(newData);
                return new FastMap<>().add("result", "添加成功").add("callurl", "/resource/find").done();
            } else {
                RbacResource oldData = rbacResourceDAO.findById(id);
                if (oldData == null) {
                    throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "没有找到数据");
                }
                oldData.setName(name);
                oldData.setOrderno(orderno);
                oldData.setType(type);
                oldData.setUrl(url);
                oldData.setResume(resume);
                oldData.setState(state);
                oldData.setUtime(System.currentTimeMillis());
                rbacResourceDAO.update(oldData);
                return new FastMap<>().add("result", "修改成功").add("callurl", "/resource/find?pojo[id]=" + id).done();
            }
        } catch (MybatisDAOEx ex) {
            ex.printStackTrace();
        }
        throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, RetCode.BIZ_ERROR_MESSAGE);
    }

    @Override
    public Object delete(RbacResourceVO vo) throws BizErrorEx {
        List<Long> idArr = vo.getModelIdList();
        if (idArr == null) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "参数ID不能为空");
        }
        try {
            rbacResourceDAO.delete(new SQLCnd().in("id", idArr));
            return new FastMap<>().add("result", "删除成功").done();
        } catch (MybatisDAOEx ex) {
            ex.printStackTrace();
        }
        throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, RetCode.BIZ_ERROR_MESSAGE);
    }

    @Override
    public Object findByPid(RbacResourceVO vo) throws BizErrorEx {
        RbacResource model = vo.getModel();
        if (model == null || model.getPid() == null) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "参数PID不能为空");
        }
        try {
            return rbacResourceDAO.findByCnd(new SQLCnd().eq("pid", model.getPid()).orderby("orderno", SortBy.A));
        } catch (MybatisDAOEx ex) {
            ex.printStackTrace();
        }
        throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, RetCode.BIZ_ERROR_MESSAGE);
    }

}
