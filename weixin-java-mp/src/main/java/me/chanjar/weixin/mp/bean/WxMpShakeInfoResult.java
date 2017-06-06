package me.chanjar.weixin.mp.bean;

import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * 摇一摇周边：获取设备及用户信息接口返回JSON数据接收类
 * Created by rememberber on 2017/6/5.
 *
 * @author rememberber
 */
public class WxMpShakeInfoResult implements Serializable {

  private Integer errcode;

  private String errmsg;

  private Data data;

  public static WxMpShakeInfoResult fromJson(String json) {
    return WxMpGsonBuilder.INSTANCE.create().fromJson(json, WxMpShakeInfoResult.class);
  }

  public class Data {

    private String page_id;

    private String openid;

    private String poi_id;

    private String brand_userame;

    private BeaconInfo beacon_info;

    public class BeaconInfo {

      private double distance;

      private Integer major;

      private Integer measure_power;

      private Integer minor;

      private Integer rssi;

      private String uuid;

      public double getDistance() {
        return distance;
      }

      public void setDistance(double distance) {
        this.distance = distance;
      }

      public Integer getMajor() {
        return major;
      }

      public void setMajor(Integer major) {
        this.major = major;
      }

      public Integer getMeasure_power() {
        return measure_power;
      }

      public void setMeasure_power(Integer measure_power) {
        this.measure_power = measure_power;
      }

      public Integer getMinor() {
        return minor;
      }

      public void setMinor(Integer minor) {
        this.minor = minor;
      }

      public Integer getRssi() {
        return rssi;
      }

      public void setRssi(Integer rssi) {
        this.rssi = rssi;
      }

      public String getUuid() {
        return uuid;
      }

      public void setUuid(String uuid) {
        this.uuid = uuid;
      }
    }

    public String getPage_id() {
      return page_id;
    }

    public void setPage_id(String page_id) {
      this.page_id = page_id;
    }

    public String getOpenid() {
      return openid;
    }

    public void setOpenid(String openid) {
      this.openid = openid;
    }

    public String getPoi_id() {
      return poi_id;
    }

    public void setPoi_id(String poi_id) {
      this.poi_id = poi_id;
    }

    public BeaconInfo getBeacon_info() {
      return beacon_info;
    }

    public void setBeacon_info(BeaconInfo beacon_info) {
      this.beacon_info = beacon_info;
    }

    public String getBrand_userame() {
      return brand_userame;
    }

    public void setBrand_userame(String brand_userame) {
      this.brand_userame = brand_userame;
    }
  }

  public Integer getErrcode() {
    return errcode;
  }

  public void setErrcode(Integer errcode) {
    this.errcode = errcode;
  }

  public String getErrmsg() {
    return errmsg;
  }

  public void setErrmsg(String errmsg) {
    this.errmsg = errmsg;
  }

  public Data getData() {
    return data;
  }

  public void setData(Data data) {
    this.data = data;
  }
}
