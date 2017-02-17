package me.chanjar.weixin.mp.bean.material;

import me.chanjar.weixin.common.util.ToStringUtils;

import java.io.Serializable;

public class WxMpMaterialCountResult implements Serializable {
  private static final long serialVersionUID = -5568772662085874138L;
  private int voiceCount;
  private int videoCount;
  private int imageCount;
  private int newsCount;

  public int getVoiceCount() {
    return this.voiceCount;
  }

  public void setVoiceCount(int voiceCount) {
    this.voiceCount = voiceCount;
  }

  public int getVideoCount() {
    return this.videoCount;
  }

  public void setVideoCount(int videoCount) {
    this.videoCount = videoCount;
  }

  public int getImageCount() {
    return this.imageCount;
  }

  public void setImageCount(int imageCount) {
    this.imageCount = imageCount;
  }

  public int getNewsCount() {
    return this.newsCount;
  }

  public void setNewsCount(int newsCount) {
    this.newsCount = newsCount;
  }

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }
}

