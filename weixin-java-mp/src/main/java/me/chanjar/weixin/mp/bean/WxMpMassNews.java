package me.chanjar.weixin.mp.bean;

import me.chanjar.weixin.common.util.ToStringUtils;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 群发时用到的图文消息素材
 *
 * @author chanjarster
 */
public class WxMpMassNews implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 565937155013581016L;
  private List<WxMpMassNewsArticle> articles = new ArrayList<>();

  public List<WxMpMassNewsArticle> getArticles() {
    return this.articles;
  }

  public void addArticle(WxMpMassNewsArticle article) {
    this.articles.add(article);
  }

  public String toJson() {
    return WxMpGsonBuilder.INSTANCE.create().toJson(this);
  }

  public boolean isEmpty() {
    return this.articles == null || this.articles.isEmpty();
  }

  /**
   * <pre>
   * 群发图文消息article
   * 1. thumbMediaId  (必填) 图文消息缩略图的media_id，可以在基础支持-上传多媒体文件接口中获得
   * 2. author          图文消息的作者
   * 3. title           (必填) 图文消息的标题
   * 4. contentSourceUrl 在图文消息页面点击“阅读原文”后的页面链接
   * 5. content (必填)  图文消息页面的内容，支持HTML标签
   * 6. digest          图文消息的描述
   * 7, showCoverPic  是否显示封面，true为显示，false为不显示
   * </pre>
   *
   * @author chanjarster
   */
  public static class WxMpMassNewsArticle {
    /**
     * (必填) 图文消息缩略图的media_id，可以在基础支持-上传多媒体文件接口中获得
     */
    private String thumbMediaId;
    /**
     * 图文消息的作者
     */
    private String author;
    /**
     * (必填) 图文消息的标题
     */
    private String title;
    /**
     * 在图文消息页面点击“阅读原文”后的页面链接
     */
    private String contentSourceUrl;
    /**
     * (必填) 图文消息页面的内容，支持HTML标签
     */
    private String content;
    /**
     * 图文消息的描述
     */
    private String digest;
    /**
     * 是否显示封面，true为显示，false为不显示
     */
    private boolean showCoverPic;

    public String getThumbMediaId() {
      return this.thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
      this.thumbMediaId = thumbMediaId;
    }

    public String getAuthor() {
      return this.author;
    }

    public void setAuthor(String author) {
      this.author = author;
    }

    public String getTitle() {
      return this.title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getContentSourceUrl() {
      return this.contentSourceUrl;
    }

    public void setContentSourceUrl(String contentSourceUrl) {
      this.contentSourceUrl = contentSourceUrl;
    }

    public String getContent() {
      return this.content;
    }

    public void setContent(String content) {
      this.content = content;
    }

    public String getDigest() {
      return this.digest;
    }

    public void setDigest(String digest) {
      this.digest = digest;
    }

    public boolean isShowCoverPic() {
      return this.showCoverPic;
    }

    public void setShowCoverPic(boolean showCoverPic) {
      this.showCoverPic = showCoverPic;
    }

    @Override
    public String toString() {
      return ToStringUtils.toSimpleString(this);
    }
  }

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }
}
