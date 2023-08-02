package me.chanjar.weixin.open.bean.minishop;

import lombok.Data;

import java.io.Serializable;

@Data
public class MinishopDeliveryTemplate implements Serializable {
  private static final long serialVersionUID = 6408833494371482534L;

  public enum ValuationType {
    PACKAGE,
    WEIGHT
  }

  private Integer templateId;

  private String name;

  private ValuationType valuationType;
}
