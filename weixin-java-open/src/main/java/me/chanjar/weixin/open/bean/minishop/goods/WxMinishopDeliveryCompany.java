package me.chanjar.weixin.open.bean.minishop.goods;

import lombok.Data;

import java.io.Serializable;

@Data
public class WxMinishopDeliveryCompany implements Serializable {
  private String deliveryId;

  private String deliveryName;
}
