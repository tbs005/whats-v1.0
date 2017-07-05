package com.sunder.xie.whats.sso.model;

/**
 * Created by xieshengrong on 2017/5/23.
 */
public class SSOValidationResult {
    private boolean success;
    private SSOValidationResult.SSOValidationUser user;

    public SSOValidationResult() {
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public SSOValidationResult.SSOValidationUser getUser() {
        return this.user;
    }

    public void setUser(SSOValidationResult.SSOValidationUser user) {
        this.user = user;
    }

    public class SSOValidationUser {
        private String loginName;
        private String realName;

        public SSOValidationUser() {
        }

        public String getLoginName() {
            return this.loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getRealName() {
            return this.realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }
    }
}
