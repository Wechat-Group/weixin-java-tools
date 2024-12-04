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
public class WxMaPromotionGetMsgRequest implements Serializable {

  private static final long serialVersionUID = -2445469292144155035L;

  /*
    {
    "msg_type" : 1
    }
   */

  /**
   * 消息id，发送模板消息接口返回的值
   * 必填
   */
  @SerializedName("msg_id")
  private String msgId;
}
