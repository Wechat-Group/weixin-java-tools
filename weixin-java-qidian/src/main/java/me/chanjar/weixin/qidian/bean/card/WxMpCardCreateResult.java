package me.chanjar.weixin.qidian.bean.card;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.qidian.util.json.WxMpGsonBuilder;

/**
 * .
 * @author yuanqixun
 */
@Data
public class WxMpCardCreateResult implements Serializable {
  private static final long serialVersionUID = -128818731449449537L;
  @SerializedName("card_id")
  private String cardId;
  private Integer errcode;
  private String errmsg;

  public boolean isSuccess() {
    return 0 == errcode;
  }

  public static WxMpCardCreateResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpCardCreateResult.class);
  }

  public static WxMpCardCreateResult failure(String errmsg) {
    WxMpCardCreateResult result = new WxMpCardCreateResult();
    result.setErrcode(500);
    result.setErrmsg(errmsg);
    return result;
  }

  public static WxMpCardCreateResult success() {
    WxMpCardCreateResult result = new WxMpCardCreateResult();
    result.setErrcode(0);
    result.setErrmsg("ok");
    return result;
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

}

