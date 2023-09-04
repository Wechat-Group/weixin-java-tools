package me.chanjar.weixin.channel.bean.league.window;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 团长商品搜索参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductSearchParam implements Serializable {

  private static final long serialVersionUID = -4771046746777827382L;
  /** 团长appid */
  @JsonProperty("appid")
  private String appid;

  /** 视频号openfinderid */
  @JsonProperty("openfinderid")
  private String openfinderid;

  /** 起始位置（从0开始） */
  @JsonProperty("offset")
  private Integer offset;

  /** page_size(默认100, 最大500) */
  @JsonProperty("page_size")
  private Integer pageSize;

  /** 默认为false */
  @JsonProperty("need_total_num")
  private Boolean needTotalNum;

}
