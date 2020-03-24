package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpUserExternalContactInfo;

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
  List<String> listFollowUser() throws WxErrorException;

}
