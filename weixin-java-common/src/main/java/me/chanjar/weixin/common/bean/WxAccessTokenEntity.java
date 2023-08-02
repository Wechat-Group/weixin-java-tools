package me.chanjar.weixin.common.bean;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * token
 *
 * @author cn
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxAccessTokenEntity extends WxAccessToken {
  private String appid;
}
