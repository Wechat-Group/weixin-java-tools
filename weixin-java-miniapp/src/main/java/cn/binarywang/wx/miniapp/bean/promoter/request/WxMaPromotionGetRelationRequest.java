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
public class WxMaPromotionGetRelationRequest implements Serializable {

  private static final long serialVersionUID = 8525361618611598316L;

  /*
    {
      "openid": "xxxxx",
      "begin_time": 1668614400,
      "end_time": 1668666429,
      "scene": 1077,
      "path": "pages/xxxxx",
      "start_id": "123",
      "need_unionid": 1
    }
   */

  /**
   * 推广员的openid或unionid
   * 必填
   */
  @SerializedName("openid")
  private String openid;

  /**
   * 触达时间开始秒级时间戳
   * 非必填
   */
  @SerializedName("begin_time")
  private Long beginTime;

  /**
   * 触达时间结束秒级时间戳
   * 非必填
   */
  @SerializedName("end_time")
  private Long endTime;

  /**
   * 触达场景值，枚举值参考场景值列表
   * <a href="https://developers.weixin.qq.com/miniprogram/dev/reference/scene-list.html">场景值列表</a>
   * 非必填
   */
  @SerializedName("scene")
  private Long scene;

  /**
   * 触达path，原生分享path里参数可能乱序导致搜不到
   * 非必填
   */
  @SerializedName("path")
  private String path;

  /**
   * 用于分页时透传，单次拉取上限为1000，超过1000须分页
   * 非必填
   */
  @SerializedName("start_id")
  private String startId;

  /**
   * 默认返回openid，填1：返回unionid
   * 非必填
   */
  @SerializedName("need_unionid")
  private Long needUnionid;
}
