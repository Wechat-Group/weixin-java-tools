package me.chanjar.weixin.open.bean.minishop.goods;

import lombok.Data;

import java.io.Serializable;

@Data
public class WxMinishopDeliveryCompany implements Serializable {
  private static final long serialVersionUID = 3736970376549639779L;
  private String deliveryId;

  private String deliveryName;
}
