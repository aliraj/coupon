package com.roof.coupon.outerapi;

import com.roof.coupon.itemcats.entity.ItemCats;

import java.util.List;

/**
 * 商品类目
 *
 * @author liuxin
 * @since 2018/4/1
 */
public interface ItemCatsService {

    List<ItemCats> queryByParent(long parentCid);

}
