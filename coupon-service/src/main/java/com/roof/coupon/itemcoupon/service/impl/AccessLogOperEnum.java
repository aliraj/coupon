package com.roof.coupon.itemcoupon.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializable;
import com.alibaba.fastjson.serializer.JSONSerializer;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author liht
 */

public enum AccessLogOperEnum implements JSONSerializable {

    read_coupon_detail(1, "读券详情", "read_coupon_detail"),
    search_coupon_list(0, "搜索券列表", "search_coupon_list");


    private Integer code;
    private String display;
    private String value;

    private AccessLogOperEnum(Integer code, String display, String value) {
        this.code = code;
        this.display = display;
        this.value = value;
    }

    public static AccessLogOperEnum getEnumByCode(Integer code) {
        for (AccessLogOperEnum enumvalue : values()) {
            if (enumvalue.getCode().equals(code)) {
                return enumvalue;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void write(JSONSerializer serializer, Object fieldName, Type fieldType, int features) throws IOException {

        JSONObject object = new JSONObject();
        object.put("code", code);
        object.put("display", display);
        serializer.write(object);

    }
}
