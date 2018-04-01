package com.roof.coupon.itemcats.dao.impl;

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

import com.roof.coupon.itemcats.dao.api.IItemCatsDao;
import com.roof.coupon.itemcats.entity.ItemCats;
@Service
public class ItemCatsDao extends AbstractDao implements IItemCatsDao {
	
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, ItemCats itemCats) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectItemCatsPage", "selectItemCatsCount");
		//IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectItemCatsPage", null);
		return pageQuery.select(itemCats);
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