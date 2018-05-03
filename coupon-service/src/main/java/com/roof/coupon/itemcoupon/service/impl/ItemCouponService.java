package com.roof.coupon.itemcoupon.service.impl;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import com.roof.coupon.outerapi.ItemCouponOuterApiService;
import org.roof.roof.dataaccess.api.Page;
import com.roof.coupon.itemcoupon.dao.api.IItemCouponDao;
import com.roof.coupon.itemcoupon.entity.ItemCoupon;
import com.roof.coupon.itemcoupon.entity.ItemCouponVo;
import com.roof.coupon.itemcoupon.service.api.IItemCouponService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class ItemCouponService implements IItemCouponService {
    private final static Logger logger = LoggerFactory.getLogger(ItemCouponService.class);
    private IItemCouponDao itemCouponDao;

    private ItemCouponOuterApiService jingtuituiItemCouponOuterApiService;

    private ItemCouponOuterApiService taokeItemCouponOuterApiService;

    @Override
    public ItemCouponVo wechatLoad(ItemCouponVo itemCoupon) {
        return (ItemCouponVo) itemCouponDao.reload(new ItemCoupon(itemCoupon.getNumIid()));
    }

    @Override
    public Serializable save(ItemCoupon itemCoupon) {
        return itemCouponDao.save(itemCoupon);
    }

    @Override
    public Page pageConnect(Page page, String type, String name) {
        Assert.notNull(type, "查询平台不能为空");
        Assert.notNull(name, "查询关键字不能为空");
        try {
            if (type.equals("jingtuitui")) {
                page = jingtuituiItemCouponOuterApiService.search(name, page);
            } else if (type.equals("taoke")) {
                page = taokeItemCouponOuterApiService.search(name, page);
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return page;
    }


    public void delete(ItemCoupon itemCoupon) {
        itemCouponDao.delete(itemCoupon);
    }

    public void deleteByExample(ItemCoupon itemCoupon) {
        itemCouponDao.deleteByExample(itemCoupon);
    }

    public void update(ItemCoupon itemCoupon) {
        itemCouponDao.update(itemCoupon);
    }

    public void updateIgnoreNull(ItemCoupon itemCoupon) {
        itemCouponDao.updateIgnoreNull(itemCoupon);
    }

    public void updateByExample(ItemCoupon itemCoupon) {
        itemCouponDao.update("updateByExampleItemCoupon", itemCoupon);
    }

    public ItemCouponVo load(ItemCoupon itemCoupon) {
        return (ItemCouponVo) itemCouponDao.reload(itemCoupon);
    }

    public ItemCouponVo selectForObject(ItemCoupon itemCoupon) {
        return (ItemCouponVo) itemCouponDao.selectForObject("selectItemCoupon", itemCoupon);
    }

    public List<ItemCouponVo> selectForList(ItemCoupon itemCoupon) {
        return (List<ItemCouponVo>) itemCouponDao.selectForList("selectItemCoupon", itemCoupon);
    }

    public Page page(Page page, ItemCoupon itemCoupon) {
        return itemCouponDao.page(page, itemCoupon);
    }

    @Override
    public void saveOrUpdateByOuterId(List<ItemCoupon> itemCoupons) {
        for (ItemCoupon itemCoupon : itemCoupons) {
            int affected = itemCouponDao.updateByOuterId(itemCoupon);
            if (affected == 0) {
                save(itemCoupon);
            }
        }
    }

    @Autowired
    public void setIItemCouponDao(
            @Qualifier("itemCouponDao") IItemCouponDao itemCouponDao
    ) {
        this.itemCouponDao = itemCouponDao;
    }

    @Autowired
    public void setJingtuituiItemCouponOuterApiService(@Qualifier("jingtuituiItemCouponOuterApiService") ItemCouponOuterApiService jingtuituiItemCouponOuterApiService) {
        this.jingtuituiItemCouponOuterApiService = jingtuituiItemCouponOuterApiService;
    }

    @Autowired
    public void setTaokeItemCouponOuterApiService(@Qualifier("taokeItemCouponOuterApiService") ItemCouponOuterApiService taokeItemCouponOuterApiService) {
        this.taokeItemCouponOuterApiService = taokeItemCouponOuterApiService;
    }
}
