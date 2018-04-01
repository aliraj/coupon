package com.roof.coupon.apilog.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.coupon.apilog.entity.ApiLog;

public interface IApiLogDao extends IDaoSupport {
	Page page(Page page, ApiLog apiLog);
}