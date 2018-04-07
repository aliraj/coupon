package com.roof.coupon.outerapi.jingdong;

import com.roof.coupon.outerapi.ItemCatsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Log4jConfigurer;

import java.io.FileNotFoundException;

/**
 * @author liuxin
 * @since 2018/4/1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring.xml"})
public class JingdongItemCatsServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(JingdongItemCatsServiceTest.class);
    @Autowired
    private ItemCatsService itemCatsService;

    @Test
    public void queryByParent() {
        LOGGER.error("s");
        itemCatsService.queryByParent(0);
    }



}