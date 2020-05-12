package me.chanjar.weixin.mp.bean.invoice.merchant;

import lombok.Data;

import java.io.Serializable;

/**
 * 电子发票信息查询结果
 */
@Data
public class InvoiceResult implements Serializable {

  /**
   * 发票相关信息
   */
  private InvoiceDetail invoicedetail;

  @Data
  public static class InvoiceDetail implements Serializable {
    /**
     * 发票流水号
     */
    private String fpqqlsh;

    /**
     * 检验码
     */
    private String jym;

    /**
     * 校验码
     */
    private String kprq;

    /**
     * 发票代码
     */
    private String fpdm;

    /**
     * 发票号码
     */
    private String fphm;

    /**
     * 发票url
     */
    private String pdfurl;

  }

}
