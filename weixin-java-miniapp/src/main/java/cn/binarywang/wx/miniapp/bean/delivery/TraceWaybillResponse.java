package cn.binarywang.wx.miniapp.bean.delivery;

import cn.binarywang.wx.miniapp.bean.WxMaBaseResponse;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <pre>
 * 传运单接口 trace_waybill 响应参数
 * </pre>
 *
 * @author boris
 * @since 2022-04-01
 */
@Data
@Accessors(chain = true)
public class TraceWaybillResponse extends WxMaBaseResponse implements Serializable {

  private static final long serialVersionUID = 3773007367000633663L;

  /**
   * 查询id.
   */
  @SerializedName("waybill_token")
  private String waybillToken;


  public static TraceWaybillResponse fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, TraceWaybillResponse.class);
  }
}
