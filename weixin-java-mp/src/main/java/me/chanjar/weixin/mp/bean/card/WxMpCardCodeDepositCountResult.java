package me.chanjar.weixin.mp.bean.card;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;


/**
 * @author S <sshzh90@gmail.com>
 */
@Data
public class WxMpCardCodeDepositCountResult implements Serializable {
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

