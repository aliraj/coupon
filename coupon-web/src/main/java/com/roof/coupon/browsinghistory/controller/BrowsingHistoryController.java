package com.roof.coupon.browsinghistory.controller;

import java.util.List;
import java.util.Map;
import com.google.common.collect.Maps;
import javax.servlet.http.HttpServletRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.coupon.browsinghistory.entity.BrowsingHistory;
import com.roof.coupon.browsinghistory.entity.BrowsingHistoryVo;
import com.roof.coupon.browsinghistory.service.api.IBrowsingHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "browsinghistory", description = "浏览记录管理")
@Controller
@RequestMapping("coupon")
public class BrowsingHistoryController {
	private IBrowsingHistoryService browsingHistoryService;

	@ApiOperation(value = "获得浏览记录基础信息")
	@RequestMapping(value = "browsinghistory/base", method = {RequestMethod.GET})
	public @ResponseBody Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		return new Result(Result.SUCCESS, map);
	}

	@ApiOperation(value = "获得浏览记录分页列表")
    @RequestMapping(value = "browsinghistory", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(BrowsingHistory browsingHistory, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = browsingHistoryService.page(page, browsingHistory);
	    return new Result(Result.SUCCESS, page);
	}


    @ApiOperation(value = "新增浏览记录")
	@RequestMapping(value = "browsinghistory", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody BrowsingHistory browsingHistory) {
		if (browsingHistory != null) {
			browsingHistoryService.save(browsingHistory);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID加载浏览记录")
    @RequestMapping(value = "browsinghistory/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<BrowsingHistoryVo> load(@PathVariable Long id) {
		BrowsingHistoryVo browsingHistoryVo = browsingHistoryService.load(new BrowsingHistory(id));
        return new Result(Result.SUCCESS,browsingHistoryVo);
    }

	@ApiOperation(value = "根据ID更新浏览记录")
	@RequestMapping(value = "browsinghistory/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody BrowsingHistory browsingHistory) {
		if (id != null && browsingHistory != null) {
			browsingHistory.setId(id);
			browsingHistoryService.updateIgnoreNull(browsingHistory);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID删除浏览记录")
	@RequestMapping(value = "browsinghistory/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		browsingHistoryService.delete(new BrowsingHistory(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setBrowsingHistoryService(
			@Qualifier("browsingHistoryService") IBrowsingHistoryService browsingHistoryService) {
		this.browsingHistoryService = browsingHistoryService;
	}


}
