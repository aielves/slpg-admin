package com.aielves.slpg.admin.pojo;

import com.aielves.slpg.domain.SlpgResource;
import com.aielves.slpg.domain.SlpgUser;
import com.soho.spring.model.ReqData;

/**
 * Created by shadow on 2018/6/4.
 */
public class SlpgResourceVO extends ReqData<SlpgUser, SlpgResource> {

    private Long pid1;
    private Long pid2;

    public Long getPid1() {
        return pid1;
    }

    public void setPid1(Long pid1) {
        this.pid1 = pid1;
    }

    public Long getPid2() {
        return pid2;
    }

    public void setPid2(Long pid2) {
        this.pid2 = pid2;
    }

}
