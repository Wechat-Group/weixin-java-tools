package me.chanjar.weixin.cp.bean.message;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * @author liuzhibin
 * Date: 2022/10/19
 */
@Data
public class WxCpMessageRecallResult implements Serializable {


  private static final long serialVersionUID = -1108821386251764702L;

  @SerializedName("errcode")
  private Integer errCode;

  @SerializedName("errmsg")
  private String errMsg;


  public static WxCpMessageRecallResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpMessageRecallResult.class);
  }

  @Override
  public String toString() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}
