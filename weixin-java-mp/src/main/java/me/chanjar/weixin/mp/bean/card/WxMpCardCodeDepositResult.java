package me.chanjar.weixin.mp.bean.card;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;
import java.util.List;


/**
 * @author S <sshzh90@gmail.com>
 */
@Data
public class WxMpCardCodeDepositResult  implements Serializable {
  private static final long serialVersionUID = 2955588617765355420L;

  /**
   * 成功的code
   */
  @SerializedName("succ_code")
  private List<String> succCode;

  /**
   * 重复导入的code
   */
  @SerializedName("duplicate_code")
  private List<String> duplicateCode;

  /**
   * 失败的code
   */
  @SerializedName("fail_code")
  private List<String> failCode;


  public static WxMpCardCodeDepositResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpCardCodeDepositResult.class);
  }


  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

}

