package cn.binarywang.wx.miniapp.bean;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author borisbao
 */
@Data
public class WxMaMediaAsyncCheckResult implements Serializable {
  private static final long serialVersionUID = 3928132365399916183L;

  /**
   * 任务id，用于匹配异步推送结果
   */
  @SerializedName("trace_id")
  private String traceId;


  public static WxMaMediaAsyncCheckResult fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WxMaMediaAsyncCheckResult.class);
  }

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
