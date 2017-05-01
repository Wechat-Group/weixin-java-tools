package me.chanjar.weixin.cp.bean.article;

/**
 * <pre>
 *  Created by BinaryWang on 2017/3/27.
 * </pre>
 *
 * @author Binary Wang
 */
public class NewArticle {

  private String title;
  private String description;
  private String url;
  private String picUrl;

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getPicUrl() {
    return this.picUrl;
  }

  public void setPicUrl(String picUrl) {
    this.picUrl = picUrl;
  }

}
