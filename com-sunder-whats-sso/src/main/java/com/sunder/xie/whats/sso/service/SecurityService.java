package com.sunder.xie.whats.sso.service;

import com.sunder.xie.whats.sso.context.SpringAppContextUtil;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.*;

/**
 * Created by xieshengrong on 2017/5/23.
 */
public class SecurityService {
    private List<SecurityServiceHandler> securityServiceHandlers;

    public SecurityService() {
    }

    public void init() {
        if (this.securityServiceHandlers == null) {
            this.securityServiceHandlers = new ArrayList();
            Map i$ = SpringAppContextUtil.getBeansOfType(SecurityServiceHandler.class);
            Iterator handler = i$.values().iterator();

            while (handler.hasNext()) {
                SecurityServiceHandler handler1 = (SecurityServiceHandler) handler.next();
                this.securityServiceHandlers.add(handler1);
                handler1.init();
            }
        } else {
            Iterator i$1 = this.securityServiceHandlers.iterator();

            while (i$1.hasNext()) {
                SecurityServiceHandler handler2 = (SecurityServiceHandler) i$1.next();
                handler2.init();
            }
        }

    }

    public Map<ServletRequest, ServletResponse> doFilter(ServletRequest request, ServletResponse response) {
        HashMap hashMap = new HashMap();
        hashMap.put(request, response);
        if (this.securityServiceHandlers == null) {
            return hashMap;
        } else {
            SecurityServiceHandler handler;
            ServletRequest request1;
            ServletResponse response1;
            for (Iterator i$ = this.securityServiceHandlers.iterator(); i$.hasNext(); hashMap = (HashMap) handler.doFilter(request1, response1)) {
                handler = (SecurityServiceHandler) i$.next();
                request1 = (ServletRequest) ((Map.Entry) hashMap.entrySet().iterator().next()).getKey();
                response1 = (ServletResponse) ((Map.Entry) hashMap.entrySet().iterator().next()).getValue();
            }

            return hashMap;
        }
    }

    public void destroy() {
        if (this.securityServiceHandlers != null) {
            Iterator i$ = this.securityServiceHandlers.iterator();

            while (i$.hasNext()) {
                SecurityServiceHandler handler = (SecurityServiceHandler) i$.next();
                handler.destroy();
            }
        }

    }

    public List<SecurityServiceHandler> getSecurityServiceHandlers() {
        return this.securityServiceHandlers;
    }

    public void setSecurityServiceHandlers(List<SecurityServiceHandler> securityServiceHandlers) {
        this.securityServiceHandlers = securityServiceHandlers;
    }
}
