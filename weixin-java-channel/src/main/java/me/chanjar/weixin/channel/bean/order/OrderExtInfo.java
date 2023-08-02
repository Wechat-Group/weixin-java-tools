package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单备注信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class OrderExtInfo implements Serializable {

  private static final long serialVersionUID = 4568097877621455429L;
  /** 用户备注 */
  @JsonProperty("customer_notes")
  private String customerNotes;

  /** 商家备注 */
  @JsonProperty("merchant_notes")
  private String merchantNotes;

}
