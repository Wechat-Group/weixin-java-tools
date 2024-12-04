package cn.binarywang.wx.miniapp.bean.promoter.response;

import cn.binarywang.wx.miniapp.bean.WxMaBaseResponse;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhuangzibin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxMaPromotionGetPromoterResponse extends WxMaBaseResponse implements Serializable {

  private static final long serialVersionUID = -2443311045690767883L;

  /*
    {
    "promoter_list":
      [
        {
          "openid": "xxxxx",
          "role_id": 1,
          "retail_id": "xxxxx",
          "extra_info": "xxxxx",
          "auth_status": 1,
          "decl_status": 1,
          "update_time": 1668667349,
          "id": "100",
          "name": "xxxxx",
          "phone": "xxxxx"
        },
        {
          "openid": "xxxxx",
          "role_id": 1,
          "retail_id": "xxxxx",
          "extra_info": "xxxxx",
          "auth_status": 1,
          "decl_status": 1,
          "update_time": 1668667349,
          "id": "123",
          "name": "xxxxx",
          "phone": "xxxxx"
        }
      ],
    "total_cnt": 2,
    "errcode": 0,
    "errmsg": "OK"
    }
   */

  /**
   * 推广员的openid或unionid
   * 必填
   */
  @SerializedName("total_cnt")
  private String total_cnt;

  /**
   * 门店id，长度不能超过20个字符
   * 非必填
   */
  @SerializedName("promoter_list")
  private List<Promoter> promoterList;

  @Data
  public static class Promoter {

    /**
     * 推广员的openid或unionid
     */
    @SerializedName("openid")
    private String openid;

    /**
     * 角色id
     */
    @SerializedName("role_id")
    private Long roleId;

    /**
     * 门店id
     */
    @SerializedName("retail_id")
    private String retailId;

    /**
     * 推广员参数
     */
    @SerializedName("extra_info")
    private String extraInfo;

    /**
     * 推广员名称
     */
    @SerializedName("name")
    private String name;

    /**
     * 推广员手机号
     */
    @SerializedName("phone")
    private String phone;

    /**
     * 0：推广员未授权 1：推广员已授权 2：推广员取消授权
     */
    @SerializedName("auth_status")
    private Long authStatus;

    /**
     * 1：商家已声明 2：商家取消声明
     */
    @SerializedName("decl_status")
    private String declStatus;

    /**
     * 推广员授权状态变更秒级时间戳
     */
    @SerializedName("update_time")
    private Long updateTime;

    /**
     * 唯一id，分页和更新时回传
     */
    @SerializedName("id")
    private String id;
  }
}
