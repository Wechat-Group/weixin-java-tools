package me.chanjar.weixin.qidian.bean.card.membercard;

import java.io.Serializable;

import lombok.Data;
import me.chanjar.weixin.qidian.util.json.WxMpGsonBuilder;

@Data
public class MemberCardActivateUserFormResult implements Serializable {
  private Integer errcode;
  private String errmsg;

  public boolean isSuccess() {
    return 0 == errcode;
  }

  public static MemberCardActivateUserFormResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, MemberCardActivateUserFormResult.class);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

}

