package me.chanjar.weixin.channel.bean.league;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商详信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class DescInfo implements Serializable {

  private static final long serialVersionUID = 5319244341160446531L;
  /** 商品详情图片(最多20张)。如果添加时没录入，回包可能不包含该字段 */
  @JsonProperty("imgs")
  private List<String> imgs;

  /** 商品详情文字。如果添加时没录入，回包可能不包含该字 */
  @JsonProperty("desc")
  private String desc;
}
