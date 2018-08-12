package com.roof.coupon.order.impl;

import com.alibaba.fastjson.JSON;
import com.roof.coupon.core.http.HttpClientUtil;
import com.roof.coupon.order.UnifiedorderRequest;
import com.roof.coupon.order.api.WechatOrderService;
import com.roof.coupon.wechat.service.api.IWeChatHander;
import org.roof.commons.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * com.roof.coupon.order.impl
 *
 * @author liht
 * @date 2018/6/7
 */
@Service
public class WechatOrderServiceImpl implements WechatOrderService {


    private static final Logger logger = LoggerFactory.getLogger(WechatOrderServiceImpl.class);
    private String wechatappid = PropertiesUtil.getPorpertyString("wechat.appid");

    private String appid = PropertiesUtil.getPorpertyString("fpa.mini.appid");
    private String mchid = PropertiesUtil.getPorpertyString("wechat.pay.mch_id");
    private String notify_url = PropertiesUtil.getPorpertyString("wechat.pay.notify_url");
    private String messageservice_sendurl = PropertiesUtil.getPorpertyString("wechat.pay.unifiedorder.url");

    @Autowired
    private IWeChatHander weChatHander;


    /**
     * 自动生成32位的UUid，对应数据库的主键id进行插入用。
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Override
    public SortedMap<Object, Object> getPrepayId(String openid, String prodName, String orderNum, String ip, Integer fee) {
        SortedMap<Object, Object> packageP = new TreeMap<Object, Object>();
        String times = System.currentTimeMillis() + "";

        UnifiedorderRequest request = new UnifiedorderRequest();
        request.setAppid(appid);
//        request.setSub_appid(appid);
//        request.setAttach("附加数据");
        request.setBody(prodName);
//        request.setDetail("");
        //request.setDevice_info();
//        request.setFee_type("CNY");
//        request.setGoods_tag();
//        request.setLimit_pay();
        request.setMch_id(mchid);
        request.setNonce_str(times);
        request.setNotify_url(notify_url);
        request.setOpenid(openid);

//        request.setSub_openid("oE38F0bP2WOHi8hPs6auZYcrMt3E");
        request.setOut_trade_no(orderNum);
//        request.setProduct_id();
//        request.setSign_type("MD5");
        request.setSpbill_create_ip(ip);
//        request.setTime_expire();
//        request.setTime_start();
        request.setTotal_fee(fee);//单位为分
        request.setTrade_type("JSAPI");

        SortedMap<Object, Object> param = PayCommonUtil.objToSortedMap(request);
        String sign = PayCommonUtil.createSign("UTF-8", param);
        param.put("sign", sign);


        String requestXML = PayCommonUtil.getRequestXml(param);
        System.out.println(requestXML);


        try {
            logger.info("发送的文本的消息格式为：" + requestXML);
            String resXml = HttpClientUtil.post(messageservice_sendurl, requestXML);
            logger.info("微信返回结果为：" + resXml);


            Map map = XMLUtil.doXMLParse(resXml);
            System.out.println(map);

            //得到prepay_id
            String prepay_id = (String) map.get("prepay_id");

            packageP.put("appId", appid);//！！！注意，这里是appId,上面是appid，真怀疑写这个东西的人。。。
            packageP.put("nonceStr", times);//时间戳
            packageP.put("package", "prepay_id=" + prepay_id);//必须把package写成 "prepay_id="+prepay_id这种形式
            packageP.put("signType", "MD5");//paySign加密
            packageP.put("timeStamp", (System.currentTimeMillis() / 1000) + "");
            //得到paySign
            String paySign = PayCommonUtil.createSign("UTF-8", packageP);
            packageP.put("paySign", paySign);


            System.out.println(JSON.toJSONString(packageP));

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return packageP;
    }

    @Override
    public String createOrder() {
        return null;
    }
}
