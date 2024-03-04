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
public class WxCpMailMeetingSendRequest implements Serializable {
  private static final long serialVersionUID = -4961279393895454138L;


  /**
   * 收件人，to.emails 和 to.userids 至少传一个
   */
  @SerializedName("to")
  private TO to;

  /**
   * 抄送
   */
  @SerializedName("cc")
  private CC cc;

  /**
   * 文档类型, 3:文档 4:表格
   */
  @SerializedName("bcc")
  private BCC bcc;

  /**
   * 标题
   */
  @SerializedName("subject")
  private String subject;

  /**
   * 内容
   */
  @SerializedName("content")
  private String content;

  /**
   * 附件相关
   */
  @SerializedName("attachment_list")
  private List<Attachment> attachmentList;

  /**
   * 内容类型 html，text（默认是html）
   */
  @SerializedName("content_type")
  private String contentType;

  /**
   * 表示是否开启id转译，0表示否，1表示是，默认0。仅第三方应用需要用到，企业自建应用可以忽略。
   * 目前仅subject、content、attachment_list[].file_name字段支持转译。
   */
  @SerializedName("enable_id_trans")
  private Integer enableIdTrans;


  /**
   * 会议相关，会议邮件必填，且必须同时带上schedule，会议的基本设置放在schedule里
   */
  @SerializedName("meeting")
  private Meeting meeting;


  @Getter
  @Setter
  public static class TO implements Serializable {
    private static final long serialVersionUID = -4860239393895754598L;

    /**
     * 收件人，邮箱地址
     */
    @SerializedName("emails")
    private List<String> emails;

    /**
     * 收件人，企业内成员的userid
     */
    @SerializedName("userids")
    private List<String> userIds;

    /**
     * From json space info.
     *
     * @param json the json
     * @return the space info
     */
    public static TO fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, TO.class);
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
  public static class CC implements Serializable {
    private static final long serialVersionUID = -4863239393895754598L;

    /**
     * 抄送人，邮箱地址
     */
    @SerializedName("emails")
    private List<String> emails;

    /**
     * 抄送人，企业内成员的userid
     */
    @SerializedName("userids")
    private List<String> userIds;

    /**
     * From json space info.
     *
     * @param json the json
     * @return the space info
     */
    public static CC fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, CC.class);
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
  public static class BCC implements Serializable {
    private static final long serialVersionUID = -4860239393885754598L;

    /**
     * 密送人，邮箱地址
     */
    @SerializedName("emails")
    private List<String> emails;

    /**
     * 密送人，企业内成员的userid
     */
    @SerializedName("userids")
    private List<String> userIds;

    /**
     * From json space info.
     *
     * @param json the json
     * @return the space info
     */
    public static BCC fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, BCC.class);
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
  public static class Attachment implements Serializable {
    private static final long serialVersionUID = -4860230393895754598L;

    /**
     * 文件名
     */
    @SerializedName("file_name")
    private String fileName;

    /**
     * 文件内容（base64编码），所有附件加正文的大小不允许超过50M, 且附件个数不能超过200个
     */
    @SerializedName("content")
    private String content;

    /**
     * From json space info.
     *
     * @param json the json
     * @return the space info
     */
    public static Attachment fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, Attachment.class);
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
