package com.roof.coupon.searchconfig.controller;

import java.util.List;
import java.util.Map;
import com.google.common.collect.Maps;
import javax.servlet.http.HttpServletRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.coupon.searchconfig.entity.SearchConfig;
import com.roof.coupon.searchconfig.entity.SearchConfigVo;
import com.roof.coupon.searchconfig.service.api.ISearchConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "searchconfig", description = "搜索配置管理")
@Controller
@RequestMapping("coupon")
public class SearchConfigController {
	private ISearchConfigService searchConfigService;

	@ApiOperation(value = "获得搜索配置基础信息")
	@RequestMapping(value = "searchconfig/base", method = {RequestMethod.GET})
	public @ResponseBody Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		return new Result(Result.SUCCESS, map);
	}

	@ApiOperation(value = "获得搜索配置分页列表")
    @RequestMapping(value = "searchconfig", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(SearchConfig searchConfig, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = searchConfigService.page(page, searchConfig);
	    return new Result(Result.SUCCESS, page);
	}


    @ApiOperation(value = "新增搜索配置")
	@RequestMapping(value = "searchconfig", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody SearchConfig searchConfig) {
		if (searchConfig != null) {
			searchConfigService.save(searchConfig);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID加载搜索配置")
    @RequestMapping(value = "searchconfig/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<SearchConfigVo> load(@PathVariable Long id) {
		SearchConfigVo searchConfigVo = searchConfigService.load(new SearchConfig(id));
        return new Result(Result.SUCCESS,searchConfigVo);
    }

	@ApiOperation(value = "根据ID更新搜索配置")
	@RequestMapping(value = "searchconfig/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody SearchConfig searchConfig) {
		if (id != null && searchConfig != null) {
			searchConfig.setId(id);
			searchConfigService.updateIgnoreNull(searchConfig);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID删除搜索配置")
	@RequestMapping(value = "searchconfig/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		searchConfigService.delete(new SearchConfig(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setSearchConfigService(
			@Qualifier("searchConfigService") ISearchConfigService searchConfigService) {
		this.searchConfigService = searchConfigService;
	}


}
