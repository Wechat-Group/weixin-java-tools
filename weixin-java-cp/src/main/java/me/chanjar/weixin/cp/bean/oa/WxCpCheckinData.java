package me.chanjar.weixin.cp.bean.oa;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 企业微信打卡数据.
 *
 * @author Element
 * @date 2019-04-06 11:01
 */
@Data
public class WxCpCheckinData implements Serializable {
  private static final long serialVersionUID = 1915820330847799605L;

  /**
   * userid	用户id
   */
  @SerializedName("userid")
  private String userId;

  /**
   * groupname	打卡规则名称
   */
  @SerializedName("groupname")
  private String groupName;

  /**
   * checkin_type	打卡类型。字符串，目前有：上班打卡，下班打卡，外出打卡
   */
  @SerializedName("checkin_type")
  private String checkinType;

  /**
   * exception_type	异常类型，字符串，包括：时间异常，地点异常，未打卡，wifi异常，非常用设备。如果有多个异常，以分号间隔
   */
  @SerializedName("exception_type")
  private String exceptionType;

  /**
   * checkin_time	打卡时间。Unix时间戳
   */
  @SerializedName("checkin_time")
  private Long checkinTime;

  /**
   * location_title	打卡地点title
   */
  @SerializedName("location_title")
  private String locationTitle;

  /**
   * location_detail	打卡地点详情
   */
  @SerializedName("location_detail")
  private String locationDetail;

  /**
   * wifiname	打卡wifi名称
   */
  @SerializedName("wifiname")
  private String wifiName;

  /**
   * wifimac	打卡的MAC地址/bssid
   */
  @SerializedName("wifimac")
  private String wifiMac;

  /**
   * notes	打卡备注
   */
  private String notes;

  /**
   * mediaids	打卡的附件media_id，可使用media/get获取附件
   */
  @SerializedName("mediaids")
  private List<String> mediaIds;

  /**
   * lat	位置打卡地点纬度，是实际纬度的1000000倍，与腾讯地图一致采用GCJ-02坐标系统标准
   */
  private Integer lat;

  /**
   * lng	位置打卡地点经度，是实际经度的1000000倍，与腾讯地图一致采用GCJ-02坐标系统标准
   */
  private Integer lng;

  /**
   * deviceid	打卡设备id
   */
  @SerializedName("deviceid")
  private String deviceId;
}
