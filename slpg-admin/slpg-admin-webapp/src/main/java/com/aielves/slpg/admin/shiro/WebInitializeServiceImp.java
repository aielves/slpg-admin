package com.aielves.slpg.admin.shiro;

import com.aielves.slpg.admin.freemarker.MenuTag;
import com.aielves.slpg.admin.shiro.realm.ShiroAuthorizingRealm;
import com.soho.aliyun.ggk.interceptor.KillRobotInterceptor;
import com.soho.spring.mvc.interceptor.FormTokenInterceptor;
import com.soho.spring.mvc.model.FastMap;
import com.soho.spring.shiro.initialize.InitDefinition;
import com.soho.spring.shiro.initialize.RuleChain;
import com.soho.spring.shiro.initialize.WebInitializeService;
import freemarker.template.TemplateDirectiveModel;
import org.apache.shiro.realm.Realm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shadow
 */
@Component
public class WebInitializeServiceImp implements WebInitializeService {

    @Autowired
    private ShiroAuthorizingRealm shiroAuthorizingRealm;

    @Override
    public InitDefinition initShiroFilterChainDefinition() {
        InitDefinition definition = new InitDefinition();
        definition.setLoginUrl("/user/loginInit");
        definition.setSuccessUrl("/user/index");
        definition.setUnauthorizedUrl("/403");
        List<RuleChain> anonRuleChains = new ArrayList<>();  // 无权限校验
        anonRuleChains.add(new RuleChain("/static/**", "anon"));
        anonRuleChains.add(new RuleChain("/error/**", "anon"));
        anonRuleChains.add(new RuleChain("/user/login*", "anon"));
        anonRuleChains.add(new RuleChain("/user/signup*", "anon"));
        anonRuleChains.add(new RuleChain("/ggk/*", "anon"));
        List<RuleChain> roleRuleChains = new ArrayList<>(); // 有权限校验
        roleRuleChains.add(new RuleChain("/user/index", "kickout,role[admin]"));
        roleRuleChains.add(new RuleChain("/**", "kickout,authc"));
        // definition.setRuleChains(WCCUtils.ruleChainComparator(ruleChains));
        definition.setAnonRuleChains(anonRuleChains);
        definition.setRoleRuleChains(roleRuleChains);
        return definition;
    }

    @Override
    public List<Realm> initShiroRealms() {
        List<Realm> realms = new ArrayList<>();
        realms.add(shiroAuthorizingRealm);
        return realms;
    }

    @Override
    public Map<String, Filter> initShiroFilters() {
        return new LinkedHashMap<>();
    }

    @Override
    public boolean isHttpsCookieSecure() {
        return false;
    }

    @Override
    public List<HandlerInterceptor> initWebMVCInterceptor() {
        List<HandlerInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new KillRobotInterceptor());
        interceptors.add(new FormTokenInterceptor());
        return interceptors;
    }

    @Override
    public Map<String, TemplateDirectiveModel> initFreeMarkerTag() {
        return new FastMap<TemplateDirectiveModel>().add("leftMenu", new MenuTag()).done();
    }

}
