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
public class WxMaPromotionAddPromoterResponse extends WxMaBaseResponse implements Serializable {

  private static final long serialVersionUID = -6310277945996005821L;

  /*
    {
    "total_cnt": 200,
    "fail_cnt": 2,
    "fail_list":
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
          "phone": "xxxxx",
          "errcode": 103003,
          "errmsg": "data already exists"
        }
      ],
    "errcode": 0,
    "errmsg": "OK"
    }
   */

  /**
   * 声明推广员总数
   */
  @SerializedName("total_cnt")
  private Long totalCnt;

  /**
   * 声明推广员失败数
   */
  @SerializedName("fail_cnt")
  private Long failCnt;

  /**
   * 生命推广员失败列表
   * 非必填
   */
  @SerializedName("fail_list")
  private List<Promoter> failList;

  @Data
  public static class Promoter {
    /**
     * 声明失败推广员的openid
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
     * 错误码
     */
    @SerializedName("errcode")
    private Integer errcode;

    /**
     * 错误信息
     */
    @SerializedName("errmsg")
    private String errmsg;
  }
}
