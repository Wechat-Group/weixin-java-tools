package me.chanjar.weixin.channel.bean.league;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class SimpleProductInfo implements Serializable {

  private static final long serialVersionUID = -2444641123422095497L;
  /** 标题 */
  @JsonProperty("title")
  protected String title;

  /** 副标题 */
  @JsonProperty("sub_title")
  protected String subTitle;

  /** 主图,多张,列表,最多9张,每张不超过2MB */
  @JsonProperty("head_imgs")
  protected List<String> headImgs;

  /** 商详信息 */
  @JsonProperty("desc_info")
  protected DescInfo descInfo;

  /** 类目信息 */
  @JsonProperty("cats")
  protected List<CatInfo> cats;

}
