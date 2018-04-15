package com.roof.coupon.outerapi.jingtuitui;

/**
 * @author liuxin
 * @since 2018/4/14
 */
public class JingtuituiItem {
    private String goods_id; //商品SKU
    private String goods_name; //名称
    private String goods_link; //商品链接
    private String goods_img; //商品主图
    private String goods_price; //商品原价
    private String coupon_price; //商品最终价格
    private String discount_start; //优惠券开始时间(时间戳)
    private String discount_end; //优惠券到期时间(时间戳)
    private String discount_price; //优惠券面额
    private String discount_link; //领券链接
    private String goods_content; //商品文案
    private String goods_type; //商品类别
    private String commission; //商品佣金比例

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_link() {
        return goods_link;
    }

    public void setGoods_link(String goods_link) {
        this.goods_link = goods_link;
    }

    public String getGoods_img() {
        return goods_img;
    }

    public void setGoods_img(String goods_img) {
        this.goods_img = goods_img;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public String getCoupon_price() {
        return coupon_price;
    }

    public void setCoupon_price(String coupon_price) {
        this.coupon_price = coupon_price;
    }

    public String getDiscount_start() {
        return discount_start;
    }

    public void setDiscount_start(String discount_start) {
        this.discount_start = discount_start;
    }

    public String getDiscount_end() {
        return discount_end;
    }

    public void setDiscount_end(String discount_end) {
        this.discount_end = discount_end;
    }

    public String getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(String discount_price) {
        this.discount_price = discount_price;
    }

    public String getDiscount_link() {
        return discount_link;
    }

    public void setDiscount_link(String discount_link) {
        this.discount_link = discount_link;
    }

    public String getGoods_content() {
        return goods_content;
    }

    public void setGoods_content(String goods_content) {
        this.goods_content = goods_content;
    }

    public String getGoods_type() {
        return goods_type;
    }

    public void setGoods_type(String goods_type) {
        this.goods_type = goods_type;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }
}
