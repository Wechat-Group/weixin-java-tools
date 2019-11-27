package cn.binarywang.wx.miniapp.bean.express.result;

import cn.binarywang.wx.miniapp.util.json.WxMaGsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * 生成运单返回结果对象
 * </pre>
 * @author <a href="https://github.com/mr-xiaoyu">xiaoyu</a>
 * @since 2019-11-26
 */
@Data
public class WxMaExpressAddOrderResult implements Serializable {
  private static final JsonParser JSON_PARSER = new JsonParser();
  private static final long serialVersionUID = -9166603059965942285L;

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
   * 运单信息
   */
  private Map<String,String> waybillData;


  public static WxMaExpressAddOrderResult fromJson(String json) {
    WxMaExpressAddOrderResult result = WxMaGsonBuilder.create().fromJson(json, WxMaExpressAddOrderResult.class);
    JsonArray waybills = JSON_PARSER.parse(json).getAsJsonObject().getAsJsonArray("waybill_data");
    Map<String,String> waybillData = new HashMap<>(16);
    for (JsonElement waybill : waybills) {
      String key = waybill.getAsJsonObject().get("key").getAsString();
      String value = waybill.getAsJsonObject().get("value").getAsString();
      waybillData.put(key,value);
    }
    result.setWaybillData(waybillData);
    return result;
  }
}
