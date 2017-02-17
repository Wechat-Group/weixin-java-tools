package me.chanjar.weixin.cp.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * 微信用户信息
 *
 * @author Daniel Qian
 */
public class WxCpUser implements Serializable {

  private static final long serialVersionUID = -5696099236344075582L;
  private final List<Attr> extAttrs = new ArrayList<>();
  private String userId;
  private String name;
  private Integer[] departIds;
  private String position;
  private String mobile;
  private String gender;
  private String tel;
  private String email;
  private String weiXinId;
  private String avatar;
  private Integer status;
  private Integer enable;

  public static WxCpUser fromJson(String json) {
    return WxCpGsonBuilder.INSTANCE.create().fromJson(json, WxCpUser.class);
  }

  public String getUserId() {
    return this.userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer[] getDepartIds() {
    return this.departIds;
  }

  public void setDepartIds(Integer[] departIds) {
    this.departIds = departIds;
  }

  public String getGender() {
    return this.gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getPosition() {
    return this.position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public String getMobile() {
    return this.mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getTel() {
    return this.tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getWeiXinId() {
    return this.weiXinId;
  }

  public void setWeiXinId(String weiXinId) {
    this.weiXinId = weiXinId;
  }

  public String getAvatar() {
    return this.avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public Integer getStatus() {
    return this.status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Integer getEnable() {
    return this.enable;
  }

  public void setEnable(Integer enable) {
    this.enable = enable;
  }

  public void addExtAttr(String name, String value) {
    this.extAttrs.add(new Attr(name, value));
  }

  public List<Attr> getExtAttrs() {
    return this.extAttrs;
  }

  public String toJson() {
    return WxCpGsonBuilder.INSTANCE.create().toJson(this);
  }

  public static class Attr {

    private String name;
    private String value;

    public Attr(String name, String value) {
      this.name = name;
      this.value = value;
    }

    public String getName() {
      return this.name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getValue() {
      return this.value;
    }

    public void setValue(String value) {
      this.value = value;
    }

  }

}
