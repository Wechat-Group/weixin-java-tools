package cn.binarywang.wx.miniapp.bean.device;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 小程序设备ticket请求参数
 *
 * @author <a href="https://github.com/leejuncheng">JCLee</a>
 * @since 2021-12-16 17:13:28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaDeviceTicketRequest  implements Serializable {
  private static final long serialVersionUID = -2152114813101871295L;

  /**
   * 设备型号id。通过注册设备获得（必填）
   *
   */
  @SerializedName("model_id")
  private String modelId;

  /**
   * 设备唯⼀序列号。由⼚商分配。⻓度不能超过128字节。字符只接受数字与⼤⼩写字⺟（必填）
   */
  @SerializedName("sn")
  private String sn;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
