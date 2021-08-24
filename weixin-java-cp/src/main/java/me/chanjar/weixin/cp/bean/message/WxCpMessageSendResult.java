package me.chanjar.weixin.cp.bean.message;

import com.google.common.base.Splitter;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 消息发送结果对象类.
 * Created by Binary Wang on 2017-6-22.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
public class WxCpMessageSendResult implements Serializable {
  private static final long serialVersionUID = 916455987193190004L;

  @Override
  public String toString() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  public static WxCpMessageSendResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpMessageSendResult.class);
  }

  @SerializedName("errcode")
  private Integer errCode;

  @SerializedName("errmsg")
  private String errMsg;

  @SerializedName("invaliduser")
  private String invalidUser;

  @SerializedName("invalidparty")
  private String invalidParty;

  @SerializedName("invalidtag")
  private String invalidTag;
  
  @SerializedName("msgid")
  private String msgId;

  public List<String> getInvalidUserList() {
    return this.content2List(this.invalidUser);
  }

  private List<String> content2List(String content) {
    if (StringUtils.isBlank(content)) {
      return Collections.emptyList();
    }

    return Splitter.on("|").splitToList(content);
  }

  public List<String> getInvalidPartyList() {
    return this.content2List(this.invalidParty);
  }

  public List<String> getInvalidTagList() {
    return this.content2List(this.invalidTag);
  }
}
