package cn.binarywang.wx.miniapp.bean.shop.response;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * @author leiin
 * created on  2022/7/1 3:59 下午
 */
@Data
public class WxMaShopUserCouponListResponse extends WxMaShopBaseResponse implements Serializable {

  private static final long serialVersionUID = 3264119403757388410L;

  @SerializedName("total_num")
  private Long totalNum;
  @SerializedName("result_list")
  private List<UserCouponResultItem> resultList;

  @Data
  public static class UserCouponResultItem {

    /**
     * 商家侧用户优惠券ID
     */
    @SerializedName("out_user_coupon_id")
    private String outUserCouponId;
    /**
     * openid
     */
    @SerializedName("openid")
    private String openid;
    /**
     * 商家侧优惠券ID
     */
    @SerializedName("out_coupon_id")
    private String outCouponId;
    /**
     * 用户优惠券状态
     */
    @SerializedName("status")
    private Integer status;
    /**
     * 用户优惠券创建时间
     */
    @SerializedName("create_time")
    private Long createTime;
    /**
     * 用户优惠券更新时间
     */
    @SerializedName("update_time")
    private Long updateTime;
    /**
     * 用户优惠券有效开始时间
     */
    @SerializedName("start_time")
    private Long startTime;
    /**
     * 用户优惠券有效结束时间
     */
    @SerializedName("end_time")
    private Long endTime;

    @SerializedName("ext_info")
    private UserCouponExtInfo extInfo;

    @Data
    public static class UserCouponExtInfo {
      @SerializedName("use_time")
      private Long useTime;
    }
  }

}
