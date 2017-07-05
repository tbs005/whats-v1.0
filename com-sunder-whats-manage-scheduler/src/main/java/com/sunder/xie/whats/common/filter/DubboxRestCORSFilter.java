package com.sunder.xie.whats.common.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

import org.apache.commons.lang3.StringUtils;

import com.sunder.xie.whats.common.config.PublicConfig;

/**
 * 接口跨域调用
 * @author zhu_shangjin
 * @version 2016年12月5日 上午11:15:50
 */
public class DubboxRestCORSFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext paramContainerRequestContext,
			ContainerResponseContext paramContainerResponseContext)
			throws IOException {
		// String remoteHost = RpcContext.getContext().getRemoteHost();
		List<String> serverhost = paramContainerRequestContext.getHeaders()
				.get("Origin");
		List<String> serverhostip = new ArrayList<String>();
		if (serverhost !=null) {
			for (String ip : serverhost) {
				if (StringUtils.isBlank(ip) || Objects.equals("null", ip)) {
				} else if(ip.startsWith("http")){
					if (ip.lastIndexOf(":") > 5) {
						ip = ip.substring(ip.indexOf("//") + 2, ip.lastIndexOf(":"));
					} else {
						ip = ip.substring(ip.indexOf("//") + 2);
					}
					serverhostip.add(ip);
				}

			}
			
		} 
		

		if (PublicConfig.getWhiteList().containsAll(serverhostip)) {
			paramContainerResponseContext.getHeaders().putSingle(
					"Access-Control-Allow-Origin", "*");

			// paramContainerResponseContext.getHeaders().putSingle(
			// "Access-Control-Allow-Headers",
			// "X-Requested-With,Content-Type");

			paramContainerResponseContext.getHeaders().putSingle(
					"Access-Control-Allow-Headers",
					"Origin, X-Requested-With, Content-Type, Accept, Cookie");

			paramContainerResponseContext.getHeaders().putSingle("Accept",
					"text/html,application/json");
			paramContainerResponseContext.getHeaders().putSingle(
					"Access-Control-Allow-Methods",
					"GET,POST,PUT,DELETE,OPTIONS");
			paramContainerResponseContext.getHeaders().putSingle(
					"Content-Type", "application/json");
			paramContainerResponseContext.getHeaders().putSingle(
					"Access-Control-Allow-Credentials", "true");

		}

	}

}
