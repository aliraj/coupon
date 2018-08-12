package com.roof.coupon.order;

import com.google.common.collect.Ordering;
import com.roof.coupon.customer.entity.Customer;
import com.roof.coupon.customer.service.api.ICustomerService;
import com.roof.coupon.itemcoupon.service.api.IItemCouponService;
import com.roof.coupon.message.api.ReceiveMessageService;
import com.roof.coupon.order.api.WechatOrderService;
import com.roof.coupon.order.impl.PayCommonUtil;
import com.roof.coupon.order.impl.XMLUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jdom2.JDOMException;
import org.roof.commons.PropertiesUtil;
import org.roof.spring.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author liht
 */
@Api(value = "order", description = "订单服务")
@Controller
@RequestMapping("customer/order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private String appid = PropertiesUtil.getPorpertyString("fpa.mini.appid");

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private WechatOrderService wechatOrderService;


    @ApiOperation(value = "获取预订单信息")
    @RequestMapping(value = "createOrder")
    public @ResponseBody
    Result createOrder(Long customerId, Integer fee,
                       HttpServletRequest request
    ) {
//        logger.info("json:" + json);
        //创建订单
        Customer c = customerService.load(new Customer(customerId));
        //

        SortedMap<Object, Object> rs = wechatOrderService.getPrepayId(c.getWeixinOpenId(), "测试", "1232131231", "128.0.0.1", fee);
        return new Result(Result.SUCCESS, rs);
    }

    @ApiOperation(value = "获取预订单信息")
    @RequestMapping(value = "payReturnMsg")
    public @ResponseBody
    void payReturnMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("微信支付回调");
        PrintWriter writer = response.getWriter();
        InputStream inStream = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        String result = new String(outSteam.toByteArray(), "utf-8");
        System.out.println("微信支付通知结果：" + result);
        Map<String, String> map = null;
        try {
            /**
             * 解析微信通知返回的信息
             */
            map = XMLUtil.doXMLParse(result);
        } catch (JDOMException e) {
            e.printStackTrace();
        }
        System.out.println("=========:" + result);
        // 若支付成功，则告知微信服务器收到通知
        if (map.get("result_code").equals("SUCCESS")) {
            System.out.println("充值成功！");
            //更新订单？
//                PayRecord payRecord = payRecordService.get(Long.valueOf(map.get("out_trade_no")));
//                System.out.println("订单号：" + Long.valueOf(map.get("out_trade_no")));
//                System.out.println("payRecord.getPayTime():" + payRecord.getPayTime() == null + "," + payRecord.getPayTime());
            //判断通知是否已处理，若已处理，则不予处理
//                if (payRecord.getPayTime() == null) {
            System.out.println("通知微信后台");
//                    payRecord.setPayTime(new Date());
//                    String phone = payRecord.getPhone();
//                    AppCustomer appCustomer = appCustomerService.getByPhone(phone);
//                    float balance = appCustomer.getBalance();
//                    balance += Float.valueOf(map.get("total_fee")) / 100;
//                    appCustomer.setBalance(balance);
//                    appCustomerService.update(appCustomer);
//                    payRecordService.update(payRecord);
            String notifyStr = XMLUtil.setXML("SUCCESS", "");
            writer.write(notifyStr);
            writer.flush();
//                }
        }

//        SortedMap<Object, Object> rs = new TreeMap<>();
//        System.out.println(return_code);
//        System.out.println(return_msg);
//        Map map = null;
//        try
//
//        {
//            map = XMLUtil.doXMLParse(return_msg);
//
//
//        } catch (
//                JDOMException e)
//
//        {
//            e.printStackTrace();
//        } catch (
//                IOException e)
//
//        {
//            e.printStackTrace();
//        }
//        System.out.println(map);
//
//        rs.put("return_code", "SUCCESS");
//        rs.put("return_msg", "OK");

//        return PayCommonUtil.getRequestXml(rs);
    }
}
