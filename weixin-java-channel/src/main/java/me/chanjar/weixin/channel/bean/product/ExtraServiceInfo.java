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
public class ExtraServiceInfo implements Serializable {

  private static final long serialVersionUID = -5517806977282063174L;

  /**
   * 是否支持七天无理由退货，0-不支持七天无理由, 1-支持七天无理由, 2-支持七天无理由(定制商品除外)。 管理规则请参见七天无理由退货管理规则。类目是否必须支持七天无理由退货，
   * 可参考文档获取类目信息中的字段attr.seven_day_return
   */
  @JsonProperty("seven_day_return")
  private Integer sevenDayReturn;
}
