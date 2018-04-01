package com.roof.coupon.itemcatsfeatures.service.impl;

import java.io.Serializable;
import java.util.List;
import org.roof.roof.dataaccess.api.Page;
import com.roof.coupon.itemcatsfeatures.dao.api.IItemCatsFeaturesDao;
import com.roof.coupon.itemcatsfeatures.entity.ItemCatsFeatures;
import com.roof.coupon.itemcatsfeatures.entity.ItemCatsFeaturesVo;
import com.roof.coupon.itemcatsfeatures.service.api.IItemCatsFeaturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ItemCatsFeaturesService implements IItemCatsFeaturesService {
	private IItemCatsFeaturesDao itemCatsFeaturesDao;

	public Serializable save(ItemCatsFeatures itemCatsFeatures){
		return itemCatsFeaturesDao.save(itemCatsFeatures);
	}

	public void delete(ItemCatsFeatures itemCatsFeatures){
		itemCatsFeaturesDao.delete(itemCatsFeatures);
	}
	
	public void deleteByExample(ItemCatsFeatures itemCatsFeatures){
		itemCatsFeaturesDao.deleteByExample(itemCatsFeatures);
	}

	public void update(ItemCatsFeatures itemCatsFeatures){
		itemCatsFeaturesDao.update(itemCatsFeatures);
	}
	
	public void updateIgnoreNull(ItemCatsFeatures itemCatsFeatures){
		itemCatsFeaturesDao.updateIgnoreNull(itemCatsFeatures);
	}
		
	public void updateByExample(ItemCatsFeatures itemCatsFeatures){
		itemCatsFeaturesDao.update("updateByExampleItemCatsFeatures", itemCatsFeatures);
	}

	public ItemCatsFeaturesVo load(ItemCatsFeatures itemCatsFeatures){
		return (ItemCatsFeaturesVo)itemCatsFeaturesDao.reload(itemCatsFeatures);
	}
	
	public ItemCatsFeaturesVo selectForObject(ItemCatsFeatures itemCatsFeatures){
		return (ItemCatsFeaturesVo)itemCatsFeaturesDao.selectForObject("selectItemCatsFeatures",itemCatsFeatures);
	}
	
	public List<ItemCatsFeaturesVo> selectForList(ItemCatsFeatures itemCatsFeatures){
		return (List<ItemCatsFeaturesVo>)itemCatsFeaturesDao.selectForList("selectItemCatsFeatures",itemCatsFeatures);
	}
	
	public Page page(Page page, ItemCatsFeatures itemCatsFeatures) {
		return itemCatsFeaturesDao.page(page, itemCatsFeatures);
	}

	@Autowired
	public void setIItemCatsFeaturesDao(
			@Qualifier("itemCatsFeaturesDao") IItemCatsFeaturesDao  itemCatsFeaturesDao) {
		this.itemCatsFeaturesDao = itemCatsFeaturesDao;
	}
	

}
