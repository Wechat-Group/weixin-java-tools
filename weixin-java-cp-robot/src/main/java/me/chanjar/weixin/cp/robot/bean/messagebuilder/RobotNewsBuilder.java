package me.chanjar.weixin.cp.robot.bean.messagebuilder;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.cp.bean.article.NewArticle;
import me.chanjar.weixin.cp.robot.bean.WxCpRobotMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 机器人图文消息builder
 * <pre>
 * 用法:
 * WxCpRobotMessage m = WxCpRobotMessage.NEWS().addArticle(article).addMember(...).build();
 * </pre>
 *
 * @author Daniel Qian
 */
public final class RobotNewsBuilder extends RobotBuilder<RobotNewsBuilder> {

  private List<NewArticle> articles = new ArrayList<>();

  public RobotNewsBuilder() {
    this.msgType = WxConsts.KefuMsgType.NEWS;
  }

  public RobotNewsBuilder addArticle(NewArticle... articles) {
    Collections.addAll(this.articles, articles);
    return this;
  }

  public RobotNewsBuilder articles(List<NewArticle> articles) {
    this.articles = articles;
    return this;
  }

  @Override
  public WxCpRobotMessage build() {
    WxCpRobotMessage m = super.build();
    m.setArticles(this.articles);
    return m;
  }
}
