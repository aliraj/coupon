package com.roof.coupon.outerapi.jingtuitui;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.roof.coupon.itemcoupon.entity.ItemCoupon;
import com.roof.coupon.outerapi.AbstractCouponOuterApiService;
import com.roof.coupon.outerapi.log.LogBean;
import org.apache.commons.lang3.math.NumberUtils;
import org.roof.commons.CustomizedPropertyPlaceholderConfigurer;
import org.roof.roof.dataaccess.api.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author liuxin
 * @since 2018/4/15
 */
@Component
public class JingtuituiItemCouponOuterApiService extends AbstractCouponOuterApiService implements InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(JingtuituiItemCouponOuterApiService.class);

    private static final String GET_GOODS_LIST
            = "http://japi.jingtuitui.com/api/get_goods_list?appid={0}&appkey={1}&num={2}&page={3}";
    private static final int DEFAULT_PAGE_SIZE = 50;
    private String jingtuituiAppId;
    private String jingtuituiAppKey;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.jingtuituiAppId = CustomizedPropertyPlaceholderConfigurer.getContextProperty("jingtuitui.appid").toString();
        this.jingtuituiAppKey = CustomizedPropertyPlaceholderConfigurer.getContextProperty("jingtuitui.appkey").toString();
    }

    @Override
    public Page query(Map<String, String> params, Page page) {
        String url = MessageFormat.format(GET_GOODS_LIST, jingtuituiAppId, jingtuituiAppKey, DEFAULT_PAGE_SIZE, page.getCurrentPage());
        String respStr = doGet(url, GET_GOODS_LIST, params, LogBean.PLATFORM_JINGTUITUI, LOGGER);
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
            itemCoupon.setCategory(NumberUtils.toLong(jingtuituiItem.getGoods_type(), 0));
            itemCoupon.setCommissionRate(jingtuituiItem.getCommission());
            itemCoupon.setPlatform(LogBean.PLATFORM_JINGTUITUI);
            result.add(itemCoupon);
        }

        return result;
    }


}
