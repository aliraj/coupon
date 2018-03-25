package com.roof.coupon.wechat.service.api;

import com.roof.coupon.wechat.service.impl.WeChatDto;

import java.io.IOException;

/**
 * Created by zhenglt on 2017/12/2.
 */
public interface IWeChatHander {
    public String getOpenid(String code) throws IOException;

    public void getacode() throws Exception;

    public String getAccess_token() throws Exception;

    public WeChatDto getWeChatDto(String code) throws IOException;


}
