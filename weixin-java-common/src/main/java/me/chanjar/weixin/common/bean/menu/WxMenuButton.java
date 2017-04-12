package me.chanjar.weixin.common.bean.menu;

import com.google.gson.annotations.SerializedName;
import me.chanjar.weixin.common.util.ToStringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WxMenuButton implements Serializable {
  private static final long serialVersionUID = -1070939403109776555L;

  private String type;
  private String name;
  private String key;
  private String url;

  @SerializedName("media_id")
  private String mediaId;

  @SerializedName("sub_button")
  private List<WxMenuButton> subButtons = new ArrayList<>();

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getKey() {
    return this.key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public List<WxMenuButton> getSubButtons() {
    return this.subButtons;
  }

  public void setSubButtons(List<WxMenuButton> subButtons) {
    this.subButtons = subButtons;
  }

  public String getMediaId() {
    return this.mediaId;
  }

  public void setMediaId(String mediaId) {
    this.mediaId = mediaId;
  }
}
