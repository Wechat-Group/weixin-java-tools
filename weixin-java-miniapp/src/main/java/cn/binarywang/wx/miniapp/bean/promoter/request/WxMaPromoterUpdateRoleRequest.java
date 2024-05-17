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
public class WxMaPromoterUpdateRoleRequest implements Serializable {

  private static final long serialVersionUID = -3498323828391890607L;

  /*
    {
    "role_id": 1,
    "name": "xxxxx",
    "desc": "xxxxx"
    }
   */

  /**
   * 角色id
   * 必填
   */
  @SerializedName("role_id")
  private Long roleId;

  /**
   * 角色名称，长度不能超过50个字符
   * name和desc二者必填其一
   */
  @SerializedName("name")
  private String name;

  /**
   * 角色描述，长度不能超过200个字符
   * name和desc二者必填其一
   */
  @SerializedName("desc")
  private String desc;
}
