package com.roof.coupon.itemcoupon.controller;

import java.util.List;
import java.util.Map;
import com.google.common.collect.Maps;
import javax.servlet.http.HttpServletRequest;

import com.roof.coupon.DefaultUseableEnum;
import com.roof.coupon.itemcats.entity.ItemCats;
import com.roof.coupon.itemcats.entity.ItemCatsVo;
import com.roof.coupon.itemcats.service.api.IItemCatsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.coupon.itemcoupon.entity.ItemCoupon;
import com.roof.coupon.itemcoupon.entity.ItemCouponVo;
import com.roof.coupon.itemcoupon.service.api.IItemCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "itemcoupon", description = "商品优惠券管理")
@Controller
@RequestMapping("coupon")
public class ItemCouponController {
	private IItemCouponService itemCouponService;

	@Autowired
	private IItemCatsService iItemCatsService;

	@ApiOperation(value = "获得商品优惠券基础信息")
	@RequestMapping(value = "itemcoupon/base", method = {RequestMethod.GET})
	public @ResponseBody Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		List<ItemCatsVo> itemcats = iItemCatsService.selectForList(new ItemCats());
		map.put("itemcats",itemcats);
		return new Result(Result.SUCCESS, map);
	}

	@ApiOperation(value = "获得商品优惠券分页列表")
    @RequestMapping(value = "itemcoupon", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(ItemCouponVo itemCoupon, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = itemCouponService.page(page, itemCoupon);
	    return new Result(Result.SUCCESS, page);
	}


    @ApiOperation(value = "新增商品优惠券")
	@RequestMapping(value = "itemcoupon", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody ItemCoupon itemCoupon) {
		if (itemCoupon != null) {
			itemCouponService.save(itemCoupon);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID加载商品优惠券")
    @RequestMapping(value = "itemcoupon/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<ItemCouponVo> load(@PathVariable Long id) {
		ItemCouponVo itemCouponVo = itemCouponService.load(new ItemCoupon(id));
        return new Result(Result.SUCCESS,itemCouponVo);
    }

	@ApiOperation(value = "根据ID更新商品优惠券")
	@RequestMapping(value = "itemcoupon/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody ItemCoupon itemCoupon) {
		if (id != null && itemCoupon != null) {
			itemCoupon.setNumIid(id);
			itemCouponService.updateIgnoreNull(itemCoupon);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

	@ApiOperation(value = "根据ID上架")
	@RequestMapping(value = "itemcoupon/{id}/up", method = {RequestMethod.PUT})
	public @ResponseBody Result up(@PathVariable Long id ) {
		if (id != null ) {
			ItemCoupon itemCoupon = new ItemCoupon(id);
			itemCoupon.setUseable(DefaultUseableEnum.usable.getCode());
			itemCouponService.updateIgnoreNull(itemCoupon);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

	@ApiOperation(value = "根据ID上架")
	@RequestMapping(value = "itemcoupon/{id}/down", method = {RequestMethod.PUT})
	public @ResponseBody Result down(@PathVariable Long id ) {
		if (id != null ) {
			ItemCoupon itemCoupon = new ItemCoupon(id);
			itemCoupon.setUseable(DefaultUseableEnum.unusable.getCode());
			itemCouponService.updateIgnoreNull(itemCoupon);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID删除商品优惠券")
	@RequestMapping(value = "itemcoupon/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		itemCouponService.delete(new ItemCoupon(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setItemCouponService(
			@Qualifier("itemCouponService") IItemCouponService itemCouponService) {
		this.itemCouponService = itemCouponService;
	}


}
