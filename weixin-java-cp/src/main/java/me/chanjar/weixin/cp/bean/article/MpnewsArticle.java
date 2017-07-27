package me.chanjar.weixin.cp.bean.article;

import java.io.Serializable;

/**
 * <pre>
 *  Created by BinaryWang on 2017/3/27.
 * </pre>
 *
 * @author Binary Wang
 */
public class MpnewsArticle implements Serializable {
  private static final long serialVersionUID = 6985871812170756481L;

  private String title;
  private String thumbMediaId;
  private String author;
  private String contentSourceUrl;
  private String content;
  private String digest;
  private String showCoverPic;

  private MpnewsArticle(Builder builder) {
    setTitle(builder.title);
    setThumbMediaId(builder.thumbMediaId);
    setAuthor(builder.author);
    setContentSourceUrl(builder.contentSourceUrl);
    setContent(builder.content);
    setDigest(builder.digest);
    setShowCoverPic(builder.showCoverPic);
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getThumbMediaId() {
    return thumbMediaId;
  }

  public void setThumbMediaId(String thumbMediaId) {
    this.thumbMediaId = thumbMediaId;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getContentSourceUrl() {
    return contentSourceUrl;
  }

  public void setContentSourceUrl(String contentSourceUrl) {
    this.contentSourceUrl = contentSourceUrl;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getDigest() {
    return digest;
  }

  public void setDigest(String digest) {
    this.digest = digest;
  }

  public String getShowCoverPic() {
    return showCoverPic;
  }

  public void setShowCoverPic(String showCoverPic) {
    this.showCoverPic = showCoverPic;
  }

  public static final class Builder {
    private String title;
    private String thumbMediaId;
    private String author;
    private String contentSourceUrl;
    private String content;
    private String digest;
    private String showCoverPic;

    private Builder() {
    }

    public Builder title(String title) {
      this.title = title;
      return this;
    }

    public Builder thumbMediaId(String thumbMediaId) {
      this.thumbMediaId = thumbMediaId;
      return this;
    }

    public Builder author(String author) {
      this.author = author;
      return this;
    }

    public Builder contentSourceUrl(String contentSourceUrl) {
      this.contentSourceUrl = contentSourceUrl;
      return this;
    }

    public Builder content(String content) {
      this.content = content;
      return this;
    }

    public Builder digest(String digest) {
      this.digest = digest;
      return this;
    }

    public Builder showCoverPic(String showCoverPic) {
      this.showCoverPic = showCoverPic;
      return this;
    }

    public MpnewsArticle build() {
      return new MpnewsArticle(this);
    }
  }
}
