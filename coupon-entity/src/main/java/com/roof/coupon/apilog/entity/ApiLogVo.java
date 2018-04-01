package com.roof.coupon.apilog.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_api_log <br/>
 *         描述：接口日志 <br/>
 */
public class ApiLogVo extends ApiLog {

	private List<ApiLogVo> apiLogList;

	public ApiLogVo() {
		super();
	}

	public ApiLogVo(Long id) {
		super();
		this.id = id;
	}

	public List<ApiLogVo> getApiLogList() {
		return apiLogList;
	}

	public void setApiLogList(List<ApiLogVo> apiLogList) {
		this.apiLogList = apiLogList;
	}

}
