package com.roof.coupon.accesslog.service.impl;

import java.io.Serializable;
import java.util.List;
import org.roof.roof.dataaccess.api.Page;
import com.roof.coupon.accesslog.dao.api.IAccessLogDao;
import com.roof.coupon.accesslog.entity.AccessLog;
import com.roof.coupon.accesslog.entity.AccessLogVo;
import com.roof.coupon.accesslog.service.api.IAccessLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AccessLogService implements IAccessLogService {
	private IAccessLogDao accessLogDao;

	public Serializable save(AccessLog accessLog){
		return accessLogDao.save(accessLog);
	}

	public void delete(AccessLog accessLog){
		accessLogDao.delete(accessLog);
	}
	
	public void deleteByExample(AccessLog accessLog){
		accessLogDao.deleteByExample(accessLog);
	}

	public void update(AccessLog accessLog){
		accessLogDao.update(accessLog);
	}
	
	public void updateIgnoreNull(AccessLog accessLog){
		accessLogDao.updateIgnoreNull(accessLog);
	}
		
	public void updateByExample(AccessLog accessLog){
		accessLogDao.update("updateByExampleAccessLog", accessLog);
	}

	public AccessLogVo load(AccessLog accessLog){
		return (AccessLogVo)accessLogDao.reload(accessLog);
	}
	
	public AccessLogVo selectForObject(AccessLog accessLog){
		return (AccessLogVo)accessLogDao.selectForObject("selectAccessLog",accessLog);
	}
	
	public List<AccessLogVo> selectForList(AccessLog accessLog){
		return (List<AccessLogVo>)accessLogDao.selectForList("selectAccessLog",accessLog);
	}
	
	public Page page(Page page, AccessLog accessLog) {
		return accessLogDao.page(page, accessLog);
	}

	@Autowired
	public void setIAccessLogDao(
			@Qualifier("accessLogDao") IAccessLogDao  accessLogDao) {
		this.accessLogDao = accessLogDao;
	}
	

}
