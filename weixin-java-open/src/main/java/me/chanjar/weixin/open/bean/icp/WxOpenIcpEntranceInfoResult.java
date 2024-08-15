package me.chanjar.weixin.open.bean.icp;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.chanjar.weixin.open.bean.result.WxOpenResult;

import java.io.Serializable;


/**
 * @author xzh
 * @Description 获取小程序备案状态及驳回原因
 * @createTime 2024/08/14 10:52
 */
@Getter
@Setter
@NoArgsConstructor
public class WxOpenIcpEntranceInfoResult extends WxOpenResult {

  private static final long serialVersionUID = 4661275080517814216L;

  /**
   * 备案状态信息
   */
  private Info info;

  @Getter
  @Setter
  @NoArgsConstructor
  public static class Info implements Serializable {

    private static final long serialVersionUID = 879913578935521216L;

    /**
     * 备案状态，取值参考备案状态枚举，示例值：1024
     */
    @SerializedName("status")
    private Integer status;

    /**
     * 是否正在注销备案
     */
    @SerializedName("is_canceling")
    private Boolean canceling;

    /**
     * 驳回原因，备案不通过时返回
     */
    @SerializedName("audit_data")
    private AuditData auditData;

    /**
     * 备案入口是否对该小程序开放，0：不开放，1：开放。特定情况下入口不会开放，如小程序昵称包含某些关键词时、管局系统不可用时，当备案入口开放时才能提交备案申请
     */
    @SerializedName("available")
    private Integer available;

    /**
     * 管局短信核验状态，仅当备案状态为 4（管局审核中）的时候才有效。1：等待核验中，2：核验完成，3：核验超时。
     */
    @SerializedName("sms_verify_status")
    private Integer smsVerifyStatus;
  }

  @Getter
  @Setter
  @NoArgsConstructor
  public static class AuditData implements Serializable {

    private static final long serialVersionUID = 2217833539540191890L;

    /**
     * 审核不通过的字段中文名
     */
    @SerializedName("key_name")
    private String keyName;

    /**
     * 字段不通过的原因
     */
    @SerializedName("error")
    private String error;

    /**
     * 修改建议
     */
    @SerializedName("suggest")
    private String suggest;
  }
}
