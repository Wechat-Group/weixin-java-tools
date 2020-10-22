package me.chanjar.weixin.cp.api;

import lombok.NonNull;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.external.*;

import java.util.Date;
import java.util.List;

/**
 * <pre>
 * 外部联系人管理接口，企业微信的外部联系人的接口和通讯录接口已经拆离
 *  Created by Joe Cao on 2019/6/14
 * </pre>
 *
 * @author <a href="https://github.com/JoeCao">JoeCao</a>
 */
public interface WxCpExternalContactService {

  /**
   * 配置客户联系「联系我」方式
   * <pre>
   * 企业可以在管理后台-客户联系中配置成员的「联系我」的二维码或者小程序按钮，客户通过扫描二维码或点击小程序上的按钮，即可获取成员联系方式，主动联系到成员。
   * 企业可通过此接口为具有客户联系功能的成员生成专属的「联系我」二维码或者「联系我」按钮。
   * 如果配置的是「联系我」按钮，需要开发者的小程序接入小程序插件。
   *
   * 注意:
   * 通过API添加的「联系我」不会在管理端进行展示，每个企业可通过API最多配置50万个「联系我」。
   * 用户需要妥善存储返回的config_id，config_id丢失可能导致用户无法编辑或删除「联系我」。
   * 临时会话模式不占用「联系我」数量，但每日最多添加10万个，并且仅支持单人。
   * 临时会话模式的二维码，添加好友完成后该二维码即刻失效。
   * </pre>
   *
   * @param info 客户联系「联系我」方式
   * @return wx cp contact way result
   * @throws WxErrorException the wx error exception
   */
  WxCpContactWayResult addContactWay(@NonNull WxCpContactWayInfo info) throws WxErrorException;

  /**
   * 获取企业已配置的「联系我」方式
   *
   * <pre>
   * <b>批量</b>获取企业配置的「联系我」二维码和「联系我」小程序按钮。
   * </pre>
   *
   * @param configId 联系方式的配置id,必填
   * @return contact way
   * @throws WxErrorException the wx error exception
   */
  WxCpContactWayInfo getContactWay(@NonNull String configId) throws WxErrorException;

  /**
   * 更新企业已配置的「联系我」方式
   *
   * <pre>
   * 更新企业配置的「联系我」二维码和「联系我」小程序按钮中的信息，如使用人员和备注等。
   * </pre>
   *
   * @param info 客户联系「联系我」方式
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp updateContactWay(@NonNull WxCpContactWayInfo info) throws WxErrorException;

  /**
   * 删除企业已配置的「联系我」方式
   *
   * <pre>
   * 删除一个已配置的「联系我」二维码或者「联系我」小程序按钮。
   * </pre>
   *
   * @param configId 企业联系方式的配置id,必填
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp deleteContactWay(@NonNull String configId) throws WxErrorException;

  /**
   * 结束临时会话
   *
   * <pre>
   * 将指定的企业成员和客户之前的临时会话断开，断开前会自动下发已配置的结束语。
   *
   * 注意：请保证传入的企业成员和客户之间有仍然有效的临时会话, 通过<b>其他方式的添加外部联系人无法通过此接口关闭会话</b>。
   * </pre>
   *
   * @param userId         the user id
   * @param externalUserId the external user id
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp closeTempChat(@NonNull String userId, @NonNull String externalUserId) throws WxErrorException;


  /**
   * 获取外部联系人详情.
   * <pre>
   *   企业可通过此接口，根据外部联系人的userid，拉取外部联系人详情。权限说明：
   * 企业需要使用外部联系人管理secret所获取的accesstoken来调用
   * 第三方应用需拥有“企业客户”权限。
   * 第三方应用调用时，返回的跟进人follow_user仅包含应用可见范围之内的成员。
   * </pre>
   *
   * @param userId 外部联系人的userid
   * @return . external contact
   * @throws WxErrorException the wx error exception
   * @deprecated 建议使用 {@link #getContactDetail(String)}
   */
  @Deprecated
  WxCpUserExternalContactInfo getExternalContact(String userId) throws WxErrorException;

  /**
   * 获取客户详情.
   * <pre>
   *
   * 企业可通过此接口，根据外部联系人的userid（如何获取?），拉取客户详情。
   *
   * 请求方式：GET（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/externalcontact/get?access_token=ACCESS_TOKEN&external_userid=EXTERNAL_USERID
   *
   * 权限说明：
   *
   * 企业需要使用“客户联系”secret或配置到“可调用应用”列表中的自建应用secret所获取的accesstoken来调用（accesstoken如何获取？）；
   * 第三方/自建应用调用时，返回的跟进人follow_user仅包含应用可见范围之内的成员。
   * </pre>
   *
   * @param userId 外部联系人的userid，注意不是企业成员的帐号
   * @return . contact detail
   * @throws WxErrorException .
   */
  WxCpUserExternalContactInfo getContactDetail(String userId) throws WxErrorException;

  /**
   * 修改客户备注信息.
   * <pre>
   * 企业可通过此接口修改指定用户添加的客户的备注信息。
   * 请求方式: POST(HTTP)
   * 请求地址:https://qyapi.weixin.qq.com/cgi-bin/externalcontact/remark?access_token=ACCESS_TOKEN
   * 文档地址：https://work.weixin.qq.com/api/doc/90000/90135/92115
   * </pre>
   *
   * @param request 备注信息请求
   * @throws WxErrorException .
   */
  void updateRemark(WxCpUpdateRemarkRequest request) throws WxErrorException;

  /**
   * 获取客户列表.
   * <pre>
   *   企业可通过此接口获取指定成员添加的客户列表。客户是指配置了客户联系功能的成员所添加的外部联系人。没有配置客户联系功能的成员，所添加的外部联系人将不会作为客户返回。
   *
   * 请求方式：GET（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/externalcontact/list?access_token=ACCESS_TOKEN&userid=USERID
   *
   * 权限说明：
   *
   * 企业需要使用“客户联系”secret或配置到“可调用应用”列表中的自建应用secret所获取的accesstoken来调用（accesstoken如何获取？）；
   * 第三方应用需拥有“企业客户”权限。
   * 第三方/自建应用只能获取到可见范围内的配置了客户联系功能的成员。
   * </pre>
   *
   * @param userId 企业成员的userid
   * @return List of External wx id
   * @throws WxErrorException .
   */
  List<String> listExternalContacts(String userId) throws WxErrorException;

  /**
   * 企业和第三方服务商可通过此接口获取配置了客户联系功能的成员(Customer Contact)列表。
   * <pre>
   *   企业需要使用外部联系人管理secret所获取的accesstoken来调用（accesstoken如何获取？）；
   *   第三方应用需拥有“企业客户”权限。
   *   第三方应用只能获取到可见范围内的配置了客户联系功能的成员
   * </pre>
   *
   * @return List of CpUser id
   * @throws WxErrorException .
   */
  List<String> listFollowers() throws WxErrorException;

  /**
   * 企业和第三方可通过此接口，获取所有离职成员的客户列表，并可进一步调用离职成员的外部联系人再分配接口将这些客户重新分配给其他企业成员。
   *
   * @param page     the page
   * @param pageSize the page size
   * @return wx cp user external unassign list
   * @throws WxErrorException the wx error exception
   */
  WxCpUserExternalUnassignList listUnassignedList(Integer page, Integer pageSize) throws WxErrorException;

  /**
   * 企业可通过此接口，将已离职成员的外部联系人分配给另一个成员接替联系。
   *
   * @param externalUserid the external userid
   * @param handOverUserid the hand over userid
   * @param takeOverUserid the take over userid
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp transferExternalContact(String externalUserid, String handOverUserid, String takeOverUserid) throws WxErrorException;

  /**
   * <pre>
   * 该接口用于获取配置过客户群管理的客户群列表。
   * 企业需要使用“客户联系”secret或配置到“可调用应用”列表中的自建应用secret所获取的accesstoken来调用（accesstoken如何获取？）。
   * 暂不支持第三方调用。
   * 微信文档：https://work.weixin.qq.com/api/doc/90000/90135/92119
   * </pre>
   *
   * @param pageIndex the page index
   * @param pageSize  the page size
   * @param status    the status
   * @param userIds   the user ids
   * @param partyIds  the party ids
   * @return the wx cp user external group chat list
   * @throws WxErrorException the wx error exception
   */
  WxCpUserExternalGroupChatList listGroupChat(Integer pageIndex, Integer pageSize, int status, String[] userIds, String[] partyIds) throws WxErrorException;

  /**
   * <pre>
   * 通过客户群ID，获取详情。包括群名、群成员列表、群成员入群时间、入群方式。（客户群是由具有客户群使用权限的成员创建的外部群）
   * 企业需要使用“客户联系”secret或配置到“可调用应用”列表中的自建应用secret所获取的accesstoken来调用（accesstoken如何获取？）。
   * 暂不支持第三方调用。
   * 微信文档：https://work.weixin.qq.com/api/doc/90000/90135/92122
   * </pre>
   *
   * @param chatId the chat id
   * @return group chat
   * @throws WxErrorException the wx error exception
   */
  WxCpUserExternalGroupChatInfo getGroupChat(String chatId) throws WxErrorException;

  /**
   * <pre>
   * 企业可通过此接口获取成员联系客户的数据，包括发起申请数、新增客户数、聊天数、发送消息数和删除/拉黑成员的客户数等指标。
   * 企业需要使用“客户联系”secret或配置到“可调用应用”列表中的自建应用secret所获取的accesstoken来调用（accesstoken如何获取？）。
   * 第三方应用需拥有“企业客户”权限。
   * 第三方/自建应用调用时传入的userid和partyid要在应用的可见范围内;
   * </pre>
   *
   * @param startTime the start time
   * @param endTime   the end time
   * @param userIds   the user ids
   * @param partyIds  the party ids
   * @return user behavior statistic
   * @throws WxErrorException the wx error exception
   */
  WxCpUserExternalUserBehaviorStatistic getUserBehaviorStatistic(Date startTime, Date endTime, String[] userIds, String[] partyIds) throws WxErrorException;

  /**
   * <pre>
   * 获取指定日期全天的统计数据。注意，企业微信仅存储60天的数据。
   * 企业需要使用“客户联系”secret或配置到“可调用应用”列表中的自建应用secret所获取的accesstoken来调用（accesstoken如何获取？）。
   * 暂不支持第三方调用。
   * </pre>
   *
   * @param startTime the start time
   * @param orderBy   the order by
   * @param orderAsc  the order asc
   * @param pageIndex the page index
   * @param pageSize  the page size
   * @param userIds   the user ids
   * @param partyIds  the party ids
   * @return group chat statistic
   * @throws WxErrorException the wx error exception
   */
  WxCpUserExternalGroupChatStatistic getGroupChatStatistic(Date startTime, Integer orderBy, Integer orderAsc, Integer pageIndex, Integer pageSize, String[] userIds, String[] partyIds) throws WxErrorException;

  /**
   * 添加企业群发消息任务
   * 企业可通过此接口添加企业群发消息的任务并通知客服人员发送给相关客户或客户群。（注：企业微信终端需升级到2.7.5版本及以上）
   * 注意：调用该接口并不会直接发送消息给客户/客户群，需要相关的客服人员操作以后才会实际发送（客服人员的企业微信需要升级到2.7.5及以上版本）
   * 同一个企业每个自然月内仅可针对一个客户/客户群发送4条消息，超过限制的用户将会被忽略。
   * <p>
   * 请求方式: POST(HTTP)
   * <p>
   * 请求地址:https://qyapi.weixin.qq.com/cgi-bin/externalcontact/add_msg_template?access_token=ACCESS_TOKEN
   * <p>
   * 文档地址：https://work.weixin.qq.com/api/doc/90000/90135/92135
   *
   * @param wxCpMsgTemplate the wx cp msg template
   * @return the wx cp msg template add result
   * @throws WxErrorException the wx error exception
   */
  WxCpMsgTemplateAddResult addMsgTemplate(WxCpMsgTemplate wxCpMsgTemplate) throws WxErrorException;

  /**
   * 发送新客户欢迎语
   * <pre>
   * 企业微信在向企业推送添加外部联系人事件时，会额外返回一个welcome_code，企业以此为凭据调用接口，即可通过成员向新添加的客户发送个性化的欢迎语。
   * 为了保证用户体验以及避免滥用，企业仅可在收到相关事件后20秒内调用，且只可调用一次。
   * 如果企业已经在管理端为相关成员配置了可用的欢迎语，则推送添加外部联系人事件时不会返回welcome_code。
   * 每次添加新客户时可能有多个企业自建应用/第三方应用收到带有welcome_code的回调事件，但仅有最先调用的可以发送成功。后续调用将返回41051（externaluser has started chatting）错误，请用户根据实际使用需求，合理设置应用可见范围，避免冲突。
   * 请求方式: POST(HTTP)
   *
   * 请求地址:https://qyapi.weixin.qq.com/cgi-bin/externalcontact/send_welcome_msg?access_token=ACCESS_TOKEN
   *
   * 文档地址：https://work.weixin.qq.com/api/doc/90000/90135/92137
   * </pre>
   *
   * @param msg .
   * @throws WxErrorException .
   */
  void sendWelcomeMsg(WxCpWelcomeMsg msg) throws WxErrorException;

  /**
   * <pre>
   * 企业可通过此接口获取企业客户标签详情。
   * </pre>
   *
   * @param tagId the tag id
   * @return corp tag list
   * @throws WxErrorException the wx error exception
   */
  WxCpUserExternalTagGroupList getCorpTagList(String[] tagId) throws WxErrorException;

  /**
   * <pre>
   * 企业可通过此接口向客户标签库中添加新的标签组和标签，每个企业最多可配置3000个企业标签。
   * 暂不支持第三方调用。
   * </pre>
   *
   * @param tagGroup the tag group
   * @return wx cp user external tag group info
   * @throws WxErrorException the wx error exception
   */
  WxCpUserExternalTagGroupInfo addCorpTag(WxCpUserExternalTagGroupInfo tagGroup) throws WxErrorException;

  /**
   * <pre>
   * 企业可通过此接口编辑客户标签/标签组的名称或次序值。
   * 暂不支持第三方调用。
   * </pre>
   *
   * @param id    the id
   * @param name  the name
   * @param order the order
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp editCorpTag(String id, String name, Integer order) throws WxErrorException;

  /**
   * <pre>
   * 企业可通过此接口删除客户标签库中的标签，或删除整个标签组。
   * 暂不支持第三方调用。
   * </pre>
   *
   * @param tagId   the tag id
   * @param groupId the group id
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp delCorpTag(String[] tagId, String[] groupId) throws WxErrorException;

  /**
   * <pre>
   * 企业可通过此接口为指定成员的客户添加上由企业统一配置的标签。
   * https://work.weixin.qq.com/api/doc/90000/90135/92117
   * </pre>
   *
   * @param userid         the userid
   * @param externalUserid the external userid
   * @param addTag         the add tag
   * @param removeTag      the remove tag
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp markTag(String userid, String externalUserid, String[] addTag, String[] removeTag) throws WxErrorException;
}
