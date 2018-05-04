package com.roof.coupon.itemcoupon.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 * 表名： c_item_coupon <br/>
 * 描述：商品优惠券 <br/>
 */
public class ItemCouponVo extends ItemCoupon {

    private List<ItemCouponVo> itemCouponList;

    private Long customerId;

    private String couponInfoPrefix;
    private String volumePrefix;
    private String couponTotalCountPrefix;
    private String commissionRatePrefix;
    private String couponRemainCountPrefix;


    public ItemCouponVo() {
        super();
    }

    public ItemCouponVo(Long num_iid) {
        super();
        this.numIid = num_iid;
    }

    public ItemCouponVo(Long num_iid, Long customerId) {
        super();
        this.numIid = num_iid;
        this.customerId = customerId;
    }

    public ItemCouponVo(String outerId, Long customerId) {
        super();
        this.outerId = outerId;
        this.customerId = customerId;
    }

    public List<ItemCouponVo> getItemCouponList() {
        return itemCouponList;
    }

    public void setItemCouponList(List<ItemCouponVo> itemCouponList) {
        this.itemCouponList = itemCouponList;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }


    public String getCouponInfoPrefix() {
        return couponInfoPrefix;
    }

    public void setCouponInfoPrefix(String couponInfoPrefix) {
        this.couponInfoPrefix = couponInfoPrefix;
    }

    public String getVolumePrefix() {
        return volumePrefix;
    }

    public void setVolumePrefix(String volumePrefix) {
        this.volumePrefix = volumePrefix;
    }

    public String getCouponTotalCountPrefix() {
        return couponTotalCountPrefix;
    }

    public void setCouponTotalCountPrefix(String couponTotalCountPrefix) {
        this.couponTotalCountPrefix = couponTotalCountPrefix;
    }

    public String getCommissionRatePrefix() {
        return commissionRatePrefix;
    }

    public void setCommissionRatePrefix(String commissionRatePrefix) {
        this.commissionRatePrefix = commissionRatePrefix;
    }

    public String getCouponRemainCountPrefix() {
        return couponRemainCountPrefix;
    }

    public void setCouponRemainCountPrefix(String couponRemainCountPrefix) {
        this.couponRemainCountPrefix = couponRemainCountPrefix;
    }
}
