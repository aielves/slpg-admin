var isRequest = false;

function submitByAjax(formId, formUrl, showSuccess) {
    jQuery(formId).ajaxSubmit({ // ajax方式提交表单
        url: formUrl,
        type: 'post',
        dataType: 'json',
        beforeSubmit: function () {
            if (isRequest == true) {
                $.alertModal({title: '友情提示', content: '请求正在处理,请耐心等候...'});
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
                        $.alertModal({
                            title: '友情提示', content: result, ok: function () {
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
                    $.alertModal({title: '友情提示', content: json.msg});
                }
            } else {
                $.alertModal({title: '错误提示', content: json.msg});
            }
            isRequest = false;
        },
        clearForm: false,// 禁止清楚表单
        resetForm: false // 禁止重置表单
    });
}

function validNull(obj) {
    if (obj == undefined || obj == null || obj == '') {
        return true;
    }
    return false;
}

function logout() {
    $.confirmModal({
        title: '友情提示', content: '确定要退出吗?', ok: function () {
            top.location.href = '/user/logout';
        }, no: function () {
        }
    });
}

$.alertModal = function (option) {
    var $modal = jQuery('#my-alert');
    $modal.find('#my-alert-title').text(option.title);
    $modal.find('#my-alert-content').text(option.content);
    if (option.ok != undefined) {
        $modal.find('#my-alert-btn').on('click', option.ok);
    }
    $modal.modal({closeViaDimmer: false});
}

$.confirmModal = function (option) {
    var $confirm = jQuery('#my-confirm');
    $confirm.find('#my-confirm-title').text(option.title);
    $confirm.find('#my-confirm-content').text(option.content);
    $confirm.modal({
        closeViaDimmer: false,
        onConfirm: option.ok == undefined ? function () {
        } : option.ok,
        onCancel: option.no == undefined ? function () {
        } : option.no,
    });
    $modal.modal();
}

// 分页导航栏触发函数
function pagingForm(fn, pageNo, pageSize) {
    $("#" + fn + "_pageNo").val(pageNo);
    $("#" + fn + "_pageSize").val(pageSize);
    $("#" + fn).submit();
}

// 表单数据全选/反选
function checkedData(obj) {
    var checked = $(obj).find("input").is(":checked");
    if (checked) {
        $(".am-secondary").find("input[type=checkbox]").uCheck("check");
    } else {
        $(".am-secondary").find("input[type=checkbox]").uCheck("uncheck");
    }
}

function eidtCheckedData(url) {
    var $list = $(".am-secondary").find("input[type=checkbox]:checked");
    if ($list.length <= 0) {
        $.alertModal({title: "友情提示", content: "请至少选中一个"});
        return false;
    }
    if ($list.length != 1) {
        $.alertModal({title: "友情提示", content: "只能选中单个数据进行修改"});
        return false;
    }
    $list.each(function () {
        top.location.href = url + "?pojo[id]=" + $(this).val();
    });
}

function deleteCheckedData(url, form) {
    var $list = $(".am-secondary").find("input[type=checkbox]:checked");
    if ($list.length <= 0) {
        $.alertModal({title: "友情提示", content: "请至少选中一个"});
        return false;
    }
    $.confirmModal({
        title: "友情提示", content: "删除后无法恢复,确定要删除选中的吗?", ok: function () {
            var idArr = "";
            $.each($list, function (i, v) {
                idArr += "," + $(this).val();
            });
            $.post(url, "pojo[idArr]=" + idArr, function (json) {
                if (json.code == "000000") {
                    $.alertModal({
                        title: "操作提示", content: json.data.result, ok: function () {
                            $("#" + form).submit();
                        }
                    });
                } else {
                    $.alertModal({
                        title: "操作提示", content: json.msg
                    });
                }
            });
        }, no: function () {

        }
    });
}

function deleteCheckedDataById(url, form, id) {
    $.confirmModal({
        title: "友情提示", content: "删除后无法恢复,确定要删除选中的吗?", ok: function () {
            var idArr = id;
            $.post(url, "pojo[idArr]=" + idArr, function (json) {
                if (json.code == "000000") {
                    $.alertModal({
                        title: "操作提示", content: json.data.result, ok: function () {
                            $("#" + form).submit();
                        }
                    });
                } else {
                    $.alertModal({
                        title: "操作提示", content: json.msg
                    });
                }
            });
        }, no: function () {

        }
    });
}