package me.chanjar.weixin.mp.bean.freepublish;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * 一条发布的图文记录
 *
 * @author dragon
 * @date 2021-10-23
 */
@NoArgsConstructor
@Data
public class WxMpFreePublishArticles implements Serializable {
  private static final long serialVersionUID = -6435229818150835883L;

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
   * 图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空。
   */
  @SerializedName("digest")
  private String digest;
  /**
   * 图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS
   */
  @SerializedName("content")
  private String content;
  /**
   * 图文消息的原文地址，即点击“阅读原文”后的URL
   */
  @SerializedName("content_source_url")
  private String contentSourceUrl;
  /**
   * 图文消息的封面图片素材id（一定是永久MediaID）
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

  /**
   * 图文消息的封面url
   */
  @SerializedName("thumb_url")
  private String thumbUrl;

  /*
   * ===== 上面的参数，就是草稿箱的内容的字段，为了后续扩展，单独写一份====
   */

  /**
   * 草稿的临时链接
   */
  @SerializedName("url")
  private String url;
  /**
   * 该图文是否被删除
   */
  @SerializedName("is_deleted")
  private Boolean isDeleted;

  public static WxMpFreePublishArticles fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpFreePublishArticles.class);
  }
}
