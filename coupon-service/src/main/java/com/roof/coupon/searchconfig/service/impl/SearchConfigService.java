package com.roof.coupon.searchconfig.service.impl;

import java.io.Serializable;
import java.util.List;
import org.roof.roof.dataaccess.api.Page;
import com.roof.coupon.searchconfig.dao.api.ISearchConfigDao;
import com.roof.coupon.searchconfig.entity.SearchConfig;
import com.roof.coupon.searchconfig.entity.SearchConfigVo;
import com.roof.coupon.searchconfig.service.api.ISearchConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SearchConfigService implements ISearchConfigService {
	private ISearchConfigDao searchConfigDao;

	public Serializable save(SearchConfig searchConfig){
		return searchConfigDao.save(searchConfig);
	}

	public void delete(SearchConfig searchConfig){
		searchConfigDao.delete(searchConfig);
	}
	
	public void deleteByExample(SearchConfig searchConfig){
		searchConfigDao.deleteByExample(searchConfig);
	}

	public void update(SearchConfig searchConfig){
		searchConfigDao.update(searchConfig);
	}
	
	public void updateIgnoreNull(SearchConfig searchConfig){
		searchConfigDao.updateIgnoreNull(searchConfig);
	}
		
	public void updateByExample(SearchConfig searchConfig){
		searchConfigDao.update("updateByExampleSearchConfig", searchConfig);
	}

	public SearchConfigVo load(SearchConfig searchConfig){
		return (SearchConfigVo)searchConfigDao.reload(searchConfig);
	}
	
	public SearchConfigVo selectForObject(SearchConfig searchConfig){
		return (SearchConfigVo)searchConfigDao.selectForObject("selectSearchConfig",searchConfig);
	}
	
	public List<SearchConfigVo> selectForList(SearchConfig searchConfig){
		return (List<SearchConfigVo>)searchConfigDao.selectForList("selectSearchConfig",searchConfig);
	}
	
	public Page page(Page page, SearchConfig searchConfig) {
		return searchConfigDao.page(page, searchConfig);
	}

	@Autowired
	public void setISearchConfigDao(
			@Qualifier("searchConfigDao") ISearchConfigDao  searchConfigDao) {
		this.searchConfigDao = searchConfigDao;
	}
	

}
