package com.roof.coupon.itemcoupon.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 *         表名： c_item_coupon <br/>
 *         描述：商品优惠券 <br/>
 */
@ApiModel(value = "c_item_coupon", description = "商品优惠券")
public class ItemCoupon implements Serializable {
	// 需要手动添加非默认的serialVersionUID
	@ApiModelProperty(value = "itemId")
	protected Long numIid;// itemId
	@ApiModelProperty(value = "商品标题")
	protected String title;// 商品标题
	@ApiModelProperty(value = "商品小图列表")
	protected String smallImages;// 商品小图列表
	@ApiModelProperty(value = "店铺名称")
	protected String shopTitle;// 店铺名称
	@ApiModelProperty(value = "卖家类型，0表示集市，1表示商城")
	protected Integer userType;// 卖家类型，0表示集市，1表示商城
	@ApiModelProperty(value = "折扣价")
	protected String zkFinalPrice;// 折扣价
	@ApiModelProperty(value = "卖家昵称")
	protected String nick;// 卖家昵称
	@ApiModelProperty(value = "卖家id")
	protected Long sellerId;// 卖家id
	@ApiModelProperty(value = "30天销量")
	protected Integer volume;// 30天销量
	@ApiModelProperty(value = "商品主图")
	protected String pictUrl;// 商品主图
	@ApiModelProperty(value = "商品详情页链接地址")
	protected String itemUrl;// 商品详情页链接地址
	@ApiModelProperty(value = "优惠券总量")
	protected Integer couponTotalCount;// 优惠券总量
	@ApiModelProperty(value = "佣金比率(%)")
	protected String commissionRate;// 佣金比率(%)
	@ApiModelProperty(value = "优惠券面额")
	protected String couponInfo;// 优惠券面额
	@ApiModelProperty(value = "后台一级类目")
	protected Long category;// 后台一级类目
	@ApiModelProperty(value = "优惠券剩余量")
	protected Integer couponRemainCount;// 优惠券剩余量
	@ApiModelProperty(value = "优惠券开始时间")
	protected String couponStartTime;// 优惠券开始时间
	@ApiModelProperty(value = "优惠券结束时间")
	protected String couponEndTime;// 优惠券结束时间
	@ApiModelProperty(value = "商品优惠券推广链接")
	protected String couponClickUrl;// 商品优惠券推广链接
	@ApiModelProperty(value = "宝贝描述（推荐理由）")
	protected String itemDescription;// 宝贝描述（推荐理由）

	public ItemCoupon() {
		super();
	}

	public ItemCoupon(Long numIid) {
		super();
		this.numIid = numIid;
	}
	
	@Id// 主键
	public Long getNumIid() {
		return numIid;
	}
	public void setNumIid(Long numIid) {
		this.numIid = numIid;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getSmallImages() {
		return smallImages;
	}
	public void setSmallImages(String smallImages) {
		this.smallImages = smallImages;
	}
	
	public String getShopTitle() {
		return shopTitle;
	}
	public void setShopTitle(String shopTitle) {
		this.shopTitle = shopTitle;
	}
	
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
	public String getZkFinalPrice() {
		return zkFinalPrice;
	}
	public void setZkFinalPrice(String zkFinalPrice) {
		this.zkFinalPrice = zkFinalPrice;
	}
	
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	
	public Long getSellerId() {
		return sellerId;
	}
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}
	
	public Integer getVolume() {
		return volume;
	}
	public void setVolume(Integer volume) {
		this.volume = volume;
	}
	
	public String getPictUrl() {
		return pictUrl;
	}
	public void setPictUrl(String pictUrl) {
		this.pictUrl = pictUrl;
	}
	
	public String getItemUrl() {
		return itemUrl;
	}
	public void setItemUrl(String itemUrl) {
		this.itemUrl = itemUrl;
	}
	
	public Integer getCouponTotalCount() {
		return couponTotalCount;
	}
	public void setCouponTotalCount(Integer couponTotalCount) {
		this.couponTotalCount = couponTotalCount;
	}
	
	public String getCommissionRate() {
		return commissionRate;
	}
	public void setCommissionRate(String commissionRate) {
		this.commissionRate = commissionRate;
	}
	
	public String getCouponInfo() {
		return couponInfo;
	}
	public void setCouponInfo(String couponInfo) {
		this.couponInfo = couponInfo;
	}
	
	public Long getCategory() {
		return category;
	}
	public void setCategory(Long category) {
		this.category = category;
	}
	
	public Integer getCouponRemainCount() {
		return couponRemainCount;
	}
	public void setCouponRemainCount(Integer couponRemainCount) {
		this.couponRemainCount = couponRemainCount;
	}
	
	public String getCouponStartTime() {
		return couponStartTime;
	}
	public void setCouponStartTime(String couponStartTime) {
		this.couponStartTime = couponStartTime;
	}
	
	public String getCouponEndTime() {
		return couponEndTime;
	}
	public void setCouponEndTime(String couponEndTime) {
		this.couponEndTime = couponEndTime;
	}
	
	public String getCouponClickUrl() {
		return couponClickUrl;
	}
	public void setCouponClickUrl(String couponClickUrl) {
		this.couponClickUrl = couponClickUrl;
	}
	
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
}
