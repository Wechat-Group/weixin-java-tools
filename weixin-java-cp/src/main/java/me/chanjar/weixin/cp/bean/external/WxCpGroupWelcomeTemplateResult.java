package me.chanjar.weixin.cp.bean.external;

import lombok.*;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.external.msg.*;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 入群欢迎语素材.
 *
 * @author <a href="https://github.com/wslongchen">Mr.Pan</a>
 * @date 2021-11-3
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

  public static WxCpGroupWelcomeTemplateResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpGroupWelcomeTemplateResult.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}
