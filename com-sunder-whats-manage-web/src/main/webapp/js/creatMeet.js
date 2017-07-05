$(function () {

	$('.begin-time').mobiscroll().datetime({
		theme: "android-holo-light", // Specify theme like: theme: 'ios' or omit setting to use default 
		mode: "scroller", //效果
		display: "bottom", // 显示位置
		lang: "zh", //中文
		minDate: new Date(), //最小日期
//		maxDate: new Date(new Date().setMonth(new Date().getMonth() + 6)), //最大日期
		dateFormat: "yy-mm-dd",
		timeFormat: "HH:ii",
		stepMinute: 1 // More info about stepMinute: http://docs.mobiscroll.com/2-16-1/datetime#!opt-stepMinute
	});

});

var vue=new Vue({
	el: '#creat-meet',
	data: {
		formBox: {
			basic: true,
			time: false,
			remark: false
		},
		itemText: {
			timeTxt: '请选择',
			remarkTxt: '请填写'
		},
		modal: false,
	}
	//	过滤器
	,
	filters: {}
	//方法
	,

	methods: {
		//  回基本信息
		go_basic: function () {
			this.formBox.basic = true;
			this.formBox.time = false;
			this.formBox.remark = false;
		},
		selectTime: function () {
			this.formBox.basic = false;
			this.formBox.time = true;
		},
		//  保存时间
		saveTime: function () {
			var _this = this,
				inputIndex = 0,
				inputList = $('.js-meetTime-info').find('input');
			inputList.each(function () {
				if (this.value != '') {
					inputIndex++;
				}
			});
			if (inputIndex > 0) {
				_this.itemText.timeTxt = "已选择"
			} else {
				_this.itemText.timeTxt = "请选择"
			}
			this.go_basic();
		},
		//   备注
		remarkNum: function () {
			var markNum = document.getElementById('remarks_box').value.length;
			document.getElementById("text-num").textContent = markNum;
		},
		selectRemark: function () {
			this.formBox.basic = false;
			this.formBox.remark = true;
		},
		saveRemark: function () {
			_this = this;
			var markNum = document.getElementById('remarks_box').value;
			if (markNum == '') {
				_this.itemText.remarkTxt = '请填写';
			} else {
				_this.itemText.remarkTxt = '已填写';
			}
			this.go_basic();
		},
		// 时间转换
		setTime:function(e){
			var obj=e.currentTarget;
			var newstr = obj.value.replace(/-/g,'/'); 
			var date =  new Date(newstr); 
			var time_str = date.getTime().toString();
            $(obj).next('input').val(time_str);
 
		},
		//  创建子会议
//		childtrue: function () {
//			console.log(meeId);
//			window.location.href = "toMeetList?meeParentId="+meeId+"&hallId="+hallId;
//			//	
//		},
//		// 保存子会议
//		saveChildMeet: function () {
//			 window.location.href = "ch-meet-list.html"	
//		},
//		//  不创建子会议
//		childfalse: function () {
//         	window.location.href = "creatmeet-success.html";	
//		},
			// 创建会议
//		greatmeet: function () {
//		   this.modal=true;
//		}
	}
});