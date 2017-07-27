package me.chanjar.weixin.mp.bean.membercard;

import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * 用于 `7 更新会员信息` 的接口调用后的返回结果
 * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1451025283
 *
 * @author YuJian(mgcnrx11@gmail.com)
 * @version 2017/7/15
 */
public class WxMpMemberCardUpdateResult  implements Serializable {

  private static final long serialVersionUID = 9084886191442098311L;

  private String errorCode;

  private String errorMsg;

  private String openId;

  private Integer resultBonus;

  private Integer resultBalance;

  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorMsg() {
    return errorMsg;
  }

  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }

  public String getOpenId() {
    return openId;
  }

  public void setOpenId(String openId) {
    this.openId = openId;
  }

  public Integer getResultBonus() {
    return resultBonus;
  }

  public void setResultBonus(Integer resultBonus) {
    this.resultBonus = resultBonus;
  }

  public Integer getResultBalance() {
    return resultBalance;
  }

  public void setResultBalance(Integer resultBalance) {
    this.resultBalance = resultBalance;
  }

  @Override
  public String toString() {
    return "WxMpMemberCardUpdateResult{" +
      "errorCode='" + errorCode + '\'' +
      ", errorMsg='" + errorMsg + '\'' +
      ", openId='" + openId + '\'' +
      ", resultBonus=" + resultBonus +
      ", resultBalance=" + resultBalance +
      '}';
  }

  public static WxMpMemberCardUpdateResult fromJson(String json) {
    return WxMpGsonBuilder.INSTANCE.create().fromJson(json, WxMpMemberCardUpdateResult.class);
  }
}
