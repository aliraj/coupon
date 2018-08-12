package com.roof.coupon.order.api;

import java.util.SortedMap;

/**
 * com.roof.coupon.order.api
 *
 * @author liht
 * @date 2018/6/7
 */
public interface WechatOrderService {


    public SortedMap<Object, Object> getPrepayId(String openid, String prodName, String orderNum, String ip, Integer fee);

    public String createOrder();


}
