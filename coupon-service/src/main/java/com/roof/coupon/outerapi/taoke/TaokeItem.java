package com.roof.coupon.outerapi.taoke;

import java.util.Date;

/**
 * @author liuxin
 * @since 2018/4/14
 */
public class TaokeItem {
    private String id;
    private String goods_name; //名称
    private String goods_id; //淘宝id
    private String cate_id; //分类id
    private String cate_name; //分类名称
    private String goods_url; //商品URL
    private String pic; //商品图片
    private String video_url; //视频链接
    private String price; //在线售价
    private String price_after_coupons; //券后售价
    private String price_coupons; //券的价格
    private String sales; //销售量
    private String rate; //佣金比例
    private String commission_type; //佣金类型
    private String commission_type_name; //佣金类型名称
    private String quan_link; //领券链接
    private String quan_shengyu; //领券的剩余
    private String quan_zhong; // 领券的总数
    private String quan_ling; //领了多少
    private String quan_expired_time; //领券结束时间
    private String quan_note; //领券的备注
    private String quan_guid_content; //领的导购文案
    private String quan_qq_img; //QQ群发时候用的图片
    private String src; //是否天猫，0否1是
    private String jhs; //是否聚划算商品，0否1是
    private String tqg; //是否淘抢购，0否1是
    private String gold; //是否金牌卖家，0否1是
    private String freight; //是否赠运费险，0否1是
    private String abroad; //是否海淘商品，0否1是
    private String flagship; //是否旗舰店，0否1是

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getCate_id() {
        return cate_id;
    }

    public void setCate_id(String cate_id) {
        this.cate_id = cate_id;
    }

    public String getCate_name() {
        return cate_name;
    }

    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }

    public String getGoods_url() {
        return goods_url;
    }

    public void setGoods_url(String goods_url) {
        this.goods_url = goods_url;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice_after_coupons() {
        return price_after_coupons;
    }

    public void setPrice_after_coupons(String price_after_coupons) {
        this.price_after_coupons = price_after_coupons;
    }

    public String getPrice_coupons() {
        return price_coupons;
    }

    public void setPrice_coupons(String price_coupons) {
        this.price_coupons = price_coupons;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getCommission_type() {
        return commission_type;
    }

    public void setCommission_type(String commission_type) {
        this.commission_type = commission_type;
    }

    public String getCommission_type_name() {
        return commission_type_name;
    }

    public void setCommission_type_name(String commission_type_name) {
        this.commission_type_name = commission_type_name;
    }

    public String getQuan_link() {
        return quan_link;
    }

    public void setQuan_link(String quan_link) {
        this.quan_link = quan_link;
    }

    public String getQuan_shengyu() {
        return quan_shengyu;
    }

    public void setQuan_shengyu(String quan_shengyu) {
        this.quan_shengyu = quan_shengyu;
    }

    public String getQuan_zhong() {
        return quan_zhong;
    }

    public void setQuan_zhong(String quan_zhong) {
        this.quan_zhong = quan_zhong;
    }

    public String getQuan_ling() {
        return quan_ling;
    }

    public void setQuan_ling(String quan_ling) {
        this.quan_ling = quan_ling;
    }

    public String getQuan_expired_time() {
        return quan_expired_time;
    }

    public void setQuan_expired_time(String quan_expired_time) {
        this.quan_expired_time = quan_expired_time;
    }

    public String getQuan_note() {
        return quan_note;
    }

    public void setQuan_note(String quan_note) {
        this.quan_note = quan_note;
    }

    public String getQuan_guid_content() {
        return quan_guid_content;
    }

    public void setQuan_guid_content(String quan_guid_content) {
        this.quan_guid_content = quan_guid_content;
    }

    public String getQuan_qq_img() {
        return quan_qq_img;
    }

    public void setQuan_qq_img(String quan_qq_img) {
        this.quan_qq_img = quan_qq_img;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getJhs() {
        return jhs;
    }

    public void setJhs(String jhs) {
        this.jhs = jhs;
    }

    public String getTqg() {
        return tqg;
    }

    public void setTqg(String tqg) {
        this.tqg = tqg;
    }

    public String getGold() {
        return gold;
    }

    public void setGold(String gold) {
        this.gold = gold;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String getAbroad() {
        return abroad;
    }

    public void setAbroad(String abroad) {
        this.abroad = abroad;
    }

    public String getFlagship() {
        return flagship;
    }

    public void setFlagship(String flagship) {
        this.flagship = flagship;
    }
}
