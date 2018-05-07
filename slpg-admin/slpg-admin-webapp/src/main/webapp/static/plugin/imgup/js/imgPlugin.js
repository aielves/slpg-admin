;(function ($) {
    var delParent;
    $.fn.extend({
        takungaeImgup: function (opt, serverCallBack) {
            // var $fileInput = $(this);
            // var $fileInputId = $fileInput.attr('id');
            var imageNum = opt.imageNum;
            var defaults = {
                fileType: ["jpg", "png", "jpeg", "JPG", "PNG", "JPEG"], // 上传文件的类型
                fileSize: 1024 * 1024 * opt.fileSize, // 单位/M
            };
            // 组装参数;
            if (opt.success) {
                var successCallBack = opt.success;
                delete opt.success;
            }
            if (opt.error) {
                var errorCallBack = opt.error;
                delete opt.error;
            }
            /* 点击图片的文本框 */
            $(this).change(function () {
                // var reader = new FileReader(); // 新建一个FileReader();
                var idFile = $(this).attr("id");
                var file = document.getElementById(idFile);
                var imgContainer = $(this).parents(".z_photo"); // 存放图片的父亲元素
                var fileList = file.files; // 获取的图片文件
                // var input = $(this).parent();// 文本框的父亲元素
                var imgArr = [];
                // 遍历得到的图片文件
                var numUp = imgContainer.find(".up-section").length;
                var totalNum = numUp + fileList.length; // 总的数量
                // console.log("总共图片数量: " + totalNum + "----限制数量: " + imageNum);
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
                        $img0.attr("src", "/static/plugin/imgup/img/a7.png").appendTo($section);
                        var $img = $("<img class='up-img up-opcity'>");
                        $img.attr("src", imgArr[i]);
                        $img.appendTo($section);
                        var $p = $("<p class='img-name-p'>");
                        $p.html(fileList[i].name).appendTo($section);
                        var $input = $("<input id='taglocation' name='taglocation' value='' type='hidden'>");
                        $input.appendTo($section);
                        var $input2 = $("<input id='tags' name='tags' value='' type='hidden'/>");
                        $input2.appendTo($section);
                        uploadImg(opt, fileList[i], $section);
                    }
                }
                numUp = imgContainer.find(".up-section").length;
                if (numUp >= imageNum) {
                    $(this).parent().hide();
                }
                $(this).val(""); //input内容清空
            });

            $(".z_photo").delegate(".close-upimg", "click", function (event) {
                event.preventDefault();
                event.stopPropagation();
                $(".works-mask").show();
                delParent = $(this).parent();
                // console.log(delParent.html() + "delegzat=======");
            });

            $(".wsdel-ok").click(function (event) {
                event.preventDefault();
                event.stopPropagation();
                $(".works-mask").hide();
                var img = delParent.parent().find("input[name='" + opt.inputName + "']");
                var url = img.attr("value");
                var sign = img.attr("sign");
                console.log(url+"----"+sign);
                var numUp = delParent.siblings(".up-section").length;
                if (numUp < imageNum + 1) {
                    delParent.parent().find(".z_file").show();
                }
                delParent.remove();
            });

            $(".wsdel-no").click(function () {
                $(".works-mask").hide();
            });

            // 验证文件的合法性
            function validateUp(files, defaults) {
                var arrFiles = [];// 替换的文件数组
                for (var i = 0, file; file = files[i]; i++) {
                    // 获取文件上传的后缀名
                    var newStr = file.name.split("").reverse().join("");
                    if (newStr.split(".")[0] != null) {
                        var type = newStr.split(".")[0].split("").reverse().join("");
                        // console.log("图片类型: " + type + "----图片大小: " + file.size);
                        if (jQuery.inArray(type, defaults.fileType) > -1) { // 类型符合，可以上传
                            if (file.size > defaults.fileSize) {
                                alert('【' + file.name + '】超出【' + opt.fileSize + 'M】限制！');
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

            function uploadImg(opt, file, obj) {
                // $("#imguploadFinish").val(false);
                // 验证通过图片异步上传
                var data = new FormData();
                data.append("file", file);
                var attrData = opt.attrData;
                // console.log(attrData);
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
                        if (json.code == '000000') { // 上传成功
                            $(".up-section").removeClass("loading");
                            $(".up-img").removeClass("up-opcity");
                            // $("#imguploadFinish").val(true);
                            var htmlStr = "<input type='text' style='display:none;' name='" + opt.inputName + "' value='" + json.data.url + "' sign='" + json.data.sign + "'>";
                            obj.append(htmlStr);
                            if (successCallBack) {
                                successCallBack(json.data);
                            }
                        } else {
                            obj.remove();
                            // $("#imguploadFinish").val(false);
                            if (errorCallBack) {
                                errorCallBack(json.msg);
                            }
                        }
                    },
                    error: function (e) {
                        obj.remove();
                        // $("#imguploadFinish").val(false);
                        if (errorCallBack) {
                            errorCallBack("上传失败，请重新尝试!");
                        }
                    }
                });
            }

        }
    });

})(jQuery);