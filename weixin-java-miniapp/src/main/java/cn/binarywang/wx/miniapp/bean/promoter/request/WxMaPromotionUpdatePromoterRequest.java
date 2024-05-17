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
public class WxMaPromotionUpdatePromoterRequest implements Serializable {

  private static final long serialVersionUID = 613641392778175502L;

  /*
    {
    "id": "123",
    "role_id": 1,
    "retail_id": "xxxxx",
    "extra_info": "xxxxx",
    "decl_status": 2,
    "name": "xxxxx",
    "phone": "139xxxxxxxx"
    }
   */

  /**
   * 推广员的唯一id
   * 必填
   */
  @SerializedName("id")
  private String id;

  /**
   * 角色id
   * 非必填
   */
  @SerializedName("role_id")
  private Long roleId;

  /**
   * 门店id，长度不能超过20个字符
   * 非必填
   */
  @SerializedName("retail_id")
  private String retailId;

  /**
   * 推广员参数，用于自定义标识推广员，长度不能超过80个字符
   * 非必填
   */
  @SerializedName("extra_info")
  private String extraInfo;

  /**
   * 推广员名称，长度不能超过30个字符
   * 非必填
   */
  @SerializedName("name")
  private String name;

  /**
   * 推广员手机号，长度不能超过20个字符
   * 非必填
   */
  @SerializedName("phone")
  private String phone;

  /**
   * 1：商家已声明 2：商家取消声明
   * 非必填
   */
  @SerializedName("decl_status")
  private String declStatus;
}
