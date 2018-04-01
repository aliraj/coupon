package com.roof.coupon.outerapi.entity;

/**
 * 商品类目
 *
 * @author liuxin
 * @since 2018/4/1
 */
public interface ItemCatsService {

    void queryByParent(long parentCid);

}
