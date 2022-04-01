package me.chanjar.weixin.cp.bean.oa;

import com.google.gson.annotations.SerializedName;

/**
 * 审批方式
 *
 * @author element
 */
public enum WxCpApproverAttr {
  /**
   * 或签
   */
  @SerializedName("1")
  ONE_SIGN(1),
  /**
   * 会签
   */
  @SerializedName("2")
  ALL_SIGN(2);

  private final Integer attr;

  WxCpApproverAttr(Integer attr) {
    this.attr = attr;
  }

}
