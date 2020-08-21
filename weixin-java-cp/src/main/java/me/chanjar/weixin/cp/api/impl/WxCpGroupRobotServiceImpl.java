package me.chanjar.weixin.cp.api.impl;

import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpGroupRobotService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpGroupRobotMessage;
import me.chanjar.weixin.cp.bean.article.NewArticle;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.constant.WxCpApiPathConsts;

import java.util.List;

/**
 * 微信群机器人消息发送api 实现
 *
 * @author yr
 * @date 2020-08-20
 */
@RequiredArgsConstructor
public class WxCpGroupRobotServiceImpl implements WxCpGroupRobotService {
  private final WxCpService cpService;

  private String getApiUrl() {
    WxCpConfigStorage wxCpConfigStorage = cpService.getWxCpConfigStorage();
    return wxCpConfigStorage.getApiUrl(WxCpApiPathConsts.WEBHOOK_SEND) + wxCpConfigStorage.getWebhookKey();
  }

  @Override
  public void sendText(String content, List<String> mentionedList, List<String> mobileList) throws WxErrorException {
    WxCpGroupRobotMessage message = new WxCpGroupRobotMessage()
      .setMsgType(WxConsts.GroupRobotMsgType.TEXT)
      .setContent(content)
      .setMentionedList(mentionedList)
      .setMentionedMobileList(mobileList);
    cpService.postWithoutToken(this.getApiUrl(), message.toJson());
  }

  @Override
  public void sendMarkDown(String content) throws WxErrorException {
    WxCpGroupRobotMessage message = new WxCpGroupRobotMessage()
      .setMsgType(WxConsts.GroupRobotMsgType.MARKDOWN)
      .setContent(content);
    cpService.postWithoutToken(this.getApiUrl(), message.toJson());
  }

  @Override
  public void sendImage(String base64, String md5) throws WxErrorException {
    WxCpGroupRobotMessage message = new WxCpGroupRobotMessage()
      .setMsgType(WxConsts.GroupRobotMsgType.IMAGE)
      .setBase64(base64)
      .setMd5(md5);
    cpService.postWithoutToken(this.getApiUrl(), message.toJson());
  }

  @Override
  public void sendNews(List<NewArticle> articleList) throws WxErrorException {
    WxCpGroupRobotMessage message = new WxCpGroupRobotMessage()
      .setMsgType(WxConsts.GroupRobotMsgType.NEWS)
      .setArticles(articleList);
    cpService.postWithoutToken(this.getApiUrl(), message.toJson());
  }

}
