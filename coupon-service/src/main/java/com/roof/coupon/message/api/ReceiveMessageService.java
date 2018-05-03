package com.roof.coupon.message.api;

/**
 * com.roof.coupon.message.api
 *
 * @author liht
 * @date 2018/5/2
 */
public interface ReceiveMessageService {

    public String receiveMessage(String msg);

    public String sendTextMessage(String touser, String content);

    public String sendLinkMessage(String touser, String title, String description, String url, String thumb_url);
}
