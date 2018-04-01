package com.roof.coupon.itemcatsfeatures.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.coupon.itemcatsfeatures.entity.ItemCatsFeatures;

public interface IItemCatsFeaturesDao extends IDaoSupport {
	Page page(Page page, ItemCatsFeatures itemCatsFeatures);
}