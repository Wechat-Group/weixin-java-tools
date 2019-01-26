package me.chanjar.weixin.cp.api.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.JsonParser;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.cp.api.WxCpChatService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpAppChatMessage;
import me.chanjar.weixin.cp.bean.WxCpChat;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * 群聊服务实现.
 *
 * @author gaigeshen
 */
public class WxCpChatServiceImpl implements WxCpChatService {
  private static final JsonParser JSON_PARSER = new JsonParser();
  private final WxCpService cpService;

  /**
   * 创建群聊服务实现的实例.
   *
   * @param cpService 企业微信的服务
   */
  WxCpChatServiceImpl(WxCpService cpService) {
    this.cpService = cpService;
  }

  @Override
  public String chatCreate(String name, String owner, List<String> users, String chatId) throws WxErrorException {
    Map<String, Object> data = new HashMap<>(4);
    if (StringUtils.isNotBlank(name)) {
      data.put("name", name);
    }
    if (StringUtils.isNotBlank(owner)) {
      data.put("owner", owner);
    }
    if (users != null) {
      data.put("userlist", users);
    }
    if (StringUtils.isNotBlank(chatId)) {
      data.put("chatid", chatId);
    }
    String result = this.cpService.post(APPCHAT_CREATE, WxGsonBuilder.create().toJson(data));
    return new JsonParser().parse(result).getAsJsonObject().get("chatid").getAsString();
  }

  @Override
  public void chatUpdate(String chatId, String name, String owner, List<String> usersToAdd, List<String> usersToDelete)
    throws WxErrorException {
    Map<String, Object> data = new HashMap<>(5);
    if (StringUtils.isNotBlank(chatId)) {
      data.put("chatid", chatId);
    }
    if (StringUtils.isNotBlank(name)) {
      data.put("name", name);
    }
    if (StringUtils.isNotBlank(owner)) {
      data.put("owner", owner);
    }
    if (usersToAdd != null && !usersToAdd.isEmpty()) {
      data.put("add_user_list", usersToAdd);
    }
    if (usersToDelete != null && !usersToDelete.isEmpty()) {
      data.put("del_user_list", usersToDelete);
    }

    this.cpService.post(APPCHAT_UPDATE, WxGsonBuilder.create().toJson(data));
  }

  @Override
  public WxCpChat chatGet(String chatId) throws WxErrorException {
    String result = this.cpService.get(APPCHAT_GET_CHATID + chatId, null);
    return WxCpGsonBuilder.create()
      .fromJson(JSON_PARSER.parse(result).getAsJsonObject().getAsJsonObject("chat_info").toString(), WxCpChat.class);
  }

  @Override
  public void sendMsg(WxCpAppChatMessage message) throws WxErrorException {
    this.cpService.post("https://qyapi.weixin.qq.com/cgi-bin/appchat/send", message.toJson());
  }

}
