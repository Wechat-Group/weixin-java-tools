package me.chanjar.weixin.cp.bean.oa;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * The type Wx cp set checkin schedule.
 */
@Data
public class WxCpSetCheckinSchedule implements Serializable {
  private static final long serialVersionUID = -7106074373528367075L;

  /**
   * 打卡规则的规则id，可通过“获取打卡规则”、“获取打卡数据”、“获取打卡人员排班信息”等相关接口获取
   */
  @SerializedName("groupid")
  private Integer groupId;

  /**
   * 排班表信息
   */
  @SerializedName("items")
  private List<Item> items;

  /**
   * 排班表月份，格式为年月，如202011
   */
  @SerializedName("yearmonth")
  private Integer yearmonth;


  @Data
  public static class Item implements Serializable {

    private static final long serialVersionUID = -918057757709951513L;

    /**
     * 打卡人员userid
     */
    @SerializedName("userid")
    private String userid;

    /**
     * 要设置的天日期，取值在1-31之间。联合yearmonth组成唯一日期 比如20201205
     */
    @SerializedName("day")
    private Integer day;

    /**
     * 对应groupid规则下的班次id，通过预先拉取规则信息获取，0代表休息
     */
    @SerializedName("schedule_id")
    private Integer scheduleId;
  }

}
