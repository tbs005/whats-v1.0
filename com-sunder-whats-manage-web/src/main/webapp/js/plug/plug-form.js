//表单格式化json
(function ($) {
	$.fn.serializeJson = function (arrlist) {
		var serializeObj = {};
		var array = this.serializeArray();
		$(array).each(function () {
			// 是否存在键名
			if (serializeObj[this.name]) {
				//    是否为数组
				if ($.isArray(serializeObj[this.name])) {
					serializeObj[this.name].push(this.value);
				} else {
					serializeObj[this.name] = [serializeObj[this.name], this.value];
				}
			} else if(this.name!="") {

					if (this.name == arrlist) {
						serializeObj[this.name] = [this.value];
					} else {
						serializeObj[this.name] = this.value;
					}
				


			}
		});
		return serializeObj;
	};
})(jQuery);
// 验证码倒计时

var countdown = 60;

function getCode(obj) {
	var $obj = $(obj);
	if (countdown == 0) {
		changBtnAfter($obj);
		countdown = 60;
		return;
	} else {
		$obj.text("重发(" + countdown + "S)");
		countdown--;
	}
	setTimeout(function () {
		getCode(obj)
	}, 1000)
}

function changBtnBefore(obj){
	obj.attr("disabled", "disabled");
	obj.text("验证码发送中");
}

function changBtnAfter(obj){
	obj.removeAttr("disabled");
	obj.text("获取验证码");
}