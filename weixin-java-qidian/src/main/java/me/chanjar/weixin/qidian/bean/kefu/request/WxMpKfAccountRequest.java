package me.chanjar.weixin.qidian.bean.kefu.request;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import me.chanjar.weixin.qidian.util.json.WxMpGsonBuilder;

@Data
@Builder
@AllArgsConstructor
public class WxMpKfAccountRequest implements Serializable {
  private static final long serialVersionUID = -5451863610674856927L;

  /**
   * kf_account.
   * 完整客服账号，格式为：账号前缀@公众号微信号
   */
  @SerializedName("kf_account")
  private String kfAccount;

  /**
   * nickname.
   * 客服昵称，最长6个汉字或12个英文字符
   */
  @SerializedName("nickname")
  private String nickName;

  /**
   * invite_wx.
   * 接收绑定邀请的客服微信号
   */
  @SerializedName("invite_wx")
  private String inviteWx;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  public String toJson() {
    return WxMpGsonBuilder.create().toJson(this);
  }

}
