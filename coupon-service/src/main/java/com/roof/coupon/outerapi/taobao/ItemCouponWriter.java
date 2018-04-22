package com.roof.coupon.outerapi.taobao;

import com.roof.coupon.itemcoupon.entity.ItemCoupon;
import com.roof.coupon.itemcoupon.service.api.IItemCouponService;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * @author liuxin
 * @since 2018/4/15
 */
public class ItemCouponWriter implements ItemWriter<List<ItemCoupon>> {
    private IItemCouponService iItemCouponService;


    public void setiItemCouponService(IItemCouponService iItemCouponService) {
        this.iItemCouponService = iItemCouponService;
    }

    @Override
    public void write(List<? extends List<ItemCoupon>> list) throws Exception {
        for (List<ItemCoupon> lists : list) {
            iItemCouponService.saveOrUpdateByOuterId(lists);
        }
    }
}
