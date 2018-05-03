package com.roof.coupon.accesslog.entity;

import com.roof.coupon.itemcoupon.entity.ItemCoupon;
import com.roof.coupon.itemcoupon.entity.ItemCouponVo;

import java.util.List;

/**
 * @author 模版生成 <br/>
 * 表名： c_access_log <br/>
 * 描述：用户行为日志 <br/>
 */
public class AccessLogVo extends AccessLog {

    private List<AccessLogVo> accessLogList;

    private ItemCouponVo itemCouponVo;

    public AccessLogVo() {
        super();
    }

    public AccessLogVo(Long id) {
        super();
        this.id = id;
    }

    public List<AccessLogVo> getAccessLogList() {
        return accessLogList;
    }

    public void setAccessLogList(List<AccessLogVo> accessLogList) {
        this.accessLogList = accessLogList;
    }

    public ItemCouponVo getItemCouponVo() {
        return itemCouponVo;
    }

    public void setItemCouponVo(ItemCouponVo itemCouponVo) {
        this.itemCouponVo = itemCouponVo;
    }
}
