package com.roof.coupon.outerapi;

import org.roof.roof.dataaccess.api.Page;

import java.util.Map;

/**
 * @author liuxin
 * @since 2018/4/14
 */
public interface ItemCouponOuterApiService {
    Page query(Map<String, String> params, Page page);
}
