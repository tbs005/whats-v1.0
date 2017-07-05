package com.sunder.xie.whats.sso.service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Map;

/**
 * Created by xieshengrong on 2017/5/23.
 */
public interface SecurityServiceHandler { void init();

    Map<ServletRequest, ServletResponse> doFilter(ServletRequest var1, ServletResponse var2);

    void destroy();
}
