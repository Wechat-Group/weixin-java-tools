package me.chanjar.weixin.cp.api.impl;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpAgentService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpAgent;


/**
 * <pre>
 *  管理企业号应用
 *  Created by huansinho on 2018/4/13.
 * </pre>
 *
 * @author <a href="https://github.com/huansinho">huansinho</a>
 */
public class WxCpAgentServiceImpl implements WxCpAgentService {
  private WxCpService mainService;

  public WxCpAgentServiceImpl(WxCpService mainService) {
    this.mainService = mainService;
  }

  @Override
  public WxCpAgent get(Integer agentId) throws WxErrorException {
    if (agentId == null) {
      throw new IllegalArgumentException("缺少agentid参数");
    }

    String url = "https://qyapi.weixin.qq.com/cgi-bin/agent/get?agentid=" + agentId;
    String responseContent = this.mainService.get(url, null);
    return WxCpAgent.fromJson(responseContent);
  }

}
