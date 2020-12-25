package me.chanjar.weixin.qidian.bean.card.membercard;

import lombok.Data;
import me.chanjar.weixin.qidian.util.json.WxMpGsonBuilder;


/**
 * @author thomas
 * @date 2019/4/26
 */
@Data
public class WxMpMemberCardActivateTempInfoResult {

  private String errorCode;

  private String errorMsg;

  private MemberCardUserInfo userInfo;

  public static WxMpMemberCardActivateTempInfoResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpMemberCardActivateTempInfoResult.class);
  }

}
