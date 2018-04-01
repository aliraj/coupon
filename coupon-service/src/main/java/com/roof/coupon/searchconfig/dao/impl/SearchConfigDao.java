package com.roof.coupon.searchconfig.dao.impl;

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

import com.roof.coupon.searchconfig.dao.api.ISearchConfigDao;
import com.roof.coupon.searchconfig.entity.SearchConfig;
@Service
public class SearchConfigDao extends AbstractDao implements ISearchConfigDao {
	
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, SearchConfig searchConfig) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectSearchConfigPage", "selectSearchConfigCount");
		//IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectSearchConfigPage", null);
		return pageQuery.select(searchConfig);
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