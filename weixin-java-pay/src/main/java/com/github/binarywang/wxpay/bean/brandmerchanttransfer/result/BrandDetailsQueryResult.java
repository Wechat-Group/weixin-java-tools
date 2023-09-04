package com.github.binarywang.wxpay.bean.brandmerchanttransfer.result;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 品牌红包微信明细单号查询明细单 响应实体、
 *
 * @author moran
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BrandDetailsQueryResult implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：品牌主商户号
   * 变量名：brand_mchid
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   *  微信服务商下特约商户的商户号，且已经认证品牌的品牌主商户号
   * 示例值：1900001109
   * </pre>
   */
  @SerializedName("brand_mchid")
  private String brandMchid;

  /**
   * <pre>
   * 字段名：商家品牌红包批次单号
   * 变量名：out_batch_no
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   *  商户系统内部的商家品牌红包批次单号，在商户系统内部唯一
   * 示例值：plfk2020042013
   * </pre>
   */
  @SerializedName("out_batch_no")
  private String outBatchNo;

  /**
   * <pre>
   * 字段名：微信支付品牌红包批次单号
   * 变量名：batch_no
   * 是否必填：是
   * 类型：string[32, 64]
   * 描述：
   *  微信支付品牌红包批次单号，微信商家品牌红包系统返回的唯一标识
   * 示例值：1030000071100999991182020050700019480001
   * </pre>
   */
  @SerializedName("batch_no")
  private String batchNo;

  /**
   * <pre>
   * 字段名：商家品牌红包明细单号
   * 变量名：out_detail_no
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   *  品牌商户系统内部的品牌红包批次单号，要求此参数只能由数字、大小写字母组成，在商户系统内部唯一
   * 示例值：x23zy545Bd5436
   * </pre>
   */
  @SerializedName("out_detail_no")
  private String outDetailNo;

  /**
   * <pre>
   * 字段名：微信支付品牌红包明细单号
   * 变量名：detail_id
   * 是否必填：是
   * 类型：string[32, 64]
   * 描述：
   *  微信支付系统内部区分品牌红包批次单下不同品牌红包明细单的唯一标识
   * 示例值：1040000071100999991182020050700019500100
   * </pre>
   */
  @SerializedName("detail_no")
  private String detailNo;

  /**
   * <pre>
   * 字段名：品牌红包明细单状态
   * 变量名：detail_state
   * 是否必填：是
   * 类型：string
   * 描述：
   *  品牌红包明细单状态
   * DETAIL_PROCESSING - 发送中，正在处理中，品牌红包发送结果尚未明确
   * DETAIL_SUCCESS - 发送成功，发送成功
   * DETAIL_FAIL - 发送失败，需要确认失败原因后，再决定是否重新对该笔品牌红包明细单进行发送（并非整个品牌红包批次单）
   * 示例值：DETAIL_SUCCESS
   * </pre>
   */
  @SerializedName("detail_state")
  private String detailState;

  /**
   * <pre>
   * 字段名：红包金额
   * 变量名：amount
   * 是否必填：是
   * 类型：int
   * 描述：
   *  红包金额单位为“分”
   * 示例值：100
   * </pre>
   */
  @SerializedName("amount")
  private Integer amount;

  /**
   * <pre>
   * 字段名：红包备注
   * 变量名：remark
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   *  单个品牌红包备注（微信用户会收到该备注），UTF8编码，最多允许32个字符
   * 示例值：来自XX品牌红包
   * </pre>
   */
  @SerializedName("remark")
  private String remark;

  /**
   * <pre>
   * 字段名：明细失败原因
   * 变量名：fail_reason
   * 是否必填：否
   * 类型：string
   * 描述：
   *  明细失败原因
   * ACCOUNT_FROZEN - 该用户账户被冻结
   * REAL_NAME_CHECK_FAIL - 收款人未实名认证，需要用户完成微信实名认证
   * NAME_NOT_CORRECT - 收款人姓名校验不通过，请核实信息
   * OPENID_INVALID - OpenID格式错误或者不属于商家公众账号
   * TRANSFER_QUOTA_EXCEED - 超过用户单笔收款区间，核实产品设置是否准确
   * DAY_RECEIVED_QUOTA_EXCEED - 超过用户单日收款额度，核实产品设置是否准确
   * DAY_RECEIVED_COUNT_EXCEED - 超过用户单日收款次数，核实产品设置是否准确
   * PRODUCT_AUTH_CHECK_FAIL - 未开通该权限或权限被冻结，请核实产品权限状态
   * OVERDUE_CLOSE - 该笔转账已关闭
   * ACCOUNT_NOT_EXIST - 该用户账户不存在
   * TRANSFER_RISK - 该笔转账可能存在风险，已被微信拦截
   * USER_ACCOUNT_LIMIT - 用户账户收款受限，请引导用户在微信支付查看详情
   * FAIL_REASON_UNKNOWN - 失败原因未知
   * PAYER_ACCOUNT_ABNORMAL - 商户账户付款受限，可前往商户平台获取解除功能限制指引
   * PAYEE_ACCOUNT_ABNORMAL - 用户账户收款异常，请联系用户完善其在微信支付的身份信息以继续收款
   * USER_RECEIVE_OVERDUE - 用户逾期未领取
   * REMARK_NOT_CORRECT - 红包备注设置失败，请修改后再试
   * 示例值：ACCOUNT_FROZEN
   * </pre>
   */
  @SerializedName("fail_reason")
  private String failReason;

  /**
   * <pre>
   * 字段名：接收红包用户OpenID
   * 变量名：openid
   * 是否必填：是
   * 类型：string[1, 64]
   * 描述：
   *  接收红包的用户OpenID，OpenID为用户在对应AppID下的唯一标识
   * 示例值：o-MYE42l80oelYMDE34nYD456Xoy
   * </pre>
   */
  @SerializedName("openid")
  private String openid;

  /**
   * <pre>
   * 字段名：接收红包用户姓名
   * 变量名：user_name
   * 是否必填：否
   * 类型：string[1,1024]
   * 描述：
   *  发放品牌红包时传入的接收红包用户姓名，已使用商户的私钥加密
   * 示例值：757b340b45ebef5467rter35gf464344v3542sdf4t6re4tb4f54ty45t4yyry45
   * </pre>
   */
  @SerializedName("user_name")
  private String userName;

  /**
   * <pre>
   * 字段名：品牌红包发起时间
   * 变量名：initiate_time
   * 是否必填：否
   * 类型：string[1,32]
   * 描述：
   *  品牌红包发起的时间，遵循rfc3339标准格式，
   *  格式为yyyy-MM-DDTHH:mm:ss.sss+TIMEZONE，yyyy-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss.sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。
   *  例如：2015-05-20T13:29:35.120+08:00表示北京时间2015年05月20日13点29分35秒
   * 示例值：2015-05-20T13:29:35.120+08:00
   * </pre>
   */
  @SerializedName("initiate_time")
  private String initiateTime;

  /**
   * <pre>
   * 字段名：品牌红包更新时间
   * 变量名：update_time
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   *  品牌红包明细单最后一次状态变更时间，遵循rfc3339标准格式，
   *  格式为yyyy-MM-DDTHH:mm:ss.sss+TIMEZONE，yyyy-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss.sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。
   *  例如：2015-05-20T13:29:35.120+08:00表示北京时间2015年05月20日13点29分35秒
   * 示例值：2015-05-20T13:29:35.120+08:00
   * </pre>
   */
  @SerializedName("update_time")
  private String updateTime;

  /**
   * <pre>
   * 字段名：品牌ID
   * 变量名：brand_id
   * 是否必填：是
   * 类型：int
   * 描述：
   *  品牌在微信支付进行品牌认证后的唯一标识品牌ID
   * 示例值：1234
   * </pre>
   */
  @SerializedName("brand_id")
  private Integer brandId;

  /**
   * <pre>
   * 字段名：品牌红包模板ID
   * 变量名：template_id
   * 是否必填：是
   * 类型：string[1, 128]
   * 描述：
   *  品牌主配置的品牌红包模板ID
   * 示例值：12340000000001
   * </pre>
   */
  @SerializedName("template_id")
  private String templateId;

  /**
   * <pre>
   * 字段名：品牌AppID
   * 变量名：brand_appid
   * 是否必填：否
   * 类型：string[1, 32]
   * 描述：
   *  品牌商户在微信申请公众号/小程序或移动应用成功后分配的账号ID，该AppID需与品牌ID有绑定关系（B-A绑定关系）
   * 示例值：wxf636efh567hg4356
   * </pre>
   */
  @SerializedName("brand_appid")
  private String brandAppid;

}
