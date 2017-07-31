package me.chanjar.weixin.mp.bean.material;

import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

public class WxMpMaterialVideoInfoResult implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1269131745333792202L;
  private String title;
  private String description;
  private String downUrl;

  public static WxMpMaterialVideoInfoResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpMaterialVideoInfoResult.class);
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDownUrl() {
    return this.downUrl;
  }

  public void setDownUrl(String downUrl) {
    this.downUrl = downUrl;
  }

  @Override
  public String toString() {
    return "WxMpMaterialVideoInfoResult [title=" + this.title + ", description=" + this.description + ", downUrl=" + this.downUrl + "]";
  }

}
