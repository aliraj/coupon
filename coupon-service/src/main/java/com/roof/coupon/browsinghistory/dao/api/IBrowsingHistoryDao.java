package com.roof.coupon.browsinghistory.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.coupon.browsinghistory.entity.BrowsingHistory;

public interface IBrowsingHistoryDao extends IDaoSupport {
	Page page(Page page, BrowsingHistory browsingHistory);
}