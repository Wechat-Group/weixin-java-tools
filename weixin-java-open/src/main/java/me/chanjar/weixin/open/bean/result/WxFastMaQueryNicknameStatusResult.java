package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询改名状态实体类.
 *
 * @author Hipple
 * @since 2019/1/26 17:52
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WxFastMaQueryNicknameStatusResult extends WxOpenResult {

  private static final long serialVersionUID = 8492116046290791757L;

  /**
   * 审核昵称
   */
  @SerializedName("nickname")
  private String nickname;
  /**
   * 审核状态，1：审核中，2：审核失败，3：审核成功
   */
  @SerializedName("audit_stat")
  private int auditStat;
  /**
   * 失败原因
   */
  @SerializedName("fail_reason")
  private String failReason;
  /**
   * 审核提交时间
   */
  @SerializedName("create_time")
  private String createTime;
  /**
   * 审核提交时间
   */
  @SerializedName("audit_time")
  private String auditTime;
}
