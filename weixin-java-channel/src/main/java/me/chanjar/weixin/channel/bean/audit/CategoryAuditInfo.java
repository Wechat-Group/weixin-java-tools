package me.chanjar.weixin.channel.bean.audit;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 类目审核信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryAuditInfo implements Serializable {

  private static final long serialVersionUID = -8792967130645424788L;

  /** 一级类目，字符类型，最长不超过10 */
  @JsonProperty("level1")
  private Long level1;

  /** 二级类目，字符类型，最长不超过10 */
  @JsonProperty("level2")
  private Long level2;

  /** 三级类目，字符类型，最长不超过10 */
  @JsonProperty("level3")
  private Long level3;

  /** 资质材料，图片url，图片类型，最多不超过10张 */
  @JsonProperty("certificate")
  private List<String> certificates;
}
