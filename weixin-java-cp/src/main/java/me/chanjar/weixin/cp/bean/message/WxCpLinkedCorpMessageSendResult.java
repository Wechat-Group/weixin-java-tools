package me.chanjar.weixin.cp.bean.message;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * 互联企业的消息推送接口返回实体
 *
 * @author pg
 * @date 2021年6月22日
 */
@Setter
@Getter
public class WxCpLinkedCorpMessageSendResult extends WxCpBaseResp {
  private static final long serialVersionUID = 3990693822996824333L;

  @SerializedName("invaliduser")
  private String[] invalidUser;

  @SerializedName("invalidparty")
  private String[] invalidParty;

  @SerializedName("invalidtag")
  private String[] invalidTag;

  @Override
  public String toString() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  public static WxCpLinkedCorpMessageSendResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpLinkedCorpMessageSendResult.class);
  }

}
