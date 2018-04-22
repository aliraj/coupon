package com.roof.coupon.outerapi.support;

import com.roof.coupon.outerapi.ItemCouponOuterApiService;
import com.roof.coupon.outerapi.SearchService;
import org.roof.roof.dataaccess.api.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * @author liuxin
 * @since 2018/4/21
 */
public class DefaultSearchService implements SearchService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultSearchService.class);

    private Map<String, ItemCouponOuterApiService> itemCouponOuterApiServiceMap;

    @Override
    public Page doSearch(String keywords, Page page, String[] platforms) {
        for (String platform : platforms) {
            Page p = new Page();
            page.setLimit(page.getLimit());
            page.setCurrentPage(page.getCurrentPage());
            try {
                p = itemCouponOuterApiServiceMap.get(platform).search(keywords, p);
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
            Collection c = page.getDataList();
            if (c == null) {
                c = new ArrayList();
                page.setDataList(c);
            }
            c.addAll(p.getDataList());
        }
        return page;
    }

    public void setItemCouponOuterApiServiceMap(Map<String, ItemCouponOuterApiService> itemCouponOuterApiServiceMap) {
        this.itemCouponOuterApiServiceMap = itemCouponOuterApiServiceMap;
    }
}
