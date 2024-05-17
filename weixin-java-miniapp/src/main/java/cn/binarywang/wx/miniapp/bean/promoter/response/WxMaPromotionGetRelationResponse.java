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
public class WxMaPromotionGetRelationResponse extends WxMaBaseResponse implements Serializable {

  private static final long serialVersionUID = 8507306550498671699L;

  /*
    {
      "relation_list":
      [
        {
          "promoter_openid": "xxxxx",
          "role_id": 1,
          "retail_id": "xxxxx",
          "extra_info": "xxxxx",
          "openid": "xxxxx",
          "create_time": 1668667349,
          "path": "pages/xxxxx",
          "scene": 1077,
          "share_extra_info": "xxxxx"
        },
        {
          "promoter_openid": "xxxxx",
          "role_id": 1,
          "retail_id": "xxxxx",
          "extra_info": "xxxxx",
          "openid": "xxxxx",
          "create_time": 1668667349,
          "path": "pages/xxxxx",
          "scene": 1077,
          "share_extra_info": "xxxxx"
        }
      ],
      "total_cnt": 2,
      "start_id": "2",
      "errcode": 0,
      "errmsg": "OK"
    }
   */

  /**
   * 数据数组
   */
  @SerializedName("relation_list")
  private List<Relation> relationList;

  /**
   * 拉取的推广员总数
   */
  @SerializedName("total_cnt")
  private Long totalCnt;

  /**
   * 用于分页时透传
   */
  @SerializedName("start_id")
  private String startId;

  @Data
  public static class Relation {

    /**
     * 推广员的openid或unionid
     */
    @SerializedName("promoter_openid")
    private String promoterOpenid;

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
     * 触达后访问小程序的用户openid或unionid
     */
    @SerializedName("openid")
    private String openid;

    /**
     * 触达时间秒级时间戳
     */
    @SerializedName("create_time")
    private Long createTime;

    /**
     * 触达path
     */
    @SerializedName("path")
    private String path;

    /**
     * 触达场景值，枚举值参考场景值列表
     * <a href="https://developers.weixin.qq.com/miniprogram/dev/reference/scene-list.html">场景值列表</a>
     * 非必填
     */
    @SerializedName("scene")
    private Long scene;

    /**
     * 生成分享素材时的自定义参数
     */
    @SerializedName("share_extra_info")
    private String shareExtraInfo;
  }
}
