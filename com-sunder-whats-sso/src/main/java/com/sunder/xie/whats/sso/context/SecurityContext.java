package com.sunder.xie.whats.sso.context;

import com.cpic.caf.core.context.ThreadLocalContext;
import com.sunder.xie.whats.sso.model.UserInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by xieshengrong on 2017/5/16.
 */
public class SecurityContext {
    private static Set<String> allfunctions = new HashSet();

    public SecurityContext() {
    }

    public static Set<String> getAllfunctions() {
        return allfunctions;
    }

    public static void setAllfunctions(Set<String> allfunctions) {
        allfunctions = allfunctions;
    }

    public static Set<String> getCurrentDataAuthorities() {
        return (Set) (getCurrentDataAuthorities((String) ThreadLocalContext.getContext().get("caf.current.access.url")) != null ? getCurrentDataAuthorities((String) ThreadLocalContext.getContext().get("caf.current.access.url")) : new HashSet());
    }

    public Set<String> getOrgAuthorityByCode(String functionCode) {
        return (Set) (getCurrentUser().getOrgAuthority().get(functionCode) != null ? (Set) getCurrentUser().getOrgAuthority().get(functionCode) : new HashSet());
    }

    public static Map<String, String> getMenus() {
        UserInfo user = getCurrentUser();
        return (Map) (user.getMenus() != null ? user.getMenus() : new HashMap());
    }

    public static Set<String> getCurrentDataAuthorities(String resourceCode) {
        UserInfo user = getCurrentUser();
        Map authority = user.getOrgAuthority();
        return (Set) (authority != null && authority.get(resourceCode) != null ? (Set) authority.get(resourceCode) : new HashSet());
    }

    public static boolean isGranted(String resourceCode) {
        UserInfo user = getCurrentUser();
        Map authority = user.getOrgAuthority();
        return authority != null && authority.containsKey(resourceCode);
    }

    public static UserInfo getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication == null ? null : authentication.getPrincipal();
        return principal != null && principal instanceof UserInfo ? (UserInfo) principal : null;
    }
}
