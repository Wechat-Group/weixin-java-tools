package me.chanjar.weixin.qidian.bean.kefu.result;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.qidian.util.json.WxMpGsonBuilder;

/**
 * @author Binary Wang
 */
@Data
public class WxMpKfSessionList implements Serializable {
  private static final long serialVersionUID = -7680371346226640206L;

  /**
   * 会话列表
   */
  @SerializedName("sessionlist")
  private List<WxMpKfSession> kfSessionList;

  public static WxMpKfSessionList fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json,
      WxMpKfSessionList.class);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

}
