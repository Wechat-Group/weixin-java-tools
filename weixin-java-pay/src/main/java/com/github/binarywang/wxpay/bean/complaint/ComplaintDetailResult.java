package com.github.binarywang.wxpay.bean.complaint;


import com.github.binarywang.wxpay.v3.SpecEncrypt;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 微信消费者投诉2.0
 * 查询投诉单列表返回的实体
 *
 * @author <a href="https://gitee.com/jeequan/jeepay">jmdhappy</a>
 * @date 2022-3-19
 */
@Data
public class ComplaintDetailResult implements Serializable {

  private static final long serialVersionUID = -6201692411535927503L;

  /**
   * <pre>
   * 字段名：投诉单号
   * 是否必填：是
   * 描述：投诉单对应的投诉单号
   * </pre>
   */
  @SerializedName("complaint_id")
  private String complaintId;

  /**
   * <pre>
   * 字段名：投诉时间
   * 是否必填：是
   * 描述：投诉时间，遵循rfc3339标准格式，格式为yyyy-MM-DDTHH:mm:ss.sss+TIMEZONE，yyyy-MM-DD表示年月日，
   * T出现在字符串中，表示time元素的开头，HH:mm:ss.sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。
   * 例如：2015-05-20T13:29:35.120+08:00表示北京时间2015年05月20日13点29分35秒
   * 示例值：2015-05-20T13:29:35.120+08:00
   * </pre>
   */
  @SerializedName("complaint_time")
  private String complaintTime;

  /**
   * <pre>
   * 字段名：投诉详情
   * 是否必填：是
   * 投诉的具体描述
   * </pre>
   */
  @SerializedName("complaint_detail")
  private String complaintDetail;

  /**
   * <pre>
   * 字段名：被诉商户号
   * 是否必填：是
   * 投诉单对应的被诉商户号。
   * </pre>
   */
  @SerializedName("complainted_mchid")
  private String complaintedMchid;

  /**
   * <pre>
   * 字段名：投诉单状态
   * 是否必填：是
   * 标识当前投诉单所处的处理阶段，具体状态如下所示：
   * PENDING：待处理
   * PROCESSING：处理中
   * PROCESSED：已处理完成
   * </pre>
   */
  @SerializedName("complaint_state")
  private String complaintState;

  /**
   * <pre>
   * 字段名：投诉人联系方式
   * 是否必填：否
   * 投诉人联系方式。该字段已做加密处理，具体解密方法详见敏感信息加密说明。
   * </pre>
   */
  @SerializedName("payer_phone")
  @SpecEncrypt
  private String payerPhone;

  /**
   * <pre>
   * 字段名：投诉人openid
   * 是否必填：是
   * 投诉人在商户appid下的唯一标识
   * </pre>
   */
  @SerializedName("payer_openid")
  private String payerOpenid;


  /**
   * <pre>
   * 字段名：投诉资料列表
   * 是否必填：是
   * 用户上传的投诉相关资料，包括图片凭证等
   * </pre>
   */
  @SerializedName("complaint_media_list")
  private List<ComplaintMedia> complaintMediaList;

  @Data
  public static class ComplaintMedia implements Serializable {
    private static final long serialVersionUID = 4240983048700956803L;

    /**
     * <pre>
     * 字段名：媒体文件业务类型
     * 是否必填：是
     * 描述：
     * 媒体文件对应的业务类型
     * USER_COMPLAINT_IMAGE：用户投诉图片，用户提交投诉时上传的图片凭证
     * OPERATION_IMAGE：操作流水图片，用户、商户、微信支付客服在协商解决投诉时，上传的图片凭证
     * 注：用户上传的图片凭证会以白名单的形式提供给商户，若希望查看用户图片，联系微信支付客服
     * 示例值：USER_COMPLAINT_IMAGE
     * </pre>
     */
    @SerializedName("media_type")
    private String mediaType;

    /**
     * <pre>
     * 字段名：媒体文件请求url
     * 是否必填：是
     * 描述：
     * 微信返回的媒体文件请求url
     * </pre>
     */
    @SerializedName("media_url")
    private String mediaUrl;

  }

  /**
   * <pre>
   * 字段名：投诉单关联订单信息
   * 是否必填：是
   * 投诉单关联订单信息
   * 注：投诉单和订单目前是一对一关系，array是预留未来一对多的扩展
   * </pre>
   */
  @SerializedName("complaint_order_info")
  private List<ComplaintOrder> complaintOrderInfo;

  @Data
  public static class ComplaintOrder implements Serializable {
    private static final long serialVersionUID = 4240983048700956804L;

    /**
     * <pre>
     * 字段名：微信订单号
     * 是否必填：是
     * 描述：
     * 投诉单关联的微信订单号
     * </pre>
     */
    @SerializedName("transaction_id")
    private String transactionId;

    /**
     * <pre>
     * 字段名：商户订单号
     * 是否必填：是
     * 描述：
     * 投诉单关联的商户订单号
     * </pre>
     */
    @SerializedName("out_trade_no")
    private String outTradeNo;

    /**
     * <pre>
     * 字段名：订单金额
     * 是否必填：是
     * 描述：
     * 订单金额，单位（分）
     * </pre>
     */
    @SerializedName("amount")
    private Integer amount;

  }

  /**
   * <pre>
   * 字段名：投诉单是否已全额退款
   * 是否必填：是
   * 描述：
   * 投诉单下所有订单是否已全部全额退款
   * </pre>
   */
  @SerializedName("complaint_full_refunded")
  private Boolean complaintFullRefunded;

  /**
   * <pre>
   * 字段名：是否有待回复的用户留言
   * 是否必填：是
   * 描述：
   * 投诉单是否有待回复的用户留言
   * </pre>
   */
  @SerializedName("incoming_user_response")
  private Boolean incomingUserResponse;

  /**
   * <pre>
   * 字段名：问题描述
   * 是否必填：是
   * 描述：
   * 用户发起投诉前选择的faq标题（2021年7月15日之后的投诉单均包含此信息）
   * </pre>
   */
  @SerializedName("problem_description")
  private String problemDescription;

  /**
   * <pre>
   * 字段名：用户投诉次数
   * 是否必填：是
   * 描述：
   * 用户投诉次数。用户首次发起投诉记为1次，用户每有一次继续投诉就加1
   * </pre>
   */
  @SerializedName("user_complaint_times")
  private Integer userComplaintTimes;
}
