package com.github.binarywang.wxpay.bean.applyment.enums;

/**
 * 个体户/企业/党政、机关及事业单位/其他组织：可选择任一证件类型。
 * 枚举值
 */
public enum IdTypeEnum {
  /**
   * 中国大陆居民-身份证
   */
  IDENTIFICATION_TYPE_IDCARD,
  /**
   * 其他国家或地区居民-护照
   */
  IDENTIFICATION_TYPE_OVERSEA_PASSPORT,
  /**
   * 中国香港居民-来往内地通行证
   */
  IDENTIFICATION_TYPE_HONGKONG_PASSPORT,
  /**
   * 中国澳门居民-来往内地通行证
   */
  IDENTIFICATION_TYPE_MACAO_PASSPORT,
  /**
   * 中国台湾居民-来往大陆通行证
   */
  IDENTIFICATION_TYPE_TAIWAN_PASSPORT,
  ;
}
