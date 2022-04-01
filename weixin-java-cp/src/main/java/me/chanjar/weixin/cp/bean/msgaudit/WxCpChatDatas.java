package me.chanjar.weixin.cp.bean.msgaudit;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 聊天记录数据内容.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpChatDatas implements Serializable {
  private static final long serialVersionUID = -5028321625140879571L;

  @SerializedName("errcode")
  private Integer errCode;

  @SerializedName("errmsg")
  private String errMsg;

  @SerializedName("chatdata")
  private List<WxCpChatData> chatData;

  @Getter
  @Setter
  public static class WxCpChatData implements Serializable {
    private static final long serialVersionUID = -5028321625140879571L;

    @SerializedName("seq")
    private Long seq;

    @SerializedName("msgid")
    private String msgId;

    @SerializedName("publickey_ver")
    private Integer publickeyVer;

    @SerializedName("encrypt_random_key")
    private String encryptRandomKey;

    @SerializedName("encrypt_chat_msg")
    private String encryptChatMsg;

    public static WxCpChatData fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, WxCpChatData.class);
    }

    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  public static WxCpChatDatas fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpChatDatas.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
