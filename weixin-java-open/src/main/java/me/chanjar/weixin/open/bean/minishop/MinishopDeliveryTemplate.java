package me.chanjar.weixin.open.bean.minishop;

import lombok.Data;

import java.io.Serializable;

@Data
public class MinishopDeliveryTemplate implements Serializable {

  public enum ValuationType {
    PACKAGE,
    WEIGHT
  }

  private Integer templateId;

  private String name;

  private ValuationType valuationType;
}
