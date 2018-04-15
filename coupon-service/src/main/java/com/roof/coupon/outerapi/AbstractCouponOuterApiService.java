package com.roof.coupon.outerapi;

import com.alibaba.fastjson.JSON;
import com.roof.coupon.outerapi.log.LogBean;
import org.roof.httpclinet.HttpClientUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Map;

/**
 * @author liuxin
 * @since 2018/4/15
 */
public abstract class AbstractCouponOuterApiService implements ItemCouponOuterApiService {
    private HttpClientUtils httpClientUtils;

    protected StringBuilder addParams(Map<String, String> params, String url) {
        StringBuilder builder = new StringBuilder(url);
        if (params != null) {
            for (Map.Entry<String, String> paramEntry : params.entrySet()) {
                builder.append("&");
                builder.append(paramEntry.getKey());
                builder.append("=");
                builder.append(paramEntry.getValue());
            }
        }
        return builder;
    }

    protected String doGet(String url, String api, Map<String, String> params,String pat, Logger logger) {
        StringBuilder builder = addParams(params, url);
        ResponseEntity<String> stringResponseEntity = null;
        try {
            stringResponseEntity = httpClientUtils.doGet(builder.toString());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
        if (stringResponseEntity == null) {
            return null;
        }
        logger.info(JSON.toJSONString(new LogBean(LogBean.PLATFORM_TAOKE, api, params, stringResponseEntity.toString())));
        if (!stringResponseEntity.getStatusCode().is2xxSuccessful()) {
            return null;
        }
        return stringResponseEntity.getBody();
    }

    @Autowired
    public void setHttpClientUtils(HttpClientUtils httpClientUtils) {
        this.httpClientUtils = httpClientUtils;
    }
}
