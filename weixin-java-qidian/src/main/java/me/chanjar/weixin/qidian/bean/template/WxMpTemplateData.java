package me.chanjar.weixin.qidian.bean.template;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Daniel Qian
 */
@Data
public class WxMpTemplateData implements Serializable {
  private static final long serialVersionUID = 6301835292940277870L;

  private String name;
  private String value;
  private String color;

  public WxMpTemplateData() {
  }

  public WxMpTemplateData(String name, String value) {
    this.name = name;
    this.value = value;
  }

  public WxMpTemplateData(String name, String value, String color) {
    this.name = name;
    this.value = value;
    this.color = color;
  }

}
