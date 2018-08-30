package me.chanjar.weixin.mp.bean.card.enums;

/**
 * 微信卡券激活字段类型
 *
 * @author yuanqixun
 * @date 2018-08-30
 */
public enum CardFieldType {
  COMMON_FIELD("微信选项"),
  CUSTOM_FIELD("自定义选项"),
  RICH_FIELD("自定义富文本类型");

  private String description;

  CardFieldType(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
