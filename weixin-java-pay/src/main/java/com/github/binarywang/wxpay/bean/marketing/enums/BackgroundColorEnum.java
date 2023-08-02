package com.github.binarywang.wxpay.bean.marketing.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 券的背景颜色
 *
 * @author thinsstar
 */
@Getter
@AllArgsConstructor
public enum BackgroundColorEnum {

  /**
   * 颜色 #63B359
   */
  COLOR010("COLOR010", "#63B359"),

  /**
   * 颜色 #2C9F67
   */
  COLOR020("COLOR020", "#2C9F67"),

  /**
   * 颜色 #509FC9
   */
  COLOR030("COLOR030", "#509FC9"),

  /**
   * 颜色 #5885CF
   */
  COLOR040("COLOR040", "#5885CF"),

  /**
   * 颜色 #9062C0
   */
  COLOR050("COLOR050", "#9062C0"),

  /**
   * 颜色 #D09A45
   */
  COLOR060("COLOR060", "#D09A45"),

  /**
   * 颜色 #E4B138
   */
  COLOR070("COLOR070", "#E4B138"),

  /**
   * 颜色 #EE903C
   */
  COLOR080("COLOR080", "#EE903C"),

  /**
   * 颜色 #DD6549
   */
  COLOR090("COLOR090", "#DD6549"),

  /**
   * 颜色 #CC463D
   */
  COLOR100("COLOR100", "#CC463D"),
  ;

  /**
   * 色值
   */
  private final String value;

  /**
   * 十六进制颜色码
   */
  private final String code;
}
