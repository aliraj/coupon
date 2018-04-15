package com.roof.coupon.outerapi.taobao;

import com.roof.coupon.itemcoupon.entity.ItemCoupon;
import com.roof.coupon.itemcoupon.service.api.IItemCouponService;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * @author liuxin
 * @since 2018/4/15
 */
public class ItemCouponWriter implements ItemWriter<List<List<ItemCoupon>>> {
    private IItemCouponService iItemCouponService;

    @Override
    public void write(List<? extends List<List<ItemCoupon>>> list) throws Exception {
        for (List<List<ItemCoupon>> lists : list) {
            for (List<ItemCoupon> itemCoupons : lists) {
                iItemCouponService.saveOrUpdateByOuterId(itemCoupons);
            }
        }
    }

    public void setiItemCouponService(IItemCouponService iItemCouponService) {
        this.iItemCouponService = iItemCouponService;
    }
}
