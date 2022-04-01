package me.chanjar.weixin.cp.bean.oa;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 企业微信企业所有打卡规则.
 *
 * @author Liuwm
 */
@Data
public class WxCpCropCheckinOption implements Serializable {
  private static final long serialVersionUID = 1725954575430704232L;

  /**
   * 打卡规则类型，1：固定时间上下班；2：按班次上下班；3：自由上下班
   */
  @SerializedName("grouptype")
  private Long groupType;

  /**
   * 打卡规则id
   */
  @SerializedName("groupid")
  private Long groupId;

  /**
   * 打卡规则名称
   */
  @SerializedName("groupname")
  private String groupName;

  /**
   * 打卡时间，当规则类型为排班时没有意义
   */
  @SerializedName("checkindate")
  private List<CheckinDate> checkinDate;

  /**
   * 特殊日期-必须打卡日期信息，timestamp表示具体时间
   */
  @SerializedName("spe_workdays")
  private List<SpeWorkday> speWorkdays;

  /**
   * 特殊日期-不用打卡日期信息， timestamp表示具体时间
   */
  @SerializedName("spe_offdays")
  private List<SpeOffDay> speOffDays;

  /**
   * 是否同步法定节假日，true为同步，false为不同步，当前排班不支持
   */
  @SerializedName("sync_holidays")
  private Boolean syncHolidays;

  /**
   * 是否打卡必须拍照，true为必须拍照，false为不必须拍照
   */
  @SerializedName("need_photo")
  private Boolean needPhoto;

  /**
   * 是否备注时允许上传本地图片，true为允许，false为不允许
   */
  @SerializedName("note_can_use_local_pic")
  private Boolean noteCanUseLocalPic;

  /**
   * 是否非工作日允许打卡,true为允许，false为不允许
   */
  @SerializedName("allow_checkin_offworkday")
  private Boolean allowCheckinOffWorkDay;

  /**
   * 是否允许提交补卡申请，true为允许，false为不允许
   */
  @SerializedName("allow_apply_offworkday")
  private Boolean allowApplyOffWorkDay;

  /**
   * 打卡地点-WiFi打卡信息
   */
  @SerializedName("wifimac_infos")
  private List<WifiMacInfo> wifiMacInfos;

  /**
   * 打卡地点-WiFi打卡信息
   */
  @SerializedName("loc_infos")
  private List<LocInfo> locInfos;

  /**
   * 打卡人员信息
   */
  @SerializedName("range")
  private Range range;

  /**
   * 创建打卡规则时间，为unix时间戳
   */
  @SerializedName("create_time")
  private Long createTime;

  /**
   * 打卡人员白名单，即不需要打卡人员，需要有设置白名单才能查看
   */
  @SerializedName("white_users")
  private List<String> whiteUsers;

  /**
   * 打卡方式，0:手机；2:智慧考勤机；3:手机+智慧考勤机
   */
  @SerializedName("type")
  private Integer type;

  /**
   * 打卡方式，0:手机；2:智慧考勤机；3:手机+智慧考勤机
   */
  @SerializedName("reporterinfo")
  private ReporterInfo reporterInfo;

  /**
   * 加班信息，相关信息需要设置后才能显示
   */
  @SerializedName("ot_info")
  private OtInfo otInfo;

  /**
   * 每月最多补卡次数，默认-1表示不限制
   */
  @SerializedName("allow_apply_bk_cnt")
  private Integer allowApplyBkCnt;

  /**
   * 范围外打卡处理方式，0-视为范围外异常，默认值；1-视为正常外勤；2:不允许范围外打卡
   */
  @SerializedName("option_out_range")
  private Integer optionOutRange;

  /**
   * 规则创建人userid
   */
  @SerializedName("create_userid")
  private String createUserid;

  /**
   * 人脸识别打卡开关，true为启用，false为不启用
   */
  @SerializedName("use_face_detect")
  private Boolean useFaceDetect;

  /**
   * 允许补卡时限，默认-1表示不限制。单位天
   */
  @SerializedName("allow_apply_bk_day_limit")
  private Integer allowApplyBkDayLimit;

  /**
   * 规则最近编辑人userid
   */
  @SerializedName("update_userid")
  private String updateUserid;

  /**
   * 加班信息，相关信息需要设置后才能显示
   */
  @SerializedName("schedulelist")
  private List<Schedule> schedulelist;


  /**
   * 自由签到，上班打卡后xx秒可打下班卡
   */
  @SerializedName("offwork_interval_time")
  private Integer offWorkIntervalTime;


  @Data
  public static class CheckinDate implements Serializable {
    private static final long serialVersionUID = -8560643656775167406L;
    /**
     * 工作日。若为固定时间上下班或自由上下班，则1到6分别表示星期一到星期六，0表示星期日
     */
    @SerializedName("workdays")
    private List<Integer> workdays;

    /**
     * 工作日上下班打卡时间信息
     */
    @SerializedName("checkintime")
    private List<CheckinTime> checkinTime;

    /**
     * 下班不需要打卡，true为下班不需要打卡，false为下班需要打卡
     */
    @SerializedName("noneed_offwork")
    private Boolean noneedOffwork;

    /**
     * 打卡时间限制（毫秒）
     */
    @SerializedName("limit_aheadtime")
    private Long limitAheadtime;

    /**
     * 允许迟到时间，单位ms
     */
    @SerializedName("flex_on_duty_time")
    private Integer flexOnDutyTime;

    /**
     * 允许早退时间，单位ms
     */
    @SerializedName("flex_off_duty_time")
    private Integer flexOffDutyTime;
  }

  @Data
  public static class CheckinTime implements Serializable {

    private static final long serialVersionUID = -5507709858609705279L;
    /**
     * 上班时间，表示为距离当天0点的秒数。
     */
    @SerializedName("work_sec")
    private Integer workSec;

    /**
     * 下班时间，表示为距离当天0点的秒数。
     */
    @SerializedName("off_work_sec")
    private Integer offWorkSec;

    /**
     * 上班提醒时间，表示为距离当天0点的秒数。。
     */
    @SerializedName("remind_work_sec")
    private Integer remindWorkSec;

    /**
     * 下班提醒时间，表示为距离当天0点的秒数。
     */
    @SerializedName("remind_off_work_sec")
    private Integer remindOffWorkSec;
  }

  @Data
  public static class SpeWorkday implements Serializable {

    private static final long serialVersionUID = -4620710297258742666L;
    /**
     * 特殊日期-必须打卡日期时间戳
     */
    @SerializedName("timestamp")
    private Long timestamp;

    /**
     * 特殊日期备注
     */
    @SerializedName("notes")
    private String notes;

    /**
     * 特殊日期-必须打卡日期-上下班打卡时间
     */
    @SerializedName("checkintime")
    private List<CheckinTime> checkinTime;
  }

  @Data
  public static class SpeOffDay implements Serializable {
    private static final long serialVersionUID = 9214798931489490993L;
    /**
     * 特殊日期-不用打卡日期时间戳
     */
    @SerializedName("timestamp")
    private Long timestamp;

    /**
     * 特殊日期备注
     */
    @SerializedName("notes")
    private String notes;
  }

  @Data
  public static class WifiMacInfo implements Serializable {

    private static final long serialVersionUID = 6742659716677227089L;

    /**
     * WiFi打卡地点名称
     */
    @SerializedName("wifiname")
    private String wifiname;

    /**
     * WiFi打卡地点MAC地址/bssid
     */
    @SerializedName("wifimac")
    private String wifimac;
  }

  @Data
  public static class LocInfo implements Serializable {

    private static final long serialVersionUID = -5591379191341944101L;
    /**
     * 位置打卡地点纬度，是实际纬度的1000000倍，与腾讯地图一致采用GCJ-02坐标系统标准
     */
    @SerializedName("lat")
    private Long lat;

    /**
     * 位置打卡地点经度，是实际经度的1000000倍，与腾讯地图一致采用GCJ-02坐标系统标准
     */
    @SerializedName("lng")
    private Long lng;

    /**
     * 位置打卡地点名称
     */
    @SerializedName("loc_title")
    private String locTitle;

    /**
     * 位置打卡地点详情
     */
    @SerializedName("loc_detail")
    private String locDetail;

    /**
     * 允许打卡范围（米）
     */
    @SerializedName("distance")
    private Integer distance;
  }

  @Data
  public static class Range implements Serializable {

    private static final long serialVersionUID = 8940086218556453088L;

    /**
     * 打卡人员中，单个打卡人员节点的userid
     */
    @SerializedName("party_id")
    private List<String> partyid;

    /**
     * 打卡人员中，部门节点的id
     */
    @SerializedName("userid")
    private List<String> userid;

    /**
     * 打卡人员中，标签节点的标签id
     */
    @SerializedName("tagid")
    private List<Integer> tagid;


  }

  @Data
  public static class ReporterInfo implements Serializable {
    private static final long serialVersionUID = 1132450350458936772L;
    /**
     * 汇报对象，每个汇报人用userid表示
     */
    @SerializedName("reporters")
    private List<Reporter> reporters;

    /**
     * 汇报对象更新时间
     */
    @SerializedName("updatetime")
    private long updateTime;
  }

  @Data
  public static class Reporter implements Serializable {

    private static final long serialVersionUID = 4925417850482005397L;

    @SerializedName("userid")
    private String userid;
  }

  @Data
  public static class OtInfo implements Serializable {

    private static final long serialVersionUID = 1610150484871066199L;

    /**
     * 加班类型
     * 0：以加班申请核算打卡记录（根据打卡记录和加班申请核算）,
     * 1：以打卡时间为准（根据打卡时间计算），
     * 2: 以加班申请审批为准（只根据加班申请计算）
     */
    @SerializedName("type")
    private Integer type;

    /**
     * 允许工作日加班，true为允许，false为不允许
     */
    @SerializedName("allow_ot_workingday")
    private Boolean allowOtWorkingDay;

    /**
     * 允许非工作日加班，true为允许，flase为不允许
     */
    @SerializedName("allow_ot_nonworkingday")
    private Boolean allowOtNonworkingDay;

    /**
     * 允许非工作日加班，true为允许，flase为不允许
     */
    @SerializedName("otcheckinfo")
    private OtCheckInfo otcheckinfo;

    /**
     * 更新时间
     */
    @SerializedName("uptime")
    private Long uptime;

    /**
     * 允许非工作日加班，true为允许，flase为不允许
     */
    @SerializedName("otapplyinfo")
    private OtApplyInfo otapplyinfo;
  }

  @Data
  public static class OtCheckInfo implements Serializable {

    private static final long serialVersionUID = -2363047492489556390L;

    /**
     * 允许工作日加班-加班开始时间：下班后xx秒开始计算加班，距离最晚下班时间的秒数，例如，1800（30分钟 乘以 60秒），默认值30分钟
     */
    @SerializedName("ot_workingday_time_start")
    private Integer otWorkingDayTimeStart;

    /**
     * 允许工作日加班-最短加班时长：不足xx秒视为未加班，单位秒，默认值30分钟
     */
    @SerializedName("ot_workingday_time_min")
    private Integer otWorkingDayTimeMin;

    /**
     * 允许工作日加班-最长加班时长：超过则视为加班xx秒，单位秒，默认值240分钟
     */
    @SerializedName("ot_workingday_time_max")
    private Integer otWorkingDayTimeMax;

    /**
     * 允许非工作日加班-最短加班时长：不足xx秒视为未加班，单位秒，默认值30分钟
     */
    @SerializedName("ot_nonworkingday_time_min")
    private Integer otNonworkingDayTimeMin;

    /**
     * 允许非工作日加班-最长加班时长：超过则视为加班xx秒 单位秒，默认值240分钟
     */
    @SerializedName("ot_nonworkingday_time_max")
    private Integer otNonworkingDayTimeMax;

    /**
     * 非工作日加班，跨天时间，距离当天00:00的秒数
     */
    @SerializedName("ot_nonworkingday_spanday_time")
    private Integer otNonworkingDaySpanDayTime;

    /**
     * 允许非工作日加班，true为允许，flase为不允许
     */
    @SerializedName("ot_workingday_restinfo")
    private OtWorkingDayRestInfo otWorkingdayRestinfo;

    /**
     * 允许非工作日加班，true为允许，flase为不允许
     */
    @SerializedName("ot_nonworkingday_restinfo")
    private OtNonworkingDayRestInfo otNonworkingdayRestinfo;
  }

  @Data
  public static class OtWorkingDayRestInfo implements Serializable {

    private static final long serialVersionUID = -4011047369711928306L;

    /**
     * 工作日加班-休息扣除类型：0-不开启扣除；1-指定休息时间扣除；2-按加班时长扣除休息时间
     */
    @SerializedName("type")
    private Integer type;

    /**
     * 工作日加班-指定休息时间配置信息，当group.ot_info.otcheckinfo.ot_workingday_restinfo.type为1时有意义
     */
    @SerializedName("fix_time_rule")
    private FixTimeRule fixTimeRule;

    /**
     * 工作日加班-按加班时长扣除配置信息，当group.ot_info.otcheckinfo.ot_workingday_restinfo.type为2时有意义
     */
    @SerializedName("cal_ottime_rule")
    private CalOtTimeRule calOttimeRule;
  }

  @Data
  public static class FixTimeRule implements Serializable {

    private static final long serialVersionUID = 5709478500196619664L;

    /**
     * 工作日加班-指定休息时间的开始时间， 距离当天00:00的秒数
     */
    @SerializedName("fix_time_begin_sec")
    private Integer fixTimeBeginSec;

    /**
     * 工作日加班-指定休息时间的结束时间， 距离当天00:00的秒数
     */
    @SerializedName("fix_time_end_sec")
    private Integer fixTimeEndSec;
  }

  @Data
  public static class CalOtTimeRule implements Serializable {

    private static final long serialVersionUID = -2407839982631243413L;

    /**
     * 工作日加班-按加班时长扣除条件信息
     */
    @SerializedName("items")
    private List<Item> items;

  }

  @Data
  public static class Item implements Serializable {

    private static final long serialVersionUID = 5235770378506228461L;

    /**
     * 加班满-时长（秒）
     */
    @SerializedName("ot_time")
    private Integer otTime;

    /**
     * 对应扣除-时长（秒）
     */
    @SerializedName("rest_time")
    private Integer restTime;
  }

  @Data
  public static class OtNonworkingDayRestInfo implements Serializable {

    private static final long serialVersionUID = 3773846077049838088L;

    /**
     * 非工作日加班-休息扣除类型：0-不开启扣除；1-指定休息时间扣除；2-按加班时长扣除休息时间
     */
    @SerializedName("type")
    private Integer type;

    /**
     * 非工作日加班-指定休息时间配置信息，当group.ot_info.otcheckinfo.ot_workingday_restinfo.type为1时有意义
     */
    @SerializedName("fix_time_rule")
    private FixTimeRule fixTimeRule;

    /**
     * 非工作日加班-按加班时长扣除配置信息，当group.ot_info.otcheckinfo.ot_workingday_restinfo.type为2时有意义
     */
    @SerializedName("cal_ottime_rule")
    private CalOtTimeRule calOttimeRule;
  }

  @Data
  public static class OtApplyInfo implements Serializable {

    private static final long serialVersionUID = 961217471918884103L;

    /**
     * 允许工作日加班，true为允许，false为不允许
     */
    @SerializedName("allow_ot_workingday")
    private Boolean allowOtWorkingDay;

    /**
     * 允许非工作日加班，true为允许，flase为不允许
     */
    @SerializedName("allow_ot_nonworkingday")
    private Boolean allowOtNonworkingDay;

    /**
     * 更新时间
     */
    @SerializedName("uptime")
    private Long uptime;

    /**
     * 允许非工作日加班，true为允许，flase为不允许
     */
    @SerializedName("ot_workingday_restinfo")
    private OtWorkingDayRestInfo otWorkingdayRestinfo;

    /**
     * 允许非工作日加班，true为允许，flase为不允许
     */
    @SerializedName("ot_nonworkingday_restinfo")
    private OtNonworkingDayRestInfo otNonworkingdayRestinfo;

    /**
     * 非工作日加班，跨天时间，距离当天00:00的秒数
     */
    @SerializedName("ot_nonworkingday_spanday_time")
    private Integer otNonworkingDaySpanDayTime;

  }

  @Data
  public static class Schedule implements Serializable {

    private static final long serialVersionUID = -2461113644925307266L;

    /**
     * 班次id
     */
    @SerializedName("schedule_id")
    private Integer scheduleId;

    /**
     * 班次名称
     */
    @SerializedName("schedule_name")
    private String scheduleName;

    /**
     * 班次上下班时段信息
     */
    @SerializedName("time_section")
    private List<TimeSection> timeSection;

    /**
     * 允许提前打卡时间
     */
    @SerializedName("limit_aheadtime")
    private Long limitAheadTime;

    /**
     * 下班xx秒后不允许打下班卡
     */
    @SerializedName("limit_offtime")
    private Integer limitOffTime;

    /**
     * 下班不需要打卡
     */
    @SerializedName("noneed_offwork")
    private Boolean noNeedOffWork;

    /**
     * 是否允许弹性时间
     */
    @SerializedName("allow_flex")
    private Boolean allowFlex;

    /**
     * 允许迟到时间
     */
    @SerializedName("flex_on_duty_time")
    private Integer flexOnDutyTime;

    /**
     * 允许早退时间
     */
    @SerializedName("flex_off_duty_time")
    private Integer flexOffDutyTime;

    /**
     * 非工作日加班，跨天时间，距离当天00:00的秒数
     */
    @SerializedName("late_rule")
    private LateRule lateRule;

    /**
     * 最早可打卡时间限制
     */
    @SerializedName("max_allow_arrive_early")
    private Integer maxAllowArriveEarly;

    /**
     * 最晚可打卡时间限制，max_allow_arrive_early、max_allow_arrive_early与flex_on_duty_time、flex_off_duty_time互斥，当设置其中一组时，另一组数值置0
     */
    @SerializedName("max_allow_arrive_late")
    private Integer maxAllowArriveLate;

  }


  @Data
  public static class TimeSection implements Serializable {
    private static final long serialVersionUID = 7497252128339062724L;

    /**
     * 时段id，为班次中某一堆上下班时间组合的id
     */
    @SerializedName("time_id")
    private Integer timeId;

    /**
     * 上班时间，表示为距离当天0点的秒数。
     */
    @SerializedName("work_sec")
    private Integer workSec;

    /**
     * 下班时间，表示为距离当天0点的秒数。
     */
    @SerializedName("off_work_sec")
    private Integer offWorkSec;

    /**
     * 上班提醒时间，表示为距离当天0点的秒数。
     */
    @SerializedName("remind_work_sec")
    private Long remindWorkSec;

    /**
     * 下班提醒时间，表示为距离当天0点的秒数。
     */
    @SerializedName("remind_off_work_sec")
    private Integer remindOffWorkSec;

    /**
     * 休息开始时间，仅单时段支持，距离0点的秒
     */
    @SerializedName("rest_begin_time")
    private Integer restBeginTime;

    /**
     * 休息结束时间，仅单时段支持，距离0点的秒
     */
    @SerializedName("rest_end_time")
    private Integer restEndTime;

    /**
     * 是否允许休息
     */
    @SerializedName("allow_rest")
    private Boolean allowRest;
  }


  @Data
  public static class LateRule implements Serializable {

    private static final long serialVersionUID = 5604969713950037053L;


    /**
     * 是否允许超时下班（下班晚走次日晚到）允许时onwork_flex_time，offwork_after_time才有意义
     */
    @SerializedName("allow_offwork_after_time")
    private Boolean allowOffWorkAfterTime;

    /**
     * 迟到规则时间
     */
    @SerializedName("timerules")
    private List<TimeRule> timerules;
  }

  @Data
  public static class TimeRule implements Serializable {

    private static final long serialVersionUID = 5680614050081598333L;

    /**
     * 晚走的时间 距离最晚一个下班的时间单位：秒
     */
    @SerializedName("offwork_after_time")
    private Integer offWorkAfterTime;

    /**
     * 第二天第一个班次允许迟到的弹性时间单位：秒
     */
    @SerializedName("onwork_flex_time")
    private Integer onWorkFlexTime;

  }
}
