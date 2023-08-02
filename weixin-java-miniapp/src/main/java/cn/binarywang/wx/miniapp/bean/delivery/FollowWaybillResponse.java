package cn.binarywang.wx.miniapp.bean.delivery;

import cn.binarywang.wx.miniapp.bean.WxMaBaseResponse;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <pre>
 * 查运单接口 query_follow_trace 响应参数
 * </pre>
 *
 * @author boris
 * @since 2022-04-01
 */
@Data
@Accessors(chain = true)
public class FollowWaybillResponse extends WxMaBaseResponse implements Serializable {

  private static final long serialVersionUID = 3773007367000633663L;

  /**
   * 查询id.
   */
  @SerializedName("waybill_token")
  private String waybillToken;


  public static FollowWaybillResponse fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, FollowWaybillResponse.class);
  }
}
