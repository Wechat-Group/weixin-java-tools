package cn.binarywang.wx.miniapp.bean.shop.response;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.Data;

/**
 * @author leiin
 * created on  2022/6/18 2:58 下午
 */
@Data
public class WxMaShopSearchSharerResponse extends WxMaShopBaseResponse implements Serializable {

  private static final long serialVersionUID = 2049214239752832818L;

  @SerializedName("invited_time")
  private Long invitedTime;
  @SerializedName("bind_time")
  private Long bindTime;
  @SerializedName("nickname")
  private String nickname;
  @SerializedName("bind_status")
  private Integer bindStatus;
}
