package cn.binarywang.wx.miniapp.bean;

import cn.binarywang.wx.miniapp.util.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 获取直播房间列表
 *
 * @author yjwang
 * @date 2020/4/5
 */
@Data
public class WxMaGetLiveInfo implements Serializable {
  private static final long serialVersionUID = 7285263767524755887L;
  private Integer errcode;
  private String errmsg;
  private Integer total;
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

  public static WxMaGetLiveInfo fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WxMaGetLiveInfo.class);
  }

  /**
   * 直播列表
   */
  @Data
  public static class RoomInfo implements Serializable {
    private static final long serialVersionUID = 7745775280267417154L;
    private String name;
    private Integer roomid;
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
    @SerializedName("anchor_img")
    private String anchorImg;
    private List<Goods> goods;
  }

  /**
   * 商品列表
   */
  @Data
  public static class Goods implements Serializable {
    private static final long serialVersionUID = 5769245932149287574L;
    @SerializedName("cover_img")
    private String coverImg;
    private String url;
    private String price;
    private String name;
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
