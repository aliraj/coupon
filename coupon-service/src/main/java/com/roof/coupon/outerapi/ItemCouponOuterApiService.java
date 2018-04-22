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
    /**
     * 获取商品列表
     *
     * @param params 参数列表
     * @param page   分页参数
     * @throws IOException 查询时出现网络异常
     */
    Page query(Map<String, String> params, Page page) throws IOException;

    /**
     * 搜索
     *
     * @param keywords 关键字
     * @param page     分页参数
     * @throws IOException 查询时出现网络异常
     */
    Page search(String keywords, Page page) throws IOException;
}
