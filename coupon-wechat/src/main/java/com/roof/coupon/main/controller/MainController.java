package com.roof.coupon.main.controller;

import com.roof.coupon.banner.entity.Banner;
import com.roof.coupon.banner.entity.BannerVo;
import com.roof.coupon.banner.service.api.IBannerService;
import com.roof.coupon.core.http.HttpClientUtil;
import com.roof.coupon.itemcats.entity.ItemCats;
import com.roof.coupon.itemcats.entity.ItemCatsVo;
import com.roof.coupon.itemcats.service.api.IItemCatsService;
import com.roof.coupon.itemcoupon.entity.ItemCouponVo;
import com.roof.coupon.itemcoupon.service.api.IItemCouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author liht
 */
@Api(value = "main", description = "首页")
@Controller
@RequestMapping("/coupon/wechat/main")
public class MainController {

    @Autowired
    private IItemCatsService itemCatsService;

    @Autowired
    private IBannerService bannerService;

    @Autowired
    private IItemCouponService itemCouponService;

    @ApiOperation(value = "获得首页菜单")
    @RequestMapping(value = "menu", method = {RequestMethod.GET})
    public @ResponseBody
    Result menu(HttpServletRequest httpServletRequest) {
        List<ItemCatsVo> itemCatsVos = itemCatsService.selectForList(new ItemCats());

        return new Result(Result.SUCCESS, itemCatsVos);
    }

    @ApiOperation(value = "获得首页banner")
    @RequestMapping(value = "banner", method = {RequestMethod.GET})
    public @ResponseBody
    Result banner(HttpServletRequest httpServletRequest) {
        List<BannerVo> bannerVos = bannerService.selectForList(new Banner());
        return new Result(Result.SUCCESS, bannerVos);
    }

    @ApiOperation(value = "获得首页优惠券")
    @RequestMapping(value = "coupons", method = {RequestMethod.GET})
    public @ResponseBody
    Result<Page> list(ItemCouponVo itemCouponVo, HttpServletRequest request) {
        Page page = PageUtils.createPage(request);
//        itemCouponVo.setPlatform("jingtuitui");
        page = itemCouponService.page(page, itemCouponVo);
//        List<ItemCouponVo> list = (List<ItemCouponVo>) page.getDataList();
//        for (ItemCouponVo vo : list
//                ) {
//            if (vo.getCouponClickUrl().indexOf("jd.com") > -1) {
//                vo.setPlatform("jingtuitui");
//            } else {
//                vo.setPlatform("taoke");
//            }
//        }
        return new Result(Result.SUCCESS, page);
    }


}
