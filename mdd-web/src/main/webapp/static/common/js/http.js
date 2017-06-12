/**
 * Created by 甘乃瑜 on 2017/5/7.
 */
if (typeof jQuery === 'undefined') {
    throw new Error('Bootstrap\'s JavaScript requires jQuery. jQuery must be included before Bootstrap\'s JavaScript.')
}

var fp = new Fingerprint();

var token =JSON.parse(localStorage.getItem("token"));

$(document).ready(function () {
    if (!token) {
        getToken();
    }
});


var Get = function (settings) {
    settings.type = "get";
    settings.url = host + settings.url;
    Http(settings);

}
var Post = function (settings) {
    settings.type = "post";
    settings.url = host + settings.url;
    Http(settings);

}

function getToken(){
    Get({
        url: "token/get",
        data: {fingerPrint: JSON.stringify(fp.get())},
        success: function (res) {
            if(res != undefined){
                localStorage.setItem("token",JSON.stringify(res));
                token = res.data;
            };
        }
    });
}

var Http = function (settings) {
    function callback(obj) {
        debugger
        if(!(obj instanceof Object)){
            try {
                obj = eval(obj);
            } catch (e) {
                obj = obj;
            }

        }
        var code = obj['code'];
        if (code == undefined) {
            showError({
                code: -6,
                msg: '请求出错了！'
            });
        } else if (code == 1) {
            settings.success && settings.success(obj['data']);
        } else {
            if(300 <= code &&　code <= 329){
                showError({
                    msg:obj['msg'],
                    confirm:function(){
                        localStorage.removeItem("token");
                        window.location.href = host;
                    }
                });
            }
            if (settings.error == undefined) {
                showError(obj);
            } else {
                settings.error(obj);
            }
        }
    }

    $.ajax({
        url: settings.url,
        type: settings.type || "post",
        data: settings.data,
        async:settings.async == undefined ? true : false,
        traditional: settings.traditional || true,
        timeout: settings.timeout || 5000,
        beforeSend: function (request) {
            request.setRequestHeader("requestType", "ajax");
            if(token){
                token.fingerPrint = JSON.stringify(fp.get());
                request.setRequestHeader("token", JSON.stringify(token));
            }
        },
        success: callback,
        error: function (xhr, status) {
            var map = {
                "abort": {
                    code: -1,
                    msg: "网络请求被取消。"
                },
                "parsererror": {
                    code: -2,
                    msg: "网络返回解析错误。"
                },
                "timeout": {
                    code: -3,
                    msg: "网络请求超时。"
                },
                "error": {
                    code: -4,
                    msg: "网络错误。"
                }
            };
            var result = map[status];
            if (!result) {
                result = {
                    code: -5,
                    msg: "未知的网络错误。"
                };
            }
            showError(result);
        },
        complete: settings.complete
    });
};

/*
 * 默认错误提示
 */
var showError =  function (settings) {
    var modalId = 'http-error-modal';
    var modalTplHtml = '<div id="' + modalId + '" class="http modal modal-warning fade"> ' +
        '<div class="modal-dialog"> ' +
        '<div class="modal-content"> ' +
        '<div class="modal-header"> ' +
        '<button type="button" class="close" data-dismiss="modal" aria-label="Close">' +
        '<span aria-hidden="true">×</span>' +
        '</button> ' +
        '<h4 class="modal-title">OH!HO!</h4> ' +
        '</div> ' +
        '<div class="modal-body">' +
        '<p>One fine body…</p> ' +
        '</div> ' +
        '<div class="modal-footer"> ' +
        '<button type="button" class="btn btn-outline confirm-btn bg-danger" data-dismiss="modal">确定</button>' +
        '</div> ' +
        '</div>' +
        '</div>' +
        '</div>';
    var modalELe = null;
    if ($('#' + modalId).length == 0) {
        $('body').append($(modalTplHtml));
        modalEle = $('#' + modalId);
        modalEle.modal({
            backdrop: 'static',
            keyboard: false,
            show: false
        });
    } else {
        modalELe = $('#' + modalId);
    }
    $('.modal-body', modalEle).html(settings.msg);
    if(settings['confirm']) {
        $('.confirm-btn',modalEle).off('click').on('click',function(){
            settings['confirm']();
            modalEle.modal('hide');
        });
    }
    modalEle.modal('show');
}

var showYea = function(settings) {
    var modalId = 'http-yea-modal';
    var modalTplHtml = '<div id="' + modalId + '" class="http modal modal-default fade "> ' +
        '<div class="modal-dialog"> ' +
        '<div class="modal-content"> ' +
        '<div class="modal-header"> ' +
        '<h4 class="modal-title">操作成功</h4> ' +
        '</div> ' +
        '<div class="modal-body">' +
        '<p>One fine body…</p> ' +
        '</div> ' +
        '<div class="modal-footer">  ' +
        '<button type="button" class="btn btn-success confirm-btn" data-dismiss="modal">朕已阅</button>' +
        ' </div> ' +
        '</div>' +
        '</div>' +
        '</div>';
    var modalEle = null;
    if ($('#' + modalId).length == 0) {
        $('body').append($(modalTplHtml));
        modalEle = $('#' + modalId);
        modalEle.modal({
            backdrop: 'static',
            keyboard: false,
            show: false
        });
    } else {
        modalEle = $('#' + modalId);
    }
    $('.modal-body', modalEle).html(settings.msg);
    $('.modal-title', modalEle).html(settings['title'] ? settings['title'] : '操作成功');
    if (settings['confirm']) {
        $('.confirm-btn', modalEle).off('click').on('click', function () {
            settings['confirm']();
            modalEle.modal('hide');
        });
    }
    modalEle.modal('show');
}

var actionConfirm = function(settings) {
    var modalId = 'cms-confirm-modal';
    var modalTplHtml = '<div id="'+modalId+'" class="http modal modal-default fade ">' +
        '<div class="modal-dialog"> ' +
        '<div class="modal-content">' +
        '<div class="modal-header">' +
        '<button type="button" class="close" data-dismiss="modal" aria-label="Close">' +
        '<span aria-hidden="true">×</span>' +
        '</button> ' +
        '<h4 class="modal-title">提示</h4> ' +
        '</div> ' +
        '<div class="modal-body"> ' +
        '<p>One fine body…</p> ' +
        '</div> ' +
        '<div class="modal-footer"> ' +
        '<button type="button" class="btn btn-default pull-left cancle-btn" data-dismiss="modal">取消</button> ' +
        '<button type="button" class="btn btn-warning confirm-btn" data-dismiss="modal">确定</button>' +
        '</div> ' +
        '</div>' +
        '</div>' +
        '</div>';
    var modalEle = null;
    if($('#'+modalId).length == 0) {
        $('body').append($(modalTplHtml));
        modalEle = $('#'+modalId);
        modalEle.modal({
            backdrop: 'static',
            keyboard: false,
            show: false
        });
    } else {
        modalEle = $('#'+modalId);
    }
    $('.modal-body',modalEle).html(settings.msg);
    $('.confirm-btn',modalEle).off('click').on('click',function(){
        settings['confirm']&&settings['confirm']();
        modalEle.modal('hide');
    });
    $('.cancle-btn',modalEle).off('click').on('click',function(){
        settings['cancle']&&settings['cancle']();
        modalEle.modal('hide');
    });
    modalEle.modal('show');
}

