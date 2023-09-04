package me.chanjar.weixin.channel.bean.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分类资质信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class CategoryQualification implements Serializable {

  private static final long serialVersionUID = 6495550078851408381L;

  /** 类目 */
  @JsonProperty("cat")
  private ShopCategory category;

  /** 资质信息 */
  @JsonProperty("qua")
  private QualificationInfo info;

  /** 商品资质信息 */
  @JsonProperty("product_qua")
  private QualificationInfo productInfo;

}
