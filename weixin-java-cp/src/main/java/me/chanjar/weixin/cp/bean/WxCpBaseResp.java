package me.chanjar.weixin.cp.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 返回结果
 *
 * @author yqx & WangWong
 * @date 2020/3/16
 */
@Getter
@Setter
public class WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -4301684507150486556L;

  @SerializedName("errcode")
  protected Long errcode;

  @SerializedName("errmsg")
  protected String errmsg;

  public boolean success() {
    return getErrcode() == 0;
  }

  public static WxCpBaseResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpBaseResp.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
