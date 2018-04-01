package com.roof.coupon.searchconfig.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.coupon.searchconfig.entity.SearchConfig;

public interface ISearchConfigDao extends IDaoSupport {
	Page page(Page page, SearchConfig searchConfig);
}