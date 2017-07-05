package com.sunder.xie.whats.sso.web.support;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xieshengrong on 2017/5/24.
 */
public class ErrorCode <T> implements Serializable {
    private static final long serialVersionUID = 4418416282894231647L;
    private String errcode;
    private String msg;
    private String syscode;
    private String sysmsg;
    private T retval;
    private boolean success = false;
    private static final String CODE_SUCCESS = "0000";
    private static final String CODE_UNKNOWN_ERROR = "9999";
    public static ErrorCode SUCCESS = new ErrorCode("0000", "操作成功！", (Object) null);

    private ErrorCode(String code, String msg, T retval) {
        this.errcode = code;
        this.msg = msg;
        this.syscode = code;
        this.sysmsg = msg;
        this.retval = retval;
        this.success = StringUtils.equals(code, "0000");
    }

    public boolean equals(Object another) {
        return another != null && another instanceof ErrorCode ? this.errcode == ((ErrorCode) another).errcode : false;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public static ErrorCode getFailure(String msg) {
        return new ErrorCode("9999", msg, (Object) null);
    }

    public static ErrorCode getFailure(String code, String msg) {
        return new ErrorCode(code, msg, (Object) null);
    }

    public static ErrorCode getSuccess(String msg) {
        return new ErrorCode("0000", msg, (Object) null);
    }

    public static <T> ErrorCode<T> getSuccessReturn(T retval) {
        return new ErrorCode("0000", (String) null, retval);
    }

    public static <T> ErrorCode<T> getSuccessReturn(String msg, T retval) {
        return new ErrorCode("0000", msg, retval);
    }

    public static ErrorCode getSuccessMap() {
        return new ErrorCode("0000", (String) null, new HashMap());
    }

    public static <T> ErrorCode getFailureReturn(T retval) {
        return new ErrorCode("9999", (String) null, retval);
    }

    public static ErrorCode getFullErrorCode(String code, String msg, Object retval) {
        return new ErrorCode(code, msg, retval);
    }

    public T getRetval() {
        return this.retval;
    }

    public String getMsg() {
        return this.msg;
    }

    public void put(Object key, Object value) {
        ((Map) this.retval).put(key, value);
    }

    public String getErrcode() {
        return this.errcode;
    }

    public String getSyscode() {
        return this.syscode;
    }

    public ErrorCode setSyscode(String syscode) {
        this.syscode = syscode;
        return this;
    }

    public String getSysmsg() {
        return this.sysmsg;
    }

    public ErrorCode setSysmsg(String sysmsg) {
        this.sysmsg = sysmsg;
        return this;
    }
