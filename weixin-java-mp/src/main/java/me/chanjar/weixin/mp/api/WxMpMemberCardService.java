package me.chanjar.weixin.mp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.card.MemberCardActivateUserFormRequest;
import me.chanjar.weixin.mp.bean.card.MemberCardActivateUserFormResult;
import me.chanjar.weixin.mp.bean.card.WxMpCardCreateResult;
import me.chanjar.weixin.mp.bean.membercard.*;

/**
 * 会员卡相关接口
 *
 * @author YuJian(mgcnrx11 @ gmail.com)
 * @author yuanqixun
 * @version 2017/7/8
 * @date 2018-08-30
 */
public interface WxMpMemberCardService {
  String MEMBER_CARD_CREAET = "https://api.weixin.qq.com/card/create";
  String MEMBER_CARD_ACTIVATE = "https://api.weixin.qq.com/card/membercard/activate";
  String MEMBER_CARD_USER_INFO_GET = "https://api.weixin.qq.com/card/membercard/userinfo/get";
  String MEMBER_CARD_UPDATE_USER = "https://api.weixin.qq.com/card/membercard/updateuser";
  /**
   * 会员卡激活之微信开卡接口(wx_activate=true情况调用)
   */
  String MEMBER_CARD_ACTIVATEUSERFORM = "https://api.weixin.qq.com/card/membercard/activateuserform/set";

  /**
   * 得到WxMpService
   */
  WxMpService getWxMpService();

  /**
   * 会员卡创建接口
   *
   * @param createJson
   * @return
   * @throws WxErrorException
   */
  WxMpCardCreateResult createMemberCard(String createJson) throws WxErrorException;

  /**
   * 会员卡创建接口
   *
   * @param createMessageMessage
   * @return WxMpCardCreateResult
   * @throws WxErrorException
   */
  WxMpCardCreateResult createMemberCard(WxMpMemberCardCreateMessage createMessageMessage) throws WxErrorException;

  /**
   * 会员卡激活接口
   *
   * @param activatedMessage 激活所需参数
   * @return 返回json字符串
   * @throws WxErrorException 接口调用失败抛出的异常
   */
  String activateMemberCard(WxMpMemberCardActivatedMessage activatedMessage) throws WxErrorException;

  /**
   * 拉取会员信息接口
   *
   * @param cardId 会员卡的CardId，微信分配
   * @param code   领取会员的会员卡Code
   * @return 会员信息的结果对象
   * @throws WxErrorException 接口调用失败抛出的异常
   */
  WxMpMemberCardUserInfoResult getUserInfo(String cardId, String code) throws WxErrorException;

  /**
   * 当会员持卡消费后，支持开发者调用该接口更新会员信息。会员卡交易后的每次信息变更需通过该接口通知微信，便于后续消息通知及其他扩展功能。
   * <p>
   * 1.开发者可以同时传入add_bonus和bonus解决由于同步失败带来的幂等性问题。同时传入add_bonus和bonus时
   * add_bonus作为积分变动消息中的变量值，而bonus作为卡面上的总积分额度显示。余额变动同理。
   * 2.开发者可以传入is_notify_bonus控制特殊的积分对账变动不发送消息，余额变动同理。
   *
   * @param updateUserMessage 更新会员信息所需字段消息
   * @return 调用返回的JSON字符串。
   * @throws WxErrorException 接口调用失败抛出的异常
   */
  WxMpMemberCardUpdateResult updateUserMemberCard(WxMpMemberCardUpdateMessage updateUserMessage) throws WxErrorException;

  /**
   * 设置会员卡激活的字段（会员卡设置：wx_activate=true 时需要）
   *
   * @param userFormRequest
   * @return
   * @throws WxErrorException
   */
  MemberCardActivateUserFormResult setActivateUserForm(MemberCardActivateUserFormRequest userFormRequest) throws WxErrorException;

}
