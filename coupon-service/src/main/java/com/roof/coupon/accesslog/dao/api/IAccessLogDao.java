package com.roof.coupon.accesslog.dao.api;

import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.coupon.accesslog.entity.AccessLog;

public interface IAccessLogDao extends IDaoSupport {
	Page page(Page page, AccessLog accessLog);
}