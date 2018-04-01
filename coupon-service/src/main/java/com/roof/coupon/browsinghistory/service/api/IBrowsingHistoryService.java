package com.roof.coupon.browsinghistory.service.api;

import java.util.List;
import java.io.Serializable;

import org.roof.roof.dataaccess.api.Page;
import com.roof.coupon.browsinghistory.entity.BrowsingHistory;
import com.roof.coupon.browsinghistory.entity.BrowsingHistoryVo;

public interface IBrowsingHistoryService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(BrowsingHistory browsingHistory);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(BrowsingHistory browsingHistory);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(BrowsingHistory browsingHistory);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(BrowsingHistory browsingHistory);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(BrowsingHistory browsingHistory);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(BrowsingHistory browsingHistory);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract BrowsingHistoryVo load(BrowsingHistory browsingHistory);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract BrowsingHistoryVo selectForObject(BrowsingHistory browsingHistory);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<BrowsingHistoryVo> selectForList(BrowsingHistory browsingHistory);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, BrowsingHistory browsingHistory);

}