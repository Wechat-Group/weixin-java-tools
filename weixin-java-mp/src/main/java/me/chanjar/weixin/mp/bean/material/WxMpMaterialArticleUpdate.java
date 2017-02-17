package me.chanjar.weixin.mp.bean.material;

import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

public class WxMpMaterialArticleUpdate implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -7611963949517780270L;
  private String mediaId;
  private int index;
  private WxMpMaterialNews.WxMpMaterialNewsArticle articles;

  public String getMediaId() {
    return this.mediaId;
  }

  public void setMediaId(String mediaId) {
    this.mediaId = mediaId;
  }

  public int getIndex() {
    return this.index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public WxMpMaterialNews.WxMpMaterialNewsArticle getArticles() {
    return this.articles;
  }

  public void setArticles(WxMpMaterialNews.WxMpMaterialNewsArticle articles) {
    this.articles = articles;
  }

  public String toJson() {
    return WxMpGsonBuilder.create().toJson(this);
  }

  @Override
  public String toString() {
    return "WxMpMaterialArticleUpdate [" + "mediaId=" + this.mediaId + ", index=" + this.index + ", articles=" + this.articles + "]";
  }
}
