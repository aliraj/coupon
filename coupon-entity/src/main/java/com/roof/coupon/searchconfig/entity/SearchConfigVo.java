package com.roof.coupon.searchconfig.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_search_config <br/>
 *         描述：搜索配置 <br/>
 */
public class SearchConfigVo extends SearchConfig {

	private List<SearchConfigVo> searchConfigList;

	public SearchConfigVo() {
		super();
	}

	public SearchConfigVo(Long id) {
		super();
		this.id = id;
	}

	public List<SearchConfigVo> getSearchConfigList() {
		return searchConfigList;
	}

	public void setSearchConfigList(List<SearchConfigVo> searchConfigList) {
		this.searchConfigList = searchConfigList;
	}

}
