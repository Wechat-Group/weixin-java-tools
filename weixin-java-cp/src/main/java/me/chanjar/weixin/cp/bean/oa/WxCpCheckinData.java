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

  @SerializedName("userid")
  private String userId;

  @SerializedName("groupname")
  private String groupName;

  @SerializedName("checkin_type")
  private String checkinType;

  @SerializedName("exception_type")
  private String exceptionType;

  @SerializedName("checkin_time")
  private Long checkinTime;

  @SerializedName("location_title")
  private String locationTitle;

  @SerializedName("location_detail")
  private String locationDetail;

  @SerializedName("wifiname")
  private String wifiName;

  @SerializedName("wifimac")
  private String wifiMac;

  private String notes;

  @SerializedName("mediaids")
  private List<String> mediaIds;
}
