package me.chanjar.weixin.cp.robot.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 机器人消息发送结果对象类.
 *
 * @author linfeng
 */
@Data
public class WxCpRobotMessageSendResult implements Serializable {
  private static final long serialVersionUID = 916455987193190004L;

  @Override
  public String toString() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  public static WxCpRobotMessageSendResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpRobotMessageSendResult.class);
  }

  @SerializedName("errcode")
  private Integer errCode;

  @SerializedName("errmsg")
  private String errMsg;

}
