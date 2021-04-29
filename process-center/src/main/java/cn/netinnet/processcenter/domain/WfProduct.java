/*
 * WfProduct.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2021-04-20 Created
 */
package cn.netinnet.processcenter.domain;

import cn.netinnet.processcenter.domain.group.Add;
import cn.netinnet.processcenter.domain.group.Edit;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author admin
 * @date   2021-04-20
 **/
public class WfProduct implements Serializable {
    /**
     * 产品id
     */
    @NotNull(groups = Edit.class, message = "产品id不可为空")
    private Long productId;

    /**
     * 账套id
     */
    private Long asId;

    /**
     * 编号
     */
    @NotEmpty(groups = {Add.class, Edit.class}, message = "编号不可为空")
    private String productSeq;

    /**
     * 名称
     */
    @NotEmpty(groups = {Add.class, Edit.class}, message = "名称不可为空")
    private String productName;

    /**
     * 型号
     */
    @NotEmpty(groups = Add.class, message = "型号不可为空")
    private String productModel;

    /**
     * 计价单位
     */
    @NotEmpty(groups = Add.class, message = "计价单位不可为空")
    private String unit;

    /**
     * 基准价
     */
    @NotNull(groups = Add.class, message = "基准价不可为空")
    private BigDecimal basePrice;

    /**
     * 单价
     */
    @NotNull(groups = Add.class, message = "单价不可为空")
    private BigDecimal price;

    /**
     * 数量
     */
    @NotNull(groups = Add.class, message = "数量不可为空")
    private Integer number;

    /**
     * 创建人
     */
    private Long createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private Long modifyUserId;

    /**
     * 修改时间
     */
    private Date modifyTime;

    private static final long serialVersionUID = 9058574596144401408L;

    /**
     * 获取产品id
     *
     * @return product_id - 产品id
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * 设置产品id
     *
     * @param productId 产品id
     */
    public void setProductId(Long productId) {
        this.productId = productId;
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
     * 获取编号
     *
     * @return product_seq - 编号
     */
    public String getProductSeq() {
        return productSeq;
    }

    /**
     * 设置编号
     *
     * @param productSeq 编号
     */
    public void setProductSeq(String productSeq) {
        this.productSeq = productSeq;
    }

    /**
     * 获取名称
     *
     * @return product_name - 名称
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 设置名称
     *
     * @param productName 名称
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 获取型号
     *
     * @return product_model - 型号
     */
    public String getProductModel() {
        return productModel;
    }

    /**
     * 设置型号
     *
     * @param productModel 型号
     */
    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    /**
     * 获取计价单位
     *
     * @return unit - 计价单位
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 设置计价单位
     *
     * @param unit 计价单位
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * 获取基准价
     *
     * @return base_price - 基准价
     */
    public BigDecimal getBasePrice() {
        return basePrice;
    }

    /**
     * 设置基准价
     *
     * @param basePrice 基准价
     */
    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    /**
     * 获取单价
     *
     * @return price - 单价
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置单价
     *
     * @param price 单价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取数量
     *
     * @return number - 数量
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置数量
     *
     * @param number 数量
     */
    public void setNumber(Integer number) {
        this.number = number;
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
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改人
     *
     * @return modify_user_id - 修改人
     */
    public Long getModifyUserId() {
        return modifyUserId;
    }

    /**
     * 设置修改人
     *
     * @param modifyUserId 修改人
     */
    public void setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", productId=").append(productId);
        sb.append(", asId=").append(asId);
        sb.append(", productSeq=").append(productSeq);
        sb.append(", productName=").append(productName);
        sb.append(", productModel=").append(productModel);
        sb.append(", unit=").append(unit);
        sb.append(", basePrice=").append(basePrice);
        sb.append(", price=").append(price);
        sb.append(", number=").append(number);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyUserId=").append(modifyUserId);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}