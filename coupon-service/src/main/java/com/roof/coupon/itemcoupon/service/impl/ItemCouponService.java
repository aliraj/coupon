package com.roof.coupon.itemcoupon.service.impl;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import com.alibaba.fastjson.JSON;
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
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisKeyValueTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Service
public class ItemCouponService implements IItemCouponService {
    private final static Logger logger = LoggerFactory.getLogger(ItemCouponService.class);
    private IItemCouponDao itemCouponDao;

    private ItemCouponOuterApiService jingtuituiItemCouponOuterApiService;

    private ItemCouponOuterApiService taokeItemCouponOuterApiService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private LoadingCache<Long, ItemCouponVo> cache = CacheBuilder.newBuilder().maximumSize(1000L)
            .expireAfterAccess(10, TimeUnit.MINUTES)//设置时间对象没有被读/写访问则对象从内存中删除
            .expireAfterWrite(10, TimeUnit.MINUTES)//设置时间对象没有被写访问则对象从内存中删除
            .recordStats()
            .build(new CacheLoader<Long, ItemCouponVo>() {
                @Override
                public ItemCouponVo load(Long key) {
                    //从SQL或者NoSql 获取对象
                    return (ItemCouponVo) itemCouponDao.reload(new ItemCoupon(key));
                }
            });


    @Override
    public ItemCouponVo wechatLoad(ItemCouponVo itemCoupon) {
        try {
            return cache.get(itemCoupon.getNumIid());
        } catch (ExecutionException e) {
            e.printStackTrace();
            return (ItemCouponVo) itemCouponDao.reload(new ItemCoupon(itemCoupon.getNumIid()));
        }
    }

    @Override
    public Serializable save(ItemCoupon itemCoupon) {
        return itemCouponDao.save(itemCoupon);
    }

    @Override
    public ItemCoupon loadConnect(ItemCouponVo itemCouponVo) {
        BoundValueOperations<String, String> operations = redisTemplate.boundValueOps(itemCouponVo.getOuterId());
        String json = operations.get();
        if (!StringUtils.isEmpty(json)) {
            return JSON.parseObject(json, ItemCoupon.class);
        }
        return new ItemCoupon();
    }

    @Override
    public Page pageConnect(Page page, String type, String name) {
        Assert.notNull(type, "查询平台不能为空");
        Assert.notNull(name, "查询关键字不能为空");
        try {
            if (type.equals("jingtuitui")) {
                page = jingtuituiItemCouponOuterApiService.search(name, page);
                //缓存数据
                if (page.getDataList() != null && page.getDataList().size() != 0) {
                    List<ItemCoupon> list = (List<ItemCoupon>) page.getDataList();
                    for (ItemCoupon itemCoupon :
                            list) {
                        BoundValueOperations<String, String> operations = redisTemplate.boundValueOps(itemCoupon.getOuterId());
                        operations.set(JSON.toJSONString(itemCoupon), 7, TimeUnit.DAYS);
                    }
                }
            } else if (type.equals("taoke")) {
//                page = taokeItemCouponOuterApiService.search(name, page);
//                //缓存数据
//                if (page.getDataList() != null && page.getDataList().size() != 0) {
//                    List<ItemCoupon> list = (List<ItemCoupon>) page.getDataList();
//                    List<ItemCoupon> out_list = new ArrayList<ItemCoupon>();
//
//                    for (ItemCoupon itemCoupon :
//                            list) {
//                        if (itemCoupon.getCouponClickUrl() != null) {
//                            BoundValueOperations<String, String> operations = redisTemplate.boundValueOps(itemCoupon.getOuterId());
//                            operations.set(JSON.toJSONString(itemCoupon), 7, TimeUnit.DAYS);
//                            out_list.add(itemCoupon);
//                        }
//                    }
//                    page.setDataList(out_list);
//                }
                this.findTaobaoPage(name, page, 10);

            }

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return page;
    }


    private Page findTaobaoPage(String name, Page page, int times) throws IOException {
        if (times > 0) {
            page = taokeItemCouponOuterApiService.search(name, page);
            if (page.getDataList() != null && page.getDataList().size() != 0) {
                List<ItemCoupon> list = (List<ItemCoupon>) page.getDataList();
                List<ItemCoupon> out_list = new ArrayList<ItemCoupon>();

                for (ItemCoupon itemCoupon :
                        list) {
                    if (itemCoupon.getCouponClickUrl() != null) {
                        BoundValueOperations<String, String> operations = redisTemplate.boundValueOps(itemCoupon.getOuterId());
                        operations.set(JSON.toJSONString(itemCoupon), 7, TimeUnit.DAYS);
                        out_list.add(itemCoupon);
                    }
                }
                times = times - 1;
                if (out_list.size() > 0) {
                    page.setDataList(out_list);
                } else {
                    page.setCurrentPage(page.getCurrentPage() + 1);
                    page = this.findTaobaoPage(name, page, times);

                }

            } else {
                return page;
            }
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
