package com.roof.coupon.outerapi.jingdong;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.JdException;
import com.jd.open.api.sdk.request.cps.UnionSearchGoodsCategoryQueryRequest;
import com.jd.open.api.sdk.response.cps.UnionSearchGoodsCategoryQueryResponse;
import com.roof.coupon.outerapi.OauthService;
import com.roof.coupon.outerapi.entity.ItemCatsService;
import com.roof.coupon.outerapi.entity.OauthToken;
import org.roof.commons.CustomizedPropertyPlaceholderConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

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
    public void queryByParent(long parentCid) {
        UnionSearchGoodsCategoryQueryRequest request = new UnionSearchGoodsCategoryQueryRequest();
        request.setParentId((int) parentCid);
        try {
            UnionSearchGoodsCategoryQueryResponse response = client.execute(request);
            String itemCatsStr = response.getQuerygoodscategoryResult();
            JSONObject jsonObject = JSON.parseObject(itemCatsStr);

        } catch (JdException e) {
        }
    }

    @Autowired
    public void setOauthService(@Qualifier("jingdongOauthService") OauthService oauthService) {
        this.oauthService = oauthService;
    }
}
