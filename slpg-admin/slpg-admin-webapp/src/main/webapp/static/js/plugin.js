var isRequest = false;
function submitByAjax(formId, formUrl, callUrl) {
    jQuery('#' + formId).ajaxSubmit(      // ajax方式提交表单
        {
            url: formUrl,
            type: 'post',
            dataType: 'json',
            beforeSubmit: function () {
                if (isRequest == true) {
                    alert('请求正在处理,请耐心等候...');
                    return false;
                }
                isRequest = true;
            },
            success: function (data) {
                console.log(data);
                if (data.code == '000000') {
                    alert(data.msg);
                    if (callUrl != '') {
                        top.location.href = callUrl;
                    }
                } else {
                    alert(data.msg);
                }
                isRequest = false;
            },
            clearForm: false,// 禁止清楚表单
            resetForm: false // 禁止重置表单
        });
}