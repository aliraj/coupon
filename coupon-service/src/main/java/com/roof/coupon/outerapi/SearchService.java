package com.roof.coupon.outerapi;

import org.roof.roof.dataaccess.api.Page;

/**
 * @author liuxin
 * @since 2018/4/18
 */
public interface SearchService {
    /**
     * 全平台查询
     *
     * @param keywords  关键字
     * @param platforms 平台
     * @return 商品列表
     */
    Page doSearch(String keywords, Page page, String[] platforms);
}
