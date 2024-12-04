package cn.binarywang.wx.miniapp.bean.promoter.response;

import cn.binarywang.wx.miniapp.bean.WxMaBaseResponse;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhuangzibin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxMaPromotionGetMsgClickDataResponse extends WxMaBaseResponse implements Serializable {

  private static final long serialVersionUID = -4985995863552139208L;

  /*
    dimonsion为0时返回：
    {
      "data_list" : [
        {
          "msg_id" : "123456",
          "send_time" : 1691114400,
          "click_uv" : 123,
          "click_pv" : 200
        }
      ],
      "errcode": 0,
      "errmsg": "OK"
    }

    dimonsion为1时返回：
    {
      "data_list" : [
        {
          "msg_type" : 1,
          "click_uv" : 123,
          "click_pv" : 200
        }
      ],
      "errcode": 0,
      "errmsg": "OK"
    }
   */

  /**
   * 数据数组
   * 非必填
   */
  @SerializedName("data_list")
  private List<Dimonsion> dataList;

  @Data
  public static class Dimonsion {
    /**
     * 消息点击人数
     */
    @SerializedName("click_uv")
    private Long clickUv;

    /**
     * 消息点击次数
     */
    @SerializedName("click_pv")
    private Long clickPv;

    /**
     * 消息类型，枚举值参考小程序推广员消息模板汇总
     */
    @SerializedName("msg_type")
    private Long msgType;

    /**
     * 消息id，发送模板消息接口返回的值
     */
    @SerializedName("msg_id")
    private String msgId;

    /**
     * 消息发送时间
     */
    @SerializedName("send_time")
    private Long sendTime;
  }
}
