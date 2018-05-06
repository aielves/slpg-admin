package com.aielves.slpg.admin.shiro;

import com.aielves.slpg.admin.shiro.realm.ShiroAuthorizingRealm;
import com.soho.spring.shiro.initialize.InitDefinition;
import com.soho.spring.shiro.initialize.RuleChain;
import com.soho.spring.shiro.initialize.ShiroInitializeService;
import org.apache.shiro.realm.Realm;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ShiroAuthorizingRealm shiroAuthorizingRealm;

    @Override
    public InitDefinition initFilterChainDefinition() {
        InitDefinition definition = new InitDefinition();
        definition.setLoginUrl("/user/loginInit");
        definition.setSuccessUrl("/user/index");
        definition.setUnauthorizedUrl("/403");
        List<RuleChain> ruleChains = new ArrayList<>();
        // 无权限校验
        ruleChains.add(new RuleChain("/static/**", "anon"));
        ruleChains.add(new RuleChain("/user/loginInit", "anon"));
        ruleChains.add(new RuleChain("/user/login", "anon"));
        // 踢出会话,角色资源校验
        ruleChains.add(new RuleChain("/user/index", "kickout,role[admin]"));
        ruleChains.add(new RuleChain("/**", "kickout,authc"));
        // definition.setRuleChains(WCCUtils.ruleChainComparator(ruleChains));
        definition.setRuleChains(ruleChains);
        return definition;
    }

    @Override
    public List<Realm> initRealms() {
        List<Realm> realms = new ArrayList<>();
        realms.add(shiroAuthorizingRealm);
        return realms;
    }

    @Override
    public Map<String, Filter> initFilters() {
        return new LinkedHashMap<>();
    }

}
