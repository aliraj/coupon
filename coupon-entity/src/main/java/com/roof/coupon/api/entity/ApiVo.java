package com.roof.coupon.api.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_api <br/>
 *         描述：外部接口列表 <br/>
 */
public class ApiVo extends Api {

	private List<ApiVo> apiList;

	public ApiVo() {
		super();
	}

	public ApiVo(Long id) {
		super();
		this.id = id;
	}

	public List<ApiVo> getApiList() {
		return apiList;
	}

	public void setApiList(List<ApiVo> apiList) {
		this.apiList = apiList;
	}

}
