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
public class WxMaPromotionGetRoleRequest implements Serializable {

  private static final long serialVersionUID = 3661919584555497735L;

  /*
    {
    "role_id": 1
    }
   */

  /**
   * 角色id
   * 非必填
   */
  @SerializedName("role_id")
  private Long roleId;

}
