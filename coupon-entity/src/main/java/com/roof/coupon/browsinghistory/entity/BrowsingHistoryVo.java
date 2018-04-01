package com.roof.coupon.browsinghistory.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_browsing_history <br/>
 *         描述：浏览记录 <br/>
 */
public class BrowsingHistoryVo extends BrowsingHistory {

	private List<BrowsingHistoryVo> browsingHistoryList;

	public BrowsingHistoryVo() {
		super();
	}

	public BrowsingHistoryVo(Long id) {
		super();
		this.id = id;
	}

	public List<BrowsingHistoryVo> getBrowsingHistoryList() {
		return browsingHistoryList;
	}

	public void setBrowsingHistoryList(List<BrowsingHistoryVo> browsingHistoryList) {
		this.browsingHistoryList = browsingHistoryList;
	}

}
