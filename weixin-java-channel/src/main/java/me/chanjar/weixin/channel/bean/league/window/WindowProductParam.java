package me.chanjar.weixin.channel.bean.league.window;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 团长商品
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WindowProductParam implements Serializable {

  private static final long serialVersionUID = 363738166094927337L;
  /** 团长appid */
  @JsonProperty("appid")
  private String appid;

  /** 视频号openfinderid */
  @JsonProperty("openfinderid")
  private String openfinderid;

  /** 团长商品ID */
  @JsonProperty("product_id")
  private String productId;
}
