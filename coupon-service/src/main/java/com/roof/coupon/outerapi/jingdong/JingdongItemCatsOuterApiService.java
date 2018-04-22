package com.roof.coupon.outerapi.jingdong;

import com.roof.coupon.itemcats.dao.impl.ItemCatsDao;
import com.roof.coupon.itemcats.entity.ItemCats;
import com.roof.coupon.outerapi.ItemCatsOuterApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author liuxin
 * @since 2018/4/1
 */
@Component
public class JingdongItemCatsOuterApiService implements ItemCatsOuterApiService, InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(JingdongItemCatsOuterApiService.class);

    private ItemCatsDao itemCatsDao;
    private JingdongClientManager jingdongClientManager;


    @Override
    public void afterPropertiesSet() throws Exception {
    }

    @Override
    public List<ItemCats> queryByParent(long parentCid) {
        return queryByParent(parentCid, 0);
    }


    private List<ItemCats> queryByParent(long parentCid, int grade) {
//        JdClient client = jingdongClientManager.getInstance();
//        UnionSearchGoodsCategoryQueryRequest request = new UnionSearchGoodsCategoryQueryRequest();
//        request.setParentId((int) parentCid);
//        request.setGrade(grade);
//        List<ItemCats> result = new ArrayList<>();
//        try {
//            UnionSearchGoodsCategoryQueryResponse response = client.execute(request);
//            LOGGER.info(JSON.toJSONString(new LogBean(LogBean.PLATFORM_JINGDONG, "jingdong.union.search.goods.category.query", request, response)));
//            String itemCatsStr = response.getQuerygoodscategoryResult();
//            JSONObject jsonObject = JSON.parseObject(itemCatsStr);
//            if (jsonObject.getInteger("resultCode") == 1) {
//                JSONArray data = jsonObject.getJSONArray("data");
//                for (Object datum : data) {
//                    JSONObject itemJSONObject = (JSONObject) datum;
//                    ItemCats itemCats = new ItemCats();
//                    int id = itemJSONObject.getInteger("id");
//                    itemCats.setCid((long) id);
//                    itemCats.setName(itemJSONObject.getString("name"));
//                    int parentId = itemJSONObject.getInteger("parentId");
//                    itemCats.setParentCid((long) parentId);
//                    result.add(itemCats);
//                    List<ItemCats> subs = queryByParent(id, grade + 1);
//                    if (subs == null || subs.size() == 0) {
//                        itemCats.setIsParent("0");
//                    } else {
//                        itemCats.setIsParent("1");
//                    }
//                    itemCatsDao.save(itemCats);
//                }
//            }
//            return result;
//        } catch (JdException e) {
//            LOGGER.error(JSON.toJSONString(new LogBean(LogBean.PLATFORM_JINGDONG, "jingdong.union.search.goods.category.query", request, e.getErrMsg())));
//        }
//        return result;
        return null;
    }


    @Autowired
    public void setItemCatsDao(ItemCatsDao itemCatsDao) {
        this.itemCatsDao = itemCatsDao;
    }

    @Autowired
    public void setJingdongClientManager(JingdongClientManager jingdongClientManager) {
        this.jingdongClientManager = jingdongClientManager;
    }
}
