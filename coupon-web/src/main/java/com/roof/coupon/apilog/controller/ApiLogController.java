package com.roof.coupon.apilog.controller;

import java.util.List;
import java.util.Map;
import com.google.common.collect.Maps;
import javax.servlet.http.HttpServletRequest;

import com.roof.coupon.api.entity.ApiVo;
import com.roof.coupon.api.service.api.IApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.coupon.apilog.entity.ApiLog;
import com.roof.coupon.apilog.entity.ApiLogVo;
import com.roof.coupon.apilog.service.api.IApiLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "apilog", description = "接口日志管理")
@Controller
@RequestMapping("coupon")
public class ApiLogController {
	private IApiLogService apiLogService;
	@Autowired
	private IApiService apiService;

	@ApiOperation(value = "获得接口日志基础信息")
	@RequestMapping(value = "apilog/base", method = {RequestMethod.GET})
	public @ResponseBody Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		List<ApiVo> apiList = apiService.selectForList(new com.roof.coupon.api.entity.Api());
		map.put("apiList",apiList);
		return new Result(Result.SUCCESS, map);
	}

	@ApiOperation(value = "获得接口日志分页列表")
    @RequestMapping(value = "apilog", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(ApiLog apiLog, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = apiLogService.page(page, apiLog);
	    return new Result(Result.SUCCESS, page);
	}


    @ApiOperation(value = "新增接口日志")
	@RequestMapping(value = "apilog", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody ApiLog apiLog) {
		if (apiLog != null) {
			apiLogService.save(apiLog);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID加载接口日志")
    @RequestMapping(value = "apilog/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<ApiLogVo> load(@PathVariable Long id) {
		ApiLogVo apiLogVo = apiLogService.load(new ApiLog(id));
        return new Result(Result.SUCCESS,apiLogVo);
    }

	@ApiOperation(value = "根据ID更新接口日志")
	@RequestMapping(value = "apilog/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody ApiLog apiLog) {
		if (id != null && apiLog != null) {
			apiLog.setId(id);
			apiLogService.updateIgnoreNull(apiLog);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID删除接口日志")
	@RequestMapping(value = "apilog/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		apiLogService.delete(new ApiLog(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setApiLogService(
			@Qualifier("apiLogService") IApiLogService apiLogService) {
		this.apiLogService = apiLogService;
	}


}
