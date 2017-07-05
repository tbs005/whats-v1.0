package com.sunder.xie.whats.sso.model;

import com.sunder.xie.whats.sso.entity.GenericUser;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by xieshengrong on 2017/5/23.
 */

public interface UserInfo extends UserDetails {
    Long getId();

    String getPassword();

    String getUserCode();

    Map<String, Set<String>> getOrgAuthority();

    Map<String, String> getMenus();

    List<String> getResources();

    GenericUser getSimpleUser();
}

