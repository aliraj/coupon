package com.roof.coupon.itemcoupon.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_item_coupon <br/>
 *         描述：商品优惠券 <br/>
 */
public class ItemCouponVo extends ItemCoupon {

	private List<ItemCouponVo> itemCouponList;

	public ItemCouponVo() {
		super();
	}

	public ItemCouponVo(Long num_iid) {
		super();
		this.numIid = num_iid;
	}

	public List<ItemCouponVo> getItemCouponList() {
		return itemCouponList;
	}

	public void setItemCouponList(List<ItemCouponVo> itemCouponList) {
		this.itemCouponList = itemCouponList;
	}

}
