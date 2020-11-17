package me.chanjar.weixin.mp.bean.invoice.merchant;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 开票信息请求参数
 */
@Data
public class MakeOutInvoiceRequest implements Serializable {

  private InvoiceInfo invoiceinfo;

  /**
   * 发票信息
   */
  @Data
  public static class InvoiceInfo implements Serializable {
    /**
     * 维修openid
     */
    private String wxopenid;

    /**
     * 订单号
     */
    private String ddh;

    /**
     * 发票请求流水号，唯一识别开票请求的流水号
     */
    private String fpqqlsh;

    /**
     * 纳税人识别码
     */
    private String nsrsbh;

    /**
     * 纳税人名称
     */
    private String nsrmc;

    /**
     * 纳税人地址
     */
    private String nsrdz;

    /**
     * 纳税人电话
     */
    private String nsrdh;

    /**
     * 纳税人开户行
     */
    private String nsrbank;

    /**
     * 纳税人银行账号
     */
    private String nsrbankid;

    /**
     * 购货方名称
     */
    private String ghfnsrsbh;

    /**
     * 购货方识别号
     */
    private String ghfmc;

    /**
     * 购货方地址
     */
    private String ghfdz;

    /**
     * 购货方电话
     */
    private String ghfdh;

    /**
     * 购货方开户行
     */
    private String ghfbank;

    /**
     * 购货方银行帐号
     */
    private String ghfbankid;

    /**
     * 开票人
     */
    private String kpr;

    /**
     * 收款人
     */
    private String skr;

    /**
     * 复核人
     */
    private String fhr;

    /**
     * 价税合计
     */
    private String jshj;

    /**
     * 合计金额
     */
    private String hjje;

    /**
     * 合计税额
     */
    private String hjse;

    /**
     * 备注
     */
    private String bz;

    /**
     * 行业类型 0 商业 1其它
     */
    private String hylx;

    /**
     * 发票商品条目
     */
    private List<InvoiceDetailItem> invoicedetailList;

  }

  /**
   * 发票条目
   */
  @Data
  public static class InvoiceDetailItem implements Serializable {
    /**
     * 发票性质
     */
    private String fphxz;

    /**
     * 19位税收分类编码
     */
    private String spbm;

    /**
     * 项目名称
     */
    private String xmmc;

    /**
     * 计量单位
     */
    private String dw;

    /**
     * 规格型号
     */
    private String ggxh;

    /**
     * 项目数量
     */
    private String xmsl;

    /**
     * 项目单价
     */
    private String xmdj;

    /**
     * 项目金额 不含税，单位元 两位小数
     */
    private String xmje;

    /**
     * 税率 精确到两位小数 如0.01
     */
    private String sl;

    /**
     * 税额 单位元 两位小数
     */
    private String se;

  }

}
