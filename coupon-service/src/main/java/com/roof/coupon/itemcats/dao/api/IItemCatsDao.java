package com.roof.coupon.itemcats.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.coupon.itemcats.entity.ItemCats;

public interface IItemCatsDao extends IDaoSupport {
	Page page(Page page, ItemCats itemCats);
}