package com.roof.coupon.accesslog.controller;

import java.util.List;
import java.util.Map;
import com.google.common.collect.Maps;
import javax.servlet.http.HttpServletRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.coupon.accesslog.entity.AccessLog;
import com.roof.coupon.accesslog.entity.AccessLogVo;
import com.roof.coupon.accesslog.service.api.IAccessLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "accesslog", description = "用户行为日志管理")
@Controller
@RequestMapping("coupon")
public class AccessLogController {
	private IAccessLogService accessLogService;

	@ApiOperation(value = "获得用户行为日志基础信息")
	@RequestMapping(value = "accesslog/base", method = {RequestMethod.GET})
	public @ResponseBody Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		return new Result(Result.SUCCESS, map);
	}

	@ApiOperation(value = "获得用户行为日志分页列表")
    @RequestMapping(value = "accesslog", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(AccessLog accessLog, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = accessLogService.page(page, accessLog);
	    return new Result(Result.SUCCESS, page);
	}


    @ApiOperation(value = "新增用户行为日志")
	@RequestMapping(value = "accesslog", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody AccessLog accessLog) {
		if (accessLog != null) {
			accessLogService.save(accessLog);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID加载用户行为日志")
    @RequestMapping(value = "accesslog/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<AccessLogVo> load(@PathVariable Long id) {
		AccessLogVo accessLogVo = accessLogService.load(new AccessLog(id));
        return new Result(Result.SUCCESS,accessLogVo);
    }

	@ApiOperation(value = "根据ID更新用户行为日志")
	@RequestMapping(value = "accesslog/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody AccessLog accessLog) {
		if (id != null && accessLog != null) {
			accessLog.setId(id);
			accessLogService.updateIgnoreNull(accessLog);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID删除用户行为日志")
	@RequestMapping(value = "accesslog/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		accessLogService.delete(new AccessLog(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setAccessLogService(
			@Qualifier("accessLogService") IAccessLogService accessLogService) {
		this.accessLogService = accessLogService;
	}


}
