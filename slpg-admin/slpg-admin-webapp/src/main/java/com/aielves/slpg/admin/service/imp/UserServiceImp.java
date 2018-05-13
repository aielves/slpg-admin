package com.aielves.slpg.admin.service.imp;

import com.aielves.slpg.admin.aconst.ErrorCode;
import com.aielves.slpg.admin.service.UserService;
import com.aielves.slpg.dao.SlpgUserDAO;
import com.aielves.slpg.domain.SlpgUser;
import com.soho.mybatis.exception.BizErrorEx;
import com.soho.mybatis.sqlcode.condition.imp.SQLCnd;
import com.soho.spring.model.RGX;
import com.soho.spring.model.RetCode;
import com.soho.spring.mvc.model.FastMap;
import com.soho.spring.shiro.utils.SessionUtils;
import com.soho.spring.utils.RGXUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author shadow
 * @create 2018.5.6
 */
@Component
public class UserServiceImp implements UserService {

    @Autowired
    private SlpgUserDAO slpgUserDAO;

    @Override
    public Object login(String username, String password, boolean rememberMe) throws BizErrorEx {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        try {
            SessionUtils.login(token, false);
            return new FastMap<>().add("result", "登录成功").add("callurl", "/user/index").done();
        } catch (AuthenticationException e) {
            throw new BizErrorEx(ErrorCode.LOGIN_ERROR, e.getMessage());
        } catch (Exception e) {
            throw new BizErrorEx(ErrorCode.LOGIN_ERROR, "登录系统正繁忙");
        }
    }

    @Override
    public Object myself_edit(SlpgUser user) throws BizErrorEx {
        try {
            String nickname = user.getNickname();
            Integer sex = user.getSex();
            String email = user.getEmail();
            String headimg = user.getHeadimg();
            if (StringUtils.isEmpty(nickname) || nickname.length() > 8) {
                throw new BizErrorEx("昵称不能为空,8个字以内");
            }
            if (sex == null || sex < 1 || sex > 2) {
                throw new BizErrorEx("性别不合法");
            }
            if (!StringUtils.isEmpty(email) && !RGXUtils.matches(email, RGX.EMAIL)) {
                throw new BizErrorEx("邮箱不合法");
            }
            if (!StringUtils.isEmpty(headimg) && !RGXUtils.matchImgUrl(headimg)) {
                throw new BizErrorEx("图片不合法");
            }
            SlpgUser updater = slpgUserDAO.findOneByCnd(new SQLCnd().eq("id", SessionUtils.getUserId()).eq("state", 1));
            if (updater == null) {
                throw new BizErrorEx(RetCode.BIZ_ERROR_STATUS, "用户数据不存在");
            }
            updater.setNickname(nickname);
            updater.setSex(sex);
            updater.setEmail(email);
            updater.setHeadimg(headimg);
            updater.setUtime(System.currentTimeMillis());
            slpgUserDAO.update(updater);
            SessionUtils.setUser(updater, updater.getUsername());
            return new FastMap<String>().add("result", "修改成功").add("callurl", "/user/me/myself").done();
        } catch (Exception e) {
            return BizErrorEx.transform(e);
        }
    }

}
