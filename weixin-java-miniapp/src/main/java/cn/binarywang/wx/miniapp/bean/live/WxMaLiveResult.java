package cn.binarywang.wx.miniapp.bean.live;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * <pre>
 * 直播间操作返回结果
 * Created by lipengjun on 2020/6/29.
 * </pre>
 *
 * @author <a href="https://github.com/lipengjun92">lipengjun (939961241@qq.com)</a>
 */
@Data
public class WxMaLiveResult implements Serializable {
  private static final long serialVersionUID = 1L;
  private Integer total;
  private Integer auditId;
  private Integer goodsId;
  private List<Goods> goods;

  /**
   * 直播间列表
   */
  @SerializedName("room_info")
  private List<RoomInfo> roomInfos;

  /**
   * 获取回放源视频列表
   */
  @SerializedName("live_replay")
  private List<LiveReplay> liveReplay;

  public static WxMaLiveResult fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WxMaLiveResult.class);
  }

  /**
   * 商品列表
   */
  @Data
  public static class Goods implements Serializable {
    private static final long serialVersionUID = 5769245932149287574L;
    @SerializedName("goods_id")
    private Integer goodsId;
    /**
     * 获取商品列表返回的商品图片
     */
    @SerializedName("cover_img_url")
    private String coverImgUrl;
    /**
     * 获取直播间列表返回的商品图片
     */
    @SerializedName("cover_img")
    private String coverImg;
    private String name;
    private String url;
    @SerializedName("price_type")
    private Integer priceType;
    /**
     * 0：未审核，1：审核中，2:审核通过，3审核失败
     */
    @SerializedName("audit_status")
    private Integer auditStatus;
    private BigDecimal price;
    private BigDecimal price2;
    /**
     * 1, 2：表示是为api添加商品，否则是在MP添加商品
     */
    @SerializedName("third_party_tag")
    private String thirdPartyTag;
  }

  /**
   * 直播列表
   */
  @Data
  public static class RoomInfo implements Serializable {
    private static final long serialVersionUID = 7745775280267417154L;
    private String name;
    @SerializedName("roomid")
    private Integer roomId;
    @SerializedName("cover_img")
    private String coverImg;
    @SerializedName("share_img")
    private String shareImg;
    @SerializedName("live_status")
    private Integer liveStatus;
    @SerializedName("start_time")
    private Long startTime;
    @SerializedName("end_time")
    private Long endTime;
    @SerializedName("anchor_name")
    private String anchorName;
    @SerializedName("anchor_wechat")
    private String anchorWechat;
    @SerializedName("anchor_img")
    private String anchorImg;
    private Integer type;
    @SerializedName("screen_type")
    private Integer screenType;
    @SerializedName("close_like")
    private Integer closeLike;
    @SerializedName("close_goods")
    private Integer closeGoods;
    @SerializedName("close_comment")
    private Integer closeComment;
    @SerializedName("close_kf")
    private Integer closeKf;
    @SerializedName("close_replay")
    private Integer closeReplay;
    @SerializedName("is_feeds_public")
    private Integer isFeedsPublic;
    @SerializedName("creater_openid")
    private String createrOpenid;
    @SerializedName("feeds_img")
    private String feedsImg;
    private List<Goods> goods;
  }

  /**
   * 回放数据列表
   */
  @Data
  public static class LiveReplay implements Serializable {
    private static final long serialVersionUID = 7683927205627536320L;
    @SerializedName("expire_time")
    private String expireTime;
    @SerializedName("create_time")
    private String createTime;
    @SerializedName("media_url")
    private String mediaUrl;
  }
}
