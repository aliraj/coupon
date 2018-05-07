package com.roof.coupon.message.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.roof.coupon.core.http.HttpClientUtil;
import com.roof.coupon.itemcoupon.entity.ItemCoupon;
import com.roof.coupon.itemcoupon.entity.ItemCouponVo;
import com.roof.coupon.itemcoupon.service.api.IItemCouponService;
import com.roof.coupon.message.api.ReceiveMessageService;
import com.roof.coupon.message.entity.*;
import com.roof.coupon.wechat.service.api.IWeChatHander;
import org.roof.commons.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.xml.soap.Text;
import java.util.List;

/**
 * com.roof.coupon.message.impl
 *
 * @author liht
 * @date 2018/5/2
 */
@Service
public class ReceiveMessageServiceImpl implements ReceiveMessageService {
    private static final Logger logger = LoggerFactory.getLogger(ReceiveMessageServiceImpl.class);
    private String appid = PropertiesUtil.getPorpertyString("fpa.mini.appid");
    private String messageservice_sendurl = PropertiesUtil.getPorpertyString("messageservice.sendurl");

    @Autowired
    private IWeChatHander weChatHander;

    public final static String MSG_TYPE_TEXT = "text";//文字
    public final static String MSG_TYPE_IMAGE = "image";//图片
    public final static String MSG_TYPE_MINIPRO = "miniprogrampage";//小程序
    public final static String MSG_TYPE_EVENT = "event";//事件

    @Autowired
    private IItemCouponService itemCouponService;

    @Async
    @Override
    public String receiveMessage(String msg) {
        Assert.notNull(msg, "消息不能为空");
        JSONObject obj = JSON.parseObject(msg);
        String MsgType = obj.getString("MsgType");
        logger.info("收到消息内容为：" + msg);
        if (MsgType.equals(ReceiveMessageServiceImpl.MSG_TYPE_EVENT)) {
            logger.info("消息类型为：事件" + MsgType);
            EventReceiveMessage receiveMessage = JSON.parseObject(msg, EventReceiveMessage.class);
//            this.sendTextMessage(receiveMessage.getFromUserName(), "欢迎来到🧡❤️的世界里");

        } else if (MsgType.equals(ReceiveMessageServiceImpl.MSG_TYPE_IMAGE)) {
            logger.info("消息类型为：图片" + MsgType);

        } else if (MsgType.equals(ReceiveMessageServiceImpl.MSG_TYPE_MINIPRO)) {
            logger.info("消息类型为：小程序" + MsgType);

        } else if (MsgType.equals(ReceiveMessageServiceImpl.MSG_TYPE_TEXT)) {
            logger.info("消息类型为：文字" + MsgType);

            TextReceiveMessage text = JSON.parseObject(msg, TextReceiveMessage.class);
            if (text.getContent() != null && text.getContent().indexOf(":") > -1 && text.getContent().split(":")[0].equals("jd")) {
                logger.info("该消息为京东消息" + text.getContent());

                ItemCoupon itemCoupon = new ItemCoupon();
                itemCoupon.setPlatform("jingtuitui");
                itemCoupon.setOuterId(text.getContent().split(":")[1]);
                List<ItemCouponVo> list = itemCouponService.selectForList(itemCoupon);
                if (list != null && list.size() > 0) {
                    ItemCouponVo vo = list.get(0);
                    this.sendLinkMessage(text.getFromUserName(), vo.getTitle(), vo.getItemDescription(), vo.getCouponClickUrl(), vo.getPictUrl());

                } else {
                    this.sendTextMessage(text.getFromUserName(), "没有找到对应的京东优惠券数据，sorry");
                    logger.info("没有找到对应的数据");
                }
            } else {
                logger.info("该消息不为京东消息，忽略");
            }


        } else {
            logger.info("msgtype 不正确：" + MsgType);
        }
        return "";
    }

    @Override
    public String sendTextMessage(String touser, String content) {
        String result = "";
        logger.info("开始发送文本消息");
        SendMessage sendMessage = new SendMessage();
        TextMessageBody textMessageBody = new TextMessageBody();

        textMessageBody.setContent(content);
        sendMessage.setTouser(touser);
        sendMessage.setMsgtype(SendMessage.MSGTYPE_TEXT);
        sendMessage.setText(textMessageBody);

        try {
            logger.info("发送的文本的消息格式为：" + JSON.toJSONString(sendMessage));
            String rs = HttpClientUtil.post(messageservice_sendurl + "?access_token=" + weChatHander.getAccess_token(), JSON.toJSONString(sendMessage));
            logger.info("微信返回结果为：" + rs);
            result = rs;
        } catch (Exception e) {
            logger.error(e.getMessage());
            result = e.getMessage();
        }

        return result;
    }


    @Override
    public String sendLinkMessage(String touser, String title, String description, String url, String thumb_url) {
        String result = "";
        logger.info("开始发送链接消息");
        SendMessage sendMessage = new SendMessage();
        LinkMessageBody linkMessageBody = new LinkMessageBody();

        linkMessageBody.setDescription(description);
        linkMessageBody.setThumb_url(thumb_url);
        linkMessageBody.setTitle(title);
        linkMessageBody.setUrl(url);
        sendMessage.setLink(linkMessageBody);
        sendMessage.setTouser(touser);
        sendMessage.setMsgtype(SendMessage.MSGTYPE_LINK);

        try {
            logger.info("发送的link的消息格式为：" + JSON.toJSONString(sendMessage));
            String rs = HttpClientUtil.post(messageservice_sendurl + "?access_token=" + weChatHander.getAccess_token(), JSON.toJSONString(sendMessage));
            logger.info("微信返回结果为：" + rs);
            result = rs;
        } catch (Exception e) {
            logger.error(e.getMessage());
            result = e.getMessage();
        }
        return result;
    }


}
