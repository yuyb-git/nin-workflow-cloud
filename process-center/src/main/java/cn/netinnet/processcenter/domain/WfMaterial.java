/*
 * WfMaterial.java
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
public class WfMaterial implements Serializable {
    /**
     * 原材料id
     */
    @NotNull(groups = Edit.class, message = "原材料id不可为空")
    private Long materialId;

    /**
     * 账套id
     */
    private Long asId;

    /**
     * 编号
     */
    @NotEmpty(groups = {Add.class, Edit.class}, message = "编号不能为空")
    private String materialSeq;

    /**
     * 名称
     */
    @NotEmpty(groups = {Add.class, Edit.class}, message = "名称不能为空")
    private String materialName;

    /**
     * 型号
     */
    @NotEmpty(groups = Add.class, message = "型号不能为空")
    private String materialModel;

    /**
     * 计价单位
     */
    @NotEmpty(groups = Add.class, message = "计价单位不能为空")
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

    private static final long serialVersionUID = 5332522506968415232L;

    /**
     * 获取原材料id
     *
     * @return material_id - 原材料id
     */
    public Long getMaterialId() {
        return materialId;
    }

    /**
     * 设置原材料id
     *
     * @param materialId 原材料id
     */
    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
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
     * @return material_seq - 编号
     */
    public String getMaterialSeq() {
        return materialSeq;
    }

    /**
     * 设置编号
     *
     * @param materialSeq 编号
     */
    public void setMaterialSeq(String materialSeq) {
        this.materialSeq = materialSeq;
    }

    /**
     * 获取名称
     *
     * @return material_name - 名称
     */
    public String getMaterialName() {
        return materialName;
    }

    /**
     * 设置名称
     *
     * @param materialName 名称
     */
    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    /**
     * 获取型号
     *
     * @return material_model - 型号
     */
    public String getMaterialModel() {
        return materialModel;
    }

    /**
     * 设置型号
     *
     * @param materialModel 型号
     */
    public void setMaterialModel(String materialModel) {
        this.materialModel = materialModel;
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
        sb.append(", materialId=").append(materialId);
        sb.append(", asId=").append(asId);
        sb.append(", materialSeq=").append(materialSeq);
        sb.append(", materialName=").append(materialName);
        sb.append(", materialModel=").append(materialModel);
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