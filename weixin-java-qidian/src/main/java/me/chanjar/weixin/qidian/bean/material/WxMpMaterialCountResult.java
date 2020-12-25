package me.chanjar.weixin.qidian.bean.material;

import java.io.Serializable;

import lombok.Data;
import me.chanjar.weixin.qidian.util.json.WxMpGsonBuilder;

/**
 * @author codepiano
 */
@Data
public class WxMpMaterialCountResult implements Serializable {
  private static final long serialVersionUID = -5568772662085874138L;

  private int voiceCount;
  private int videoCount;
  private int imageCount;
  private int newsCount;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}

