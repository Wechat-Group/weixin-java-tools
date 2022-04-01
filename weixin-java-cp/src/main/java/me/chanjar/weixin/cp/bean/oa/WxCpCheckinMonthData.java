package me.chanjar.weixin.cp.bean.oa;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 企业微信打卡月报数据
 *
 * @author longliveh
 */

@Data
public class WxCpCheckinMonthData implements Serializable {
  private static final long serialVersionUID = -3062328201807894236L;

  /**
   * baseInfo 基础信息
   */
  @SerializedName("base_info")
  private BaseInfo baseInfo;

  @Data
  public static class BaseInfo implements Serializable {
    private static final long serialVersionUID = -5368331890851903885L;

    /**
     * record_type 记录类型：1-固定上下班；2-外出（此报表中不会出现外出打卡数据）；3-按班次上下班；4-自由签到；5-加班；7-无规则
     */
    @SerializedName("record_type")
    private Integer recordType;

    /**
     * name 打卡人员姓名
     */
    @SerializedName("name")
    private String name;

    /**
     * name_ex 打卡人员别名
     */
    @SerializedName("name_ex")
    private String nameEx;

    /**
     * departs_name 打卡人员所在部门，会显示所有所在部门
     */
    @SerializedName("departs_name")
    private String departsName;

    /**
     * acctid 打卡人员帐号，即userid
     */
    @SerializedName("acctid")
    private String acctId;

    /**
     * rule_info 打卡人员所属规则信息
     */
    @SerializedName("rule_info")
    private RuleInfo ruleInfo;

    @Data
    public static class RuleInfo implements Serializable {
      private static final long serialVersionUID = 9152263355916880710L;

      /**
       * groupid 所属规则Id
       */
      @SerializedName("groupid")
      private Integer groupId;

      /**
       * groupname 所属规则Id
       */
      @SerializedName("groupname")
      private String groupName;
    }
  }

  /**
   * summary_info 打卡人员所属规则信息
   */
  @SerializedName("summary_info")
  private SummaryInfo summaryInfo;

  @Data
  public static class SummaryInfo implements Serializable {
    private static final long serialVersionUID = -1956770107240513983L;
    /**
     * work_days 应打卡天数
     */
    @SerializedName("work_days")
    private Integer workDays;

    /**
     * regular_days 正常天数
     */
    @SerializedName("regular_days")
    private Integer regularDays;

    /**
     * except_days 异常天数
     */
    @SerializedName("except_days")
    private Integer exceptDays;

    /**
     * regular_work_sec 实际工作时长，为统计周期每日实际工作时长之和
     */
    @SerializedName("regular_work_sec")
    private Integer regularWorkSec;

    /**
     * standard_work_sec 正常天数
     */
    @SerializedName("standard_work_sec")
    private Integer standardWorkSec;

  }

  /**
   * exception_infos 异常状态统计信息
   */
  @SerializedName("exception_infos")
  private List<ExceptionInfo> exceptionInfos;

  @Data
  public static class ExceptionInfo implements Serializable {
    private static final long serialVersionUID = -4855850255704089359L;
    /**
     * exception 异常类型：1-迟到；2-早退；3-缺卡；4-旷工；5-地点异常；6-设备异常
     */
    @SerializedName("exception")
    private Integer exception;

    /**
     * count 异常次数，为统计周期内每日此异常次数之和
     */
    @SerializedName("count")
    private Integer count;

    /**
     * duration 异常时长（迟到/早退/旷工才有值），为统计周期内每日此异常时长之和
     */
    @SerializedName("duration")
    private Integer duration;
  }

  /**
   * sp_items 假勤统计信息
   */
  @SerializedName("sp_items")
  private List<SpItem> spItems;

  @Data
  public static class SpItem implements Serializable {
    private static final long serialVersionUID = 224472626753597080L;

    /**
     * type 假勤类型：1-请假；2-补卡；3-出差；4-外出；100-外勤
     */
    @SerializedName("type")
    private Integer type;

    /**
     * vacation_id 具体请假类型，当type为1请假时，具体的请假类型id，可通过审批相关接口获取假期详情
     */
    @SerializedName("vacation_id")
    private Integer vacationId;

    /**
     * count 假勤次数，为统计周期内每日此假勤发生次数之和
     */
    @SerializedName("count")
    private Integer count;

    /**
     * duration 假勤时长，为统计周期内每日此假勤发生时长之和，时长单位为天直接除以86400即为天数，单位为小时直接除以3600即为小时数
     */
    @SerializedName("duration")
    private Integer duration;

    /**
     * time_type 时长单位：0-按天 1-按小时
     */
    @SerializedName("time_type")
    private Integer timeType;

    /**
     * name 统计项名称
     */
    @SerializedName("name")
    private String name;
  }

  /**
   * overwork_info 加班情况
   */
  @SerializedName("overwork_info")
  private OverWorkInfo overworkInfo;

  @Data
  public static class OverWorkInfo implements Serializable {
    private static final long serialVersionUID = -9149524232645899305L;

    /**
     * workday_over_sec 工作日加班时长
     */
    @SerializedName("workday_over_sec")
    private Integer workdayOverSec;

    /**
     * holidays_over_sec 节假日加班时长
     */
    @SerializedName("holidays_over_sec")
    private Integer holidaysOverSec;

    /**
     * restdays_over_sec 休息日加班时长
     */
    @SerializedName("restdays_over_sec")
    private Integer restdaysOverSec;
  }
}
