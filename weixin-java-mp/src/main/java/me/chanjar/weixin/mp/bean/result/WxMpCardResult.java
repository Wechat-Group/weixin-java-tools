package me.chanjar.weixin.mp.bean.result;

import me.chanjar.weixin.common.util.ToStringUtils;
import me.chanjar.weixin.mp.bean.WxMpCard;

import java.io.Serializable;

/**
 * 卡券查询Code，核销Code接口返回结果
 *
 * @author YuJian
 * @version 15/11/11
 */
public class WxMpCardResult implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -7950878428289035637L;

  private String errorCode;

  private String errorMsg;

  private String openId;

  private WxMpCard card;

  private String userCardStatus;

  private Boolean canConsume;

  public String getErrorCode() {
    return this.errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorMsg() {
    return this.errorMsg;
  }

  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }

  public String getOpenId() {
    return this.openId;
  }

  public void setOpenId(String openId) {
    this.openId = openId;
  }

  public WxMpCard getCard() {
    return this.card;
  }

  public void setCard(WxMpCard card) {
    this.card = card;
  }

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  public String getUserCardStatus() {
    return this.userCardStatus;
  }

  public void setUserCardStatus(String userCardStatus) {
    this.userCardStatus = userCardStatus;
  }

  public Boolean getCanConsume() {
    return this.canConsume;
  }

  public void setCanConsume(Boolean canConsume) {
    this.canConsume = canConsume;
  }

}
