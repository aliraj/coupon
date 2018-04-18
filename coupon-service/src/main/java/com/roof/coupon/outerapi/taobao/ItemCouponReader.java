package com.roof.coupon.outerapi.taobao;

import com.roof.coupon.itemcoupon.entity.ItemCoupon;
import com.roof.coupon.outerapi.ItemCouponOuterApiService;
import org.roof.roof.dataaccess.api.Page;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.List;
import java.util.Map;

/**
 * @author liuxin
 * @since 2018/4/15
 */
public class ItemCouponReader implements ItemReader<List<ItemCoupon>> {
    private ItemCouponOuterApiService itemCouponOuterApiService;
    private Map<String, String> params;
    private long currentPage = 0;

    @Override
    public List<ItemCoupon> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        Page page = new Page();
        page.setCurrentPage(currentPage);
        page = itemCouponOuterApiService.query(params, page);
        currentPage++;
        List<ItemCoupon> result = (List<ItemCoupon>) page.getDataList();
        if (result == null || result.size() == 0) {
            return null;
        }
        return result;
    }

    public void setItemCouponOuterApiService(ItemCouponOuterApiService itemCouponOuterApiService) {
        this.itemCouponOuterApiService = itemCouponOuterApiService;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
