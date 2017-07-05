package com.sunder.xie.whats.sso.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by xieshengrong on 2017/5/23.
 */

public class OnlineInfoListener implements HttpSessionListener, ServletContextListener {
    @Autowired(
            required = false
    )
    Collection<LoginInfoStrategy> loginInfoStrategies;

    public OnlineInfoListener() {
    }

    public void sessionCreated(HttpSessionEvent se) {
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        Logger logger = LoggerFactory.getLogger(OnlineInfoListener.class);
        HttpSession session = se.getSession();
        String sessionId = session.getId();
        ServletContext context = session.getServletContext();
        this.init(context);
        Object object = session.getAttribute("LOGOUT_NORMAL");
        session.setAttribute("valid", Boolean.valueOf(false));
        boolean logoutNormal = false;
        if(object != null) {
            logoutNormal = ((Boolean)object).booleanValue();
        }

        if(!logoutNormal && StringUtils.isNotBlank(sessionId)) {
            Iterator i$ = this.loginInfoStrategies.iterator();

            while(i$.hasNext()) {
                LoginInfoStrategy strategy = (LoginInfoStrategy)i$.next();
                strategy.logoutSessionTimeOut(sessionId, new Date());
            }

            logger.info("为了安全,在规定的时间内没有进行任何操作,已强制用户退出! 如须继续访问,请重新登录 !");
        }

    }

    public void contextInitialized(ServletContextEvent sce) {
        this.init(sce.getServletContext());
        Iterator i$ = this.loginInfoStrategies.iterator();

        while(i$.hasNext()) {
            LoginInfoStrategy strategy = (LoginInfoStrategy)i$.next();
            strategy.logoutSystemError(new Date());
        }

    }

    public void contextDestroyed(ServletContextEvent sce) {
    }

    public void init(ServletContext context) {
        if(this.loginInfoStrategies == null) {
            WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
            Map strategies = ctx.getBeansOfType(LoginInfoStrategy.class);
            this.loginInfoStrategies = strategies.values();
        }

    }
}

