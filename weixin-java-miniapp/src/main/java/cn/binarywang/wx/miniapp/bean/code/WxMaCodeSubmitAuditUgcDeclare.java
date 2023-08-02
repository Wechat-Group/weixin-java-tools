package cn.binarywang.wx.miniapp.bean.code;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author binarywang
 */
@Data
@Accessors(chain = true)
public class WxMaCodeSubmitAuditUgcDeclare implements Serializable {
  private static final long serialVersionUID = 201470564426848261L;

  /**
   * scene
   * Number Array
   * 否
   * UGC场景 0,不涉及用户生成内容, 1.用户资料,2.图片,3.视频,4.文本,5其他, 可多选,当scene填0时无需填写下列字段
   */
  @SerializedName("scene")
  private Integer[] scene;

  /**
   * other_scene_desc
   * String
   * 否
   * 当scene选其他时的说明,不超时256字
   */
  @SerializedName("other_scene_desc")
  private String otherSceneDesc;

  /**
   * method
   * Number Array
   * 否
   * 内容安全机制 1.使用平台建议的内容安全API,2.使用其他的内容审核产品,3.通过人工审核把关,4.未做内容审核把关
   */
  @SerializedName("method")
  private Integer[] method;

  /**
   * has_audit_team
   * Number
   * 否
   * 是否有审核团队, 0.无,1.有,默认0
   */
  @SerializedName("has_audit_team")
  private Integer hasAuditTeam;

  /**
   * audit_desc
   * String
   * 否
   * 说明当前对UGC内容的审核机制,不超过256字
   */
  @SerializedName("audit_desc")
  private String auditDesc;
}
