package com.roof.coupon.accesslog.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.roof.coupon.itemcoupon.entity.ItemCoupon;
import com.roof.coupon.itemcoupon.entity.ItemCouponVo;
import com.roof.coupon.itemcoupon.service.api.IItemCouponService;
import org.roof.roof.dataaccess.api.Page;
import com.roof.coupon.accesslog.dao.api.IAccessLogDao;
import com.roof.coupon.accesslog.entity.AccessLog;
import com.roof.coupon.accesslog.entity.AccessLogVo;
import com.roof.coupon.accesslog.service.api.IAccessLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AccessLogService implements IAccessLogService {
    private IAccessLogDao accessLogDao;

    @Autowired
    private IItemCouponService itemCouponService;

    @Override
    public Serializable save(AccessLog accessLog) {
        return accessLogDao.save(accessLog);
    }

    @Override
    public void delete(AccessLog accessLog) {
        accessLogDao.delete(accessLog);
    }

    @Override
    public void deleteByExample(AccessLog accessLog) {
        accessLogDao.deleteByExample(accessLog);
    }

    @Override
    public void update(AccessLog accessLog) {
        accessLogDao.update(accessLog);
    }

    @Override
    public void updateIgnoreNull(AccessLog accessLog) {
        accessLogDao.updateIgnoreNull(accessLog);
    }

    @Override
    public void updateByExample(AccessLog accessLog) {
        accessLogDao.update("updateByExampleAccessLog", accessLog);
    }

    @Override
    public AccessLogVo load(AccessLog accessLog) {
        return (AccessLogVo) accessLogDao.reload(accessLog);
    }

    @Override
    public AccessLogVo selectForObject(AccessLog accessLog) {
        return (AccessLogVo) accessLogDao.selectForObject("selectAccessLog", accessLog);
    }

    @Override
    public List<AccessLogVo> selectForList(AccessLog accessLog) {
        return (List<AccessLogVo>) accessLogDao.selectForList("selectAccessLog", accessLog);
    }

    @Override
    public Page page(Page page, AccessLog accessLog) {
        return accessLogDao.page(page, accessLog);
    }

    @Override
    public Page pageWechat(Page page, AccessLog accessLog) {
        page = accessLogDao.pageGroupby(page, accessLog);
        List<AccessLogVo> list = (List<AccessLogVo>) page.getDataList();
        List<AccessLogVo> outlist = new ArrayList<AccessLogVo>();
        for (AccessLogVo vo :
                list) {
            if (vo.getParams() != null) {
                if (JSON.parse(vo.getParams()) instanceof JSONArray) {
                    JSONArray array = JSON.parseArray(vo.getParams());
                    JSONObject obj = array.getJSONObject(0);
                    Long numIid = obj.getLong("numIid");
                    ItemCoupon itemCoupon = new ItemCoupon(numIid);
                    ItemCouponVo itemCouponVo = itemCouponService.load(itemCoupon);
                    vo.setItemCouponVo(itemCouponVo);
                    if (vo.getItemCouponVo() != null) {
                        outlist.add(vo);
                    }
                } else if (JSON.parse(vo.getParams()) instanceof JSONObject) {

                }
            }
        }
        page.setDataList(outlist);
        return page;
    }


    @Autowired
    public void setIAccessLogDao(
            @Qualifier("accessLogDao") IAccessLogDao accessLogDao
    ) {
        this.accessLogDao = accessLogDao;
    }


}
