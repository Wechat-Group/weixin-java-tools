package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpAppChatMessage;
import me.chanjar.weixin.cp.bean.WxCpChat;

import java.util.List;

/**
 * 群聊服务.
 *
 * @author gaigeshen
 */
public interface WxCpChatService {
  String APPCHAT_CREATE = "/cgi-bin/appchat/create";
  String APPCHAT_UPDATE = "/cgi-bin/appchat/update";
  String APPCHAT_GET_CHATID = "/cgi-bin/appchat/get?chatid=";

  /**
   * 创建群聊会话，注意：刚创建的群，如果没有下发消息，在企业微信不会出现该群.
   *
   * @param name   群聊名，最多50个utf8字符，超过将截断
   * @param owner  指定群主的id。如果不指定，系统会随机从userlist中选一人作为群主
   * @param users  群成员id列表。至少2人，至多500人
   * @param chatId 群聊的唯一标志，不能与已有的群重复；字符串类型，最长32个字符。只允许字符0-9及字母a-zA-Z。如果不填，系统会随机生成群id
   * @return 创建的群聊会话chatId
   * @throws WxErrorException 发生异常
   */
  String chatCreate(String name, String owner, List<String> users, String chatId) throws WxErrorException;

  /**
   * chatCreate 同名方法
   */
  String create(String name, String owner, List<String> users, String chatId) throws WxErrorException;

  /**
   * 修改群聊会话.
   *
   * @param chatId        群聊id
   * @param name          新的群聊名。若不需更新，请忽略此参数（null or empty）。最多50个utf8字符，超过将截断
   * @param owner         新群主的id。若不需更新，请忽略此参数（null or empty）
   * @param usersToAdd    添加成员的id列表，若不需要更新，则传递空对象或者空集合
   * @param usersToDelete 踢出成员的id列表，若不需要更新，则传递空对象或者空集合
   * @throws WxErrorException 发生异常
   */
  void chatUpdate(String chatId, String name, String owner, List<String> usersToAdd, List<String> usersToDelete) throws WxErrorException;

  /**
   * chatUpdate 同名方法
   */
  void update(String chatId, String name, String owner, List<String> usersToAdd, List<String> usersToDelete) throws WxErrorException;

  /**
   * 获取群聊会话.
   *
   * @param chatId 群聊编号
   * @return 群聊会话
   * @throws WxErrorException 发生异常
   */
  WxCpChat chatGet(String chatId) throws WxErrorException;

  /**
   * chatGet 同名方法
   */
  WxCpChat get(String chatId) throws WxErrorException;

  /**
   * 应用支持推送文本、图片、视频、文件、图文等类型.
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/appchat/send?access_token=ACCESS_TOKEN
   * 文档地址：https://work.weixin.qq.com/api/doc#90000/90135/90248
   *
   * @param message 要发送的消息内容对象
   */
  void sendMsg(WxCpAppChatMessage message) throws WxErrorException;

}
