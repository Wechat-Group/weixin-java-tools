package me.chanjar.weixin.cp.api.impl;

import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpAgentService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpAgent;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;


/**
 * <pre>
 *  管理企业号应用
 *  Created by huansinho on 2018/4/13.
 * </pre>
 *
 * @author <a href="https://github.com/huansinho">huansinho</a>
 */
public class WxCpAgentServiceImpl implements WxCpAgentService {
  private static final JsonParser JSON_PARSER = new JsonParser();

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

  @Override
  public void set(WxCpAgent agentInfo) throws WxErrorException {
    String url = "https://qyapi.weixin.qq.com/cgi-bin/agent/set";
    String responseContent = this.mainService.post(url, agentInfo.toJson());
    JsonObject jsonObject = JSON_PARSER.parse(responseContent).getAsJsonObject();
    if (jsonObject.get("errcode").getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent));
    }
  }

  @Override
  public List<WxCpAgent> list() throws WxErrorException {
    String url = "https://qyapi.weixin.qq.com/cgi-bin/agent/list";
    String responseContent = this.mainService.get(url, null);
    JsonObject jsonObject = JSON_PARSER.parse(responseContent).getAsJsonObject();
    if (jsonObject.get("errcode").getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent));
    }

    return WxCpGsonBuilder.create().fromJson(jsonObject.get("agentlist").toString(), new TypeToken<List<WxCpAgent>>() {
    }.getType());
  }

}
