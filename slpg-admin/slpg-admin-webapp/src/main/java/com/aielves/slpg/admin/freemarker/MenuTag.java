package com.aielves.slpg.admin.freemarker;

import com.aielves.slpg.domain.SlpgResource;
import com.aielves.slpg.service.SlpgResourceService;
import com.soho.mybatis.exception.BizErrorEx;
import com.soho.mybatis.sqlcode.aconst.SortBy;
import com.soho.mybatis.sqlcode.condition.imp.SQLCnd;
import com.soho.spring.utils.SpringUtils;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 用户菜单标签
 *
 * @author shadow
 */
@Component
public class MenuTag implements TemplateDirectiveModel {

    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody directiveBody)
            throws IOException {
        try {
            SlpgResourceService slpgResourceService = SpringUtils.getBean(SlpgResourceService.class);
            List<SlpgResource> level1s = slpgResourceService.findByCnd(new SQLCnd().eq("pid", 0).orderby("orderno", SortBy.A));
            StringBuffer buffer = new StringBuffer("");
            for (SlpgResource level1 : level1s) {
                List<SlpgResource> level2s = slpgResourceService.findByCnd(new SQLCnd().eq("pid", level1.getId()).orderby("orderno", SortBy.A));
                if (level2s.isEmpty()) {
                    continue;
                }
                // 大模块栏目开始
                buffer.append("<li class=\"sidebar-nav-heading\">" + level1.getName() + "<span class=\"sidebar-nav-heading-info\"> " + (StringUtils.isEmpty(level1.getResume()) ? "" : level1.getResume()) + "</span></li>");
                // 大模块栏目结束
                for (SlpgResource level2 : level2s) {
                    // 小模块栏目开始
                    buffer.append("<li class=\"sidebar-nav-link\">");
                    buffer.append("<a href=\"javascript:;\" class=\"sidebar-nav-sub-title\" id=\"" + level2.getCode() + "\">");
                    buffer.append("<i class=\"am-icon-table sidebar-nav-link-logo\"></i> ").append(level2.getName());
                    buffer.append("<span class=\"am-icon-chevron-down am-fr am-margin-right-sm sidebar-nav-sub-ico\"></span>");
                    buffer.append("</a>");
                    buffer.append("<ul class=\"sidebar-nav sidebar-nav-sub\">");
                    List<SlpgResource> level3s = slpgResourceService.findByCnd(new SQLCnd().eq("pid", level2.getId()).orderby("orderno", SortBy.A));
                    for (SlpgResource level3 : level3s) {
                        // 节点块资源开始
                        buffer.append("<li class=\"sidebar-nav-link\">");
                        buffer.append("<a href=\"javascript:void(0);\" id='" + level3.getCode() + "' onclick=\"golink('" + level3.getCode() + "', '" + level3.getUrl() + "')\">");
                        buffer.append("<span class=\"am-icon-angle-right sidebar-nav-link-logo\"></span> ").append(level3.getName());
                        buffer.append("</a>");
                        buffer.append("</li>");
                        // 节点块资源结束
                    }
                    buffer.append("</ul>");
                    buffer.append("</li>");
                    // 小模块栏目结束
                }
            }
            env.getOut().write(buffer.toString());
        } catch (BizErrorEx ex) {
            ex.printStackTrace();
        }
    }

}