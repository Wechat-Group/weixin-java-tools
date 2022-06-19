package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 公众号关联的小程序
 *
 * @author zhongjun
 * @date 2022/4/29
 **/

@Data
@EqualsAndHashCode(callSuper = true)
public class WxAmpLinkResult extends WxOpenResult{

  /**
   * 关联的小程序列表，具有 items 字段，内带有参数
   */
  @SerializedName("wxopens")
  private WxOpen wxOpen;

  @Getter
  @Setter
  public static class WxOpen{
    @SerializedName("items")
    private List<Item> items;
  }

  @Getter
  @Setter
  public static class Item{

    /**
     * 关联状态
     * 1：已关联；
     * 2：等待小程序管理员确认中；
     * 3：小程序管理员拒绝关联
     * 12：等待公众号管理员确认中；
     */
    private Integer status;

    /**
     * 小程序appid
     */
    private String appid;

    /**
     * 小程序 gh_id
     */
    private String username;

    /**
     * 小程序名称
     */
    private String nickname;

    /**
     * 是否在公众号管理页展示中
     */
    private Integer selected;

    /**
     * 是否展示在附近的小程序中
     */
    @SerializedName("nearby_display_status")
    private Integer nearbyDisplayStatus;

    /**
     * 是否已经发布
     */
    private Integer released;

    /**
     * 头像 url
     */
    @SerializedName("headimg_url")
    private String headImgUrl;

    /**
     * 小程序邮箱
     */
    private String email;

    /**
     * 微信认证及支付信息
     */
    @SerializedName("func_info")
    private List<FuncInfo> funcInfo;

  }

  @Getter
  @Setter
  public static class FuncInfo{
    /**
     * 微信认证及支付信息，0 表示未开通，1 表示开通
     */
    private Integer status;

    private String name;

    private Long id;

  }
}
