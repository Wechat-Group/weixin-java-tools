package me.chanjar.weixin.common.bean;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author cn
 * @version 1.0
 * @description
 * @date 2023/3/27 18:34
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxAccessTokenEntity extends WxAccessToken {
  private String appid;
}
