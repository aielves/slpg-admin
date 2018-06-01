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
    $modal.modal();
}

$.confirmModal = function (option) {
    var $confirm = jQuery('#my-confirm');
    $confirm.find('#my-confirm-title').text(option.title);
    $confirm.find('#my-confirm-content').text(option.content);
    $confirm.modal({
        onConfirm: option.ok == undefined ? function () {
        } : option.ok,
        onCancel: option.no == undefined ? function () {
        } : option.no,
    });
    $modal.modal();
}