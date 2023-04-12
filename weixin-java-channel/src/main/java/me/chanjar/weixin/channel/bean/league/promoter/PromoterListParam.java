package me.chanjar.weixin.channel.bean.league.promoter;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 达人列表请求
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromoterListParam implements Serializable {

  private static final long serialVersionUID = -6179472484874537538L;
  /** 页面下标，下标从1开始，默认为1 */
  @JsonProperty("page_index")
  protected Integer pageIndex;

  /** 页面下标，下标从1开始，默认为1 */
  @JsonProperty("page_size")
  protected Integer pageSize;

  /** 拉取该状态下的达人列表 */
  private Integer status;
}
