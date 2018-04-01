package com.roof.coupon.browsinghistory.service.impl;

import java.io.Serializable;
import java.util.List;
import org.roof.roof.dataaccess.api.Page;
import com.roof.coupon.browsinghistory.dao.api.IBrowsingHistoryDao;
import com.roof.coupon.browsinghistory.entity.BrowsingHistory;
import com.roof.coupon.browsinghistory.entity.BrowsingHistoryVo;
import com.roof.coupon.browsinghistory.service.api.IBrowsingHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BrowsingHistoryService implements IBrowsingHistoryService {
	private IBrowsingHistoryDao browsingHistoryDao;

	public Serializable save(BrowsingHistory browsingHistory){
		return browsingHistoryDao.save(browsingHistory);
	}

	public void delete(BrowsingHistory browsingHistory){
		browsingHistoryDao.delete(browsingHistory);
	}
	
	public void deleteByExample(BrowsingHistory browsingHistory){
		browsingHistoryDao.deleteByExample(browsingHistory);
	}

	public void update(BrowsingHistory browsingHistory){
		browsingHistoryDao.update(browsingHistory);
	}
	
	public void updateIgnoreNull(BrowsingHistory browsingHistory){
		browsingHistoryDao.updateIgnoreNull(browsingHistory);
	}
		
	public void updateByExample(BrowsingHistory browsingHistory){
		browsingHistoryDao.update("updateByExampleBrowsingHistory", browsingHistory);
	}

	public BrowsingHistoryVo load(BrowsingHistory browsingHistory){
		return (BrowsingHistoryVo)browsingHistoryDao.reload(browsingHistory);
	}
	
	public BrowsingHistoryVo selectForObject(BrowsingHistory browsingHistory){
		return (BrowsingHistoryVo)browsingHistoryDao.selectForObject("selectBrowsingHistory",browsingHistory);
	}
	
	public List<BrowsingHistoryVo> selectForList(BrowsingHistory browsingHistory){
		return (List<BrowsingHistoryVo>)browsingHistoryDao.selectForList("selectBrowsingHistory",browsingHistory);
	}
	
	public Page page(Page page, BrowsingHistory browsingHistory) {
		return browsingHistoryDao.page(page, browsingHistory);
	}

	@Autowired
	public void setIBrowsingHistoryDao(
			@Qualifier("browsingHistoryDao") IBrowsingHistoryDao  browsingHistoryDao) {
		this.browsingHistoryDao = browsingHistoryDao;
	}
	

}
