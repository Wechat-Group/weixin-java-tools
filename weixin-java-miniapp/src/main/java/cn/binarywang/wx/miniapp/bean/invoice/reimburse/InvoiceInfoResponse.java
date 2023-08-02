package cn.binarywang.wx.miniapp.bean.invoice.reimburse;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import me.chanjar.weixin.common.util.json.GsonParser;

import java.util.List;

/**
 * <pre>
 * 查询报销发票信息响应对象
 * </pre>
 * @author <a href="https://github.com/mr-xiaoyu">xiaoyu</a>
 * @since 2021-03-23
 */
@Data
public class InvoiceInfoResponse {

  /**
   * 发票ID
   */
  @SerializedName("card_id")
  private String cardId;


  /**
   * 发票的有效期起始时间
   */
  @SerializedName("begin_time")
  private Integer beginTime;

  /**
   * 发票的有效期截止时间
   */
  @SerializedName("end_time")
  private Integer endTime;

  /**
   * 用户标识
   */
  private String openid;

  /**
   * 发票的类型
   */
  private String type;

  /**
   * 发票的收款方
   */
  private String payee;

  /**
   * 发票详情
   */
  private String detail;

  /**
   * 用户可在发票票面看到的主要信息
   */
  @SerializedName("user_info")
  private InvoiceUserInfo userInfo;


  public static InvoiceInfoResponse fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, InvoiceInfoResponse.class);
  }


  public static List<InvoiceInfoResponse> toList(String json) {
    JsonObject jsonObject = GsonParser.parse(json);
    return WxMaGsonBuilder.create().fromJson(jsonObject.get("item_list").toString(),
      new TypeToken<List<InvoiceInfoResponse>>() {
      }.getType());
  }
}
