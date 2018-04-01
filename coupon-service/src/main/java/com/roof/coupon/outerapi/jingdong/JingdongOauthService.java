package com.roof.coupon.outerapi.jingdong;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.roof.coupon.outerapi.OauthService;
import com.roof.coupon.outerapi.OauthToken;
import org.apache.commons.lang3.math.NumberUtils;
import org.roof.commons.CustomizedPropertyPlaceholderConfigurer;
import org.roof.httpclinet.HttpClientUtils;
import org.roof.web.dictionary.entity.Dictionary;
import org.roof.web.dictionary.service.api.IDictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.MessageFormat;

/**
 * @author liuxin
 * @since 2018/3/31
 */
@Component
public class JingdongOauthService implements OauthService, InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(JingdongOauthService.class);

    private static final String OAUTH_TOKEN = "oauthToken";
    private static final String JING_DONG_OAUTH_TOKEN = "jingDongOauthToken";
    private static final String OAUTH_TOKEN_CACHE = "oauthTokenCache";
    private IDictionaryService dictionaryService;
    private static final String REFRESH_TOKEN_URL = "https://oauth.jd.com/oauth/token?client_id={0}&client_secret={1}" +
            "&grant_type=refresh_token&refresh_token={2}";
    private static final String GET_TOKEN_URL = "https://oauth.jd.com/oauth/token?grant_type=authorization_code&client_id={0}&" +
            "redirect_uri={1}&code={2}&state=&client_secret={3}";
    private HttpClientUtils httpClientUtils;

    private CacheManager cacheManager;
    private Cache cache;

    @Override
    public void afterPropertiesSet() throws Exception {
        cache = cacheManager.getCache(OAUTH_TOKEN_CACHE);
    }

    @Override
    public void save(OauthToken oauthToken) {
        cache.put(JING_DONG_OAUTH_TOKEN, oauthToken);
        Dictionary dictionary = dictionaryService.load(OAUTH_TOKEN, JING_DONG_OAUTH_TOKEN);
        if (dictionary == null) {
            dictionary = new Dictionary();
            dictionary.setType(OAUTH_TOKEN);
            dictionary.setVal(JING_DONG_OAUTH_TOKEN);
            dictionary.setActive("1");
        }
        dictionary.setText(JSON.toJSONString(oauthToken));
        if (dictionary.getId() == null) {
            dictionaryService.save(dictionary);
        } else {
            dictionaryService.update(dictionary);
        }
    }

    @Override
    public OauthToken load() {
        OauthToken oauthToken = cache.get(JING_DONG_OAUTH_TOKEN, OauthToken.class);
        if (oauthToken != null) {
            return oauthToken;
        }
        synchronized (this) {
            if (oauthToken == null) {
                Dictionary dictionary = dictionaryService.load(OAUTH_TOKEN, JING_DONG_OAUTH_TOKEN);
                if (dictionary == null) {
                    return null;
                }
                oauthToken = JSON.parseObject(dictionary.getText(), OauthToken.class);
                cache.put(JING_DONG_OAUTH_TOKEN, oauthToken);
            }
        }
        return oauthToken;
    }

    @Override
    public OauthToken getToken(String code) {
        String appKey = CustomizedPropertyPlaceholderConfigurer.getContextProperty("oauth_jingdong_appKey").toString();
        String appSecret = CustomizedPropertyPlaceholderConfigurer.getContextProperty("oauth_jingdong_appSecret").toString();
        String connectUrl = CustomizedPropertyPlaceholderConfigurer.getContextProperty("oauth_jingdong_connectUrl").toString();
        String url = MessageFormat.format(GET_TOKEN_URL, appKey, connectUrl, code, appSecret);
        try {
            ResponseEntity<String> stringResponseEntity = httpClientUtils.doGet(url);
            if (stringResponseEntity.getStatusCode().is2xxSuccessful()) {
                JSONObject jsonObject = JSON.parseObject(stringResponseEntity.getBody());
                if (jsonObject.getInteger("code") != 0) {
                    return null;
                }
                OauthToken oauthToken = new OauthToken();
                oauthToken.setAccessToken(jsonObject.getString("access_token"));
                oauthToken.setRefreshToken(jsonObject.getString("refresh_token"));
                oauthToken.setTime(NumberUtils.toLong(jsonObject.getString("time")));
                oauthToken.setUid(jsonObject.getString("uid"));
                oauthToken.setExpiresIn(jsonObject.getLong("expires_in"));
                save(oauthToken);
            } else {
                LOGGER.error("get token error, code:{}, http code:{}, message:{}", code,
                        stringResponseEntity.getStatusCode(), stringResponseEntity.toString());
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public OauthToken refresh() {
        String appKey = CustomizedPropertyPlaceholderConfigurer.getContextProperty("oauth_jingdong_appKey").toString();
        String appSecret = CustomizedPropertyPlaceholderConfigurer.getContextProperty("oauth_jingdong_appSecret").toString();
        OauthToken oauthToken = load();
        String url = MessageFormat.format(REFRESH_TOKEN_URL, appKey, appSecret, oauthToken.getRefreshToken());
        try {
            ResponseEntity<String> stringResponseEntity = httpClientUtils.doGet(url);
            if (stringResponseEntity.getStatusCode().is2xxSuccessful()) {
                oauthToken = JSON.parseObject(stringResponseEntity.getBody(), OauthToken.class);
                save(oauthToken);
                return oauthToken;
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Autowired
    public void setDictionaryService(@Qualifier("dictionaryService") IDictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }


    @Autowired
    public void setHttpClientUtils(HttpClientUtils httpClientUtils) {
        this.httpClientUtils = httpClientUtils;
    }

    @Autowired
    public void setCacheManager(@Qualifier("localCacheManager") CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
}
