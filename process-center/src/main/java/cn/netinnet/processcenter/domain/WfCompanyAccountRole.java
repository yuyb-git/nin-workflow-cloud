/*
 * WfCompanyAccountRole.java
 * Copyright(c) 2017-2018 厦门网中网软件有限公司
 * All right reserved.
 * 2021-04-19 Created
 */
package cn.netinnet.processcenter.domain;

import java.io.Serializable;

/**
 * @author admin
 * @date   2021-04-19
 **/
public class WfCompanyAccountRole implements Serializable {
    /**
     * 企业id
     */
    private Long companyId;

    /**
     * 收取发票
     */
    private Long invoiceCollect;

    /**
     * 开具发票
     */
    private Long invoiceOpen;

    /**
     * 生成凭证
     */
    private Long voucherGenerate;

    /**
     * 凭证审核
     */
    private Long voucherAudit;

    private static final long serialVersionUID = 7610351580655586304L;

    /**
     * 获取企业id
     *
     * @return company_id - 企业id
     */
    public Long getCompanyId() {
        return companyId;
    }

    /**
     * 设置企业id
     *
     * @param companyId 企业id
     */
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取收取发票
     *
     * @return invoice_collect - 收取发票
     */
    public Long getInvoiceCollect() {
        return invoiceCollect;
    }

    /**
     * 设置收取发票
     *
     * @param invoiceCollect 收取发票
     */
    public void setInvoiceCollect(Long invoiceCollect) {
        this.invoiceCollect = invoiceCollect;
    }

    /**
     * 获取开具发票
     *
     * @return invoice_open - 开具发票
     */
    public Long getInvoiceOpen() {
        return invoiceOpen;
    }

    /**
     * 设置开具发票
     *
     * @param invoiceOpen 开具发票
     */
    public void setInvoiceOpen(Long invoiceOpen) {
        this.invoiceOpen = invoiceOpen;
    }

    /**
     * 获取生成凭证
     *
     * @return voucher_generate - 生成凭证
     */
    public Long getVoucherGenerate() {
        return voucherGenerate;
    }

    /**
     * 设置生成凭证
     *
     * @param voucherGenerate 生成凭证
     */
    public void setVoucherGenerate(Long voucherGenerate) {
        this.voucherGenerate = voucherGenerate;
    }

    /**
     * 获取凭证审核
     *
     * @return voucher_audit - 凭证审核
     */
    public Long getVoucherAudit() {
        return voucherAudit;
    }

    /**
     * 设置凭证审核
     *
     * @param voucherAudit 凭证审核
     */
    public void setVoucherAudit(Long voucherAudit) {
        this.voucherAudit = voucherAudit;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", companyId=").append(companyId);
        sb.append(", invoiceCollect=").append(invoiceCollect);
        sb.append(", invoiceOpen=").append(invoiceOpen);
        sb.append(", voucherGenerate=").append(voucherGenerate);
        sb.append(", voucherAudit=").append(voucherAudit);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}