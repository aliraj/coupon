package com.roof.coupon.outerapi.jingdong;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.JdException;
import com.jd.open.api.sdk.request.cps.UnionSearchGoodsCategoryQueryRequest;
import com.jd.open.api.sdk.response.cps.UnionSearchGoodsCategoryQueryResponse;
import com.roof.coupon.itemcats.dao.impl.ItemCatsDao;
import com.roof.coupon.itemcats.entity.ItemCats;
import com.roof.coupon.outerapi.ItemCatsService;
import com.roof.coupon.outerapi.OauthService;
import com.roof.coupon.outerapi.entity.OauthToken;
import com.roof.coupon.outerapi.log.LogBean;
import org.roof.commons.CustomizedPropertyPlaceholderConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuxin
 * @since 2018/4/1
 */
@Component
public class JingdongItemCatsService implements ItemCatsService, InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(JingdongItemCatsService.class);

    private JdClient client;
    private OauthService oauthService;
    private String appKey;
    private String appSecret;
    private ItemCatsDao itemCatsDao;


    @Override
    public void afterPropertiesSet() throws Exception {
        appKey = CustomizedPropertyPlaceholderConfigurer.getContextProperty("oauth_jingdong_appKey").toString();
        appSecret = CustomizedPropertyPlaceholderConfigurer.getContextProperty("oauth_jingdong_appSecret").toString();
        createClient();
    }

    private void createClient() {
        OauthToken oauthToken = oauthService.load();
        client = new DefaultJdClient("https://api.jd.com/routerjson", oauthToken.getAccessToken(), appKey, appSecret);
    }


    @Override
    public List<ItemCats> queryByParent(long parentCid) {
        return queryByParent(parentCid, 0);
    }


    private List<ItemCats> queryByParent(long parentCid, int grade) {
        UnionSearchGoodsCategoryQueryRequest request = new UnionSearchGoodsCategoryQueryRequest();
        request.setParentId((int) parentCid);
        request.setGrade(grade);
        List<ItemCats> result = new ArrayList<>();
        try {
            UnionSearchGoodsCategoryQueryResponse response = client.execute(request);
            LOGGER.info(JSON.toJSONString(new LogBean(LogBean.PLATFORM_JINGDONG, "jingdong.union.search.goods.category.query", request, response)));
            String itemCatsStr = response.getQuerygoodscategoryResult();
            JSONObject jsonObject = JSON.parseObject(itemCatsStr);
            if (jsonObject.getInteger("resultCode") == 1) {
                JSONArray data = jsonObject.getJSONArray("data");
                for (Object datum : data) {
                    JSONObject itemJSONObject = (JSONObject) datum;
                    ItemCats itemCats = new ItemCats();
                    int id = itemJSONObject.getInteger("id");
                    itemCats.setCid((long) id);
                    itemCats.setName(itemJSONObject.getString("name"));
                    int parentId = itemJSONObject.getInteger("parentId");
                    itemCats.setParentCid((long) parentId);
                    result.add(itemCats);
                    List<ItemCats> subs = queryByParent(id, grade + 1);
                    if (subs == null || subs.size() == 0) {
                        itemCats.setIsParent("0");
                    } else {
                        itemCats.setIsParent("1");
                    }
                    itemCatsDao.save(itemCats);
                    System.out.println(JSON.toJSONString(itemCats));
                }
            }
            return result;
        } catch (JdException e) {
            LOGGER.error(JSON.toJSONString(new LogBean(LogBean.PLATFORM_JINGDONG, "jingdong.union.search.goods.category.query", request, e.getErrMsg())));
        }
        return result;
    }

    @Autowired
    public void setOauthService(@Qualifier("jingdongOauthService") OauthService oauthService) {
        this.oauthService = oauthService;
    }

    @Autowired
    public void setItemCatsDao(ItemCatsDao itemCatsDao) {
        this.itemCatsDao = itemCatsDao;
    }
}
