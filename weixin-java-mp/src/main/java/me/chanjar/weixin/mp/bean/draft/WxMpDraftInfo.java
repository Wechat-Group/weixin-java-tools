package me.chanjar.weixin.mp.bean.draft;

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
 * 草稿箱能力-获取草稿详情.
 *
 * @author dragon
 * @date 2021-10-22
 */
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class WxMpDraftInfo implements ToJson, Serializable {
  private static final long serialVersionUID = 6111694033486314392L;

  /**
   * 文章列表
   */
  @SerializedName("news_item")
  private List<WxMpDraftArticles> newsItem;

  public static WxMpDraftInfo fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpDraftInfo.class);
  }

  @Override
  public String toJson() {
    return WxGsonBuilder.create().toJson(this);
  }

}
