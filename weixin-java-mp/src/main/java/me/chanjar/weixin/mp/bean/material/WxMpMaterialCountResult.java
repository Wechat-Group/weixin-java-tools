package me.chanjar.weixin.mp.bean.material;

import lombok.Data;
import me.chanjar.weixin.common.util.ToStringUtils;

import java.io.Serializable;

@Data
public class WxMpMaterialCountResult implements Serializable {
  private static final long serialVersionUID = -5568772662085874138L;

  private int voiceCount;
  private int videoCount;
  private int imageCount;
  private int newsCount;

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }
}

