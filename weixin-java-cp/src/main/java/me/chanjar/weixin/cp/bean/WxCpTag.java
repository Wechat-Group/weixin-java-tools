package me.chanjar.weixin.cp.bean;

import java.io.Serializable;

import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * Created by Daniel Qian
 */
public class WxCpTag implements Serializable {

  private static final long serialVersionUID = -7243320279646928402L;

  private String id;

  private String name;

  public WxCpTag() {
    super();
  }

  public WxCpTag(String id, String name) {
    super();
    this.id = id;
    this.name = name;
  }

  public static WxCpTag fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTag.class);
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
