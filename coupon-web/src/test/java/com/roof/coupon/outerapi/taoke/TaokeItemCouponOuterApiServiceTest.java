package com.roof.coupon.outerapi.taoke;

import com.roof.coupon.itemcoupon.service.impl.ItemCouponService;
import com.roof.coupon.outerapi.ItemCouponOuterApiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.roof.roof.dataaccess.api.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

/**
 * @author liuxin
 * @since 2018/4/14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath*:spring.xml"})
public class TaokeItemCouponOuterApiServiceTest {
    private ItemCouponOuterApiService itemCouponOuterApiService;

    @Test
    public void query() {
        Page page = new Page();
        page.setCurrentPage(1L);
        page = itemCouponOuterApiService.query(null, page);
        for (Object o : page.getDataList()) {
            System.out.println(o);
        }
    }

    @Autowired
    public void setItemCouponOuterApiService(@Qualifier("taokeItemCouponOuterApiService") ItemCouponOuterApiService itemCouponOuterApiService) {
        this.itemCouponOuterApiService = itemCouponOuterApiService;
    }
}