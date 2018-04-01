package com.roof.coupon.browsinghistory.dao.impl;

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

import com.roof.coupon.browsinghistory.dao.api.IBrowsingHistoryDao;
import com.roof.coupon.browsinghistory.entity.BrowsingHistory;
@Service
public class BrowsingHistoryDao extends AbstractDao implements IBrowsingHistoryDao {
	
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, BrowsingHistory browsingHistory) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectBrowsingHistoryPage", "selectBrowsingHistoryCount");
		//IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectBrowsingHistoryPage", null);
		return pageQuery.select(browsingHistory);
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