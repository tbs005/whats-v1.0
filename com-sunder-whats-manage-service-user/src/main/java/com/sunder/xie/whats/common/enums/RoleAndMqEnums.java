package com.sunder.xie.whats.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 角色权限接口返回状态码
 * @author zhu_shangjin
 * @version 2016年12月6日 上午11:02:29
 */
public enum RoleAndMqEnums {

	ROLEANDMQ_OK				(0, "success"), 
	ROLEANDMQ_EXCEPTION			(6101, "发生系统异常"), 
	ROLE_UPDATE					(6102, "角色更新失败"), 
	ROLE_ADD					(6103, "角色添加失败"), 
	ROLE_DELETE					(6104, "角色删除失败"),
	HALL_MODULE_BATCH			(6105,"批量新增主会议的显示模块失败"),
	HALL_MODULE_UPDATE			(6106,"修改主会议的显示模块失败"),
	USER_ROLE_ADD				(6116,"添加用户角色关联失败"),
	USER_ROLE_UPDATE			(6117,"修改用户角色关联失败"),
	USER_ROLE_DELETE			(6118,"删除用户角色关联失败");
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

	RoleAndMqEnums(int errcode, String errmsg) {
		this.errcode = errcode;
		this.errmsg = errmsg;
	}

	public static RoleAndMqEnums getEnum(int errcode) {
		RoleAndMqEnums resultEnum = null;
		RoleAndMqEnums[] enumAry = RoleAndMqEnums.values();
		for (int i = 0; i < enumAry.length; i++) {
			if (enumAry[i].getErrcode() == errcode) {
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}

	public static Map<String, Map<String, Object>> toMap() {
		RoleAndMqEnums[] ary = RoleAndMqEnums.values();
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
