package com.roof.coupon.cos.api;

import java.io.File;
import java.util.Map;

public interface ICosService {

    Map<String, Object> getSign(Map<String, String> params);

    Map<String,Object> getConfiguration();

    String uploadHeadImg(String key, File file);

}
