package cn.binarywang.wx.miniapp.bean.product;

import java.io.Serializable;
import lombok.Data;

@Data
public class WxMinishopDeliveryCompany implements Serializable {
  private static final long serialVersionUID = 3736970376549639779L;
  private String deliveryId;

  private String deliveryName;
}
