package com.roof.coupon.itemcatsfeatures.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_item_cats_features <br/>
 *         描述：类目属性列表 <br/>
 */
public class ItemCatsFeaturesVo extends ItemCatsFeatures {

	private List<ItemCatsFeaturesVo> itemCatsFeaturesList;

	public ItemCatsFeaturesVo() {
		super();
	}

	public ItemCatsFeaturesVo(Long id) {
		super();
		this.id = id;
	}

	public List<ItemCatsFeaturesVo> getItemCatsFeaturesList() {
		return itemCatsFeaturesList;
	}

	public void setItemCatsFeaturesList(List<ItemCatsFeaturesVo> itemCatsFeaturesList) {
		this.itemCatsFeaturesList = itemCatsFeaturesList;
	}

}
