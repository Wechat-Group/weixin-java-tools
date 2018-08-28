package me.chanjar.weixin.mp.bean.card;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 使用日期，有效期的信息
 * author:yuanqixun
 * date:2018-08-25 00:31
 */
@Data
public class DateInfo implements Serializable {

  /**
   * 使用时间的类型,支持固定时长有效类型 固定日期有效类型 永久有效类型：DATE_TYPE_FIX_TERM_RANGE、DATE_TYPE_FIX_TERM 、DATE_TYPE_PERMANENT
   */
  @SerializedName("type")
  private String type = "DATE_TYPE_PERMANENT";

  /**
   * 起用时间,type为DATE_TYPE_FIX_TIME_RANGE时专用， 表示起用时间。从1970年1月1日00:00:00至起用时间的秒数 （ 东八区时间,UTC+8，单位为秒 ）
   */
  @SerializedName("begin_timestamp")
  private Long beginTimestamp;

  /**
   * 结束时间,type为DATE_TYPE_FIX_TERM_RANGE时专用，表示结束时间 （ 东八区时间,UTC+8，单位为秒 ）
   */
  @SerializedName("end_timestamp")
  private Long endTimestamp;

  /**
   * 自领取后多少天内有效,type为DATE_TYPE_FIX_TERM时专用，表示自领取后多少天内有效，领取后当天有效填写0（单位为天）
   */
  @SerializedName("fixed_term")
  private Integer fixedTerm;

  /**
   * 自领取后多少天开始生效,type为DATE_TYPE_FIX_TERM时专用，表示自领取后多少天开始生效。（单位为天）
   */
  @SerializedName("fixed_begin_term")
  private Integer fixedBeginTerm;

  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
