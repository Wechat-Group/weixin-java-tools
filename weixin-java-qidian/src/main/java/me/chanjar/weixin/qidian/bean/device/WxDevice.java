package me.chanjar.weixin.qidian.bean.device;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author keungtung
 * @date 10/12/2016
 */
@Data
public class WxDevice implements Serializable {
  private static final long serialVersionUID = -3284819760735456195L;

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
}
