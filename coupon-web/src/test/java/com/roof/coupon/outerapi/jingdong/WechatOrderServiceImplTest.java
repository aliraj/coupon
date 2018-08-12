package com.roof.coupon.outerapi.jingdong;

import com.alibaba.fastjson.JSON;
import com.roof.coupon.order.api.WechatOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.roof.spring.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.SortedMap;

/**
 * com.roof.coupon.order.impl
 *
 * @author liht
 * @date 2018/6/13
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath*:spring.xml"})
public class WechatOrderServiceImplTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(JingdongItemCatsOuterApiServiceTest.class);

    @Autowired
    private WechatOrderService wechatOrderService;

    @Test
    public void getPrepayId() {
        SortedMap<Object, Object> rs = wechatOrderService.getPrepayId("oE38F0bP2WOHi8hPs6auZYcrMt3E", "测试", "1232131231", "128.0.0.1", 1);
        System.out.println(JSON.toJSONString(rs));
        //        String rs = wechatOrderService.getPrepayId();
//        System.out.println(rs);
    }
}