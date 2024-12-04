package cn.binarywang.wx.miniapp.bean.promoter.response;

import cn.binarywang.wx.miniapp.bean.WxMaBaseResponse;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author zhuangzibin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxMaPromotionSendMsgResponse extends WxMaBaseResponse implements Serializable {

  private static final long serialVersionUID = -7485009740371167375L;

  /*
    {
    "msg_id": "123456",
    "errcode": 0,
    "errmsg": "OK"
    }
   */

  /**
   * 消息 id
   */
  @SerializedName("msg_id")
  private String msgId;

}
