package me.chanjar.weixin.mp.bean.card;


import java.io.Serializable;

import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

/**
 * 微信卡券.
 *
 * @author YuJian
 * @version 15/11/11
 */
@Data
public class WxMpCard implements Serializable {
  private static final long serialVersionUID = 9214301870017772921L;

  private String cardId;

  private Long beginTime;

  private Long endTime;

  private String userCardStatus;

  private String membershipNumber;

  private String code;

  private Integer bonus;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}
