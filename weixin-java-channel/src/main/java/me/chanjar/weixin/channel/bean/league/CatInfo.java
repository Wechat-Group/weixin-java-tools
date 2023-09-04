package me.chanjar.weixin.channel.bean.league;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品分类信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class CatInfo implements Serializable {

  private static final long serialVersionUID = 8449223922139383888L;
  /** 类目id */
  @JsonProperty("cat_id")
  private String catId;

}
