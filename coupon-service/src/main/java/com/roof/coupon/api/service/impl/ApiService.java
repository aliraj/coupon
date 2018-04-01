package com.roof.coupon.api.service.impl;

import java.io.Serializable;
import java.util.List;
import org.roof.roof.dataaccess.api.Page;
import com.roof.coupon.api.dao.api.IApiDao;
import com.roof.coupon.api.entity.Api;
import com.roof.coupon.api.entity.ApiVo;
import com.roof.coupon.api.service.api.IApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ApiService implements IApiService {
	private IApiDao apiDao;

	public Serializable save(Api api){
		return apiDao.save(api);
	}

	public void delete(Api api){
		apiDao.delete(api);
	}
	
	public void deleteByExample(Api api){
		apiDao.deleteByExample(api);
	}

	public void update(Api api){
		apiDao.update(api);
	}
	
	public void updateIgnoreNull(Api api){
		apiDao.updateIgnoreNull(api);
	}
		
	public void updateByExample(Api api){
		apiDao.update("updateByExampleApi", api);
	}

	public ApiVo load(Api api){
		return (ApiVo)apiDao.reload(api);
	}
	
	public ApiVo selectForObject(Api api){
		return (ApiVo)apiDao.selectForObject("selectApi",api);
	}
	
	public List<ApiVo> selectForList(Api api){
		return (List<ApiVo>)apiDao.selectForList("selectApi",api);
	}
	
	public Page page(Page page, Api api) {
		return apiDao.page(page, api);
	}

	@Autowired
	public void setIApiDao(
			@Qualifier("apiDao") IApiDao  apiDao) {
		this.apiDao = apiDao;
	}
	

}
