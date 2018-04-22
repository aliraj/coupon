package com.roof.coupon.area.dao.impl;


import com.roof.coupon.area.dao.api.IAreaDao;
import com.roof.coupon.area.entity.Area;
import org.roof.dataaccess.PageQuery;
import org.roof.roof.dataaccess.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AreaDao extends AbstractDao implements IAreaDao {
	
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, Area area) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectAreaPage", "selectAreaCount");
		//IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectAreaPage", null);
		return pageQuery.select(area);
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