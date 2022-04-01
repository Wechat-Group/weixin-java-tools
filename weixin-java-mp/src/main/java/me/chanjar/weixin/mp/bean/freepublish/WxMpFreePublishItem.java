package me.chanjar.weixin.mp.bean.freepublish;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * 发布列表的一条记录
 *
 * @author dragon
 * @date 2021-10-23
 */
@NoArgsConstructor
@Data
public class WxMpFreePublishItem implements Serializable {
  private static final long serialVersionUID = -6435229818150835883L;

  /**
   * 成功发布的图文消息id
   */
  @SerializedName("article_id")
  private String articleId;

  /**
   * 图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS。
   */
  @SerializedName("content")
  private WxMpFreePublishInfo content;

  /**
   * 这篇图文消息素材的最后更新时间
   */
  @SerializedName("update_time")
  private String updateTime;

  public static WxMpFreePublishItem fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpFreePublishItem.class);
  }
}
