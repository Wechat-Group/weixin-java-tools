package me.chanjar.weixin.mp.bean.card;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.bean.result.WxMpResult;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;


@Data
public class WxMpCardCodeDepositResult extends WxMpResult implements Serializable {

  private static final long serialVersionUID = 2955588617765355420L;

  /**
   * 成功个数
   */
  @SerializedName("succ_code")
  private Integer succCode;

  /**
   * 重复导入的code会自动被过滤
   */
  @SerializedName("duplicate_code")
  private Integer duplicateCode;

  /**
   * 失败个数
   */
  @SerializedName("fail_code")
  private Integer failCode;


  public static WxMpCardCodeDepositResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpCardCodeDepositResult.class);
  }


  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

}

