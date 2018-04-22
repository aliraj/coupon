package com.roof.coupon.area.dao.api;

import com.roof.coupon.area.entity.Area;
import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

public interface IAreaDao extends IDaoSupport {
	Page page(Page page, Area area);
}