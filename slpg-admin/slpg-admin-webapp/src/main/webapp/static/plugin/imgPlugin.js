;(function ($) {
    $.fn.extend({
        takungaeImgup: function (options) {
            var imageNum = options.imageNum;
            var defaults = {
                fileType: options.fileType, // file allow type
                fileSize: 1024 * options.maxSizeKb, // size for kb
            };
            var imgContainer = $(this).parents(".z_photo");
            var numUp = imgContainer.find(".up-section").length;
            if (numUp > 0 && numUp < imageNum) {
                $(this).parent().show();
            }
            $(this).change(function () {
                var idFile = $(this).attr("id");
                var file = document.getElementById(idFile);
                var imgContainer = $(this).parents(".z_photo");
                var fileList = file.files;
                var imgArr = [];
                var numUp = imgContainer.find(".up-section").length;
                if (numUp < imageNum) {
                    fileList = validFile(fileList, options, defaults);
                    for (var i = 0; i < fileList.length; i++) {
                        var imgUrl = window.URL.createObjectURL(fileList[i]);
                        imgArr.push(imgUrl);
                        var $section = $("<section class='up-section fl loading'>");
                        imgContainer.children(".z_file").before($section);
                        var $span = $("<span class='up-span'>");
                        $span.appendTo($section);
                        var $img0 = $("<img class='close-upimg' title='删除图片' style='cursor: pointer' onclick='deleteFile(1, imageNum, \"" + options.delPath + "\");'>");
                        $img0.attr("src", "http://static.cartoonai.com/plugin/imgUp/img/a7.png").appendTo($section);
                        var $img = $("<img class='up-img up-opcity'>");
                        $img.attr("src", imgArr[i]);
                        $img.appendTo($section);
                        var $p = $("<p class='img-name-p'>");
                        $p.html(fileList[i].name).appendTo($section);
                        var $input = $("<input id='taglocation' name='taglocation' value='' type='hidden'>");
                        $input.appendTo($section);
                        var $input2 = $("<input id='tags' name='tags' value='' type='hidden'/>");
                        $input2.appendTo($section);
                        uploadFile(options, fileList[i], $section, $img, $img0);
                    }
                }
                numUp = imgContainer.find(".up-section").length;
                if (numUp >= imageNum) {
                    $(this).parent().hide();
                }
                $(this).val(""); // clean input
            });

        }

    });

})(jQuery);

function validFile(files, options, defaults) {
    var arrFiles = [];
    for (var i = 0, file = null; file = files[i]; i++) {
        var newStr = file.name.split("").reverse().join("");
        if (newStr.split(".")[0] != null) {
            var type = newStr.split(".")[0].split("").reverse().join("");
            if (jQuery.inArray(type.toLowerCase(), defaults.fileType) > -1) { // 类型符合，可以上传
                if (file.size > defaults.fileSize) {
                    $.alertModal({title: "错误提示", content: '【' + file.name + '】超出【' + options.maxSizeKb + 'KB】限制'})
                } else {
                    arrFiles.push(file);
                }
            } else {
                $.alertModal({title: "错误提示", content: '只允许上传【JPG|JPEG|PNG】图片格式'})
            }
        } else {
            $.alertModal({title: "错误提示", content: '只允许上传【JPG|JPEG|PNG】图片格式'})
        }
    }
    return arrFiles;
}

function uploadFile(options, file, section, upImg, closeImg) {
    var data = new FormData();
    data.append("file", file);
    var attrData = options.attrData;
    if (attrData != undefined && attrData != null) {
        for (var key in attrData) {
            data.append(key, attrData[key]);
        }
    }
    $.ajax({
        type: 'POST',
        url: options.upPath,
        data: data,
        processData: false,
        contentType: false,
        dataType: 'json',
        success: function (json) {
            if (json.code == '000000') {
                $(section).removeClass("loading");
                $(upImg).removeClass("up-opcity");
                $(closeImg).attr("onclick", "deleteFile('" + json.data.sign + "'," + options.imageNum + ", \'" + options.delPath + "\');");
                var htmlStr = "<input type='text' style='display:none;' name='" + options.inputName + "' value='" + json.data.url + "' sign='" + json.data.sign + "'>";
                $(section).append(htmlStr);
            } else {
                // showTipMessage(2, json.msg);
                var parent = $(section).parent();
                $(section).remove();
                parent.find("section").show();
            }
        },
        error: function (e) {
            $(section).remove();
            $(section).parent().find("section").show();
            // showTipMessage(2, showMessage);
            console.log(e);
        }
    });
}

function deleteFile(imgSign, imgNum, delPath) {
    var delImg = $("input[sign='" + imgSign + "']");
    var url = delImg.attr("value");
    var sign = delImg.attr("sign");
    console.log(url + "--" + sign)
    $.post(delPath, {url: url, sign: sign}, function (json) {

    });
    var delParent = delImg.parent();
    console.log(delParent);
    var numUp = delParent.siblings(".up-section").length;
    if (numUp < imgNum + 1) {
        delParent.parent().find(".z_file").show();
    }
    delParent.remove();
}