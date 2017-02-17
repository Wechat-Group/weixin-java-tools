package me.chanjar.weixin.mp.bean;

import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * 语义理解查询用对象
 *
 * http://mp.weixin.qq.com/wiki/index.php?title=语义理解
 *
 * @author Daniel Qian
 */
public class WxMpSemanticQuery implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 7685873048199870690L;
  private String query;
  private String category;
  private Float latitude;
  private Float longitude;
  private String city;
  private String region;
  private String appid;
  private String uid;

  public String getQuery() {
    return this.query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public String getCategory() {
    return this.category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public Float getLatitude() {
    return this.latitude;
  }

  public void setLatitude(Float latitude) {
    this.latitude = latitude;
  }

  public Float getLongitude() {
    return this.longitude;
  }

  public void setLongitude(Float longitude) {
    this.longitude = longitude;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getRegion() {
    return this.region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getAppid() {
    return this.appid;
  }

  public void setAppid(String appid) {
    this.appid = appid;
  }

  public String getUid() {
    return this.uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public String toJson() {
    return WxMpGsonBuilder.create().toJson(this);
  }

}
