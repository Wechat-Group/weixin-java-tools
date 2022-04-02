package cn.binarywang.wx.miniapp.bean.delivery;


import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <pre>
 * 查询运单接口 query_trace
 *
 * 商户在调用完trace_waybill接口后，可以使用本接口查询到对应运单的详情信息
 * </pre>
 *
 * @author boris
 * @since 2022-04-01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class QueryWaybillTraceRequest implements Serializable {

  private static final long serialVersionUID = -7538739003766268386L;


  /**
   * 查询id
   * <pre>
   * 是否必填： 是
   * 描述： 可以从 传运单接口 trace_waybill 取数据
   * </pre>
   */
  @SerializedName("waybill_token")
  private String waybillToken;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
