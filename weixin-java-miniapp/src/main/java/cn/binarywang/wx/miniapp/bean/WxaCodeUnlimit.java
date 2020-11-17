package cn.binarywang.wx.miniapp.bean;

import cn.binarywang.wx.miniapp.util.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 小程序码接口B.
 *
 * @author Element
 * @date 2017/7/27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WxaCodeUnlimit extends AbstractWxMaQrcodeWrapper implements Serializable {
  private static final long serialVersionUID = 4782193774524960401L;
  private String scene;
  private String page;

  private int width = 430;

  @SerializedName("auto_color")
  private boolean autoColor = true;

  @SerializedName("is_hyaline")
  private boolean isHyaline = false;

  @SerializedName("line_color")
  private WxMaCodeLineColor lineColor = new WxMaCodeLineColor("0", "0", "0");

  public static WxaCodeUnlimit fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WxaCodeUnlimit.class);
  }

}
