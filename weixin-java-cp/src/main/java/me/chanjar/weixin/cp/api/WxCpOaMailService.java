package me.chanjar.weixin.cp.api;

import lombok.NonNull;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.oa.mail.WxCpMailCommonSendRequest;
import me.chanjar.weixin.cp.bean.oa.mail.WxCpMailMeetingSendRequest;
import me.chanjar.weixin.cp.bean.oa.mail.WxCpMailScheduleSendRequest;

/**
 * 企业微信y邮件相关接口.
 * <a href="https://developer.work.weixin.qq.com/document/path/95486">邮件</a>
 *
 * @author Hugo
 */
public interface WxCpOaMailService {

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
  WxCpBaseResp mailCommonSend(@NonNull WxCpMailCommonSendRequest request) throws WxErrorException;

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
  WxCpBaseResp mailScheduleSend(@NonNull WxCpMailScheduleSendRequest request) throws WxErrorException;

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
  WxCpBaseResp mailMeetingSend(@NonNull WxCpMailMeetingSendRequest request) throws WxErrorException;

}
