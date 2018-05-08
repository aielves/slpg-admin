;(function ($) {
    var delParent;
    $.fn.extend({
        takungaeImgup: function (opt, serverCallBack) {
            var imageNum = opt.imageNum;
            var defaults = {
                fileType: opt.fileType, // file allow type
                fileSize: 1024 * opt.maxSizeKb, // size for kb
            };
            if (opt.success) {
                var successCallBack = opt.success;
                delete opt.success;
            }
            if (opt.error) {
                var errorCallBack = opt.error;
                delete opt.error;
            }
            $(this).change(function () {
                var idFile = $(this).attr("id");
                var file = document.getElementById(idFile);
                var imgContainer = $(this).parents(".z_photo");
                var fileList = file.files;
                var imgArr = [];
                var numUp = imgContainer.find(".up-section").length;
                if (numUp < imageNum) {
                    fileList = validateUp(fileList, defaults);
                    for (var i = 0; i < fileList.length; i++) {
                        var imgUrl = window.URL.createObjectURL(fileList[i]);
                        imgArr.push(imgUrl);
                        var $section = $("<section class='up-section fl loading'>");
                        imgContainer.children(".z_file").before($section);
                        var $span = $("<span class='up-span'>");
                        $span.appendTo($section);
                        var $img0 = $("<img class='close-upimg'>").on("click", function (event) {
                            event.preventDefault();
                            event.stopPropagation();
                            $(".works-mask").show();
                            delParent = $(this).parent();
                        });
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
                        uploadByAjax(opt, fileList[i], $section);
                    }
                }
                numUp = imgContainer.find(".up-section").length;
                if (numUp >= imageNum) {
                    $(this).parent().hide();
                }
                $(this).val(""); // clean input
            });

            $(".z_photo").delegate(".close-upimg", "click", function (event) {
                event.preventDefault();
                event.stopPropagation();
                $(".works-mask").show();
                delParent = $(this).parent();
            });

            $(".wsdel-ok").click(function (event) {
                event.preventDefault();
                event.stopPropagation();
                $(".works-mask").hide();
                var img = delParent.parent().find("input[name='" + opt.inputName + "']");
                var url = img.attr("value");
                var sign = img.attr("sign");
                $.post(opt.delPath, {url: url, sign: sign}, function (json) {

                });
                var numUp = delParent.siblings(".up-section").length;
                if (numUp < imageNum + 1) {
                    delParent.parent().find(".z_file").show();
                }
                delParent.remove();
            });

            $(".wsdel-no").click(function () {
                $(".works-mask").hide();
            });

            function validateUp(files, defaults) {
                var arrFiles = [];
                for (var i = 0, file; file = files[i]; i++) {
                    var newStr = file.name.split("").reverse().join("");
                    if (newStr.split(".")[0] != null) {
                        var type = newStr.split(".")[0].split("").reverse().join("");
                        if (jQuery.inArray(type.toLowerCase(), defaults.fileType) > -1) { // 类型符合，可以上传
                            if (file.size > defaults.fileSize) {
                                alert('【' + file.name + '】超出【' + opt.maxSizeKb + 'KB】限制！');
                            } else {
                                arrFiles.push(file);
                            }
                        } else {
                            alert('只允许上传【JPG|JPEG|PNG】图片格式');
                        }
                    } else {
                        alert('只允许上传【JPG|JPEG|PNG】图片类型');
                    }
                }
                return arrFiles;
            }

            function uploadByAjax(opt, file, obj) {
                var data = new FormData();
                data.append("file", file);
                var attrData = opt.attrData;
                if (attrData != undefined && attrData != null) {
                    for (var key in attrData) {
                        data.append(key, attrData[key]);
                    }
                }
                $.ajax({
                    type: 'POST',
                    url: opt.upPath,
                    data: data,
                    processData: false,
                    contentType: false,
                    dataType: 'json',
                    success: function (json) {
                        if (json.code == '000000') {
                            $(".up-section").removeClass("loading");
                            $(".up-img").removeClass("up-opcity");
                            var htmlStr = "<input type='text' style='display:none;' name='" + opt.inputName + "' value='" + json.data.url + "' sign='" + json.data.sign + "'>";
                            obj.append(htmlStr);
                            if (successCallBack) {
                                successCallBack(json.data);
                            }
                        } else {
                            alert(json.msg);
                            var parent = $(obj).parent();
                            obj.remove();
                            parent.find("section").show();
                            if (errorCallBack) {
                                errorCallBack(json.msg);
                            }
                        }
                    },
                    error: function (e) {
                        var parent = $(obj).parent();
                        obj.remove();
                        parent.find("section").show();
                        if (errorCallBack) {
                            errorCallBack("上传失败，请重新尝试!");
                        }
                    }
                });
            }

        }
    });

})(jQuery);