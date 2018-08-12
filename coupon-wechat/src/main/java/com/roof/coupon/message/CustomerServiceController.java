package com.roof.coupon.message;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.roof.coupon.accesslog.service.api.IAccessLogService;
import com.roof.coupon.itemcoupon.entity.ItemCoupon;
import com.roof.coupon.itemcoupon.entity.ItemCouponVo;
import com.roof.coupon.itemcoupon.service.api.IItemCouponService;
import com.roof.coupon.message.api.ReceiveMessageService;
import com.roof.coupon.searchconfig.entity.SearchConfig;
import com.roof.coupon.searchconfig.service.api.ISearchConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.commons.PropertiesUtil;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

/**
 * @author liht
 */
@Api(value = "customerservice", description = "客服消息")
@Controller
@RequestMapping("customer/service")
public class CustomerServiceController {
    private IItemCouponService itemCouponService;

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceController.class);

    private String appid = PropertiesUtil.getPorpertyString("fpa.mini.appid");

    @Autowired
    private ReceiveMessageService receiveMessageService;


    //    @ApiOperation(value = "获取客服消息")
//    @RequestMapping(value = "receiver/message")
    public @ResponseBody
    String receiverMessageTest(String signature, Long timestamp, String nonce, String echostr, HttpServletRequest request) throws NoSuchAlgorithmException {
        logger.info("入参signature=" + signature + "，timestamp=" + timestamp + "，nonce=" + nonce + "，echostr=" + echostr);
        String[] strings = new String[]{appid, timestamp.toString(), nonce};
        logger.info("strings= " + strings);
        List<String> list = Arrays.asList(strings);
        Ordering<Object> ordering = Ordering.usingToString();
        list = ordering.sortedCopy(list);
        logger.info("sort strings = " + list);
        String a = "";
        for (String s :
                list
                ) {
            a = a + s;
        }
        logger.info("组装字符为：" + a);
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        messageDigest.update(a.getBytes());
        //获取字节数组
        byte messageDigest1[] = messageDigest.digest();
        // Create Hex String
        StringBuffer hexString = new StringBuffer();
        // 字节数组转换为 十六进制 数
        for (int i = 0; i < messageDigest1.length; i++) {
            String shaHex = Integer.toHexString(messageDigest1[i] & 0xFF);
            if (shaHex.length() < 2) {
                hexString.append(0);
            }
            hexString.append(shaHex);
        }
        logger.info(hexString.toString().toUpperCase());
        if (hexString.toString().toUpperCase().equals(signature)) {
            logger.info("匹配");
        } else {
            logger.info("不匹配");
        }

//        Page page = PageUtils.createPage(request);
//        page = itemCouponService.page(page, itemCoupon);
        return echostr;
    }


    @ApiOperation(value = "获取客服消息")
    @RequestMapping(value = "receiver/message")
    public @ResponseBody
    String receiverMessage(@RequestBody String json, HttpServletRequest request) throws NoSuchAlgorithmException {
        logger.info("json:" + json);
        System.out.println(json);
        receiveMessageService.receiveMessage(json);
        return "SUCCESS";
    }

    @ApiOperation(value = "发送文本消息")
    @RequestMapping(value = "send/textmessage")
    public @ResponseBody
    Result sendTextMessage(String touser, String content, HttpServletRequest request) throws NoSuchAlgorithmException {
        String rs = receiveMessageService.sendTextMessage(touser, content);
        return new Result(Result.SUCCESS, rs);
    }

    @ApiOperation(value = "发送链接消息")
    @RequestMapping(value = "send/linkmessage")
    public @ResponseBody
    Result sendLinkMessage(String touser, String title, String description, String url, String thumb_url, HttpServletRequest request) throws NoSuchAlgorithmException {
        String rs = receiveMessageService.sendLinkMessage(touser, title, description, url, thumb_url);
        return new Result(Result.SUCCESS, rs);
    }

}
