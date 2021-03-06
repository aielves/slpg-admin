package com.aielves.platform.slpg.bizview.service.imp;

import com.aielves.platform.rbac.dao.RbacUserDAO;
import com.aielves.platform.rbac.domain.RbacUser;
import com.aielves.platform.slpg.bizview.pojo.RbacUserVO;
import com.aielves.platform.slpg.bizview.service.AdmRbacUserService;
import com.soho.mybatis.exception.BizErrorEx;
import com.soho.mybatis.exception.MybatisDAOEx;
import com.soho.mybatis.sqlcode.condition.Cnd;
import com.soho.mybatis.sqlcode.condition.imp.SQLCnd;
import com.soho.spring.model.RetCode;
import com.soho.spring.mvc.model.FastMap;
import com.soho.spring.mvc.model.FastView;
import com.soho.spring.security.EncryptService;
import com.soho.spring.shiro.utils.SessionUtils;
import com.soho.spring.utils.RGXUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by shadow on 2018/5/16.
 */
@Component
public class AdmRbacUserServiceImp implements AdmRbacUserService {

    @Autowired
    private RbacUserDAO rbacUserDAO;

    @Autowired
    private EncryptService encryptService;

    @Override
    public Object login(RbacUserVO vo) throws BizErrorEx {
        RbacUser model = vo.getModel();
        if (model == null) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "参数不能为空");
        }
        UsernamePasswordToken token = new UsernamePasswordToken(model.getMobile(), model.getPassword(), false);
        try {
            SessionUtils.login(token, false);
            return new FastMap<>().add("result", "登录成功").add("callurl", "/user/index").done();
        } catch (AuthenticationException e) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, e.getMessage());
        } catch (Exception e) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, RetCode.BIZ_ERROR_MESSAGE);
        }
    }

    @Override
    public Object editMy(RbacUserVO vo) throws BizErrorEx {
        try {
            RbacUser model = vo.getModel();
            if (model == null) {
                throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "参数不能为空");
            }
            String nickname = model.getNickname();
            String headimg = model.getHeadimg();
            if (StringUtils.isEmpty(nickname) || nickname.length() > 8) {
                throw new BizErrorEx("昵称不能为空,8个字以内");
            }
            if (!StringUtils.isEmpty(headimg) && !RGXUtils.isImgUrl(headimg)) {
                throw new BizErrorEx("图片不合法");
            }
            RbacUser updater = rbacUserDAO.findOneByCnd(new SQLCnd().eq("id", SessionUtils.getUserId()).eq("state", 1));
            if (updater == null) {
                throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "用户数据不存在");
            }
            updater.setNickname(nickname);
            updater.setHeadimg(headimg);
            updater.setUtime(System.currentTimeMillis());
            rbacUserDAO.update(updater);
            SessionUtils.setUser(updater, updater.getUsername());
            return new FastMap<String>().add("result", "修改成功").add("callurl", "/user/editMyInit").done();
        } catch (Exception e) {
            return BizErrorEx.transform(e);
        }
    }

    @Override
    public Object list(RbacUserVO vo) throws BizErrorEx {
        try {
            Cnd sql = new SQLCnd().noteq("utype", 1).limit(vo.getPageNo(), vo.getPageSize());
            RbacUser model = vo.getModel();
            if (model != null) {
                if (model.getUtype() != null && model.getUtype() != -1) {
                    sql.eq("utype", model.getUtype());
                }
                if (model.getState() != null && model.getState() != -1) {
                    sql.eq("state", model.getState());
                }
                if (!StringUtils.isEmpty(model.getUsername())) {
                    sql.or(new SQLCnd().eq("username", model.getUsername()), new SQLCnd().eq("mobile", model.getUsername()));
                }
            }
            List<RbacUser> list = rbacUserDAO.findByCnd(sql);
            return new FastView("user/list").add("models", list).add("limit", sql.getPagination()).add("vo", vo).done();
        } catch (MybatisDAOEx ex) {
            ex.printStackTrace();
        }
        throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, RetCode.BIZ_ERROR_MESSAGE);
    }

    @Override
    public Object find(RbacUserVO vo) throws BizErrorEx {
        RbacUser model = vo.getModel();
        try {
            if (model != null && model.getId() != null) {
                model = rbacUserDAO.findById(model.getId());
            }
            if (model == null || model.getId() == null) {
                model = new RbacUser();
            }
        } catch (MybatisDAOEx ex) {
            throw new BizErrorEx(ex.getErrorCode(), ex.getMessage());
        }
        return new FastView("user/edit").add("model", model).done();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Object save(RbacUserVO vo) throws BizErrorEx {
        RbacUser model = vo.getModel();
        if (model == null) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "参数不能为空");
        }
        Long id = model.getId();
        String mobile = model.getMobile();
        String password = model.getPassword();
        String confirmPassword = vo.getConfirmPassword();
        String nickname = model.getNickname();
        Integer utype = model.getUtype();
        Integer state = model.getState();
        if (!RGXUtils.isMobile(mobile)) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "手机号码不合法");
        }
        if (StringUtils.isEmpty(nickname) || nickname.length() > 10) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "用户昵称不能为空,并小于10个字");
        }
        if (StringUtils.isEmpty(utype) || (utype < 2 || utype > 3)) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "账号类型错误");
        }
        if (StringUtils.isEmpty(state)) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "状态类型不能为空");
        }
        if (state == null || (state < 1 || state > 2)) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "状态类型错误");
        }
        try {
            if (id == null) {
                if (!RGXUtils.isPassword(password)) {
                    throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "密码不合法,6-18位");
                }
                if (!password.equals(confirmPassword)) {
                    throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "两次密码输入不一致");
                }
                RbacUser newData = new RbacUser();
                newData.setMobile(mobile);
                newData.setUsername("m" + mobile);
                newData.setPassword(encryptService.md5(password));
                newData.setUid(encryptService.md5(mobile));
                newData.setNickname(nickname);
                newData.setHeadimg("http://static.cartoonai.com/1/152895634398877206.jpg");
                newData.setUtype(utype);
                newData.setState(state);
                newData.setCtime(System.currentTimeMillis());
                rbacUserDAO.insert(newData);
                return new FastMap<>().add("result", "添加成功").add("callurl", "/user/find").done();
            } else {
                RbacUser oldData = rbacUserDAO.findById(id);
                if (oldData == null) {
                    throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "没有找到数据");
                }
                if (!StringUtils.isEmpty(password)) {
                    if (!RGXUtils.isPassword(password)) {
                        throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "密码不合法,6-18位");
                    }
                    if (!password.equals(confirmPassword)) {
                        throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "两次密码输入不一致");
                    }
                    oldData.setPassword(encryptService.md5(password));
                }
                oldData.setNickname(nickname);
                oldData.setUtype(utype);
                oldData.setState(state);
                oldData.setUtime(System.currentTimeMillis());
                rbacUserDAO.update(oldData);
                return new FastMap<>().add("result", "修改成功").add("callurl", "/user/find?pojo[id]=" + id).done();
            }
        } catch (MybatisDAOEx ex) {
            ex.printStackTrace();
        }
        throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, RetCode.BIZ_ERROR_MESSAGE);
    }

    @Override
    public Object delete(RbacUserVO vo) throws BizErrorEx {
        List<Long> idArr = vo.getModelIdList();
        if (idArr == null) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "参数ID不能为空");
        }
        try {
            rbacUserDAO.delete(new SQLCnd().in("id", idArr));
            return new FastMap<>().add("result", "删除成功").done();
        } catch (MybatisDAOEx ex) {
            ex.printStackTrace();
        }
        throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, RetCode.BIZ_ERROR_MESSAGE);
    }

}
