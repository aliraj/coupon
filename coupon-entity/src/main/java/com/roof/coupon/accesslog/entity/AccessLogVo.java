package com.roof.coupon.accesslog.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_access_log <br/>
 *         描述：用户行为日志 <br/>
 */
public class AccessLogVo extends AccessLog {

	private List<AccessLogVo> accessLogList;

	public AccessLogVo() {
		super();
	}

	public AccessLogVo(Long id) {
		super();
		this.id = id;
	}

	public List<AccessLogVo> getAccessLogList() {
		return accessLogList;
	}

	public void setAccessLogList(List<AccessLogVo> accessLogList) {
		this.accessLogList = accessLogList;
	}

}
