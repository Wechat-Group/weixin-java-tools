package me.chanjar.weixin.cp.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <pre>
 *  性别枚举
 *  Created by BinaryWang on 2018/4/22.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Getter
@AllArgsConstructor
public enum Gender {
  /**
   * 未定义
   */
  UNDEFINED("未定义", "0"),
  /**
   * 男
   */
  MALE("男", "1"),
  /**
   * 女
   */
  FEMALE("女", "2");

  private final String genderName;
  private final String code;

  public static Gender fromCode(String code) {
    for (Gender a : Gender.values()) {
      if (a.code.equals(code)) {
        return a;
      }
    }

    return null;
  }
}
