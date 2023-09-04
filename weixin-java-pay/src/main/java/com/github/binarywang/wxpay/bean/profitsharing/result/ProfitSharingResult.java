package com.github.binarywang.wxpay.bean.profitsharing.result;

import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author Wang GuangXin 2019/10/22 10:06
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XStreamAlias("xml")
public class ProfitSharingResult extends BaseWxPayResult implements Serializable {
  private static final long serialVersionUID = 7435709584788869456L;

  /**
   * GSON工具
   */
  private static final Gson GSON = new GsonBuilder().create();

  /**
   * 微信订单号.
   */
  @XStreamAlias("transaction_id")
  private String transactionId;
  /**
   * 商户分账单号.
   */
  @XStreamAlias("out_order_no")
  private String outOrderNo;
  /**
   * 微信分账单号.
   */
  @XStreamAlias("order_id")
  private String orderId;

  /**
   * 分账单状态.
   */
  @XStreamAlias("status")
  private String status;

  /**
   * 分账接收方列表
   */
  @XStreamAlias("receivers")
  private String receivers;

  /**
   * 分账接收方列表
   */
  private List<Receiver> receiverList;

  /**
   * 获取分账接收方列表方法
   *
   */
  public List<Receiver> getReceiverList() {
    if (receiverList != null) {
      return receiverList;
    }

    if (StringUtils.isNotEmpty(receivers)) {
      this.receiverList = GSON.fromJson(receivers, new TypeToken<List<Receiver>>() {
      }.getType());
      return receiverList;
    }

    return Collections.emptyList();
  }

  @Override
  protected void loadXml(Document d) {
    transactionId = readXmlString(d, "transaction_id");
    outOrderNo = readXmlString(d, "out_order_no");
    orderId = readXmlString(d, "order_id");
    status = readXmlString(d, "status");
    receivers = readXmlString(d, "receivers");
  }

  /**
   * 分账接收方列表对象
   */
  @Data
  public static class Receiver implements Serializable {
    private static final long serialVersionUID = 4240983048700956806L;

    /**
     * <pre>
     * 字段名：分账接收方类型
     * 是否必填：是
     * 描述：
     * 枚举值：
     * MERCHANT_ID：商户号（mch_id或者sub_mch_id）
     * PERSONAL_OPENID：个人openid（由服务商的APPID转换得到）
     * PERSONAL_SUB_OPENID：个人sub_openid（由品牌主的APPID转换得到）
     * </pre>
     */
    @SerializedName("type")
    private String type;

    /**
     * <pre>
     * 字段名：分账接收方帐号
     * 是否必填：是
     * 描述：
     * 1、分账接收方类型为MERCHANT_ID时，分账接收方账号为商户号
     * 2、分账接收方类型为PERSONAL_OPENID时，分账接收方账号为个人openid
     * </pre>
     */
    @SerializedName("account")
    private String account;

    /**
     * <pre>
     * 字段名：分账金额
     * 是否必填：是
     * 描述： 分账金额，单位为分，只能为整数，不能超过原订单支付金额及最大分账比例金额
     * </pre>
     */
    @SerializedName("amount")
    private Integer amount;

    /**
     * <pre>
     * 字段名：分账接收商户号
     * 是否必填：是
     * 描述： 仅分账接收方类型为MERCHANT_ID时，填写微信支付分配的商户号
     * </pre>
     */
    @SerializedName(("receiver_mchid"))
    private String receiverMchid;

    /**
     * <pre>
     * 字段名：分账描述
     * 是否必填：是
     * 描述： 分账的原因描述，分账账单中需要体现
     * </pre>
     */
    @SerializedName("description")
    private String description;

    /**
     * <pre>
     * 字段名：分账结果
     * 是否必填：是
     * 描述：
     * 1、PENDING：待分账
     * 2、SUCCESS：分账成功
     * 3、CLOSED：已关闭
     * </pre>
     */
    @SerializedName("result")
    private String result;

    /**
     * <pre>
     * 字段名：分账失败原因
     * 是否必填：是
     * 描述：包含以下枚举值：
     * 1、ACCOUNT_ABNORMAL : 分账接收账户异常
     * 2、NO_RELATION : 分账关系已解除
     * 3、RECEIVER_HIGH_RISK : 高风险接收方
     * 4、RECEIVER_REAL_NAME_NOT_VERIFIED : 接收方未实名
     * 5、NO_AUTH : 分账权限已解除
     * </pre>
     */
    @SerializedName("fail_reason")
    private String failReason;

    /**
     * <pre>
     * 字段名：分账创建时间
     * 是否必填：是
     * 描述：遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss.sss+TIMEZONE，
     * YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，
     * HH:mm:ss.sss表示时分秒毫秒，
     * TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。
     * 例如：2015-05-20T13:29:35.120+08:00表示，北京时间2015年5月20日 13点29分35秒。
     * </pre>
     */
    @SerializedName("create_time")
    private String createTime;
    /**
     * <pre>
     * 字段名：分账完成时间
     * 是否必填：是
     * 描述：遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss.sss+TIMEZONE，
     * YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，
     * HH:mm:ss.sss表示时分秒毫秒，
     * TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。
     * 例如：2015-05-20T13:29:35.120+08:00表示，北京时间2015年5月20日 13点29分35秒。
     * </pre>
     */
    @SerializedName("finish_time")
    private String finishTime;

    /**
     * <pre>
     * 字段名：微信分账明细单号
     * 是否必填：是
     * 每笔分账业务执行的明细单号，可与资金账单对账使用，
     * 例如：36011111111111111111111
     * </pre>
     */
    @SerializedName("detail_id")
    private String detailId;
  }
}
