package com.roof.coupon.outerapi;

import com.alibaba.fastjson.JSON;
import com.roof.coupon.apilog.entity.ApiLog;
import com.roof.coupon.apilog.service.impl.ApiLogService;
import org.roof.httpclinet.HttpClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @author liuxin
 * @since 2018/4/15
 */
public abstract class AbstractCouponOuterApiService implements ItemCouponOuterApiService {
    protected HttpClientUtils httpClientUtils;
    protected ApiLogService apiLogService;
    private static final Logger API_LOG = LoggerFactory.getLogger("api_log");


    protected StringBuilder addParams(Map<String, String> params, String url) {
        StringBuilder builder = new StringBuilder(url);
        if (params != null) {
            for (Map.Entry<String, String> paramEntry : params.entrySet()) {
                if (builder.indexOf("?") != -1) {
                    builder.append("&");
                } else {
                    builder.append("?");
                }
                builder.append(paramEntry.getKey());
                builder.append("=");
                builder.append(paramEntry.getValue());
            }
        }
        return builder;
    }

    protected String doGet(String api, Map<String, String> params) throws IOException {
        StringBuilder builder = addParams(params, api);
        ResponseEntity<String> stringResponseEntity = null;
        try {
            stringResponseEntity = httpClientUtils.doGet(builder.toString());
            if (stringResponseEntity == null) {
                return null;
            }
            if (!stringResponseEntity.getStatusCode().is2xxSuccessful()) {
                return null;
            }
        } finally {
            ApiLog apiLog = new ApiLog();
            apiLog.setApiId(api);
            apiLog.setAccessTime(new Date());
            apiLog.setParam(JSON.toJSONString(params));
            String resultId = UUID.randomUUID().toString();
            apiLog.setResult(resultId);
            apiLogService.save(apiLog);
            if (stringResponseEntity == null) {
                apiLog.setResult("http error");
            } else {
                apiLog.setResult(stringResponseEntity.toString());
            }
            API_LOG.info(resultId + " " + JSON.toJSONString(apiLog));
        }
        return stringResponseEntity.getBody();
    }

    @Autowired
    public void setHttpClientUtils(HttpClientUtils httpClientUtils) {
        this.httpClientUtils = httpClientUtils;
    }

    @Autowired
    public void setApiLogService(ApiLogService apiLogService) {
        this.apiLogService = apiLogService;
    }
}
