package me.chanjar.weixin.cp.tp.service.impl;

import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.message.*;
import me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Message;
import me.chanjar.weixin.cp.tp.service.WxCpTpMessageService;
import me.chanjar.weixin.cp.tp.service.WxCpTpService;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * 消息推送接口实现类.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-08-30
 */
@RequiredArgsConstructor
public class WxCpTpMessageServiceImpl implements WxCpTpMessageService {
  private final WxCpTpService mainService;

  @Override
  public WxCpMessageSendResult send(String corpId, WxCpMessage message) throws WxErrorException {
    Integer agentId = message.getAgentId();
    if (null == agentId) {
      message.setAgentId(this.mainService.getWxCpTpConfigStorage().getAgentId(corpId));
    }

    String url = this.mainService.getCorpApiUrl(Message.MESSAGE_SEND, corpId);
    return WxCpMessageSendResult.fromJson(this.mainService.post(url, message.toJson()));
  }

  @Override
  public WxCpMessageSendStatistics getStatistics(String corpId, int timeType) throws WxErrorException {
    String url = this.mainService.getCorpApiUrl(Message.GET_STATISTICS, corpId);
    return WxCpMessageSendStatistics.fromJson(this.mainService.post(url,
      WxCpGsonBuilder.create().toJson(ImmutableMap.of("time_type", timeType))));
  }

  @Override
  public WxCpLinkedCorpMessageSendResult sendLinkedCorpMessage(String corpId, WxCpLinkedCorpMessage message) throws WxErrorException {
    Integer agentId = message.getAgentId();
    if (null == agentId) {
      message.setAgentId(this.mainService.getWxCpTpConfigStorage().getAgentId(corpId));
    }
    String url = this.mainService.getCorpApiUrl(Message.LINKEDCORP_MESSAGE_SEND, corpId);
    return WxCpLinkedCorpMessageSendResult.fromJson(this.mainService.post(url, message.toJson()));
  }
}
