package com.roof.coupon.aspect;

import com.alibaba.fastjson.JSON;
import com.roof.coupon.accesslog.entity.AccessLog;
import com.roof.coupon.accesslog.service.api.IAccessLogService;
import com.roof.coupon.itemcoupon.entity.ItemCouponVo;
import com.roof.coupon.itemcoupon.service.impl.AccessLogOperEnum;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * com.roof.coupon.aspect
 *
 * @author liht
 * @date 2018/4/21
 */
@Component
@Aspect
public class AccessLogInterceptor {

    @Autowired
    private IAccessLogService accessLogService;

    @Pointcut("execution(* com.roof.coupon.itemcoupon.service.impl.ItemCouponService.wechat*(..)))")
    private void readCouponDetail() {
    }

    @AfterReturning(pointcut = "readCouponDetail()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        Object[] arguments = joinPoint.getArgs();
        AccessLog accessLog = new AccessLog();
        accessLog.setOper(AccessLogOperEnum.read_coupon_detail.getValue());
        accessLog.setOperTime(new Date());
        accessLog.setParams(JSON.toJSONString(arguments));
        if (arguments != null && arguments[0] != null && arguments[0] instanceof ItemCouponVo) {
            ItemCouponVo vo = (ItemCouponVo) arguments[0];
            accessLog.setCustomerId(vo.getCustomerId());
        }
        accessLogService.save(accessLog);
    }
}

