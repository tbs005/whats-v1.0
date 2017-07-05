//package com.sunder.xie.whats.common.utils.cloopen;
//
//import java.util.HashMap;
//
//public class CCPRestSDK {
//
//	/**
//	 * @param phone
//	 *            接收验证码的手机号
//	 * @param time
//	 *            验证码有效时间，单位为分钟
//	 * @return 返回示例： {statusCode=000000,data={templateSMS={smsMessageSid=4
//	 *         cb7b592740b4527bf587d77d6d69297 , dateCreated=20161202093206}},
//	 *         code=561213} code为用户接收到的验证码，statusCode=000000表示短信发送成功
//	 */
//	public static HashMap<String, Object> sendMsgCode(String phone, int time) {
//		HashMap<String, Object> result = null;
//		CCPRestSmsSDK ccpRestSmsSDK = new CCPRestSmsSDK();
//		// 初始化服务器地址和端口，生产环境配置成app.cloopen.com，端口是8883.
//		ccpRestSmsSDK.init("app.cloopen.com", "8883");
//		// 初始化主账号名称和主账号令牌，登陆云通讯网站后，可在"控制台-应用"中看到开发者主账号ACCOUNT SID和 主账号令牌AUTH
//		// TOKEN。
//		ccpRestSmsSDK.setAccount("8a48b55150d142790150d57d29d3180b",
//				"d978d576d311457f9c023f61ef7af502");
//		// 初始化应用ID，如果是在沙盒环境开发，请配置"控制台-应用-测试DEMO"中的APPID。
//		// 如切换到生产环境，请使用自己创建应用的APPID
//		ccpRestSmsSDK.setAppId("8a48b55150d5879c0150d62cecc605b3");
//		// 创建发送短信随机数
//		String msgCode = RandomNum.randomNumber();
//
//		result = ccpRestSmsSDK.sendTemplateSMS(phone, "137494", new String[] {
//				msgCode, String.valueOf(time) });
//		result.put("code", msgCode);
//		// 修改密码时候需要提供的验证码 模板短信id： 112892
//		return result;
//	}
//
//	public static void main(String args[]) {
//		System.out.println(sendMsgCode("13937988294", 2));
//	}
//
//}
