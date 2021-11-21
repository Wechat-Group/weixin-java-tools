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
 * 草稿箱能力-新建草稿.
 *
 * @author dragon
 * @date 2021-10-22
 */
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class WxMpAddDraft implements ToJson, Serializable {
  private static final long serialVersionUID = 2481699972367293721L;

  /**
   * 图文素材列表
   */
  @SerializedName("articles")
  private List<WxMpDraftArticles> articles;

  public static WxMpAddDraft fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpAddDraft.class);
  }

  @Override
  public String toJson() {
    return WxGsonBuilder.create().toJson(this);
  }

}
