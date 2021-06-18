package me.chanjar.weixin.mp.bean.invoice.reimburse;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * <pre>
 * 用户可在发票票面看到的主要信息
 * </pre>
 * @author <a href="https://github.com/mr-xiaoyu">xiaoyu</a>
 * @since 2021-03-23
 */
@Data
public class InvoiceUserInfo {

  /**
   * 发票加税合计金额，以分为单位
   */
  private Integer fee;

  /**
   * 发票的抬头
   */
  private String title;

  /**
   * 开票时间
   */
  @SerializedName("billing_time")
  private Integer billingTime;

  /**
   * 发票代码
   */
  @SerializedName("billing_no")
  private String billingNo;

  /**
   * 发票号码
   */
  @SerializedName("billing_code")
  private String billingCode;

  /**
   * 不含税金额，以分为单位
   */
  @SerializedName("fee_without_tax")
  private Integer feeWithoutTax;

  /**
   * 税额,以分为单位
   */
  private Integer tax;

  /**
   * 发票对应的PDF_URL
   */
  @SerializedName("pdf_url")
  private String pdfUrl;

  /**
   * 其它消费凭证附件对应的URL
   */
  @SerializedName("trip_pdf_url")
  private String tripPdfUrl;

  /**
   * 发票报销状态
   */
  @SerializedName("reimburse_status")
  private String reimburseStatus;

  /**
   * 校验码
   */
  @SerializedName("check_code")
  private String checkCode;

  /**
   * 购买方纳税人识别号
   */
  @SerializedName("buyer_number")
  private String buyerNumber;

  /**
   * 购买方地址、电话
   */
  @SerializedName("buyer_address_and_phone")
  private String buyerAddressAndPhone;

  /**
   * 购买方开户行及账号
   */
  @SerializedName("buyer_bank_account")
  private String buyerBankAccount;

  /**
   * 销售方纳税人识别号
   */
  @SerializedName("seller_number")
  private String sellerNumber;

  /**
   * 销售方地址、电话
   */
  @SerializedName("seller_address_and_phone")
  private String sellerAddressAndPhone;

  /**
   * 销售方开户行及账号
   */
  @SerializedName("seller_bank_account")
  private String sellerBankAccount;

  /**
   * 备注
   */
  private String remarks;

  /**
   * 收款人
   */
  private String cashier;

  /**
   * 开票人
   */
  private String maker;

  /**
   * 商品信息结构
   */
  private List<InvoiceCommodityInfo> info;
}

