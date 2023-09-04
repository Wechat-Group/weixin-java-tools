package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单 搜索条件
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class OrderSearchCondition implements Serializable {

  private static final long serialVersionUID = 5492584333971883140L;
  /** 商品标题关键词 */
  @JsonProperty("title")
  private String title;

  /** 商品编码 */
  @JsonProperty("sku_code")
  private String skuCode;

  /** 收件人 */
  @JsonProperty("user_name")
  private String userName;

  /** 收件人电话 */
  @JsonProperty("tel_number")
  private String telNumber;

  /** 选填，只搜一个订单时使用 */
  @JsonProperty("order_id")
  private String orderId;

}
