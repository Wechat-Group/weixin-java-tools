package cn.binarywang.wx.miniapp.bean.promoter.request;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhuangzibin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaPromotionAddPromoterRequest implements Serializable {

  private static final long serialVersionUID = 589547859656958069L;

  /*
    {
    "promoter_list":
      [
        {
        "openid": "xxxxx",
        "role_id": 1,
        "retail_id": "xxxxx",
        "extra_info": "xxxxx",
        "name": "xxxxx",
        "phone": "xxxxx"
        },
        {
        "openid": "xxxxx",
        "role_id": 1,
        "retail_id": "xxxxx",
        "extra_info": "xxxxx",
        "name": "xxxxx",
        "phone": "xxxxx"
        }
      ]
    }
   */

  @SerializedName("promoter_list")
  private List<Promoter> promoterList;

  @Data
  @Builder
  public static class Promoter {
    /**
     * 推广员的openid或unionid
     * 必填
     */
    @SerializedName("openid")
    private String openid;

    /**
     * 角色id，role_id需调「查询角色」接口查询
     * 必填
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
  }
}
