package com.roof.coupon.itemcats.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializable;
import com.alibaba.fastjson.serializer.JSONSerializer;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by zhenglt on 2018/02/21.
 */
public enum ItemCatsStatusEnum implements JSONSerializable {

    normal( "正常","normal"),
    deleted( "删除","deleted");



    private String display;
    private  String value;

    private ItemCatsStatusEnum( String display, String value){
        this.display = display;
        this.value = value;
    }

    public static ItemCatsStatusEnum getEnumByCode(Integer code){
        for(ItemCatsStatusEnum stateEnum:values()){
            if(stateEnum.getValue().equals(code)){
                return stateEnum;
            }
        }
        return null;
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
        object.put("value",value);
        object.put("display",display);
        serializer.write(object);

    }
}
