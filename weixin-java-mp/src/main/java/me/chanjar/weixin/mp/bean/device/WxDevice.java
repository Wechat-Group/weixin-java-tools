package me.chanjar.weixin.mp.bean.device;

import com.google.gson.annotations.SerializedName;

/**
 * Created by keungtung on 10/12/2016.
 */
public class WxDevice {
  private String id;
  private String mac;
  @SerializedName("connect_protocol")
  private String connectProtocol;
  @SerializedName("auth_key")
  private String authKey;
  @SerializedName("close_strategy")
  private String closeStrategy;
  @SerializedName("conn_strategy")
  private String connStrategy;
  @SerializedName("crypt_method")
  private String cryptMethod;
  @SerializedName("auth_ver")
  private String authVer;
  @SerializedName("manu_mac_pos")
  private String manuMacPos;
  @SerializedName("ser_mac_pos")
  private String serMacPos;
  @SerializedName("ble_simple_protocol")
  private String bleSimpleProtocol;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getMac() {
    return mac;
  }

  public void setMac(String mac) {
    this.mac = mac;
  }

  public String getConnectProtocol() {
    return connectProtocol;
  }

  public void setConnectProtocol(String connectProtocol) {
    this.connectProtocol = connectProtocol;
  }

  public String getAuthKey() {
    return authKey;
  }

  public void setAuthKey(String authKey) {
    this.authKey = authKey;
  }

  public String getCloseStrategy() {
    return closeStrategy;
  }

  public void setCloseStrategy(String closeStrategy) {
    this.closeStrategy = closeStrategy;
  }

  public String getConnStrategy() {
    return connStrategy;
  }

  public void setConnStrategy(String connStrategy) {
    this.connStrategy = connStrategy;
  }

  public String getCryptMethod() {
    return cryptMethod;
  }

  public void setCryptMethod(String cryptMethod) {
    this.cryptMethod = cryptMethod;
  }

  public String getAuthVer() {
    return authVer;
  }

  public void setAuthVer(String authVer) {
    this.authVer = authVer;
  }

  public String getManuMacPos() {
    return manuMacPos;
  }

  public void setManuMacPos(String manuMacPos) {
    this.manuMacPos = manuMacPos;
  }

  public String getSerMacPos() {
    return serMacPos;
  }

  public void setSerMacPos(String serMacPos) {
    this.serMacPos = serMacPos;
  }

  public String getBleSimpleProtocol() {
    return bleSimpleProtocol;
  }

  public void setBleSimpleProtocol(String bleSimpleProtocol) {
    this.bleSimpleProtocol = bleSimpleProtocol;
  }
}
