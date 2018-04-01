package com.roof.coupon.itemcats.service.impl;

import java.io.Serializable;
import java.util.List;
import org.roof.roof.dataaccess.api.Page;
import com.roof.coupon.itemcats.dao.api.IItemCatsDao;
import com.roof.coupon.itemcats.entity.ItemCats;
import com.roof.coupon.itemcats.entity.ItemCatsVo;
import com.roof.coupon.itemcats.service.api.IItemCatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ItemCatsService implements IItemCatsService {
	private IItemCatsDao itemCatsDao;

	public Serializable save(ItemCats itemCats){
		return itemCatsDao.save(itemCats);
	}

	public void delete(ItemCats itemCats){
		itemCatsDao.delete(itemCats);
	}
	
	public void deleteByExample(ItemCats itemCats){
		itemCatsDao.deleteByExample(itemCats);
	}

	public void update(ItemCats itemCats){
		itemCatsDao.update(itemCats);
	}
	
	public void updateIgnoreNull(ItemCats itemCats){
		itemCatsDao.updateIgnoreNull(itemCats);
	}
		
	public void updateByExample(ItemCats itemCats){
		itemCatsDao.update("updateByExampleItemCats", itemCats);
	}

	public ItemCatsVo load(ItemCats itemCats){
		return (ItemCatsVo)itemCatsDao.reload(itemCats);
	}
	
	public ItemCatsVo selectForObject(ItemCats itemCats){
		return (ItemCatsVo)itemCatsDao.selectForObject("selectItemCats",itemCats);
	}
	
	public List<ItemCatsVo> selectForList(ItemCats itemCats){
		return (List<ItemCatsVo>)itemCatsDao.selectForList("selectItemCats",itemCats);
	}
	
	public Page page(Page page, ItemCats itemCats) {
		return itemCatsDao.page(page, itemCats);
	}

	@Autowired
	public void setIItemCatsDao(
			@Qualifier("itemCatsDao") IItemCatsDao  itemCatsDao) {
		this.itemCatsDao = itemCatsDao;
	}
	

}
