package cn.binarywang.wx.miniapp.bean.shop.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xzh
 * created on  2023/5/17 17:01
 */
@Data
public class WxMaOrderShippingIsTradeManagedResponse implements Serializable {

  private static final long serialVersionUID = -5397007157487018762L;
  /**
   * 错误码
   */
  @SerializedName("errcode")
  private Integer errCode;

  /**
   * 错误原因
   */
  @SerializedName("errmsg")
  private String errMsg;

  /**
   * 是否已开通小程序发货信息管理服务
   */
  @SerializedName("is_trade_managed")
  private Boolean tradeManaged;
}
