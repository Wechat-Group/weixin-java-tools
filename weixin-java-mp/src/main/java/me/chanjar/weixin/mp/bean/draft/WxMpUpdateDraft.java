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

/**
 * 草稿箱能力-修改草稿.
 *
 * @author dragon
 * @date 2021-10-22
 */
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class WxMpUpdateDraft implements ToJson, Serializable {

  private static final long serialVersionUID = -8564521168423899915L;
  /**
   * 要修改的图文消息的id
   */
  @SerializedName("media_id")
  private String mediaId;

  /**
   * 要更新的文章在图文消息中的位置（多图文消息时，此字段才有意义），第一篇为0
   */
  @SerializedName("index")
  private Integer index;

  /**
   * 图文素材列表
   */
  @SerializedName("articles")
  private WxMpDraftArticles articles;

  public static WxMpUpdateDraft fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpUpdateDraft.class);
  }

  @Override
  public String toJson() {
    return WxGsonBuilder.create().toJson(this);
  }

}
