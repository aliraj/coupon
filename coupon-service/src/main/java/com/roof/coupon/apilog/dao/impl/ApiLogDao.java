package com.roof.coupon.apilog.dao.impl;

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

import com.roof.coupon.apilog.dao.api.IApiLogDao;
import com.roof.coupon.apilog.entity.ApiLog;
@Service
public class ApiLogDao extends AbstractDao implements IApiLogDao {
	
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, ApiLog apiLog) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectApiLogPage", "selectApiLogCount");
		//IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectApiLogPage", null);
		return pageQuery.select(apiLog);
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