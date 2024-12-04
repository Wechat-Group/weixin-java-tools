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
public class WxMaPromotionGetPromoterRequest implements Serializable {

  private static final long serialVersionUID = 5324767626460338896L;

  /*
      {
      "openid": "xxxxx",
      "role_id": 1,
      "retail_id": "xxxxx",
      "begin_time": 1668614400,
      "end_time": 1668666429,
      "start_id": "123",
      "need_unionid": 1,
      "auth_status": 1,
      "decl_status": 1
      }
     */

  /**
   * 推广员的openid或unionid
   * 非必填
   */
  @SerializedName("openid")
  private String openid;

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
   * 推广员授权状态变更开始秒级时间戳
   * 非必填
   */
  @SerializedName("begin_time")
  private Long beginTime;

  /**
   * 推广员授权状态变更结束秒级时间戳
   * 非必填
   */
  @SerializedName("end_time")
  private Long endTime;

  /**
   * 用于分页时透传，单次拉取上限为2000，超过2000须分页
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

  /**
   * 0：推广员未授权 1：推广员已授权 2：推广员取消授权
   * 非必填
   */
  @SerializedName("auth_status")
  private Long authStatus;

  /**
   * 1：商家已声明 2：商家取消声明
   * 非必填
   */
  @SerializedName("decl_status")
  private String declStatus;

}
