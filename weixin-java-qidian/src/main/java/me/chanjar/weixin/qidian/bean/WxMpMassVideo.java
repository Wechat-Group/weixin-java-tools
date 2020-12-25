package me.chanjar.weixin.qidian.bean;

import lombok.Data;
import me.chanjar.weixin.qidian.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * 群发时用到的视频素材
 *
 * @author chanjarster
 */
@Data
public class WxMpMassVideo implements Serializable {
  private static final long serialVersionUID = 9153925016061915637L;

  private String mediaId;
  private String title;
  private String description;

  public String toJson() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}
