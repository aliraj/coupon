package com.roof.coupon.api.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 模版生成 <br/>
 *         表名： c_api <br/>
 *         描述：外部接口列表 <br/>
 */
@ApiModel(value = "c_api", description = "外部接口列表")
public class Api implements Serializable {
	// 需要手动添加非默认的serialVersionUID
	@ApiModelProperty(value = "id")
	protected Long id;// id
	@ApiModelProperty(value = "接口名称")
	protected String name;// 接口名称
	@ApiModelProperty(value = "所属平台(字典)")
	protected Long platformId;// 所属平台(字典)
	@ApiModelProperty(value = "版本号")
	protected String version;// 版本号
	@ApiModelProperty(value = "接口地址")
	protected String url;// 接口地址

	public Api() {
		super();
	}

	public Api(Long id) {
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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Long getPlatformId() {
		return platformId;
	}
	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
