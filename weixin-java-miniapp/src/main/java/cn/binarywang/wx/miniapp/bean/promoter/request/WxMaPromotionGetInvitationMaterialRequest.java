package cn.binarywang.wx.miniapp.bean.promoter.request;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhuangzibin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaPromotionGetInvitationMaterialRequest implements Serializable {

  private static final long serialVersionUID = 3579475611446461635L;

  /*
    {
    "role_id": 1,
    "invitation_type": 0
    }
   */

  /**
   * 角色id，role_id需调「查询角色」接口查询
   * 必填
   */
  @SerializedName("role_id")
  private Long roleId;

  /**
   * 0：海报 1：小程序码 2：短链（默认返回海报）
   * 非必填
   */
  @SerializedName("invitation_type")
  private Long invitationType;
}
