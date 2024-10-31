package cn.binarywang.wx.miniapp.bean.intractiy;

import org.apache.commons.lang3.builder.ToStringBuilder;

class BasicWxMaOrder {

  private String wxStoreId;
  private String userName;
  private String userPhone;
  private double userLng;
  private double userLat;
  private String userAddress;
  private int useSandbox;

  public String getWxStoreId() {
    return wxStoreId;
  }

  public void setWxStoreId(String wxStoreId) {
    this.wxStoreId = wxStoreId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserPhone() {
    return userPhone;
  }

  public void setUserPhone(String userPhone) {
    this.userPhone = userPhone;
  }

  public double getUserLng() {
    return userLng;
  }

  public void setUserLng(double userLng) {
    this.userLng = userLng;
  }

  public double getUserLat() {
    return userLat;
  }

  public void setUserLat(double userLat) {
    this.userLat = userLat;
  }

  public String getUserAddress() {
    return userAddress;
  }

  public void setUserAddress(String userAddress) {
    this.userAddress = userAddress;
  }

  public int isUseSandbox() {
    return useSandbox;
  }

  public void setUseSandbox(int useSandbox) {
    this.useSandbox = useSandbox;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  static class Cargo {
    private String cargoName;
    private int cargoWeight;
    private int cargoType;
    private int cargoNum;
    private int cargoPrice;

    public String getCargoName() {
      return cargoName;
    }

    public void setCargoName(String cargoName) {
      this.cargoName = cargoName;
    }

    public int getCargoWeight() {
      return cargoWeight;
    }

    public void setCargoWeight(int cargoWeight) {
      this.cargoWeight = cargoWeight;
    }

    public int getCargoType() {
      return cargoType;
    }

    public void setCargoType(int cargoType) {
      this.cargoType = cargoType;
    }

    public int getCargoNum() {
      return cargoNum;
    }

    public void setCargoNum(int cargoNum) {
      this.cargoNum = cargoNum;
    }

    public int getCargoPrice() {
      return cargoPrice;
    }

    public void setCargoPrice(int cargoPrice) {
      this.cargoPrice = cargoPrice;
    }

    @Override
    public String toString() {
      return ToStringBuilder.reflectionToString(this);
    }
  }
}
