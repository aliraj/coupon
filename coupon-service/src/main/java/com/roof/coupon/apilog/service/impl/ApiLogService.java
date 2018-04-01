package com.roof.coupon.apilog.service.impl;

import java.io.Serializable;
import java.util.List;
import org.roof.roof.dataaccess.api.Page;
import com.roof.coupon.apilog.dao.api.IApiLogDao;
import com.roof.coupon.apilog.entity.ApiLog;
import com.roof.coupon.apilog.entity.ApiLogVo;
import com.roof.coupon.apilog.service.api.IApiLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ApiLogService implements IApiLogService {
	private IApiLogDao apiLogDao;

	public Serializable save(ApiLog apiLog){
		return apiLogDao.save(apiLog);
	}

	public void delete(ApiLog apiLog){
		apiLogDao.delete(apiLog);
	}
	
	public void deleteByExample(ApiLog apiLog){
		apiLogDao.deleteByExample(apiLog);
	}

	public void update(ApiLog apiLog){
		apiLogDao.update(apiLog);
	}
	
	public void updateIgnoreNull(ApiLog apiLog){
		apiLogDao.updateIgnoreNull(apiLog);
	}
		
	public void updateByExample(ApiLog apiLog){
		apiLogDao.update("updateByExampleApiLog", apiLog);
	}

	public ApiLogVo load(ApiLog apiLog){
		return (ApiLogVo)apiLogDao.reload(apiLog);
	}
	
	public ApiLogVo selectForObject(ApiLog apiLog){
		return (ApiLogVo)apiLogDao.selectForObject("selectApiLog",apiLog);
	}
	
	public List<ApiLogVo> selectForList(ApiLog apiLog){
		return (List<ApiLogVo>)apiLogDao.selectForList("selectApiLog",apiLog);
	}
	
	public Page page(Page page, ApiLog apiLog) {
		return apiLogDao.page(page, apiLog);
	}

	@Autowired
	public void setIApiLogDao(
			@Qualifier("apiLogDao") IApiLogDao  apiLogDao) {
		this.apiLogDao = apiLogDao;
	}
	

}
