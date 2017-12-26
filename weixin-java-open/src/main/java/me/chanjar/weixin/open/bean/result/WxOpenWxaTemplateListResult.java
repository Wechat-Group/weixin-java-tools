/**
 *
 */
package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * @author lfj
 */
@Data
public class WxOpenWxaTemplateListResult implements Serializable {

  private static final long serialVersionUID = 5001828822025834220L;

  private List<Template> list;

  @Data
  public class Template implements Serializable {

    private static final long serialVersionUID = -4194090438477087014L;

    @SerializedName("template_id")
    private String templateId;// 模板id，发送小程序模板消息时所需
    private String title;// 模板标题
    private String content;// 模板内容
    private String example;// 模板内容示例

    @Override
    public String toString() {
      return WxMpGsonBuilder.INSTANCE.create().toJson(this);
    }

  }

  public static WxOpenWxaTemplateListResult fromJson(String json) {
    return WxOpenGsonBuilder.create().fromJson(json, WxOpenWxaTemplateListResult.class);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.INSTANCE.create().toJson(this);
  }

}
