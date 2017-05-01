package me.chanjar.weixin.mp.bean.device;

import com.google.gson.annotations.SerializedName;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.util.List;

/**
 * Created by keungtung on 16/12/2016.
 */
public class WxDeviceOpenIdResult extends AbstractDeviceBean {
  @SerializedName("errcode")
  private Integer errCode;
  @SerializedName("errmsg")
  private String errMsg;
  @SerializedName("open_id")
  private List<String> openIds;
  @SerializedName("resp_msg")
  private RespMsg respMsg;

  public static WxDeviceOpenIdResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxDeviceOpenIdResult.class);
  }

  public Integer getErrCode() {
    return errCode;
  }

  public void setErrCode(Integer errCode) {
    this.errCode = errCode;
  }

  public String getErrMsg() {
    return errMsg;
  }

  public void setErrMsg(String errMsg) {
    this.errMsg = errMsg;
  }

  public List<String> getOpenIds() {
    return openIds;
  }

  public void setOpenIds(List<String> openIds) {
    this.openIds = openIds;
  }

  public RespMsg getRespMsg() {
    return respMsg;
  }

  public void setRespMsg(RespMsg respMsg) {
    this.respMsg = respMsg;
  }
}
