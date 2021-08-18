package me.chanjar.weixin.cp.bean.message.builder;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;

/**
 * @author caiqy
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxCpTextCardMessage extends WxCpMessage {

  private static final long serialVersionUID = -6243743821110199350L;

  @SerializedName("textcard")
  private TextCardMessage textCardMessage;

  public static WxCpTextCardMessageBuilder builder(String toUser, String toParty, String toTag, Integer agentId, Integer safe, Integer enableIdTrans, Integer enableDuplicateCheck, Integer duplicateCheckInterval) {
    return _builder_().toUser(toUser).toParty(toParty).toTag(toTag).agentId(agentId);
  }

  public static WxCpTextCardMessageBuilder builderToUser(String toUser) {
    return _builder_().toUser(toUser);
  }

  public static WxCpTextCardMessageBuilder builderToParty(String toParty) {
    return _builder_().toParty(toParty);
  }

  public static WxCpTextCardMessageBuilder builderToTag(String toTag) {
    return _builder_().toTag(toTag);
  }

  private static WxCpTextCardMessageBuilder _builder_() {
    return new WxCpTextCardMessageBuilder();
  }

  @Builder(builderMethodName = "_builder_")
  public WxCpTextCardMessage(String toUser, String toParty, String toTag, Integer agentId, Integer safe, Integer enableIdTrans, Integer enableDuplicateCheck, Integer duplicateCheckInterval, String title, String description, String url, String btnTxt) {
    setTo(toUser, toParty, toTag);
    setMsgType("textcard");
    setAgentId(agentId);
    setSafe(safe);
    setEnableIdTrans(enableIdTrans);
    setEnableDuplicateCheck(enableDuplicateCheck);
    setDuplicateCheckInterval(duplicateCheckInterval);
    this.textCardMessage = new TextCardMessage(title, description, url, btnTxt);
  }

  @Data
  public static class TextCardMessage {

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("url")
    private String url;

    @SerializedName("btntxt")
    private String btnTxt;


    public TextCardMessage(String title, String description, String url, String btnTxt) {
      this.title = title;
      this.description = description;
      this.url = url;
      this.btnTxt = btnTxt;
    }
  }
}
