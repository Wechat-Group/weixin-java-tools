package me.chanjar.weixin.cp.api;

import lombok.NonNull;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.*;

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
   * @return
   * @throws WxErrorException
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
   * @return
   * @throws WxErrorException
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
   * @return
   * @throws WxErrorException
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
   * @return
   * @throws WxErrorException
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
   * @param userId
   * @param externalUserId
   * @return
   * @throws WxErrorException
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
   * @return .
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
   * @return .
   * @throws WxErrorException .
   */
  WxCpUserExternalContactInfo getContactDetail(String userId) throws WxErrorException;

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
   * @param page
   * @param pageSize
   * @return
   * @throws WxErrorException
   */
  WxCpUserExternalUnassignList listUnassignedList(Integer page, Integer pageSize) throws WxErrorException;

  /**
   * 企业可通过此接口，将已离职成员的外部联系人分配给另一个成员接替联系。
   *
   * @param externalUserid
   * @param handOverUserid
   * @param takeOverUserid
   * @return
   * @throws WxErrorException
   */
  WxCpBaseResp transferExternalContact(String externalUserid, String handOverUserid, String takeOverUserid) throws WxErrorException;

  /**
   * <pre>
   * 该接口用于获取配置过客户群管理的客户群列表。
   * 企业需要使用“客户联系”secret或配置到“可调用应用”列表中的自建应用secret所获取的accesstoken来调用（accesstoken如何获取？）。
   * 暂不支持第三方调用。
   * 微信文档：https://work.weixin.qq.com/api/doc/90000/90135/92119
   * </pre>
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
   * @param chatId
   * @return
   * @throws WxErrorException
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
   * @param startTime
   * @param endTime
   * @param userIds
   * @param partyIds
   * @return
   * @throws WxErrorException
   */
  WxCpUserExternalUserBehaviorStatistic getUserBehaviorStatistic(Date startTime, Date endTime, String[] userIds, String[] partyIds) throws WxErrorException;

  /**
   * <pre>
   * 获取指定日期全天的统计数据。注意，企业微信仅存储60天的数据。
   * 企业需要使用“客户联系”secret或配置到“可调用应用”列表中的自建应用secret所获取的accesstoken来调用（accesstoken如何获取？）。
   * 暂不支持第三方调用。
   * </pre>
   *
   * @param startTime
   * @param orderBy
   * @param orderAsc
   * @param pageIndex
   * @param pageSize
   * @param userIds
   * @param partyIds
   * @return
   * @throws WxErrorException
   */
  WxCpUserExternalGroupChatStatistic getGroupChatStatistic(Date startTime, Integer orderBy, Integer orderAsc, Integer pageIndex, Integer pageSize, String[] userIds, String[] partyIds) throws WxErrorException;

  WxCpMsgTemplateAddResult addMsgTemplate(WxCpMsgTemplate wxCpMsgTemplate) throws WxErrorException;


  /**
   * <pre>
   * 企业可通过此接口获取企业客户标签详情。
   * </pre>
   * @param tagId
   * @return
   */
  WxCpUserExternalTagGroup  getCorpTagList(String [] tagId) throws WxErrorException;


  /**
   * <pre>
   * 企业可通过此接口向客户标签库中添加新的标签组和标签，每个企业最多可配置3000个企业标签。
   * 暂不支持第三方调用。
   * </pre>
   * @param tagGroup
   * @return
   */
  WxCpUserExternalTagGroup  addCorpTag(WxCpUserExternalTagGroup tagGroup)throws WxErrorException;

  /**
   * <pre>
   * 企业可通过此接口编辑客户标签/标签组的名称或次序值。
   * 暂不支持第三方调用。
   * </pre>
   * @param id
   * @param name
   * @param order
   * @return
   */
  WxCpBaseResp  editCorpTag(String id,String name,Integer order)throws WxErrorException;

  /**
   * <pre>
   * 企业可通过此接口删除客户标签库中的标签，或删除整个标签组。
   * 暂不支持第三方调用。
   * </pre>
   * @param tagId
   * @param groupId
   * @return
   */
  WxCpBaseResp  delCorpTag(String [] tagId,String[] groupId)throws WxErrorException;

  /**
   * <pre>
   * 企业可通过此接口为指定成员的客户添加上由企业统一配置的标签。
   * https://work.weixin.qq.com/api/doc/90000/90135/92117
   * </pre>
   * @param userid
   * @param externalUserid
   * @param addTag
   * @param removeTag
   * @return
   */
  WxCpBaseResp  markTag(String userid,String externalUserid,String[] addTag,String [] removeTag)throws WxErrorException;
}
