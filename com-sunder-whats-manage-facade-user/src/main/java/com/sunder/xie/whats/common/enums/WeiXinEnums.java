package com.sunder.xie.whats.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信接口返回状态码
 * @author zhu_shangjin
 * @version 2016年12月6日 上午11:02:29
 */
public enum WeiXinEnums {

	WEIXIN_OK					(0, "success"), 
	WEIXIN_EXCEPTION			(6101, "发生系统异常"), 
	WEIXIN_UPDATE				(6107, "更新用户信息失败"), 
	WEIXIN_ADD					(6108, "添加用户信息失败"), 
	WEIXIN_DELETE				(6109, "删除用户信息失败"),
	WEIXIN_BATCH_ADD_USER		(6110,"批量新增用户表失败"),
	WEIXIN_BATCH_ADD_TOTALUSER  (6111,"批量新增用户openid表失败"),
	WEIXIN_ACCESSTOKEN          (6112,"设置accesstoken失败"),
	WEIXIN_REQADD				(6113,"新增失败"),
	WEIXIN_REQUPDATE			(6114,"更新失败"),
	WEIXIN_REQDELETE			(6115,"删除失败"),
	WEIXIN_SUB					(6119,"微信用户关注失败"),
	WEIXIN_UNSUB				(6120,"微信用户取消关注失败"),
	WEIXIN_LOCATION				(6121,"微信获取用户地理位置信息失败"),
	WEIXIN_MESSAGE				(6666,"微信消息操作失败");
	private int errcode;
	private String errmsg;

	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	WeiXinEnums(int errcode, String errmsg) {
		this.errcode = errcode;
		this.errmsg = errmsg;
	}

	public static WeiXinEnums getEnum(int errcode) {
		WeiXinEnums resultEnum = null;
		WeiXinEnums[] enumAry = WeiXinEnums.values();
		for (int i = 0; i < enumAry.length; i++) {
			if (enumAry[i].getErrcode() == errcode) {
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}

	public static Map<String, Map<String, Object>> toMap() {
		WeiXinEnums[] ary = WeiXinEnums.values();
		Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
		for (int num = 0; num < ary.length; num++) {
			Map<String, Object> map = new HashMap<String, Object>();
			String key = String.valueOf(getEnum(ary[num].getErrcode()));
			map.put("errcode", String.valueOf(ary[num].getErrcode()));
			map.put("errmsg", ary[num].getErrmsg());
			enumMap.put(key, map);
		}
		return enumMap;
	}

//	public static void main(String[] args) {
//		System.out.println(RoleAndMqEnums.ROLEANDMQ_EXCEPTION.errcode);
//		System.out.println(RoleAndMqEnums.ROLEANDMQ_EXCEPTION.errmsg);
//		RoleAndMqEnums r = RoleAndMqEnums.getEnum(6101);
//		System.out.println(RoleAndMqEnums.getEnum(6101).errmsg);
//		System.out.println(toMap());
//	}

}
