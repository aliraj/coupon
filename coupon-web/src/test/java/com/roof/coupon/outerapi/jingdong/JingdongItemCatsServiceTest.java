package com.roof.coupon.outerapi.jingdong;

import com.roof.coupon.outerapi.ItemCatsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author liuxin
 * @since 2018/4/1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath*:spring.xml"})
public class JingdongItemCatsServiceTest {
    private ItemCatsService itemCatsService;

    @Test
    public void queryByParent() {
        itemCatsService.queryByParent(0);
    }

    @Autowired
    public void setItemCatsService(@Qualifier("jingdongItemCatsService") ItemCatsService itemCatsService) {
        this.itemCatsService = itemCatsService;
    }
}