package me.chanjar.weixin.mp.bean.ocr;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * OCR身份证识别结果.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2019-06-23
 */
@Data
public class WxMpOcrIdCardResult implements Serializable {
  private static final long serialVersionUID = 8184352486986729980L;

  @SerializedName("type")
  private String type;
  @SerializedName("name")
  private String name;
  @SerializedName("id")
  private String id;
  @SerializedName("valid_date")
  private String validDate;

  public static WxMpOcrIdCardResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpOcrIdCardResult.class);
  }

}
