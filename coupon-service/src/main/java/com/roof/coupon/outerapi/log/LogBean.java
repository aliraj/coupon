package com.roof.coupon.outerapi.log;

/**
 * 接口日志
 *
 * @author liuxin
 * @since 2018/4/1
 */
public class LogBean {
    public static final String PLATFORM_JINGDONG = "jingdong";
    public static final String PLATFORM_TAOBAO = "taobao";

    private String platform;
    private String api;
    private Object req;
    private Object resp;

    public LogBean(String platform, String api, Object req, Object resp) {
        this.platform = platform;
        this.api = api;
        this.req = req;
        this.resp = resp;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public Object getReq() {
        return req;
    }

    public void setReq(Object req) {
        this.req = req;
    }

    public Object getResp() {
        return resp;
    }

    public void setResp(Object resp) {
        this.resp = resp;
    }
}
