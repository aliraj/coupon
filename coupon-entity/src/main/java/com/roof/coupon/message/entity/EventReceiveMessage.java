package com.roof.coupon.message.entity;

/**
 * com.roof.coupon.message.entity
 *
 * @author liht
 * @date 2018/5/2
 */
public class EventReceiveMessage {

    private String ToUserName;

    private String FromUserName;

    private Long CreateTime;

    private String MsgType;


    private String Event;

    private String SessionFrom;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public String getSessionFrom() {
        return SessionFrom;
    }

    public void setSessionFrom(String sessionFrom) {
        SessionFrom = sessionFrom;
    }
}
