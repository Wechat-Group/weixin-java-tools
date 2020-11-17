package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 微信认证名称检测结果类.
 *
 * @author Hipple
 * @since 2019/1/26 17:39
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxFastMaCheckNickameResult extends WxOpenResult {
  private static final long serialVersionUID = 8022192589710319473L;

  /**
   * 审核编号.
   */
  @SerializedName("hit_condition")
  boolean hitCondition;

  /**
   * 材料说明
   */
  @SerializedName("wording")
  String wording;

}
