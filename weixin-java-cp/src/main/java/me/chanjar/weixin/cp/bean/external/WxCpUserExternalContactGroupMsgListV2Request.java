package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.annotation.Required;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * @author caiqy
 */
@Data
public class WxCpUserExternalContactGroupMsgListV2Request implements Serializable {

  private static final long serialVersionUID = -3212279121189093522L;

  /**
   * 群发任务的类型，默认为single，表示发送给客户，group表示发送给客户群
   */
  @Required
  @SerializedName("chat_type")
  private String chatType;

  /**
   * 群发任务记录开始时间
   */
  @Required
  @SerializedName("start_time")
  private long startTime;

  /**
   * 群发任务记录结束时间
   */
  @Required
  @SerializedName("end_time")
  private long endTime;

  /**
   * 群发任务创建人企业账号id
   */
  @Required
  @SerializedName("creator")
  private String creator;

  /**
   * 用于分页查询的游标，字符串类型，由上一次调用返回，首次调用可不填
   */
  @SerializedName("cursor")
  private String cursor;

  /**
   * 创建人类型。0：企业发表 1：个人发表 2：所有，包括个人创建以及企业创建，默认情况下为所有类型
   */
  @SerializedName("filter_type")
  private int filterType;

  /**
   * 返回的最大记录数，整型，最大值100，默认值50，超过最大值时取默认值
   */
  @SerializedName("limit")
  private int limit;

  public void setStartTime(Date startTime) {
    this.startTime = startTime.getTime() / 1000;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime.getTime() / 1000;
  }


  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}
