package me.chanjar.weixin.mp.bean.card;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

/**
 * 使用时段限制.
 * @author yuanqixun
 * date:2018-08-25 00:34
 */
@Data
public class TimeLimit implements Serializable {

  /**
   * 限制类型枚举值,支持填入 MONDAY 周一 TUESDAY 周二 WEDNESDAY 周三 THURSDAY 周四 FRIDAY 周五 SATURDAY 周六 SUNDAY 周日 此处只控制显示， 不控制实际使用逻辑，不填默认不显示
   */
  @SerializedName("type")
  private String type;

  /**
   * 起始时间（小时）,当前type类型下的起始时间（小时） ，如当前结构体内填写了MONDAY， 此处填写了10，则此处表示周一 10:00可用
   */
  @SerializedName("begin_hour")
  private Integer beginHour;

  /**
   * 起始时间（分钟）,如当前结构体内填写了MONDAY， begin_hour填写10，此处填写了59， 则此处表示周一 10:59可用
   */
  @SerializedName("begin_minute")
  private Integer beginMinute;

  /**
   * 结束时间（小时）,如当前结构体内填写了MONDAY， 此处填写了20， 则此处表示周一 10:00-20:00可用
   */
  @SerializedName("end_hour")
  private Integer endHour;

  /**
   * 结束时间（分钟）,如当前结构体内填写了MONDAY， begin_hour填写10，此处填写了59， 则此处表示周一 10:59-00:59可用
   */
  @SerializedName("end_minute")
  private Integer endMinute;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}
