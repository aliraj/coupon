package com.roof.coupon.itemcatsfeatures.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 *         表名： c_item_cats_features <br/>
 *         描述：类目属性列表 <br/>
 */
@ApiModel(value = "c_item_cats_features", description = "类目属性列表")
public class ItemCatsFeatures implements Serializable {
	// 需要手动添加非默认的serialVersionUID
	@ApiModelProperty(value = "主键")
	protected Long id;// 主键
	@ApiModelProperty(value = "商品所属类目ID")
	protected Long itemCatsCid;// 商品所属类目ID
	@ApiModelProperty(value = "属性键")
	protected String attrKey;// 属性键
	@ApiModelProperty(value = "属性值")
	protected String attrValue;// 属性值

	public ItemCatsFeatures() {
		super();
	}

	public ItemCatsFeatures(Long id) {
		super();
		this.id = id;
	}
	
	@Id// 主键
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getItemCatsCid() {
		return itemCatsCid;
	}
	public void setItemCatsCid(Long itemCatsCid) {
		this.itemCatsCid = itemCatsCid;
	}
	
	public String getAttrKey() {
		return attrKey;
	}
	public void setAttrKey(String attrKey) {
		this.attrKey = attrKey;
	}
	
	public String getAttrValue() {
		return attrValue;
	}
	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}
}
