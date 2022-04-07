/**
 * 刷新页面
 */
function refreshPage() {
    location.reload();
}

/**
 * 延迟刷新页面
 * time - 毫秒
 */
function refreshPageTime(time) {
    setTimeout(function () {
        location.reload();
    }, time);
}

/**
 *
 * @param {Object} url
 * @param {Object} parameter post请求的参数
 * @param {Object} successfun 回调函数,必须接收返回参数值
 * @param {Object} async 是否开启异步，默认false
 * @param {Object} myParam successfun的参数
 */
function ajaxPost(url, parameter, successfun, async, myParam) {
    if (Tools.isNull(async)) {
        async = false;
    }
    $.ajax({
        type: "POST",
        dataType: "json", //返回数据的格式 json
        url: url,
        data: parameter,
        async: async, //开启异步请求
        success: function (response) {
            if (response.code === 200) {
                successfun(response, myParam);
            } else {
                confirmReminder("请求提示", "请求失败,错误码：" + response.code + "，异常信息:" + response.message + "，异常数据体：" + response.data);
            }
        },
        error: function (response) {
            confirmReminder("请求提示", "请求失败,错误码：" + response.code + "，异常信息:" + response.message + "，异常数据体：" + response.data);
        }
    });
}
function ajaxPostRequestBody(url, parameter, successfun, async, myParam) {
    if (Tools.isNull(async)) {
        async = false;
    }
    $.ajax({
        type: "POST",
        dataType: "json", //返回数据的格式 json
        url: url,
        //RequestBody 传参
        contentType:'application/json;charset=UTF-8',
        data: parameter,
        async: async, //开启异步请求
        success: function (response) {
            if (response.code === 200) {
                successfun(response, myParam);
            } else {
                confirmReminder("请求提示", "请求失败,错误码：" + response.code + "，异常信息:" + response.message + "，异常数据体：" + response.data);
            }
        },
        error: function (response) {
            confirmReminder("请求提示", "请求失败,错误码：" + response.code + "，异常信息:" + response.message + "，异常数据体：" + response.data);
        }
    });
}

function test(p) {
    console.log("提示内容：" + p);
}

/**
 * 有确认和取消的
 * 重写确认框 fun: 函数对象      params: 参数列表， 可以是数组
 * @param {Object} fun
 * @param {Object} params
 * @param {Object} title 标题
 * @param {Object} content  提示内容
 */
function confirm(fun, params, title, content) {
    realConfirm(fun, params, title, content, 1);
}

/**
 * 只有确认的按钮，可以执行函数
 * @param fun
 * @param params
 * @param title
 * @param content
 */
function confirmNoCancel(fun, params, title, content) {
    realConfirm(fun, params, title, content, 0);
}

/**
 * 仅仅提示内容
 * @param title
 * @param text
 */
function confirmReminder(title, text) {
    confirmNoCancel("", "", title, text);
}
/**
 * 提示内容，并在多少毫秒后自动关闭
 * @param title
 * @param text
 * @param timeOut 自动关闭时间，单位毫秒
 */
function confirmReminderTimeOut(title, text,timeOut) {
    confirmNoCancel("", "", title, text);
    setTimeout(function () {
        //关闭弹框
        const myConfirm = $("#myConfirm");
        myConfirm.removeClass("show");
        $("body").removeClass("modal-open");
        myConfirm.removeAttr("style");
    }, timeOut);
}

/**
 * 仅仅提示内容 - 通过response进行解析
 * @param response
 * @param param
 */
function confirmReminderResponse(response, param) {
    confirmNoCancel("", "", response.message, response.data);
}


/**
 *
 * @param fun
 * @param params
 * @param title
 * @param content
 * @param cancel 其他-不含需求按钮，1-包含取消按钮
 */
function realConfirm(fun, params, title, content, cancel) {
    var myConfirm = $("#myConfirm");
    if (myConfirm.length > 0) {
        myConfirm.remove();
    }
    var html = "";
    if (cancel === 1) {
        html = "<div id='myConfirm' role=\"dialog\" tabindex=\"-1\" class=\"modal fade show\" style=\"display: block;\">\n" +
            "    <div class=\"modal-dialog\" role=\"document\">\n" +
            "        <div class=\"modal-content\">\n" +
            "            <div class=\"modal-header\">\n" +
            "                <h4 id='myModalLabel' class=\"modal-title\">" +
            title +
            "</h4><button type=\"button\" class=\"btn-close myConfirmCancel\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\n" +
            "            </div>\n" +
            "            <div style='word-break: break-all;' class=\"modal-body\">\n" +
            "                <p>" +
            content +
            "</p>\n" +
            "            </div>\n" +
            "            <div class=\"modal-footer\"><button class=\"btn btn-light myConfirmCancel\" type=\"button\" data-bs-dismiss=\"modal\">取消</button><button id='confirmOk' class=\"btn btn-primary\" type=\"button\">确认</button></div>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</div>";
    } else {
        html = "<div id='myConfirm' role=\"dialog\" tabindex=\"-1\" class=\"modal fade show\" style=\"display: block;\">\n" +
            "    <div class=\"modal-dialog\" role=\"document\">\n" +
            "        <div class=\"modal-content\">\n" +
            "            <div class=\"modal-header\">\n" +
            "                <h4 id='myModalLabel' class=\"modal-title\">" +
            title +
            "</h4><button type=\"button\" class=\"btn-close myConfirmCancel\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\n" +
            "            </div>\n" +
            "            <div style='word-break: break-all;' class=\"modal-body\">\n" +
            "                <p>" +
            content +
            "</p>\n" +
            "            </div>\n" +
            "            <div class=\"modal-footer\"><button id='confirmOk' class=\"btn btn-primary\" type=\"button\">确认</button></div>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</div>";
    }

    var body = $("body");
    body.append(html);
    body.addClass("modal-open");
    //需要重新获取
    myConfirm = $("#myConfirm");
    myConfirm.addClass("show");
    myConfirm.attr("style", "display: block;");

    $("#confirmOk").on("click", function () {
        myConfirm.removeClass("show");
        body.removeClass("modal-open");
        myConfirm.removeAttr("style");
        console.log("函数：" + fun);
        if (fun !== '' && fun !== null)
            // 执行函数
            fun(params);
    });
    $(".myConfirmCancel").on("click", function () {
        myConfirm.removeClass("show");
        body.removeClass("modal-open");
        myConfirm.removeAttr("style");
    });
}

/**
 * 编辑模态
 * @param title
 * @param inputSpan input的提示值
 * @param inputType input的类型 number,text,email,password等类型
 * @param inputValue input的默认值
 * @param fun 确认后的回调函数
 * @param params
 */
function editModalConfirm(title, inputSpan, inputType, inputValue, fun, ...params) {
    let myConfirm = $("#editModalConfirm");
    if (myConfirm.length > 0) {
        myConfirm.remove();
    }
    const html = "<div id='editModalConfirm' role=\"dialog\" tabindex=\"-1\" class=\"modal fade\">\n" +
        "    <div class=\"modal-dialog\" role=\"document\">\n" +
        "        <div class=\"modal-content\">\n" +
        "            <div class=\"modal-header\">\n" +
        "                <h4 class=\"modal-title\">" + title + "</h4><button type=\"button\" class=\"btn-close myConfirmCancel\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\n" +
        "            </div>\n" +
        "            <div class=\"modal-body\">\n" +
        "                <div class=\"input-group\"><span class=\"input-group-text\">" +
        inputSpan + "</span>" +
        "<input type=\"" + inputValue + "\" class=\"form-control\" value=\"" + inputValue + "\"/></div>\n" +
        "            </div>\n" +
        "            <div class=\"modal-footer\"><button class=\"btn btn-light myConfirmCancel\" type=\"button\" data-bs-dismiss=\"modal\">取消</button><button id='confirmOk' class=\"btn btn-primary\" type=\"button\">确认</button></div>\n" +
        "        </div>\n" +
        "    </div>\n" +
        "</div>";
    const body = $("body");
    body.append(html);
    body.addClass("modal-open");
    //需要重新获取
    myConfirm = $("#editModalConfirm");
    myConfirm.addClass("show");
    myConfirm.attr("style", "display: block;");

    $("#confirmOk").on("click", function () {
        myConfirm.removeClass("show");
        body.removeClass("modal-open");
        myConfirm.removeAttr("style");
        console.log("函数：" + fun);
        if (fun !== '' && fun !== null)
            // 执行函数
            fun(params);
    });
    $(".myConfirmCancel").on("click", function () {
        myConfirm.removeClass("show");
        body.removeClass("modal-open");
        myConfirm.removeAttr("style");
    });
}


/**
 * @desc 工具类对象
 * @param obj 对象
 * @returns true||false
 */
var Tools = {

    isArray: function (obj) {
        return Object.prototype.toString.call(obj) === '[object Array]';
    },
    isNull: function (obj) {
        return obj == null || obj == '' || obj == undefined;
    },
    isNotNull: function (obj) {
        return !this.isNull(obj);
    },
    isString: function (o) { //是否字符串
        return Object.prototype.toString.call(o).slice(8, -1) === 'String'
    },

    isNumber: function (o) { //是否数字
        return Object.prototype.toString.call(o).slice(8, -1) === 'Number'
    },

    isBoolean: function (o) { //是否boolean
        return Object.prototype.toString.call(o).slice(8, -1) === 'Boolean'
    },

    isFunction: function (o) { //是否函数
        return Object.prototype.toString.call(o).slice(8, -1) === 'Function'
    },

    isObject: function (o) { //是否对象
        return Object.prototype.toString.call(o).slice(8, -1) === 'Object'
    },

    isDate: function (o) { //是否时间
        return Object.prototype.toString.call(o).slice(8, -1) === 'Date'
    },

    isRegExp: function (o) { //是否正则
        return Object.prototype.toString.call(o).slice(8, -1) === 'RegExp'
    },

    isError: function (o) { //是否错误对象
        return Object.prototype.toString.call(o).slice(8, -1) === 'Error'
    },

    isSymbol: function (o) { //是否Symbol函数
        return Object.prototype.toString.call(o).slice(8, -1) === 'Symbol'
    },

    isPromise: function (o) { //是否Promise对象
        return Object.prototype.toString.call(o).slice(8, -1) === 'Promise'
    },

    isSet: function (o) { //是否Set对象
        return Object.prototype.toString.call(o).slice(8, -1) === 'Set'
    },

    isIos: function () {
        var u = navigator.userAgent;
        if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) { //安卓手机
            // return "Android";
            return false
        } else if (u.indexOf('iPhone') > -1) { //苹果手机
            // return "iPhone";
            return true
        } else if (u.indexOf('iPad') > -1) { //iPad
            // return "iPad";
            return false
        } else if (u.indexOf('Windows Phone') > -1) { //winphone手机
            // return "Windows Phone";
            return false
        } else {
            return false
        }
    },

    isPC: function () { //是否为PC端
        var userAgentInfo = navigator.userAgent;
        var Agents = ["Android", "iPhone",
            "SymbianOS", "Windows Phone",
            "iPad", "iPod"
        ];
        var flag = true;
        for (var v = 0; v < Agents.length; v++) {
            if (userAgentInfo.indexOf(Agents[v]) > 0) {
                flag = false;
                break;
            }
        }
        return flag;
    },

    browserType: function () {
        var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
        var isOpera = userAgent.indexOf("Opera") > -1; //判断是否Opera浏览器
        var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera; //判断是否IE浏览器
        var isIE11 = userAgent.indexOf('Trident') > -1 && userAgent.indexOf("rv:11.0") > -1;
        var isEdge = userAgent.indexOf("Edge") > -1 && !isIE; //判断是否IE的Edge浏览器
        var isFF = userAgent.indexOf("Firefox") > -1; //判断是否Firefox浏览器
        var isSafari = userAgent.indexOf("Safari") > -1 && userAgent.indexOf("Chrome") == -1; //判断是否Safari浏览器
        var isChrome = userAgent.indexOf("Chrome") > -1 && userAgent.indexOf("Safari") > -1; //判断Chrome浏览器

        if (isIE) {
            var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
            reIE.test(userAgent);
            var fIEVersion = parseFloat(RegExp["$1"]);
            if (fIEVersion == 7) return "IE7"
            else if (fIEVersion == 8) return "IE8";
            else if (fIEVersion == 9) return "IE9";
            else if (fIEVersion == 10) return "IE10";
            else return "IE7以下" //IE版本过低
        }
        if (isIE11) return 'IE11';
        if (isEdge) return "Edge";
        if (isFF) return "FF";
        if (isOpera) return "Opera";
        if (isSafari) return "Safari";
        if (isChrome) return "Chrome";
    },

    checkStr: function (str, type) {
        switch (type) {
            case 'phone': //手机号码
                return /^1[3|4|5|6|7|8|9][0-9]{9}$/.test(str);
            case 'tel': //座机
                return /^(0\d{2,3}-\d{7,8})(-\d{1,4})?$/.test(str);
            case 'card': //身份证
                return /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(str);
            case 'pwd': //密码以字母开头，长度在6~18之间，只能包含字母、数字和下划线
                return /^[a-zA-Z]\w{5,17}$/.test(str)
            case 'postal': //邮政编码
                return /[1-9]\d{5}(?!\d)/.test(str);
            case 'QQ': //QQ号
                return /^[1-9][0-9]{4,9}$/.test(str);
            case 'email': //邮箱
                return /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/.test(str);
            case 'money': //金额(小数点2位)
                return /^\d*(?:\.\d{0,2})?$/.test(str);
            case 'URL': //网址
                return /(http|ftp|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&:/~\+#]*[\w\-\@?^=%&/~\+#])?/.test(str)
            case 'IP': //IP
                return /((?:(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d)\\.){3}(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d))/.test(str);
            case 'date': //日期时间
                return /^(\d{4})\-(\d{2})\-(\d{2}) (\d{2})(?:\:\d{2}|:(\d{2}):(\d{2}))$/.test(str) || /^(\d{4})\-(\d{2})\-(\d{2})$/.test(str)
            case 'number': //数字
                return /^[0-9]$/.test(str);
            case 'english': //英文
                return /^[a-zA-Z]+$/.test(str);
            case 'chinese': //中文
                return /^[\u4E00-\u9FA5]+$/.test(str);
            case 'lower': //小写
                return /^[a-z]+$/.test(str);
            case 'upper': //大写
                return /^[A-Z]+$/.test(str);
            case 'HTML': //HTML标记
                return /<("[^"]*"|'[^']*'|[^'">])*>/.test(str);
            default:
                return true;
        }
    },

    // 严格的身份证校验
    isCardID: function (sId) {
        if (!/(^\d{15}$)|(^\d{17}(\d|X|x)$)/.test(sId)) {
            alert('你输入的身份证长度或格式错误')
            return false
        }
        //身份证城市
        var aCity = {
            11: "北京",
            12: "天津",
            13: "河北",
            14: "山西",
            15: "内蒙古",
            21: "辽宁",
            22: "吉林",
            23: "黑龙江",
            31: "上海",
            32: "江苏",
            33: "浙江",
            34: "安徽",
            35: "福建",
            36: "江西",
            37: "山东",
            41: "河南",
            42: "湖北",
            43: "湖南",
            44: "广东",
            45: "广西",
            46: "海南",
            50: "重庆",
            51: "四川",
            52: "贵州",
            53: "云南",
            54: "西藏",
            61: "陕西",
            62: "甘肃",
            63: "青海",
            64: "宁夏",
            65: "新疆",
            71: "台湾",
            81: "香港",
            82: "澳门",
            91: "国外"
        };
        if (!aCity[parseInt(sId.substr(0, 2))]) {
            alert('你的身份证地区非法')
            return false
        }

        // 出生日期验证
        var sBirthday = (sId.substr(6, 4) + "-" + Number(sId.substr(10, 2)) + "-" + Number(sId.substr(12, 2))).replace(/-/g, "/"),
            d = new Date(sBirthday)
        if (sBirthday != (d.getFullYear() + "/" + (d.getMonth() + 1) + "/" + d.getDate())) {
            alert('身份证上的出生日期非法')
            return false
        }

        // 身份证号码校验
        var sum = 0,
            weights = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2],
            codes = "10X98765432"
        for (var i = 0; i < sId.length - 1; i++) {
            sum += sId[i] * weights[i];
        }
        var last = codes[sum % 11]; //计算出来的最后一位身份证号码
        if (sId[sId.length - 1] != last) {
            alert('你输入的身份证号非法')
            return false
        }

        return true
    }

};
