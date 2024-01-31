package me.chanjar.weixin.cp.bean.oa.mail;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 发送会议邮件请求.
 *
 * @author Hugo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpMailMeetingSendRequest extends WxCpMailScheduleSendRequest implements Serializable {
  private static final long serialVersionUID = -4961279393895454138L;

  /**
   * 会议相关，会议邮件必填，且必须同时带上schedule，会议的基本设置放在schedule里
   */
  @SerializedName("meeting")
  private Meeting meeting;

  @Getter
  @Setter
  public static class Meeting implements Serializable {
    private static final long serialVersionUID = -4860239393895754598L;

    /**
     * 会议相关设置
     */
    @SerializedName("option")
    private Option option;

    /**
     * 会议主持人列表，最多10个。定义见收件人字段，只支持填userid
     */
    @SerializedName("hosts")
    private Hosts hosts;

    /**
     * 会议管理员字段, , 仅可指定1人，只支持传userid，必须是同企业的用户，且在参与人中
     */
    @SerializedName("meeting_admins")
    private MeetingAdmins meetingAdmins;

    /**
     * From json space info.
     *
     * @param json the json
     * @return the space info
     */
    public static WxCpMailMeetingSendRequest.Meeting fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, WxCpMailMeetingSendRequest.Meeting.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  @Getter
  @Setter
  public static class Option implements Serializable {
    private static final long serialVersionUID = -4860239393895754598L;

    /**
     * 入会密码，仅可输入4-6位纯数字
     */
    @SerializedName("password")
    private String password;

    /**
     * 是否自动录制
     * 0：未开启自动录制，1：开启自动本地录制，2：开启自动云录制；默认不开启
     */
    @SerializedName("auto_record")
    private Integer autoRecord;

    /**
     * 是否开启等候室
     * false:不开启等候室；true:开启等候室；默认不开
     */
    @SerializedName("enable_waiting_room")
    private Boolean enableWaitingRoom;

    /**
     * 是否允许成员在主持人进会前加入。
     * true:允许；false:不允许。默认允许
     */
    @SerializedName("allow_enter_before_host")
    private Boolean allowEnterBeforeHost;

    /**
     * 是否限制成员入会
     * 0:所有人可入会 2:仅企业内部用户可入会；默认所有人可入会
     */
    @SerializedName("enter_restraint")
    private Integer enterRestraint;

    /**
     * 是否开启屏幕水印
     * true:开启；false:不开启。默认不开启
     */
    @SerializedName("enable_screen_watermark")
    private Boolean enableScreenWatermark;

    /**
     * 成员入会时是否静音
     * 1:开启；0:关闭；2:超过6人后自动开启静音。默认超过6人自动开启静音
     */
    @SerializedName("enable_enter_mute")
    private Integer enableEnterMute;

    /**
     * 会议开始时是否提醒
     * 1:不提醒 2:仅提醒主持人 3:提醒所有成员入会; 默认仅提醒主持人
     */
    @SerializedName("remind_scope")
    private Integer remindScope;

    /**
     * 水印类型
     * 0:单排水印 1:多排水印；默认单排水印
     */
    @SerializedName("water_mark_type")
    private Integer waterMarkType;

    /**
     * From json space info.
     *
     * @param json the json
     * @return the space info
     */
    public static WxCpMailMeetingSendRequest.Option fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, WxCpMailMeetingSendRequest.Option.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  @Getter
  @Setter
  public static class Hosts implements Serializable {
    private static final long serialVersionUID = -4860239393895754598L;

    @SerializedName("userids")
    private List<String> userIds;

    /**
     * From json space info.
     *
     * @param json the json
     * @return the space info
     */
    public static WxCpMailMeetingSendRequest.Hosts fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, WxCpMailMeetingSendRequest.Hosts.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  @Getter
  @Setter
  public static class MeetingAdmins implements Serializable {
    private static final long serialVersionUID = -4860239393895754598L;

    @SerializedName("userids")
    private List<String> userIds;

    /**
     * From json space info.
     *
     * @param json the json
     * @return the space info
     */
    public static WxCpMailMeetingSendRequest.MeetingAdmins fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, WxCpMailMeetingSendRequest.MeetingAdmins.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  /**
   * From json wx cp space create request.
   *
   * @param json the json
   * @return the wx cp space create request
   */
  public static WxCpMailMeetingSendRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpMailMeetingSendRequest.class);
  }

  /**
   * To json string.
   *
   * @return the string
   */
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
