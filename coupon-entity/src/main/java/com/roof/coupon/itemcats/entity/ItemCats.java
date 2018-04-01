package com.roof.coupon.itemcats.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 *         表名： c_item_cats <br/>
 *         描述：标准商品类目 <br/>
 */
@ApiModel(value = "c_item_cats", description = "标准商品类目")
public class ItemCats implements Serializable {
	// 需要手动添加非默认的serialVersionUID
	@ApiModelProperty(value = "商品所属类目ID")
	protected Long cid;// 商品所属类目ID
	@ApiModelProperty(value = "父类目,ID=0时，代表的是一级的类目")
	protected Long parentCid;// 父类目,ID=0时，代表的是一级的类目
	@ApiModelProperty(value = "类目名称")
	protected String name;// 类目名称
	@ApiModelProperty(value = "该类目是否为父类目(即：该类目是否还有子类目)")
	protected String isParent;// 该类目是否为父类目(即：该类目是否还有子类目)
	@ApiModelProperty(value = "状态。可选值:normal(正常),deleted(删除)")
	protected String status;// 状态。可选值:normal(正常),deleted(删除)
	@ApiModelProperty(value = "排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数 ")
	protected Integer sortOrder;// 排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
	@ApiModelProperty(value = "是否度量衡类目")
	protected String taosirCat;// 是否度量衡类目

	public ItemCats() {
		super();
	}

	public ItemCats(Long cid) {
		super();
		this.cid = cid;
	}
	
	@Id// 主键
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	
	public Long getParentCid() {
		return parentCid;
	}
	public void setParentCid(Long parentCid) {
		this.parentCid = parentCid;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getIsParent() {
		return isParent;
	}
	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	public String getTaosirCat() {
		return taosirCat;
	}
	public void setTaosirCat(String taosirCat) {
		this.taosirCat = taosirCat;
	}
}
