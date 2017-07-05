package com.sunder.xie.whats.common.config;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.sunder.xie.whats.common.utils.ResourceUtils;



public class PublicConfig {

		/**
		 * 系统文件配置 加载。
		 */
		public static Map<String, String> HTTPURL = ResourceUtils.getResource("httpurl").getMap();
		
		public static List<String> WHITELIST = new ArrayList<String>();
		
		public static List<String> getWhiteList(){
			if (WHITELIST.size()<1) {
				String whitea = PublicConfig.HTTPURL.get("WHITE_IPA");
				String whiteb = PublicConfig.HTTPURL.get("WHITE_IPB");
				String[] as = whitea.split("-");
				WHITELIST.addAll(Arrays.asList(as));
				as = whiteb.split("-");
				WHITELIST.addAll(Arrays.asList(as));
			} 
			return WHITELIST;
			
		}
	

	

	}

