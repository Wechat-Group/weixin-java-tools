package cn.binarywang.wx.miniapp.bean;

import java.io.Serializable;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 小程序码.
 *
 * @author Element
 * @date 2017/7/27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxaCode extends AbstractWxMaQrcodeWrapper implements Serializable {
  private static final long serialVersionUID = 1287399621649210322L;

  private String path;

  @Builder.Default
  private int width = 430;

  @SerializedName("auto_color")
  @Builder.Default
  private boolean autoColor = true;

  @SerializedName("is_hyaline")
  @Builder.Default
  private boolean isHyaline = false;

  @SerializedName("line_color")
  @Builder.Default
  private WxMaCodeLineColor lineColor = new WxMaCodeLineColor("0", "0", "0");

  public static WxaCode fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WxaCode.class);
  }

}
