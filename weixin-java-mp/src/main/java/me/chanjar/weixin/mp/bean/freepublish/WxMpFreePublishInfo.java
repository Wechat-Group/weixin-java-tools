package me.chanjar.weixin.mp.bean.freepublish;

import com.google.gson.annotations.SerializedName;
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
 * 发布能力-通过 article_id 获取已发布文章.
 *
 * @author dragon
 * @date 2021-10-23
 */
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class WxMpFreePublishInfo implements ToJson, Serializable {
  private static final long serialVersionUID = 3331288672996730705L;

  /**
   * 文章列表
   */
  @SerializedName("news_item")
  private List<WxMpFreePublishArticles> newsItem;

  public static WxMpFreePublishInfo fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpFreePublishInfo.class);
  }

  @Override
  public String toJson() {
    return WxGsonBuilder.create().toJson(this);
  }

}
