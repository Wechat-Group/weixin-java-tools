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
 * 为标签添加或移除用户结果对象类
 * Created by Binary Wang on 2017-6-22.
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * </pre>
 */
public class WxCpTagAddOrRemoveUsersResult {
  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  public static WxCpTagAddOrRemoveUsersResult fromJson(String json) {
    return WxCpGsonBuilder.INSTANCE.create().fromJson(json, WxCpTagAddOrRemoveUsersResult.class);
  }

  @SerializedName("errcode")
  private Integer errCode;

  @SerializedName("errmsg")
  private String errMsg;

  @SerializedName("invalidlist")
  private String invalidUsers;

  @SerializedName("invalidparty")
  private String[] invalidParty;

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
    return this.invalidUsers;
  }

  public void setInvalidUser(String invalidUser) {
    this.invalidUsers = invalidUser;
  }

  public String[] getInvalidParty() {
    return this.invalidParty;
  }

  public void setInvalidParty(String[] invalidParty) {
    this.invalidParty = invalidParty;
  }

  public List<String> getInvalidUserList() {
    return this.content2List(this.invalidUsers);
  }

  private List<String> content2List(String content) {
    if(StringUtils.isBlank(content)){
      return Collections.emptyList();
    }

    return Splitter.on("|").splitToList(content);
  }

}
