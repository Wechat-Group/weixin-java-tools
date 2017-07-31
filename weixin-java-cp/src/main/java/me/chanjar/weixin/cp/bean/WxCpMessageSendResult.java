package me.chanjar.weixin.cp.bean;

import com.google.common.base.Splitter;
import com.google.gson.annotations.SerializedName;
import me.chanjar.weixin.common.util.ToStringUtils;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;

/**
 * <pre>
 * 消息发送结果对象类
 * Created by Binary Wang on 2017-6-22.
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * </pre>
 */
public class WxCpMessageSendResult {
  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  public static WxCpMessageSendResult fromJson(String json) {
    return WxCpGsonBuilder.INSTANCE.create().fromJson(json, WxCpMessageSendResult.class);
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

  public Integer getErrCode() {
    return this.errCode;
  }

  public void setErrCode(Integer errCode) {
    this.errCode = errCode;
  }

  public String getErrMsg() {
    return this.errMsg;
  }

  public void setErrMsg(String errMsg) {
    this.errMsg = errMsg;
  }

  public String getInvalidUser() {
    return this.invalidUser;
  }

  public void setInvalidUser(String invalidUser) {
    this.invalidUser = invalidUser;
  }

  public String getInvalidParty() {
    return this.invalidParty;
  }

  public void setInvalidParty(String invalidParty) {
    this.invalidParty = invalidParty;
  }

  public String getInvalidTag() {
    return this.invalidTag;
  }

  public void setInvalidTag(String invalidTag) {
    this.invalidTag = invalidTag;
  }

  public List<String> getInvalidUserList() {
    return this.content2List(this.invalidUser);
  }

  private List<String> content2List(String content) {
    if(StringUtils.isBlank(content)){
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
