package me.chanjar.weixin.cp.api.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpOaMailService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.oa.mail.WxCpMailCommonSendRequest;
import me.chanjar.weixin.cp.bean.oa.mail.WxCpMailMeetingSendRequest;
import me.chanjar.weixin.cp.bean.oa.mail.WxCpMailScheduleSendRequest;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Oa.EXMAIL_APP_COMPOSE_SEND;

/**
 * 企业微信邮件接口实现类.
 *
 * @author Hugo
 */
@Slf4j
@RequiredArgsConstructor
public class WxCpOMailServiceImpl implements WxCpOaMailService {
  private final WxCpService cpService;

  /**
   * 发送普通邮件
   * 应用可以通过该接口发送普通邮件，支持附件能力。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: <a href="https://qyapi.weixin.qq.com/cgi-bin/exmail/app/compose_send?access_token=ACCESS_TOKEN">...</a>
   *
   * @param request 发送普通邮件请求参数
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  @Override
  public WxCpBaseResp mailCommonSend(@NonNull WxCpMailCommonSendRequest request) throws WxErrorException {
    return this.mailSend(request.toJson());
  }

  /**
   * 发送日程邮件
   * 应用可以通过该接口发送日程邮件。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: <a href="https://qyapi.weixin.qq.com/cgi-bin/exmail/app/compose_send?access_token=ACCESS_TOKEN">...</a>
   *
   * @param request 发送日程邮件请求参数
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  @Override
  public WxCpBaseResp mailScheduleSend(@NonNull WxCpMailScheduleSendRequest request) throws WxErrorException {
    return this.mailSend(request.toJson());
  }

  /**
   * 发送会议邮件
   * 应用可以通过该接口发送会议邮件。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址: <a href="https://qyapi.weixin.qq.com/cgi-bin/exmail/app/compose_send?access_token=ACCESS_TOKEN">...</a>
   *
   * @param request 发送会议邮件请求参数
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  @Override
  public WxCpBaseResp mailMeetingSend(@NonNull WxCpMailMeetingSendRequest request) throws WxErrorException {

    return this.mailSend(request.toJson());
  }

  private WxCpBaseResp mailSend(String request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(EXMAIL_APP_COMPOSE_SEND);
    String responseContent = this.cpService.post(apiUrl, request);
    return WxCpBaseResp.fromJson(responseContent);
  }
}
