package com.roof.coupon.itemcats.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_item_cats <br/>
 *         描述：标准商品类目 <br/>
 */
public class ItemCatsVo extends ItemCats {

	private List<ItemCatsVo> itemCatsList;

	public ItemCatsVo() {
		super();
	}

	public ItemCatsVo(Long cid) {
		super();
		this.cid = cid;
	}

	public List<ItemCatsVo> getItemCatsList() {
		return itemCatsList;
	}

	public void setItemCatsList(List<ItemCatsVo> itemCatsList) {
		this.itemCatsList = itemCatsList;
	}

}
