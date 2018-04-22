package com.roof.coupon.itemcoupon.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.coupon.itemcoupon.entity.ItemCoupon;

public interface IItemCouponDao extends IDaoSupport {
	Page page(Page page, ItemCoupon itemCoupon);

    int updateByOuterId(ItemCoupon itemCoupon);
}