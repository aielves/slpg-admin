var isRequest = false;

function submitByAjax(formId, formUrl, showSuccess) {
    jQuery(formId).ajaxSubmit({ // ajax方式提交表单
        url: formUrl,
        type: 'post',
        dataType: 'json',
        beforeSubmit: function () {
            if (isRequest == true) {
                jQuery.showModal({title: "请求正在处理,请耐心等候..."});
                return false;
            }
            isRequest = true;
        },
        success: function (json) {
            if (json.code == '000000') {
                var result = json.data.result;
                var callurl = json.data.callurl;
                if (!validNull(result)) {
                    if (!validNull(showSuccess) && showSuccess == true) {
                        jQuery.showModal({
                            title: result, ok: function () {
                                if (!validNull(callurl)) {
                                    top.location.href = callurl;
                                }
                            }
                        });
                    } else {
                        if (!validNull(callurl)) {
                            top.location.href = callurl;
                        }
                    }
                } else {
                    jQuery.showModal({title: json.msg});
                }
            } else {
                jQuery.showModal({title: json.msg});
            }
            isRequest = false;
        },
        clearForm: false,// 禁止清楚表单
        resetForm: false // 禁止重置表单
    });
}

// 弹幕操作显示
jQuery.showModal = function (options) {
    var container = jQuery(".works-mask");
    container.find(".del-p").text(options.title);
    container.find(".wsdel-ok").on("click", function (obj) {
        if (options.ok != undefined) {
            options.ok(obj);
        }
        container.hide();
    });
    container.find(".wsdel-no").on("click", function (obj) {
        if (options.no != undefined) {
            options.no(obj);
        }
        container.hide();
    });
    container.show();
}

function validNull(obj) {
    if (obj == undefined || obj == null || obj == '') {
        return true;
    }
    return false;
}

function logout() {
    jQuery.showModal({
        title: "确定要退出吗?", ok: function () {
            top.location.href = '/user/logout';
        }
    });
}

function menuClick(cmenu) {
    document.cookie = "cmenu=" + cmenu + "; path=/";
    $(".sidebar-nav-sub").hide();
    $(cmenu).show();
    console.log(cmenu);
}

function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return "";
}

$(function () {
    console.log(getCookie("cmenu"))
    $(".sidebar-nav-sub").hide();
    var cmenu = getCookie("cmenu");
    if (cmenu != "") {
        $(cmenu).show();
    }
});