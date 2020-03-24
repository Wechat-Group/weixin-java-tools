package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * .
 *
 * @author yqx
 * @date 2018/10/3
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxOpenMaQueryAuditResult extends WxOpenResult {
  private static final long serialVersionUID = 8022192589710319473L;

  /**
   * 审核编号.
   */
  @SerializedName("auditid")
  Long auditId;

  /**
   * 审核状态:2-审核中,0-审核通过,1-审核失败.
   */
  Integer status;

  /**
   * 审核失败原因.
   */
  String reason;
  /**
   * 当status=1，审核被拒绝时，会返回审核失败的小程序截图示例。 xxx丨yyy丨zzz是media_id可通过获取永久素材接口 拉取截图内容）.
   */
  @SerializedName(value = "screenshot")
  private String screenShot;
}
