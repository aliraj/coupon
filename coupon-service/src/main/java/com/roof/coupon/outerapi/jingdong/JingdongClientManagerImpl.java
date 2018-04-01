package com.roof.coupon.outerapi.jingdong;

import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.roof.coupon.outerapi.OauthService;
import com.roof.coupon.outerapi.entity.OauthToken;
import org.roof.commons.CustomizedPropertyPlaceholderConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author liuxin
 * @since 2018/4/1
 */
@Component
public class JingdongClientManagerImpl implements JingdongClientManager {
    private JdClient jdClient;
    private OauthService oauthService;

    @Override
    public JdClient getInstance() {
        if (jdClient == null) {
            synchronized (JingdongClientManagerImpl.class) {
                if (jdClient == null) {
                    String appKey = CustomizedPropertyPlaceholderConfigurer.getContextProperty("oauth_jingdong_appKey").toString();
                    String appSecret = CustomizedPropertyPlaceholderConfigurer.getContextProperty("oauth_jingdong_appSecret").toString();
                    OauthToken oauthToken = oauthService.load();
                    jdClient = new DefaultJdClient("https://api.jd.com/routerjson", oauthToken.getAccessToken(), appKey, appSecret);
                }
            }
        }
        return jdClient;
    }

    @Override
    public void reset() {
        jdClient = null;
    }

    @Autowired
    public void setOauthService(@Qualifier("jingdongOauthService") OauthService oauthService) {
        this.oauthService = oauthService;
    }
}
