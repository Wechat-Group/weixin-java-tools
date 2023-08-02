package me.chanjar.weixin.cp.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * userid转换
 * 将代开发应用或第三方应用获取的密文open_userid转换为明文userid
 * @author yiyingcanfeng
 */
@Data
public class WxCpOpenUseridToUseridResult implements Serializable {
  private static final long serialVersionUID = 5179329535139861515L;

  @Override
  public String toString() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  /**
   * From json wx cp open userid to userid result.
   *
   * @param json the json
   * @return the wx cp open userid to userid result
   */
  public static WxCpOpenUseridToUseridResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpOpenUseridToUseridResult.class);
  }

  @SerializedName("errcode")
  private Integer errCode;

  @SerializedName("errmsg")
  private String errMsg;

  @SerializedName("userid_list")
  private List<WxCpUseridToOpenUserid> useridList;

  @SerializedName("invalid_open_userid_list")
  private List<String> invalidOpenUseridList;


}
