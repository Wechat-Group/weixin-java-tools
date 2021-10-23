package me.chanjar.weixin.mp.bean.freepublish;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import me.chanjar.weixin.common.bean.ToJson;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 发布能力-发布状态轮询接口，通过publishId返回 article_id(删除发布时需要用到).
 *
 * @author dragon
 * @date 2021-10-23
 */
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class WxMpFreePublishStatus implements ToJson, Serializable {

  private static final long serialVersionUID = -7526369389476785732L;
  private String publish_id;
  private Integer publish_status;
  private String article_id;
  private ArticleDetail article_detail;
  private List<Integer> fail_idx;

  @NoArgsConstructor
  @Data
  public static class ArticleDetail {
    private Integer count;
    private List<Item> item;

    @NoArgsConstructor
    @Data
    public static class Item {
      private Integer idx;
      private String article_url;
    }
  }

  public static WxMpFreePublishStatus fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpFreePublishStatus.class);
  }

  @Override
  public String toJson() {
    return WxGsonBuilder.create().toJson(this);
  }
}
