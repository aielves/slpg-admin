<!-- 侧边导航栏 -->
<div class="left-sidebar">
    <!-- 用户信息 -->
    <div class="tpl-sidebar-user-panel">
        <div class="tpl-user-panel-slide-toggleable">
            <div class="tpl-user-panel-profile-picture">
                <img id="myHeadImg" src="<@usr key="headimg"/>" alt="用户头像">
            </div>
            <span class="user-panel-logged-in-text">
              <i class="am-icon-circle-o am-text-success tpl-user-panel-status-icon"></i>
            <@usr key="mobile"/>
          </span>
            <a href="/user/me/myself" class="tpl-user-panel-action-link"> <span class="am-icon-pencil"></span> 账号设置</a>
        </div>
    </div>
    <!-- 菜单 -->
    <ul class="sidebar-nav">
    <@leftMenu></@leftMenu>
    </ul>
</div>