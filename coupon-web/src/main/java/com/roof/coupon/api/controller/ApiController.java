package com.roof.coupon.api.controller;

import java.util.List;
import java.util.Map;
import com.google.common.collect.Maps;
import javax.servlet.http.HttpServletRequest;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.coupon.api.entity.Api;
import com.roof.coupon.api.entity.ApiVo;
import com.roof.coupon.api.service.api.IApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@Api(value = "api", description = "外部接口列表管理")
@Controller
@RequestMapping("coupon")
public class ApiController {
	private IApiService apiService;

	@ApiOperation(value = "获得外部接口列表基础信息")
	@RequestMapping(value = "api/base", method = {RequestMethod.GET})
	public @ResponseBody Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		return new Result(Result.SUCCESS, map);
	}

	@ApiOperation(value = "获得外部接口列表分页列表")
    @RequestMapping(value = "api", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(Api api, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = apiService.page(page, api);
	    return new Result(Result.SUCCESS, page);
	}


    @ApiOperation(value = "新增外部接口列表")
	@RequestMapping(value = "api", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody Api api) {
		if (api != null) {
			apiService.save(api);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID加载外部接口列表")
    @RequestMapping(value = "api/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<ApiVo> load(@PathVariable Long id) {
		ApiVo apiVo = apiService.load(new Api(id));
        return new Result(Result.SUCCESS,apiVo);
    }

	@ApiOperation(value = "根据ID更新外部接口列表")
	@RequestMapping(value = "api/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody Api api) {
		if (id != null && api != null) {
			api.setId(id);
			apiService.updateIgnoreNull(api);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID删除外部接口列表")
	@RequestMapping(value = "api/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		apiService.delete(new Api(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setApiService(
			@Qualifier("apiService") IApiService apiService) {
		this.apiService = apiService;
	}


}
