package com.roof.coupon.itemcoupon.dao.impl;

import java.util.Comparator;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.roof.dataaccess.PageQuery;
import org.roof.roof.dataaccess.api.AbstractDao;
import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.IPageQuery;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.roof.coupon.itemcoupon.dao.api.IItemCouponDao;
import com.roof.coupon.itemcoupon.entity.ItemCoupon;

@Service
public class ItemCouponDao extends AbstractDao implements IItemCouponDao {

    private PageQueryFactory<PageQuery> pageQueryFactory;

    public Page page(Page page, ItemCoupon itemCoupon) {
        IPageQuery pageQuery = pageQueryFactory.getPageQuery(page, "selectItemCouponPage", "selectItemCouponCount");
        //IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectItemCouponPage", null);
        return pageQuery.select(itemCoupon);
    }

    @Override
    public int updateByOuterId(ItemCoupon itemCoupon) {
        return this.update("updateIgnoreNullItemCoupon", itemCoupon);
    }

    @Autowired
    public void setPageQueryFactory(
            @Qualifier("pageQueryFactory") PageQueryFactory<PageQuery> pageQueryFactory) {
        this.pageQueryFactory = pageQueryFactory;
    }

    @Autowired
    public void setDaoSupport(
            @Qualifier("roofDaoSupport") IDaoSupport daoSupport) {
        this.daoSupport = daoSupport;
    }

}