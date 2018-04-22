package com.roof.coupon.outerapi.jingdong;

import com.roof.coupon.outerapi.ItemCatsOuterApiService;
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
public class JingdongItemCatsOuterApiServiceTest {
    private ItemCatsOuterApiService itemCatsOuterApiService;

    @Test
    public void queryByParent() {
        itemCatsOuterApiService.queryByParent(0);
    }

    @Autowired
    public void setItemCatsOuterApiService(@Qualifier("jingdongItemCatsService") ItemCatsOuterApiService itemCatsOuterApiService) {
        this.itemCatsOuterApiService = itemCatsOuterApiService;
    }
}