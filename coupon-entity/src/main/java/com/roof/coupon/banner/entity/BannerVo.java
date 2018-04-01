package com.roof.coupon.banner.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_banner <br/>
 *         描述：推广位 <br/>
 */
public class BannerVo extends Banner {

	private List<BannerVo> bannerList;

	public BannerVo() {
		super();
	}

	public BannerVo(Long id) {
		super();
		this.id = id;
	}

	public List<BannerVo> getBannerList() {
		return bannerList;
	}

	public void setBannerList(List<BannerVo> bannerList) {
		this.bannerList = bannerList;
	}

}
