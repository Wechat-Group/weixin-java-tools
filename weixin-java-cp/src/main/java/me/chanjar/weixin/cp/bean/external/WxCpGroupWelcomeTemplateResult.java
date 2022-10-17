package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.external.msg.*;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 入群欢迎语素材.
 *
 * @author <a href="https://github.com/wslongchen">Mr.Pan</a> created on  2021-11-3
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WxCpGroupWelcomeTemplateResult extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -6406667238670580612L;

  private Text text;

  private Image image;

  private Link link;

  private MiniProgram miniprogram;

  private File file;

  private Video video;

  /**
   * 欢迎语素材id
   * https://developer.work.weixin.qq.com/document/path/92366
   */
  @SerializedName("template_id")
  private String templateId;

  /**
   * 是否通知成员将这条入群欢迎语应用到客户群中，0-不通知，1-通知， 不填则通知
   */
  private Integer notify;

  /**
   * From json wx cp group welcome template result.
   *
   * @param json the json
   * @return the wx cp group welcome template result
   */
  public static WxCpGroupWelcomeTemplateResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpGroupWelcomeTemplateResult.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}
