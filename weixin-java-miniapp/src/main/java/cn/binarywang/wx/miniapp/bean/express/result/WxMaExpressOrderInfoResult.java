package cn.binarywang.wx.miniapp.bean.express.result;

import cn.binarywang.wx.miniapp.util.json.WxMaGsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 运单信息返回结果对象
 * </pre>
 * @author <a href="https://github.com/mr-xiaoyu">xiaoyu</a>
 * @since 2019-11-26
 */
@Data
public class WxMaExpressOrderInfoResult implements Serializable {
  private static final JsonParser JSON_PARSER = new JsonParser();
  private static final long serialVersionUID = -9166603059965942285L;

  /**
   * 错误码
   */
  private Integer errcode;

  /**
   * 错误信息
   */
  private String errmsg;
  /**
   * 订单ID
   */
  @SerializedName("order_id")
  private String orderId;

  /**
   * 运单ID
   */
  @SerializedName("waybill_id")
  private String waybillId;

  /**
   * 运单 html 的 BASE64 结果
   */
  @SerializedName("print_html")
  private String printHtml;

  /**
   * 运单信息
   */
  @SerializedName("waybill_data")
  private List<Map<String,String>> waybillData;


  public static WxMaExpressOrderInfoResult fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WxMaExpressOrderInfoResult.class);
  }

  public static List<WxMaExpressOrderInfoResult> toList(String json) {
    JsonObject jsonObject = JSON_PARSER.parse(json).getAsJsonObject();
    return WxMaGsonBuilder.create().fromJson(jsonObject.get("order_list").toString(),
      new TypeToken<List<WxMaExpressOrderInfoResult>>() {
      }.getType());
  }
}
