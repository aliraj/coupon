package com.roof.coupon.outerapi.taoke;

import com.roof.coupon.itemcoupon.entity.ItemCoupon;
import com.roof.coupon.itemcoupon.entity.ItemCouponVo;
import com.roof.coupon.itemcoupon.service.api.IItemCouponService;
import com.roof.coupon.itemcoupon.service.impl.ItemCouponService;
import com.roof.coupon.itemcoupon.service.impl.TaobaoCommond;
import com.roof.coupon.outerapi.ItemCouponOuterApiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.roof.roof.dataaccess.api.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;

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

    private IItemCouponService itemCouponService;

    @Autowired
    private TaobaoCommond taobaoCommond;
    @Test
    public void query() throws IOException {
        Page page = new Page();
        page.setCurrentPage(1L);
        page = itemCouponOuterApiService.query(null, page);
        for (Object o : page.getDataList()) {
            System.out.println(o);
        }
    }

    @Test
    public void getCode() throws IOException {
        //ItemCoupon itemCoupon = itemCouponService.load(new ItemCoupon(30930L));
        //taobaoCommond.getCode(itemCoupon);
    }

    @Autowired
    public void setItemCouponOuterApiService(@Qualifier("taokeItemCouponOuterApiService") ItemCouponOuterApiService itemCouponOuterApiService) {
        this.itemCouponOuterApiService = itemCouponOuterApiService;
    }

    @Autowired(required = true)
    public void setItemCouponService(@Qualifier("itemCouponService") IItemCouponService itemCouponService) {
        this.itemCouponService = itemCouponService;
    }
}