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
 * 草稿箱能力-图文素材文章实体.
 *
 * @author dragon
 * @date 2021-10-22
 */
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class WxMpDraftArticles implements ToJson, Serializable {
  /**
   * 标题
   */
  @SerializedName("title")
  private String title;
  /**
   * 作者
   */
  @SerializedName("author")
  private String author;
  /**
   * 图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空。如果本字段为没有填写，则默认抓取正文前54个字。
   */
  @SerializedName("digest")
  private String digest;
  /**
   * 图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS,涉及图片url必须来源 "上传图文消息内的图片获取URL"接口获取。外部图片url将被过滤。
   */
  @SerializedName("content")
  private String content;
  /**
   * 图文消息的原文地址，即点击“阅读原文”后的URL
   */
  @SerializedName("content_source_url")
  private String contentSourceUrl;
  /**
   * 图文消息的封面图片素材id（必须是永久MediaID）
   */
  @SerializedName("thumb_media_id")
  private String thumbMediaId;
  /**
   * 是否显示封面，0为false，即不显示，1为true，即显示(默认)
   */
  @SerializedName("show_cover_pic")
  private Integer showCoverPic;
  /**
   * 是否打开评论，0不打开(默认)，1打开
   */
  @SerializedName("need_open_comment")
  private Integer needOpenComment;
  /**
   * 是否粉丝才可评论，0所有人可评论(默认)，1粉丝才可评论
   */
  @SerializedName("only_fans_can_comment")
  private Integer onlyFansCanComment;

  public static WxMpDraftArticles fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpDraftArticles.class);
  }

  @Override
  public String toJson() {
    return WxGsonBuilder.create().toJson(this);
  }

}
