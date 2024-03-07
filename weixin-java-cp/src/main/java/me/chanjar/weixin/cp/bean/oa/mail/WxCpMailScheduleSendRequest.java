package me.chanjar.weixin.cp.bean.oa.mail;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 发送日程邮件请求.
 *
 * @author Hugo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpMailScheduleSendRequest implements Serializable {
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
   * 标题
   */
  @SerializedName("schedule")
  private Schedule schedule;

  @Getter
  @Setter
  public static class Schedule implements Serializable {
    private static final long serialVersionUID = -4860239393895754598L;

    /**
     * 日程ID (修改/取消日程必须带上schedule_id)
     */
    @SerializedName("is_remind")
    private String scheduleId;

    /**
     * 日程方法：
     * request-请求（不传schedule_id时是创建日程，传了是修改日程）
     * <p>
     * cancel-取消日程（必须带上schedule_id）
     * <p>
     * 默认为request
     */
    @SerializedName("method")
    private String method;

    /**
     * 地点
     */
    @SerializedName("location")
    private String location;

    /**
     * 日程开始时间，Unix时间戳
     */
    @SerializedName("start_time")
    private Integer startTime;

    /**
     * 日程结束时间，Unix时间戳
     */
    @SerializedName("end_time")
    private Integer endTime;

    /**
     * 重复和提醒相关字段
     */
    @SerializedName("reminders")
    private Reminders reminders;


    /**
     * From json space info.
     *
     * @param json the json
     * @return the space info
     */
    public static Schedule fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, Schedule.class);
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
  public static class Reminders implements Serializable {
    private static final long serialVersionUID = -4860239393895754598L;

    /**
     * 是否有提醒 0-不提醒 1-提醒
     */
    @SerializedName("is_remind")
    private Integer isRemind;

    /**
     * 日程开始（start_time）前多少分钟提醒，当is_remind=1时有效。例如：
     * 15表示日程开始前15分钟提醒
     * <p>
     * -15表示日程开始后15分钟提醒
     */
    @SerializedName("remind_before_event_mins")
    private Integer remindBeforeEventMins;

    /**
     * 是否重复 0-否 1-是
     */
    @SerializedName("is_repeat")
    private Integer isRepeat;

    /**
     * 是否自定义重复 0-否 1-是。当is_repeat为1时有效。
     */
    @SerializedName("is_custom_repeat")
    private Integer isCustomRepeat;

    /**
     * 时区。UTC偏移量表示(即偏离零时区的小时数)，东区为正数，西区为负数。
     * 例如：+8 表示北京时间东八区
     * <p>
     * 默认为北京时间东八区
     * <p>
     * 取值范围：-12 ~ +12
     */
    @SerializedName("timezone")
    private Integer timeZone;

    /**
     * 重复间隔
     * 仅当指定为自定义重复时有效，该字段随repeat_type不同而含义不同
     * <p>
     * 例如：
     * <p>
     * repeat_interval指定为2，repeat_type指定为每周重复，那么每2周重复一次；
     * <p>
     * repeat_interval指定为2，repeat_type指定为每月重复，那么每2月重复一次
     */
    @SerializedName("repeat_interval")
    private Integer repeatInterval;

    /**
     * 重复类型，当is_repeat=1时有效。目前支持如下类型：
     * 0 - 每日
     * <p>
     * 1 - 每周
     * <p>
     * 2 - 每月
     * <p>
     * 5 - 每年
     */
    @SerializedName("repeat_type")
    private Integer repeatType;

    /**
     * 每周周几重复
     * 仅当指定为自定义重复且重复类型为每周时有效
     * <p>
     * 取值范围：1 ~ 7，分别表示周一至周日
     */
    @SerializedName("repeat_day_of_week")
    private List<Integer> repeatDayOfWeek;

    /**
     * 每月哪几天重复
     * 仅当指定为自定义重复, 且重复类型为每月或每年时有效
     * <p>
     * 取值范围：1 ~ 31，分别表示1~31号
     */
    @SerializedName("repeat_day_of_month")
    private List<String> repeatDayOfMonth;

    /**
     * 标题
     */
    @SerializedName("repeat_week_of_month")
    private List<String> repeatWeekOfMonth;

    /**
     * 每年哪几个月重复
     * 仅当指定为自定义重复且重复类型为每年时有效
     * <p>
     * 取值范围：1 ~ 12，分别表示 1月 - 12月（每年重复需要repeat_month_of_year和repeat_day_of_month来指定某一天）
     */
    @SerializedName("repeat_month_of_year")
    private List<String> repeatMonthOfYear;

    /**
     * 重复结束时刻，Unix时间戳，当is_repeat=1时有效。不填或填0表示一直重复
     */
    @SerializedName("repeat_until")
    private Integer repeatUntil;

    /**
     * From json space info.
     *
     * @param json the json
     * @return the space info
     */
    public static Reminders fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, Reminders.class);
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

  /**
   * From json wx cp space create request.
   *
   * @param json the json
   * @return the wx cp space create request
   */
  public static WxCpMailScheduleSendRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpMailScheduleSendRequest.class);
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
