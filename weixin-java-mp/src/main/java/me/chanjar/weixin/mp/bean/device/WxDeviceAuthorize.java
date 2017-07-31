package me.chanjar.weixin.mp.bean.device;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by keungtung on 10/12/2016.
 */
public class WxDeviceAuthorize extends AbstractDeviceBean {
  @SerializedName("device_num")
  private String deviceNum;
  @SerializedName("op_type")
  private String opType;
  @SerializedName("product_id")
  private String productId;
  @SerializedName("device_list")
  private List<WxDevice> deviceList = new LinkedList<>();

  public String getDeviceNum() {
    return deviceNum;
  }

  public void setDeviceNum(String deviceNum) {
    this.deviceNum = deviceNum;
  }

  public String getOpType() {
    return opType;
  }

  public void setOpType(String opType) {
    this.opType = opType;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public List<WxDevice> getDeviceList() {
    return deviceList;
  }

  public void setDeviceList(List<WxDevice> deviceList) {
    this.deviceList = deviceList;
  }

  public void addDevice(WxDevice... devices) {
    this.deviceList.addAll(Arrays.asList(devices));
  }
}
