package com.roof.coupon.browsinghistory.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 *         表名： c_browsing_history <br/>
 *         描述：浏览记录 <br/>
 */
@ApiModel(value = "c_browsing_history", description = "浏览记录")
public class BrowsingHistory implements Serializable {
	// 需要手动添加非默认的serialVersionUID
	@ApiModelProperty(value = "id")
	protected Long id;// id
	@ApiModelProperty(value = "优惠券ID")
	protected Long itemCouponId;// 优惠券ID
	@ApiModelProperty(value = "用户ID")
	protected Long customerId;// 用户ID

	public BrowsingHistory() {
		super();
	}

	public BrowsingHistory(Long id) {
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
	
	public Long getItemCouponId() {
		return itemCouponId;
	}
	public void setItemCouponId(Long itemCouponId) {
		this.itemCouponId = itemCouponId;
	}
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
}
