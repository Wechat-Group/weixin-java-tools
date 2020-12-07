package cn.binarywang.wx.miniapp.bean.code;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 小程序代码审核状态
 *
 * @author <a href="https://github.com/charmingoh">Charming</a>
 * @since 2018-04-26 19:44:39
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaCodeAuditStatus implements Serializable {
  private static final long serialVersionUID = 4655119308692217268L;
  /**
   * 审核 ID.
   */
  @SerializedName(value = "auditId", alternate = {"auditid"})
  private Long auditId;
  /**
   * 审核状态.
   * 其中0为审核成功，1为审核失败，2为审核中
   */
  private Integer status;
  /**
   * 当status=1，审核被拒绝时，返回的拒绝原因.
   */
  private String reason;
  /**
   * 当status=1，审核被拒绝时，会返回审核失败的小程序截图示例。 xxx丨yyy丨zzz是media_id可通过获取永久素材接口 拉取截图内容）.
   */
  @SerializedName(value = "screenshot")
  private String screenShot;

  public static WxMaCodeAuditStatus fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WxMaCodeAuditStatus.class);
  }
}
