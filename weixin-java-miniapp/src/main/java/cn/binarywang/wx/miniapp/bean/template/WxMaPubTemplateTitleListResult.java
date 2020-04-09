package cn.binarywang.wx.miniapp.bean.template;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * @author ArBing
 */
@Data
public class WxMaPubTemplateTitleListResult implements Serializable {
  private static final long serialVersionUID = -7718911668757837527L;

  private int count;

  private List<TemplateItem> data;

  public static WxMaPubTemplateTitleListResult fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMaPubTemplateTitleListResult.class);
  }

  @Data
  public static class TemplateItem {

    private Integer type;

    private Integer tid;

    private String categoryId;

    private String title;
  }
}
