package com.roof.coupon.outerapi.jingdong;

import com.jd.open.api.sdk.JdClient;

/**
 * @author liuxin
 * @since 2018/4/1
 */
public interface JingdongClientManager {
    JdClient getInstance();

    void reset();
}
