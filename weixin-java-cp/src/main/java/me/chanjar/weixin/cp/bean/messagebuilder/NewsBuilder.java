package me.chanjar.weixin.cp.bean.messagebuilder;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.cp.bean.article.NewArticle;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 图文消息builder
 * <pre>
 * 用法:
 * WxCustomMessage m = WxCustomMessage.NEWS().addArticle(article).toUser(...).build();
 * </pre>
 *
 * @author Daniel Qian
 */
public final class NewsBuilder extends BaseBuilder<NewsBuilder> {

  private List<NewArticle> articles = new ArrayList<>();

  /**
   * Instantiates a new News builder.
   */
  public NewsBuilder() {
    this.msgType = WxConsts.KefuMsgType.NEWS;
  }

  /**
   * Add article news builder.
   *
   * @param articles the articles
   * @return the news builder
   */
  public NewsBuilder addArticle(NewArticle... articles) {
    Collections.addAll(this.articles, articles);
    return this;
  }

  /**
   * Articles news builder.
   *
   * @param articles the articles
   * @return the news builder
   */
  public NewsBuilder articles(List<NewArticle> articles) {
    this.articles = articles;
    return this;
  }

  @Override
  public WxCpMessage build() {
    WxCpMessage m = super.build();
    m.setArticles(this.articles);
    return m;
  }
}
