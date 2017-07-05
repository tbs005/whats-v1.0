package com.sunder.xie.whats.sso.web.model;

/**
 * Meta : 封装返回给前端的metadata
 *
 */
public class Meta {

    /**
     *
     */
    private String errcode;
    private String msg;
    private String syscode;
    private String sysmsg;

    @Override
    public String toString() {
        return "Meta{" +
                "errcode='" + errcode + '\'' +
                ", msg='" + msg + '\'' +
                ", syscode='" + syscode + '\'' +
                ", sysmsg='" + sysmsg + '\'' +
                '}';
    }

    public String getSysmsg() {
        return sysmsg;
    }

    public void setSysmsg(String sysmsg) {
        this.sysmsg = sysmsg;
    }

    public String getSyscode() {

        return syscode;
    }

    public void setSyscode(String syscode) {
        this.syscode = syscode;
    }

    public String getMsg() {

        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getErrcode() {

        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public Meta() {

    }

}
