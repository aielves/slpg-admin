package com.aielves.slpg.admin.service.imp;

import com.aielves.slpg.admin.pojo.SlpgResourceVO;
import com.aielves.slpg.admin.service.AdmSlpgResourceService;
import com.aielves.slpg.dao.SlpgResourceDAO;
import com.aielves.slpg.domain.SlpgResource;
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
public class AdmSlpgResourceServiceImp implements AdmSlpgResourceService {

    @Autowired
    private SlpgResourceDAO slpgResourceDAO;

    @Override
    public Object list(SlpgResourceVO vo) throws BizErrorEx {
        try {
            Cnd sql = new SQLCnd().limit(vo.getPageNo(), vo.getPageSize());
            List<SlpgResource> list = slpgResourceDAO.findByCnd(sql);
            return new FastView("resource/list").add("models", list).add("limit", sql.getPagination()).add("vo", vo).done();
        } catch (MybatisDAOEx ex) {
            ex.printStackTrace();
        }
        throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, RetCode.BIZ_ERROR_MESSAGE);
    }

    @Override
    public Object find(SlpgResourceVO vo) throws BizErrorEx {
        SlpgResource model = vo.getModel();
        try {
            if (model != null && model.getId() != null) {
                model = slpgResourceDAO.findById(model.getId());
            }
            if (model == null || model.getId() == null) {
                model = new SlpgResource();
            }
        } catch (MybatisDAOEx ex) {
            throw new BizErrorEx(ex.getErrorCode(), ex.getMessage());
        }
        return new FastView("resource/edit").add("model", model).done();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Object save(SlpgResourceVO vo) throws BizErrorEx {
        SlpgResource model = vo.getModel();
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
        if (StringUtils.isEmpty(name)) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "资源名称不能为空");
        }
        if (name.length() > 10) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "资源名称最多10个字符");
        }
        if (StringUtils.isEmpty(orderno)) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "优先级不能为空");
        }
        if (StringUtils.isEmpty(state)) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "状态类型不能为空");
        }
        if (state < 1 || state > 2) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "状态类型异常");
        }
        if (StringUtils.isEmpty(type)) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "资源类型不能为空");
        }
        if (type < 1 || type > 3) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "资源类型异常");
        }
        if (type == 3 && StringUtils.isEmpty(url)) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "分模块资源请求地址不能为空");
        }
        if (!StringUtils.isEmpty(resume) && resume.length() > 50) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "资源简介最多50个字符");
        }
        try {
            if (id == null) {
                String code = NumUtils.getIntSN(6);
                SlpgResource newData = slpgResourceDAO.findOneByCnd(new SQLCnd().eq("code", code));
                if (newData != null) {
                    throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "系统正繁忙");
                }
                newData = new SlpgResource();
                newData.setPid(0l);
                newData.setName(name);
                newData.setCode(code);
                newData.setOrderno(orderno);
                newData.setType(type);
                if (type == 3) {
                    newData.setUrl(url);
                }
                newData.setResume(resume);
                newData.setState(state);
                newData.setCtime(System.currentTimeMillis());
                slpgResourceDAO.insert(newData);
                return new FastMap<>().add("result", "添加成功").add("callurl", "/resource/find").done();
            } else {
                SlpgResource oldData = slpgResourceDAO.findById(id);
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
                slpgResourceDAO.update(oldData);
                return new FastMap<>().add("result", "修改成功").add("callurl", "/resource/find?pojo[id]=" + id).done();
            }
        } catch (MybatisDAOEx ex) {
            ex.printStackTrace();
        }
        throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, RetCode.BIZ_ERROR_MESSAGE);
    }

    @Override
    public Object delete(SlpgResourceVO vo) throws BizErrorEx {
        List<Long> idArr = vo.getModelIdList();
        if (idArr == null) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "参数ID不能为空");
        }
        try {
            slpgResourceDAO.delete(new SQLCnd().in("id", idArr));
            return new FastMap<>().add("result", "删除成功").done();
        } catch (MybatisDAOEx ex) {
            ex.printStackTrace();
        }
        throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, RetCode.BIZ_ERROR_MESSAGE);
    }

    @Override
    public Object findByPid(SlpgResourceVO vo) throws BizErrorEx {
        SlpgResource model = vo.getModel();
        if (model == null || model.getPid() == null) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "参数PID不能为空");
        }
        try {
            return slpgResourceDAO.findByCnd(new SQLCnd().eq("pid", model.getPid()));
        } catch (MybatisDAOEx ex) {
            ex.printStackTrace();
        }
        throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, RetCode.BIZ_ERROR_MESSAGE);
    }
}
