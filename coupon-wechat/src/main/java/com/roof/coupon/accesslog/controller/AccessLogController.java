package com.roof.coupon.accesslog.controller;

import com.google.common.collect.Maps;
import com.roof.coupon.accesslog.entity.AccessLog;
import com.roof.coupon.accesslog.entity.AccessLogVo;
import com.roof.coupon.accesslog.service.api.IAccessLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Api(value = "accesslog", description = "用户行为日志管理")
@Controller
@RequestMapping("/coupon/wechat")
public class AccessLogController {
    private IAccessLogService accessLogService;


    @ApiOperation(value = "获得某个用户行为日志分页列表")
    @RequestMapping(value = "accesslog", method = {RequestMethod.GET})
    public @ResponseBody
    Result<Page> list(AccessLog accessLog, HttpServletRequest request) {
        Page page = PageUtils.createPage(request);
        page = accessLogService.pageWechat(page, accessLog);
        return new Result(Result.SUCCESS, page);
    }

    @Autowired(required = true)
    public void setAccessLogService(
            @Qualifier("accessLogService") IAccessLogService accessLogService
    ) {
        this.accessLogService = accessLogService;
    }


}
