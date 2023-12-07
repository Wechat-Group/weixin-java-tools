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
 * @author <a href="https://github.com/0katekate0">Wang_Wong</a> created on  2022-01-17
 */
@Data
public class WxCpChatDatas implements Serializable {
  private static final long serialVersionUID = -5028321625140879571L;

  @SerializedName("errcode")
  private Integer errCode;

  @SerializedName("errmsg")
  private String errMsg;

  @SerializedName("sdk")
  private Long sdk;

  @SerializedName("chatdata")
  private List<WxCpChatData> chatData;

  /**
   * The type Wx cp chat data.
   */
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

    /**
     * From json wx cp chat data.
     *
     * @param json the json
     * @return the wx cp chat data
     */
    public static WxCpChatData fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, WxCpChatData.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  /**
   * From json wx cp chat datas.
   *
   * @param json the json
   * @return the wx cp chat datas
   */
  public static WxCpChatDatas fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpChatDatas.class);
  }

  /**
   * To json string.
   *
   * @return the string
   */
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
