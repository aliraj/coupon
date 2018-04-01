package com.roof.coupon.api.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.coupon.api.entity.Api;

public interface IApiDao extends IDaoSupport {
	Page page(Page page, Api api);
}