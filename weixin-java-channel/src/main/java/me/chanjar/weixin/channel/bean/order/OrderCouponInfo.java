package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 卡券信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class OrderCouponInfo implements Serializable {

  private static final long serialVersionUID = -2033350505767196339L;
  /** 用户优惠券id */
  @JsonProperty("user_coupon_id")
  private String userCouponId;
}
