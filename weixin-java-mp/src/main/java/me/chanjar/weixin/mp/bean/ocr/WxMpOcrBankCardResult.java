package me.chanjar.weixin.mp.bean.ocr;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * 银行卡OCR识别结果
 * @author Theo Nie
 */
@Data
public class WxMpOcrBankCardResult implements Serializable {

  @SerializedName("number")
  private String number;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

  public static WxMpOcrBankCardResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpOcrBankCardResult.class);
  }
}
