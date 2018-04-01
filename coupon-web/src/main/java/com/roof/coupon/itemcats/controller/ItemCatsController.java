package com.roof.coupon.itemcats.controller;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.coupon.itemcats.entity.ItemCats;
import com.roof.coupon.itemcats.entity.ItemCatsVo;
import com.roof.coupon.itemcats.service.api.IItemCatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "itemcats", description = "标准商品类目管理")
@Controller
@RequestMapping("coupon")
public class ItemCatsController {
    private IItemCatsService itemCatsService;

    @ApiOperation(value = "获得标准商品类目基础信息")
    @RequestMapping(value = "itemcats/base", method = {RequestMethod.GET})
    public @ResponseBody
    Result<Map<String, Object>> base(HttpServletRequest request) {
        Map<String, Object> map = Maps.newHashMap();
        return new Result(Result.SUCCESS, map);
    }

    @ApiOperation(value = "获得标准商品类目分页列表")
    @RequestMapping(value = "itemcats", method = {RequestMethod.GET})
    public @ResponseBody
    Result<Page> list(ItemCats itemCats, HttpServletRequest request) {
        Page page = PageUtils.createPage(request);
        page = itemCatsService.page(page, itemCats);
        return new Result(Result.SUCCESS, page);
    }


    @ApiOperation(value = "新增标准商品类目")
    @RequestMapping(value = "itemcats", method = {RequestMethod.POST})
    public @ResponseBody
    Result create(@RequestBody ItemCats itemCats) {
        if (itemCats != null) {
            itemCatsService.save(itemCats);
            return new Result("保存成功!");
        } else {
            return new Result(Result.FAIL, "数据传输失败!");
        }
    }

    @ApiOperation(value = "根据ID加载标准商品类目")
    @RequestMapping(value = "itemcats/{id}", method = {RequestMethod.GET})
    public @ResponseBody
    Result<ItemCatsVo> load(@PathVariable Long id) {
        ItemCatsVo itemCatsVo = itemCatsService.load(new ItemCats(id));
        return new Result(Result.SUCCESS, itemCatsVo);
    }

    @ApiOperation(value = "根据ID更新标准商品类目")
    @RequestMapping(value = "itemcats/{id}", method = {RequestMethod.PUT})
    public @ResponseBody
    Result update(@PathVariable Long id, @RequestBody ItemCats itemCats) {
        if (id != null && itemCats != null) {
            itemCats.setCid(id);
            itemCatsService.updateIgnoreNull(itemCats);
            return new Result("保存成功!");
        } else {
            return new Result(Result.FAIL, "数据传输失败!");
        }
    }

    @ApiOperation(value = "根据ID删除标准商品类目")
    @RequestMapping(value = "itemcats/{id}", method = {RequestMethod.DELETE})
    public @ResponseBody
    Result delete(@PathVariable Long id) {
        // TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
        itemCatsService.delete(new ItemCats(id));
        return new Result("删除成功!");
    }

    @Autowired(required = true)
    public void setItemCatsService(
            @Qualifier("itemCatsService") IItemCatsService itemCatsService) {
        this.itemCatsService = itemCatsService;
    }


}
