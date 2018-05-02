package com.roof.coupon.outerapi.jingdong;

import com.roof.coupon.outerapi.ItemCatsOuterApiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * @author liuxin
 * @since 2018/4/1
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring-service.xml"})
public class JingdongItemCatsOuterApiServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(JingdongItemCatsOuterApiServiceTest.class);
    @Autowired
    private ItemCatsOuterApiService itemCatsOuterApiService;

    @Test
    public void queryByParent() {
        LOGGER.error("s");
        itemCatsOuterApiService.queryByParent(0);
    }





}