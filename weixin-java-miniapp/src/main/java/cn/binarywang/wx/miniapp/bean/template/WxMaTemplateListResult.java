package cn.binarywang.wx.miniapp.bean.template;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * @author ArBing
 */
@Data
public class WxMaTemplateListResult implements Serializable {
  private static final long serialVersionUID = -7430535579782184537L;
  private List<TemplateInfo> list;

  public static WxMaTemplateListResult fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMaTemplateListResult.class);
  }

  @Data
  public static class TemplateInfo {

    @SerializedName("template_id")
    private String templateId;
    private String title;
    private String content;
    private String example;
  }
}
