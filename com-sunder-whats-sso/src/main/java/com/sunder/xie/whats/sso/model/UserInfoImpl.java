package com.sunder.xie.whats.sso.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

/**
 * Created by xieshengrong on 2017/5/23.
 */

public class UserInfoImpl implements UserInfo {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String userCode;
    private String password;
    private List<String> resources = new ArrayList();
    private Map<String, Set<String>> orgAuthority = new HashMap();
    private Map<String, String> menus = new HashMap();
    private GenericUser user;

    public UserInfoImpl() {
    }

    public UserInfoImpl(GenericUser user) {
        this.id = user.getId();
        this.userCode = user.getCode();
        this.password = user.getPassword();
        this.user = user;
    }

    public GenericUser getSimpleUser() {
        return this.user;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList grantedAuthorities = new ArrayList();
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_DEFAULT");
        grantedAuthorities.add(grantedAuthority);
        return grantedAuthorities;
    }

    public String getPassword() {
        return this.password;
    }

    public Map<String, String> getMenus() {
        if(this.menus == null) {
            this.menus = new HashMap();
        }

        return this.menus;
    }

    public String getUsername() {
        return this.getUserCode();
    }

    public String getUserCode() {
        return this.userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, Set<String>> getOrgAuthority() {
        if(this.orgAuthority == null) {
            this.orgAuthority = new HashMap();
        }

        return this.orgAuthority;
    }

    public List<String> getResources() {
        if(this.resources == null) {
            this.resources = new ArrayList();
        }

        return this.resources;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("UserInfoImpl: {");
        sb.append("id:").append(this.id).append(",");
        sb.append("userCode:").append(this.userCode).append(",");
        sb.append("password:[protected],");
        sb.append("resources:").append(this.resources);
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    public boolean equals(Object obj) {
        if(null == obj) {
            return false;
        } else if(this == obj) {
            return true;
        } else if(!this.getClass().equals(obj.getClass())) {
            return false;
        } else {
            UserInfoImpl that = (UserInfoImpl)obj;
            return null == this.getId()?false:this.getId().equals(that.getId());
        }
    }
}

