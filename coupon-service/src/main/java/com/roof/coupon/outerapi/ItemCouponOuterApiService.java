package com.roof.coupon.outerapi;

import com.roof.coupon.itemcoupon.entity.ItemCoupon;
import org.roof.roof.dataaccess.api.Page;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author liuxin
 * @since 2018/4/14
 */
public interface ItemCouponOuterApiService {
    Page query(Map<String, String> params, Page page) throws IOException;

    Page search(String keywords, Page page) throws IOException;
}
