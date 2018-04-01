package com.roof.coupon.outerapi;

/**
 * 三方授权
 *
 * @author liuxin
 * @since 2018/3/31
 */
public interface OauthService {
    /**
     * 保存token
     *
     * @param oauthToken
     */
    void save(OauthToken oauthToken);

    /**
     * 加载当前token
     */
    OauthToken load();

    /**
     * 获取token
     */
    OauthToken getToken(String code);

    /**
     * 刷新token
     */
    OauthToken refresh();

}
