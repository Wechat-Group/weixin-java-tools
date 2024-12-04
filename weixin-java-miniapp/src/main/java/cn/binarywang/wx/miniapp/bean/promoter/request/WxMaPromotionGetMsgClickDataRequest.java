package cn.binarywang.wx.miniapp.bean.promoter.request;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhuangzibin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaPromotionGetMsgClickDataRequest implements Serializable {

  private static final long serialVersionUID = 3981311999296086650L;

  /*
    {
      "send_date" : "2023-08-04",
      "dimonsion" : 0,
      "msg_type" : 1,
      "begin_send_time" : 1691114400,
      "end_send_time" : 1691128800
    }
    或
    {
      "send_date" : "2023-08-04",
      "dimonsion" : 1,
      "msg_type" : 1
    }
   */

  /**
   * 消息发送日期，格式为yyyy-MM-dd
   * 必填
   */
  @SerializedName("send_date")
  private String sendDate;

  /**
   * 消息点击数据统计维度，0：按消息id统计（群发数小于50没有数据），1：按消息类型统计
   * 必填
   */
  @SerializedName("dimonsion")
  private Long dimonsion;

  /**
   * 消息类型，枚举值参考小程序推广员消息模板汇总
   * 必填
   */
  @SerializedName("msg_type")
  private Integer msgType;

  /**
   * 消息类型，枚举值参考小程序推广员消息模板汇总
   * 必填
   */
  @SerializedName("msg_id")
  private String msgId;

  /**
   * 消息发送开始时间戳，必须属于send_date所在自然日（dimonsion为0时生效）
   * 非必填
   */
  @SerializedName("begin_send_time")
  private Long beginSendTime;

  /**
   * 消息发送结束时间戳，必须属于send_date所在自然日（dimonsion为0时生效）
   * 非必填
   */
  @SerializedName("end_send_time")
  private Long endSendTime;
}
