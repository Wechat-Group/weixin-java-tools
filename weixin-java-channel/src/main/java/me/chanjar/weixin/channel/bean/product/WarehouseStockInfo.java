package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class WarehouseStockInfo implements Serializable {

  private static final long serialVersionUID = 3184902895765107425L;

  /** 区域库存外部id */
  @JsonProperty("out_warehouse_id")
  private String outWarehouseId;

  /** 区域库存数量 */
  @JsonProperty("num")
  private Integer num;
}
