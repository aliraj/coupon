package com.roof.coupon.searchconfig.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 *         表名： c_search_config <br/>
 *         描述：搜索配置 <br/>
 */
@ApiModel(value = "c_search_config", description = "搜索配置")
public class SearchConfig implements Serializable {
	// 需要手动添加非默认的serialVersionUID
	@ApiModelProperty(value = "id")
	protected Long id;// id
	@ApiModelProperty(value = "标题")
	protected String title;// 标题
	@ApiModelProperty(value = "副标题")
	protected String subtitle;// 副标题
	@ApiModelProperty(value = "图片地址")
	protected String img;// 图片地址

	public SearchConfig() {
		super();
	}

	public SearchConfig(Long id) {
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
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
}
