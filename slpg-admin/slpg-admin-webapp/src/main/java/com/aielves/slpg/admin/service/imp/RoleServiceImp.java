package com.aielves.slpg.admin.service.imp;

import com.aielves.slpg.admin.service.RoleService;
import com.aielves.slpg.domain.SlpgRole;
import com.soho.mybatis.exception.BizErrorEx;
import com.soho.spring.model.ReqData;
import com.soho.spring.mvc.model.FastView;
import org.springframework.stereotype.Component;

/**
 * Created by shadow on 2018/5/16.
 */
@Component
public class RoleServiceImp implements RoleService {

    @Override
    public Object list(ReqData reqData) throws BizErrorEx {
        return new FastView("role/list").done();
    }

    @Override
    public Object get(SlpgRole model) throws BizErrorEx {
        return null;
    }

    @Override
    public Object add(SlpgRole model) throws BizErrorEx {
        return null;
    }

    @Override
    public Object edit(SlpgRole model) throws BizErrorEx {
        return null;
    }

    @Override
    public Object delete(SlpgRole model) throws BizErrorEx {
        return null;
    }

}
