package com.roof.coupon.itemcatsfeatures.controller;

import java.util.List;
import java.util.Map;
import com.google.common.collect.Maps;
import javax.servlet.http.HttpServletRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.coupon.itemcatsfeatures.entity.ItemCatsFeatures;
import com.roof.coupon.itemcatsfeatures.entity.ItemCatsFeaturesVo;
import com.roof.coupon.itemcatsfeatures.service.api.IItemCatsFeaturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "itemcatsfeatures", description = "类目属性列表管理")
@Controller
@RequestMapping("coupon")
public class ItemCatsFeaturesController {
	private IItemCatsFeaturesService itemCatsFeaturesService;

	@ApiOperation(value = "获得类目属性列表基础信息")
	@RequestMapping(value = "itemcatsfeatures/base", method = {RequestMethod.GET})
	public @ResponseBody Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		return new Result(Result.SUCCESS, map);
	}

	@ApiOperation(value = "获得类目属性列表分页列表")
    @RequestMapping(value = "itemcatsfeatures", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(ItemCatsFeatures itemCatsFeatures, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = itemCatsFeaturesService.page(page, itemCatsFeatures);
	    return new Result(Result.SUCCESS, page);
	}


    @ApiOperation(value = "新增类目属性列表")
	@RequestMapping(value = "itemcatsfeatures", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody ItemCatsFeatures itemCatsFeatures) {
		if (itemCatsFeatures != null) {
			itemCatsFeaturesService.save(itemCatsFeatures);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID加载类目属性列表")
    @RequestMapping(value = "itemcatsfeatures/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<ItemCatsFeaturesVo> load(@PathVariable Long id) {
		ItemCatsFeaturesVo itemCatsFeaturesVo = itemCatsFeaturesService.load(new ItemCatsFeatures(id));
        return new Result(Result.SUCCESS,itemCatsFeaturesVo);
    }

	@ApiOperation(value = "根据ID更新类目属性列表")
	@RequestMapping(value = "itemcatsfeatures/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody ItemCatsFeatures itemCatsFeatures) {
		if (id != null && itemCatsFeatures != null) {
			itemCatsFeatures.setId(id);
			itemCatsFeaturesService.updateIgnoreNull(itemCatsFeatures);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID删除类目属性列表")
	@RequestMapping(value = "itemcatsfeatures/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		itemCatsFeaturesService.delete(new ItemCatsFeatures(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setItemCatsFeaturesService(
			@Qualifier("itemCatsFeaturesService") IItemCatsFeaturesService itemCatsFeaturesService) {
		this.itemCatsFeaturesService = itemCatsFeaturesService;
	}


}
