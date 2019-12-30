package me.chanjar.weixin.mp.bean.card;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.bean.result.WxMpResult;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;


@Data
public class WxMpCardCodeDepositCountResult extends WxMpResult implements Serializable {

  private static final long serialVersionUID = -6707587956061215868L;

  /**
   * 已经成功存入的code数目
   */
  @SerializedName("count")
  private Integer count;


  public static WxMpCardCodeDepositCountResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpCardCodeDepositCountResult.class);
  }


  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

}

