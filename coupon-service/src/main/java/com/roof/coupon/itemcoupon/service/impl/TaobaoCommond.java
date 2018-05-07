package com.roof.coupon.itemcoupon.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.roof.coupon.core.http.HttpClientUtil;
import com.roof.coupon.itemcoupon.entity.ItemCoupon;
import com.roof.coupon.itemcoupon.service.api.IItemCouponService;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.protocol.HttpClientContext;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;


@Service
public class TaobaoCommond implements InitializingBean {


    @Value("${user_name}")
    private String user_name;
    @Value("${user_pass}")
    private String user_pass;
    @Value("${pid}")
    private String pid;

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    HttpClientContext context = HttpClientContext.create();

    @Autowired
    private IItemCouponService iItemCouponService;


    public String getCode(ItemCoupon coupon) throws IOException {
        if(StringUtils.isNotEmpty(coupon.getTaobaoCommand())){
            return coupon.getTaobaoCommand();
        }
        return getTkl_free_v3(coupon,1);
    }

    private synchronized void login(HttpClientContext context) throws IOException {
        Map<String,Object> map = new HashMap<>();
        map.put("user_name",user_name);
        map.put("user_pass",user_pass);
        String s = HttpClientUtil.post("http://tool.chaozhi.hk/api/wx/wx-login.php",map,context);

    }

    private String getTkl_free_v3(ItemCoupon coupon,Integer times) throws IOException {
        Map<String,Object> map2 = new HashMap<>();

        String url = coupon.getCouponClickUrl();
        String[] strings= url.split("activityId=");
        String couponId = strings[1];
        String item = coupon.getItemUrl();
        String[] strings1= item.split("id=");
        String itemId =strings1[1];
        map2.put("url",url+"&pid="+pid);


        map2.put("text","");
        map2.put("logo","");
        map2.put("action","refresh");
        map2.put("isHSL","false");
        map2.put("shareID","");
        map2.put("itemId",itemId);
        map2.put("couponId",couponId);
        map2.put("pid",pid);
        map2.put("tklEnc","false");
        String ss = HttpClientUtil.post("http://tool.chaozhi.hk/api/tb/GetTkl_free_v3.php",map2,context);


        JSONObject re = JSON.parseObject(ss);
        String warning = re.getString("warning");
        if(StringUtils.equalsAnyIgnoreCase("not_logge",warning)){
            if (times < 2 ){
                login(context);
                getTkl_free_v3(coupon,times++);
            }

        }
        Map<String,String> map = (Map<String, String>) re.get("data");
        String model = map.get("model");

        if(StringUtils.isNotEmpty(model)){
            executorService.submit(new Callable<ItemCoupon>() {
                @Override
                public ItemCoupon call() throws Exception {
                    ItemCoupon itemCoupon =  new ItemCoupon(coupon.getNumIid());
                    itemCoupon.setTaobaoCommand(model);
                    iItemCouponService.updateIgnoreNull(itemCoupon);
                    return itemCoupon;
                }
            });
        }

        return  model;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        login(context);
    }
}
