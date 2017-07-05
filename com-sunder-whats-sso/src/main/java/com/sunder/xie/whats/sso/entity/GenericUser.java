package com.sunder.xie.whats.sso.entity;

import com.cpic.caf.core.entity.supports.AbstractEntity;

/**
 * Created by xieshengrong on 2017/5/22.
 */
public class GenericUser extends AbstractEntity<Long> {
    private String name;
    private String code;
    private Long orgId;
    private String password;
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public Long getId() {
        return null;
    }
}
