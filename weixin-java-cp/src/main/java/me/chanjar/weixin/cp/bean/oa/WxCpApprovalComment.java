package me.chanjar.weixin.cp.bean.oa;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 审批申请备注信息
 *
 * @author element
 */
@Data
public class WxCpApprovalComment implements Serializable {

  private static final long serialVersionUID = -5430367411926856292L;

  /**
   * 备注人信息
   */
  private WxCpOperator commentUserInfo;

  /**
   * 备注提交时间戳，Unix时间戳
   */
  @SerializedName("commenttime")
  private Long commentTime;

  /**
   * 备注id
   */
  @SerializedName("commentid")
  private String commentId;

  /**
   * 备注文本内容
   */
  @SerializedName("commentcontent")
  private String commentContent;

  /**
   * 备注附件id，可能有多个
   */
  @SerializedName("media_id")
  private List<String> mediaIds;

}
