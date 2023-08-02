package cn.binarywang.wx.miniapp.bean.shop;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.Data;

/**
 * @author leiin
 * created on  2022/6/18 3:14 下午
 */
@Data
public class WxMaPromotionInfo implements Serializable {

  private static final long serialVersionUID = 2090629980847386450L;

  @SerializedName("finder_username")
  private String finderUsername;
  @SerializedName("finder_nickname")
  private String finderNickname;
  @SerializedName("sharer_openid")
  private String sharerOpenid;
  @SerializedName("live_start_time")
  private String liveStartTime;
}
