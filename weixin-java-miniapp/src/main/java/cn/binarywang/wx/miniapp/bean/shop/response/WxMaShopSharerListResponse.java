package cn.binarywang.wx.miniapp.bean.shop.response;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * @author leiin
 * created on  2022/6/18 2:55 下午
 */
@Data
public class WxMaShopSharerListResponse extends WxMaShopBaseResponse implements Serializable {

  private static final long serialVersionUID = -8533731677643022825L;

  private List<SharerInfo> sharers;
  @SerializedName("total_num")
  private Integer totalNum;

  @Data
  public static class SharerInfo {
    private String openid;
    @SerializedName("invited_time")
    private Long invitedTime;
    @SerializedName("bind_time")
    private Long bindTime;
    private String nickname;
  }
}
