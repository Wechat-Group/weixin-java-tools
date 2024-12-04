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
public class WxMaPromotionAddRoleRequest implements Serializable {

  private static final long serialVersionUID = -2802788361978629822L;

  /*
    {
    "name": "xxxxx",
    "desc": "xxxxx"
    }
   */

  /**
   * 角色名称，长度不能超过50个字符
   * 必填
   */
  @SerializedName("name")
  private String name;

  /**
   * 角色描述，长度不能超过200个字符
   * 非必填
   */
  @SerializedName("desc")
  private String desc;
}
