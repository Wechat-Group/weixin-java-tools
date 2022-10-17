package me.chanjar.weixin.cp.bean.oa.meetingroom;


import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import me.chanjar.weixin.common.bean.ToJson;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * The type Wx cp oa meeting room.
 *
 * @author fcat
 * @version 1.0  Create by 2022/8/12 22:46
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpOaMeetingRoom implements Serializable, ToJson {
  private static final long serialVersionUID = 2825289798463742532L;
  /**
   * 会议室Id
   */
  @SerializedName("meetingroom_id")
  private Integer meetingroomId;
  /**
   * 会议室名称，最多30个字符
   */
  @SerializedName("name")
  private String name;
  /**
   * 会议室所能容纳的人数
   */
  @SerializedName("capacity")
  private Integer capacity;
  /**
   * 会议室所在城市
   */
  @SerializedName("city")
  private String city;
  /**
   * 会议室所在楼宇
   */
  @SerializedName("building")
  private String building;
  /**
   * 会议室所在楼层
   */
  @SerializedName("floor")
  private String floor;
  /**
   * 会议室支持的设备列表,参数详细1电视2电话3投影4白板5视频
   */
  @SerializedName("equipment")
  private List<Integer> equipment;
  /**
   * 会议室所在建筑经纬度
   */
  @SerializedName("coordinate")
  private Coordinate coordinate;
  /**
   * 会议室是否需要预定
   */
  @SerializedName("need_approval")
  private Integer needApproval;

  @Override
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  /**
   * The type Coordinate.
   */
  @Data
  @AllArgsConstructor
  public static class Coordinate implements Serializable {
    private static final long serialVersionUID = 6626968559923978694L;
    /**
     * 纬度
     */
    @SerializedName("latitude")
    private String latitude;
    /**
     * 经度
     */
    @SerializedName("longitude")
    private String longitude;
  }
}
