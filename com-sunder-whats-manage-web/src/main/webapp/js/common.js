//IOS激活伪类
$(function(){
	$(".js_backTop").hide();
});
$('body').on("touchstart", function () {});
// 浮动按钮交互
$(".float-btn").on("click", function () {
	var _this = $(this);
	var $menu = $(this).siblings(".float-menu-box");
	if ($menu.is(':hidden')) {
		_this.addClass("rotate45reg");
		$menu.show();
		_this.css("background", "#f87c17");
	} else {
		_this.removeClass("rotate45reg").addClass("rotate1");
		$menu.hide();
		_this.css("background", "#ffa53a");
	}
});

// 回到顶部
$(".main-cantainer").scroll(function () {
	if ($(".main-cantainer").scrollTop() > 500) {
		$(".js_backTop").fadeIn(500);
	} else {
		$(".js_backTop").fadeOut(500);
	}
});
$(".js_backTop").on("click", function () {
	$('.main-cantainer').animate({
		scrollTop: 0
	}, 1000);
	return false;
});

// 模态框
$(".modal-bg").on("click", function () {
	$(".modal-main").hide();
});
$(document).on("click", ".modal-main .no", function () {
	$(".modal-main").hide();
});
$(document).on("click", ".modal-bg", function () {
	$(".modal-main").hide();
	$("#prompt_modal,#affirm_modal").remove();
});
$(document).on("click", ".modal-main .know", function () {
	$("#prompt-modal").remove();

});
$(document).on("click", ".modal-main .md-close", function () {
	$(".modal-main").hide();

});


// 获取文件上传路径
$("#data_upload").on("change", function () {
	var fileName = getFileName(this.value);
	$(this).siblings('.form-text').text(fileName);
});

function getFileName(path) {
	var pos1 = path.lastIndexOf('/');
	var pos2 = path.lastIndexOf('\\');
	var pos = Math.max(pos1, pos2);
	if (pos < 0)
		return path;
	else
		return path.substring(pos + 1);
}

// 搜索框
$("#search-delete").on("click", function () {
	$(this).siblings("input").val("");
});

// 文本域字数限定
$(function () {
	$("#remarks_box").on("input", function () {
		var markNum = document.getElementById('remarks_box').value.length;
		document.getElementById("text-num").textContent = markNum;
	})
});

// 读取图片并回显
function showPreview(source) {
	var file = source.files[0];
	if (!/image\/\w+/.test(file.type)) {
		$.prompt("请上传图片");
		return false;
	} else if (file.size > 5 * 1024 * 1024) {
		$.prompt("你上传的文件太大了！");
		return false;
	} else {
		if (window.FileReader) {
			var fr = new FileReader();
			fr.readAsDataURL(file);
			fr.onloadend = function (e) {
				document.getElementById("js_uploadImg").src = e.target.result;
			};

		} else {
			$.prompt("您的设备不支持FileReader")
		}
	}

}

// 多级菜单下拉
$(function () {
	$("[select = true]").on("click", function () {
		var _this = $(this);
		var $nextList = $(this).next("ul");
		if ($nextList.is(":hidden")) {
			_this.children("span").removeClass("rotate0reg").addClass("rotate90reg");
			$nextList.show();
		} else {
			$nextList.hide();
			_this.children("span").removeClass("rotate90reg").addClass("rotate0reg");
		}
	})
});

// 列表切换
$("#js_cutList1").on("click", function () {
	$(this).addClass("cutActive").siblings().removeClass("cutActive");
	$(".js_List1").show().siblings("ul").hide();
});
$("#js_cutList2").on("click", function () {
	$(this).addClass("cutActive").siblings().removeClass("cutActive");
	$(".js_List2").show().siblings("ul").hide();
});


$.extend({
	// 确认框
	affirm: function (option, callback) {

		var _this = this;
		var modal = "<div class='modal-main' id='affirm_modal'>" +
			"<div class='modal-bg'></div>" +
			"<div class='modal-contant'>" +
			"<div class='modal-body'>" +
			"<p class='txt text-m'>" + option + "</p>" +
			"</div>" +
			"<div class='modal-footer'>" +
			" <button type='button' class='yes'>是</button>" +
			"<button type='button' class='no'>否</button>" +
			"</div>" +
			"</div>" +
			"</div>";
		$('body').append(modal);
		$("#affirm_modal .yes").on("click", function () {
			if (callback) {
				callback();
			}
			$("#affirm_modal").remove();
			$(document).off("click", "#affirm_modal .yes");
		});
		$(document).on("click", "#affirm_modal .no", function () {
			$("#affirm_modal").remove();
			$(document).off("click", "#affirm_modal .yes");
		})
	},
	// 弹出框
	prompt: function (option, callback) {
		var modal = "<div class='modal-main' id='prompt_modal'>" +
			"<div class='modal-bg'></div>" +
			"<div class='modal-contant'>" +
			"<div class='modal-body'>" +
			"<p class='txt text-m'>" + option + "</p>" +
			"</div>" +
			"<div class='modal-footer'>" +
			"<button type='button' class='know'>知道了</button>" +
			"</div>" +
			"</div>" +
			"</div>";
		$('body').append(modal);
		$(document).on("click", "#prompt_modal .know,#prompt_modal .modal-bg", function () {
			if (callback) {
				callback();
				$(document).off("click", "#prompt_modal .know,#prompt_modal .modal-bg");
				$("#prompt_modal").remove();
			} else {
				$("#prompt_modal").remove();
			}
		})

	},
	toastr: function (option) {
		var toastr = "<div class='toastr-main'>" +
			"<div class='toastr-bg'></div>" +
			"<div class='toastr-body'>" +
			"<p class='toastr-txt'>" + option + "</p>" +
			"</div>" +
			"</div>";
		$('body').append(toastr);
		setTimeout(function () {
			$(".toastr-main").remove();
		}, 1000);
	}
});