package com.roof.coupon.outerapi.jingtuitui;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.roof.coupon.apilog.entity.ApiLog;
import com.roof.coupon.itemcoupon.entity.ItemCoupon;
import com.roof.coupon.outerapi.AbstractCouponOuterApiService;
import org.apache.commons.lang3.math.NumberUtils;
import org.roof.commons.CustomizedPropertyPlaceholderConfigurer;
import org.roof.roof.dataaccess.api.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuxin
 * @since 2018/4/15
 */
@Component
public class JingtuituiItemCouponOuterApiService extends AbstractCouponOuterApiService implements InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(JingtuituiItemCouponOuterApiService.class);

    private static final String GET_GOODS_LIST = "http://japi.jingtuitui.com/api/get_goods_list";
    private static final int DEFAULT_PAGE_SIZE = 100;
    private String jingtuituiAppId;
    private String jingtuituiAppKey;
    private Map<String, String> categoryMap;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.jingtuituiAppId = CustomizedPropertyPlaceholderConfigurer.getContextProperty("jingtuitui.appid").toString();
        this.jingtuituiAppKey = CustomizedPropertyPlaceholderConfigurer.getContextProperty("jingtuitui.appkey").toString();
        categoryMap = new HashMap<>();
        /**
         *       1 : 女装
         *       2 : 男装
         *       3 : 内衣配饰
         *       4 : 母婴玩具
         *       5 : 美妆个护
         *       6 : 食品保健
         *       7 : 居家生活
         *       8 : 鞋品箱包
         *       9 : 运动户外
         *       10 : 文体车品
         *       11 : 数码家电
         */
        categoryMap.put("1", "1");
        categoryMap.put("2", "2");
        categoryMap.put("3", "3");
        categoryMap.put("4", "4");
        categoryMap.put("5", "5");
        categoryMap.put("6", "8");
        categoryMap.put("7", "6");
        categoryMap.put("8", "7");
        categoryMap.put("9", "11");
        categoryMap.put("10", "9");
        categoryMap.put("11", "10");
    }

    @Override
    public Page query(Map<String, String> params, Page page) throws IOException {
        if (params == null) {
            params = new HashMap<>();
        }
        params.put("appid", jingtuituiAppId);
        params.put("appkey", jingtuituiAppKey);
        params.put("num", String.valueOf(DEFAULT_PAGE_SIZE));
        params.put("page", String.valueOf(page.getCurrentPage()));

        String respStr = doGet(GET_GOODS_LIST, params);
        if (respStr == null) {
            return page;
        }
        JSONObject jsonObject = JSON.parseObject(respStr);
        if (!"0".equals(jsonObject.getString("return"))) {
            return page;
        }
        JSONArray data = jsonObject.getJSONObject("result").getJSONArray("data");
        List<JingtuituiItem> taokeItems = data.toJavaList(JingtuituiItem.class);
        List<ItemCoupon> itemCoupons = toItemCoupon(taokeItems);
        page.setDataList(itemCoupons);
        return page;
    }

    @Override
    public Page search(String keywords, Page page) throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("so", keywords);
        return query(params, page);
    }

    private List<ItemCoupon> toItemCoupon(List<JingtuituiItem> jingtuituiItems) {
        List<ItemCoupon> result = new ArrayList<>(jingtuituiItems.size());
        for (JingtuituiItem jingtuituiItem : jingtuituiItems) {
            ItemCoupon itemCoupon = new ItemCoupon();
            itemCoupon.setOuterId(jingtuituiItem.getGoods_id());
            itemCoupon.setTitle(jingtuituiItem.getGoods_name());
            itemCoupon.setItemUrl(jingtuituiItem.getGoods_link());
            itemCoupon.setPictUrl(jingtuituiItem.getGoods_img());
            itemCoupon.setZkFinalPrice(jingtuituiItem.getGoods_price());
            itemCoupon.setCouponStartTime(jingtuituiItem.getDiscount_start());
            itemCoupon.setCouponEndTime(jingtuituiItem.getDiscount_end());
            itemCoupon.setCouponInfo(jingtuituiItem.getDiscount_price());
            itemCoupon.setCouponClickUrl(jingtuituiItem.getDiscount_link());
            itemCoupon.setItemDescription(jingtuituiItem.getGoods_content());
            itemCoupon.setCategory(NumberUtils.toLong(categoryMap.get(jingtuituiItem.getGoods_type()), 0));
            itemCoupon.setCommissionRate(jingtuituiItem.getCommission());
            itemCoupon.setPlatform(ApiLog.PLATFORM_JINGTUITUI);
            result.add(itemCoupon);
        }

        return result;
    }


}
