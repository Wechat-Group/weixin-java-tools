package cn.binarywang.wx.miniapp.bean.template;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * @author ArBing
 */
@Data
public class WxMaTemplateAddResult implements Serializable {
  private static final long serialVersionUID = 872250961973834465L;

  @SerializedName("template_id")
  private String templateId;

  public static WxMaTemplateAddResult fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMaTemplateAddResult.class);
  }
}
