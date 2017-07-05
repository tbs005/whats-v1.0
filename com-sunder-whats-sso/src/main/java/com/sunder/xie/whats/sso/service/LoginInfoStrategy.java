package com.sunder.xie.whats.sso.service;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xieshengrong on 2017/5/23.
 */

public interface LoginInfoStrategy<T extends Serializable> {
    T login(T var1);

    void logoutNormal(String var1, Date var2);

    void logoutSessionTimeOut(String var1, Date var2);

    void logoutSystemError(Date var1);
}

