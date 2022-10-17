package me.chanjar.weixin.cp.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * userid转换为open_userid
 * 将自建应用或代开发应用获取的userid转换为第三方应用的userid
 * 中间对象
 * Created by gxh0797 on 2022.07.26.
 */
@Data
public class WxCpUseridToOpenUserid implements Serializable {
  private static final long serialVersionUID = 1420065684270213578L;

  @Override
  public String toString() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  /**
   * From json wx cp userid to open userid.
   *
   * @param json the json
   * @return the wx cp userid to open userid
   */
  public static WxCpUseridToOpenUserid fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpUseridToOpenUserid.class);
  }

  @SerializedName("userid")
  private String userid;

  @SerializedName("open_userid")
  private String openUserid;

}
