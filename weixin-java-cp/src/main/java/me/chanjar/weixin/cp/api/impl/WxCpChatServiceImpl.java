package me.chanjar.weixin.cp.api.impl;

import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.cp.api.WxCpChatService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpAppChatMessage;
import me.chanjar.weixin.cp.bean.WxCpChat;
import me.chanjar.weixin.cp.constant.WxCpApiPathConsts;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Chat.*;

/**
 * 群聊服务实现.
 *
 * @author gaigeshen
 */
@RequiredArgsConstructor
public class WxCpChatServiceImpl implements WxCpChatService {

  private final WxCpService cpService;

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
    final String url = this.cpService.getWxCpConfigStorage().getApiUrl(APPCHAT_CREATE);
    String result = this.cpService.post(url, WxGsonBuilder.create().toJson(data));
    return new JsonParser().parse(result).getAsJsonObject().get("chatid").getAsString();
  }

  @Override
  public String create(String name, String owner, List<String> users, String chatId) throws WxErrorException {
    return this.chatCreate(name, owner, users, chatId);
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

    final String url = this.cpService.getWxCpConfigStorage().getApiUrl(APPCHAT_UPDATE);
    this.cpService.post(url, WxGsonBuilder.create().toJson(data));
  }

  @Override
  public void update(String chatId, String name, String owner, List<String> usersToAdd, List<String> usersToDelete)
    throws WxErrorException {
    chatUpdate(chatId, name, owner, usersToAdd, usersToDelete);
  }

  @Override
  public WxCpChat chatGet(String chatId) throws WxErrorException {
    final String url = this.cpService.getWxCpConfigStorage().getApiUrl(APPCHAT_GET_CHATID + chatId);
    String result = this.cpService.get(url, null);
    final String chatInfo = GsonParser.parse(result).getAsJsonObject("chat_info").toString();
    return WxCpGsonBuilder.create().fromJson(chatInfo, WxCpChat.class);
  }

  @Override
  public WxCpChat get(String chatId) throws WxErrorException {
    return this.chatGet(chatId);
  }

  @Override
  public void sendMsg(WxCpAppChatMessage message) throws WxErrorException {
    this.cpService.post(this.cpService.getWxCpConfigStorage().getApiUrl(APPCHAT_SEND), message.toJson());
  }

}
