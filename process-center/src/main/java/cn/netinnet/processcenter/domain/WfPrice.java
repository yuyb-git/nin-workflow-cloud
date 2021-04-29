/*
 * WfPrice.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2021-04-22 Created
 */
package cn.netinnet.processcenter.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author admin
 * @date   2021-04-22
 **/
public class WfPrice implements Serializable {
    /**
     * 价格id
     */
    private Long priceId;

    /**
     * 账套id
     */
    private Long asId;

    /**
     * 价格类型：1原材料，2产品，3办公用品
     */
    private Integer priceType;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 商户id
     */
    private Long merchantId;

    /**
     * 创建人
     */
    private Long createUserId;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 价格数据
     */
    private String priceArray;

    private static final long serialVersionUID = 3068749296276086784L;

    /**
     * 获取价格id
     *
     * @return price_id - 价格id
     */
    public Long getPriceId() {
        return priceId;
    }

    /**
     * 设置价格id
     *
     * @param priceId 价格id
     */
    public void setPriceId(Long priceId) {
        this.priceId = priceId;
    }

    /**
     * 获取账套id
     *
     * @return as_id - 账套id
     */
    public Long getAsId() {
        return asId;
    }

    /**
     * 设置账套id
     *
     * @param asId 账套id
     */
    public void setAsId(Long asId) {
        this.asId = asId;
    }

    /**
     * 获取价格类型：1原材料，2产品，3办公用品
     *
     * @return price_type - 价格类型：1原材料，2产品，3办公用品
     */
    public Integer getPriceType() {
        return priceType;
    }

    /**
     * 设置价格类型：1原材料，2产品，3办公用品
     *
     * @param priceType 价格类型：1原材料，2产品，3办公用品
     */
    public void setPriceType(Integer priceType) {
        this.priceType = priceType;
    }

    /**
     * 获取商品id
     *
     * @return goods_id - 商品id
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * 设置商品id
     *
     * @param goodsId 商品id
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取商户id
     *
     * @return merchant_id - 商户id
     */
    public Long getMerchantId() {
        return merchantId;
    }

    /**
     * 设置商户id
     *
     * @param merchantId 商户id
     */
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * 获取创建人
     *
     * @return create_user_id - 创建人
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置创建人
     *
     * @param createUserId 创建人
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取修改时间
     *
     * @return modify_time - 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置修改时间
     *
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 获取价格数据
     *
     * @return price_array - 价格数据
     */
    public String getPriceArray() {
        return priceArray;
    }

    /**
     * 设置价格数据
     *
     * @param priceArray 价格数据
     */
    public void setPriceArray(String priceArray) {
        this.priceArray = priceArray;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", priceId=").append(priceId);
        sb.append(", asId=").append(asId);
        sb.append(", priceType=").append(priceType);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", merchantId=").append(merchantId);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", priceArray=").append(priceArray);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}