package com.roof.coupon.outerapi.taoke;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.roof.coupon.api.entity.Api;
import com.roof.coupon.apilog.entity.ApiLog;
import com.roof.coupon.itemcoupon.entity.ItemCoupon;
import com.roof.coupon.outerapi.AbstractCouponOuterApiService;
import org.apache.commons.lang3.math.NumberUtils;
import org.roof.commons.CustomizedPropertyPlaceholderConfigurer;
import org.roof.roof.dataaccess.api.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * api地址参看<a>http://www.tkjidi.cn/cms/detail</a>
 *
 * @author liuxin
 * @since 2018/4/14
 */
@Component
public class TaokeItemCouponOuterApiService extends AbstractCouponOuterApiService implements InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaokeItemCouponOuterApiService.class);

    private static final String GET_GOODS_LINK = "http://api.tkjidi.com/getGoodsLink";
    private static final String GET_GOODS_LINK_TYPE_LINGQUAN = "www_lingquan";

    private static final String SEARCH_LINK = "http://api.tkjidi.com/checkWhole?appkey={0}&k={1}&page={2}";

    private String taokeAppKey;

    @Override
    public void afterPropertiesSet() throws Exception {
        taokeAppKey = CustomizedPropertyPlaceholderConfigurer.getContextProperty("taoke.appkey").toString();
    }

    /**
     * 查询淘客基地优惠券
     *
     * @param params
     * @param page
     * @return
     */
    @Override
    public Page query(Map<String, String> params, Page page) throws IOException {
        if (params == null) {
            params = new HashMap<>();
        }
        params.put("appkey", taokeAppKey);
        params.put("type", GET_GOODS_LINK_TYPE_LINGQUAN);
        params.put("page", String.valueOf(page.getCurrentPage()));
        String respStr = doGet(GET_GOODS_LINK, params);
        return createItemCouponsPage(respStr, page);
    }

    @Override
    public Page search(String keywords, Page page) throws IOException {
        String url = MessageFormat.format(SEARCH_LINK, taokeAppKey, keywords, page.getCurrentPage());
        ResponseEntity<String> stringResponseEntity = httpClientUtils.doGet(url);
        if (!stringResponseEntity.getStatusCode().is2xxSuccessful()) {
            return page;
        }
        String respStr = stringResponseEntity.getBody();
        return createItemCouponsPage2(respStr, page);
    }

    private Page createItemCouponsPage2(String respStr, Page page) {
        if (respStr == null) {
            return page;
        }
        JSONObject jsonObject = JSON.parseObject(respStr);
        if (!"200".equals(jsonObject.getString("status"))) {
            return page;
        }
        JSONArray data = jsonObject.getJSONObject("data").getJSONArray("data");
        List<TaokeItem> taokeItems = data.toJavaList(TaokeItem.class);
        List<ItemCoupon> itemCoupons = toItemCoupon(taokeItems);
        page.setDataList(itemCoupons);
        return page;
    }

    private Page createItemCouponsPage(String respStr, Page page) {
        if (respStr == null) {
            return page;
        }
        JSONObject jsonObject = JSON.parseObject(respStr);
        if (!"200".equals(jsonObject.getString("status"))) {
            return page;
        }
        JSONArray data = jsonObject.getJSONArray("data");
        List<TaokeItem> taokeItems = data.toJavaList(TaokeItem.class);
        List<ItemCoupon> itemCoupons = toItemCoupon(taokeItems);
        page.setDataList(itemCoupons);
        return page;
    }

    private List<ItemCoupon> toItemCoupon(List<TaokeItem> taokeItems) {
        List<ItemCoupon> result = new ArrayList<>(taokeItems.size());
        for (TaokeItem taokeItem : taokeItems) {
            ItemCoupon itemCoupon = new ItemCoupon();
            itemCoupon.setOuterId(taokeItem.getId());
            itemCoupon.setTitle(taokeItem.getGoods_name());
//            itemCoupon.setSmallImages(taokeItem.getPic());
            itemCoupon.setZkFinalPrice(taokeItem.getPrice_after_coupons());
            itemCoupon.setVolume(NumberUtils.toInt(taokeItem.getSales(), 0));
            itemCoupon.setPictUrl(taokeItem.getPic());
            itemCoupon.setItemUrl(taokeItem.getGoods_url());
            itemCoupon.setCouponTotalCount(NumberUtils.toInt(taokeItem.getQuan_zhong()));
            itemCoupon.setCommissionRate(taokeItem.getRate());
            itemCoupon.setCouponInfo(taokeItem.getPrice_coupons());
            itemCoupon.setCategory(NumberUtils.toLong(taokeItem.getCate_id(), 0));
            itemCoupon.setCouponRemainCount(NumberUtils.toInt(taokeItem.getQuan_shengyu()));
            itemCoupon.setCouponEndTime(taokeItem.getQuan_expired_time());
            itemCoupon.setCouponClickUrl(taokeItem.getQuan_link());
            itemCoupon.setItemDescription(taokeItem.getQuan_guid_content());
            itemCoupon.setPlatform(ApiLog.PLATFORM_JINGTUITUI);
            result.add(itemCoupon);
        }
        return result;
    }


}
