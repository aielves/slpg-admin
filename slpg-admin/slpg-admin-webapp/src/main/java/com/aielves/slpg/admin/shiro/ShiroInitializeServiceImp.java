package com.aielves.slpg.admin.shiro;

import com.aielves.slpg.admin.shiro.realm.ShiroAuthorizingRealm;
import com.soho.spring.shiro.initialize.InitDefinition;
import com.soho.spring.shiro.initialize.RuleChain;
import com.soho.spring.shiro.initialize.ShiroInitializeService;
import com.soho.spring.utils.WCCUtils;
import org.apache.shiro.realm.Realm;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shadow
 */
@Component
public class ShiroInitializeServiceImp implements ShiroInitializeService {

    @Override
    public InitDefinition initFilterChainDefinition() {
        InitDefinition definition = new InitDefinition();
        definition.setLoginUrl("/dog/login");
        definition.setSuccessUrl("/dog/findOne");
        definition.setUnauthorizedUrl("/403");
        List<RuleChain> ruleChains = new ArrayList<>();
        ruleChains.add(new RuleChain("/dog/findOne", "kickout,role[user]"));
        ruleChains.add(new RuleChain("/dog/findAll", "kickout,role[user]"));
        definition.setRuleChains(WCCUtils.ruleChainComparator(ruleChains));
        return definition;
    }

    @Override
    public List<Realm> initRealms() {
        List<Realm> realms = new ArrayList<>();
        realms.add(new ShiroAuthorizingRealm());
        return realms;
    }

    @Override
    public Map<String, Filter> initFilters() {
        return new LinkedHashMap<>();
    }

}
