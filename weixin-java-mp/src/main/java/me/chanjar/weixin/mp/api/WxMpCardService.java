package me.chanjar.weixin.mp.api;

import me.chanjar.weixin.common.bean.WxCardApiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.card.*;

import java.util.List;

/**
 * 卡券相关接口.
 *
 * @author YuJian(mgcnrx11 @ hotmail.com) on 01/11/2016
 * @author yuanqixun 2018-08-29
 */
public interface WxMpCardService {
  /**
   * 得到WxMpService.
   *
   * @return WxMpService
   */
  WxMpService getWxMpService();

  /**
   * 获得卡券api_ticket，不强制刷新卡券api_ticket.
   *
   * @return 卡券api_ticket
   * @throws WxErrorException 异常
   * @see #getCardApiTicket(boolean)
   */
  String getCardApiTicket() throws WxErrorException;

  /**
   * <pre>
   * 获得卡券api_ticket.
   * 获得时会检查卡券apiToken是否过期，如果过期了，那么就刷新一下，否则就什么都不干
   *
   * 详情请见：http://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html#.E9.99.84.E5.BD.954-.E5.8D.A1.E5.88.B8.E6.89.A9.E5.B1.95.E5.AD.97.E6.AE.B5.E5.8F.8A.E7.AD.BE.E5.90.8D.E7.94.9F.E6.88.90.E7.AE.97.E6.B3.95
   * </pre>
   *
   * @param forceRefresh 强制刷新
   * @return 卡券api_ticket
   * @throws WxErrorException 异常
   */
  String getCardApiTicket(boolean forceRefresh) throws WxErrorException;

  /**
   * <pre>
   * 创建调用卡券api时所需要的签名.
   *
   * 详情请见：http://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html#.E9.99.84.E5.BD
   * .954-.E5.8D.A1.E5.88.B8.E6.89.A9.E5.B1.95.E5.AD.97.E6.AE.B5.E5.8F.8A.E7.AD.BE.E5.90.8D.E7.94
   * .9F.E6.88.90.E7.AE.97.E6.B3.95
   * </pre>
   *
   * @param optionalSignParam 参与签名的参数数组。可以为下列字段：app_id, card_id, card_type, code, openid, location_id
   *                          </br>注意：当做wx.chooseCard调用时，必须传入app_id参与签名，否则会造成签名失败导致拉取卡券列表为空
   * @return 卡券Api签名对象
   * @throws WxErrorException 异常
   */
  WxCardApiSignature createCardApiSignature(String... optionalSignParam) throws WxErrorException;

  /**
   * 卡券Code解码.
   *
   * @param encryptCode 加密Code，通过JSSDK的chooseCard接口获得
   * @return 解密后的Code
   * @throws WxErrorException 异常
   */
  String decryptCardCode(String encryptCode) throws WxErrorException;

  /**
   * 卡券Code查询.
   * 文档地址： https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1451025272&anchor=1
   *
   * @param cardId       卡券ID代表一类卡券
   * @param code         单张卡券的唯一标准
   * @param checkConsume 是否校验code核销状态，填入true和false时的code异常状态返回数据不同
   * @return WxMpCardResult对象
   * @throws WxErrorException 异常
   */
  WxMpCardResult queryCardCode(String cardId, String code, boolean checkConsume) throws WxErrorException;

  /**
   * 卡券Code核销。核销失败会抛出异常
   *
   * @param code 单张卡券的唯一标准
   * @return 调用返回的JSON字符串。可用 com.google.gson.JsonParser#parse 等方法直接取JSON串中的errcode等信息。
   * @throws WxErrorException 异常
   */
  String consumeCardCode(String code) throws WxErrorException;

  /**
   * 卡券Code核销。核销失败会抛出异常.
   *
   * @param code   单张卡券的唯一标准
   * @param cardId 当自定义Code卡券时需要传入card_id
   * @return 调用返回的JSON字符串。可用 com.google.gson.JsonParser#parse 等方法直接取JSON串中的errcode等信息。
   * @throws WxErrorException 异常
   */
  String consumeCardCode(String code, String cardId) throws WxErrorException;

  /**
   * 卡券Mark接口.
   * 开发者在帮助消费者核销卡券之前，必须帮助先将此code（卡券串码）与一个openid绑定（即mark住），
   * 才能进一步调用核销接口，否则报错。
   *
   * @param code   卡券的code码
   * @param cardId 卡券的ID
   * @param openId 用券用户的openid
   * @param isMark 是否要mark（占用）这个code，填写true或者false，表示占用或解除占用
   * @throws WxErrorException 异常
   */
  void markCardCode(String code, String cardId, String openId, boolean isMark) throws WxErrorException;

  /**
   * 查看卡券详情接口.
   * 详见 https://mp.weixin.qq.com/wiki/14/8dd77aeaee85f922db5f8aa6386d385e.html#.E6.9F.A5.E7.9C.8B.E5.8D.A1.E5.88.B8.E8.AF.A6.E6.83.85
   *
   * @param cardId 卡券的ID
   * @return 返回的卡券详情JSON字符串
   * <br> [注] 由于返回的JSON格式过于复杂，难以定义其对应格式的Bean并且难以维护，因此只返回String格式的JSON串。
   * <br> 可由 com.google.gson.JsonParser#parse 等方法直接取JSON串中的某个字段。
   * @throws WxErrorException 异常
   */
  String getCardDetail(String cardId) throws WxErrorException;

  /**
   * 添加测试白名单.
   *
   * @param openid 用户的openid
   * @return string
   * @throws WxErrorException 异常
   */
  String addTestWhiteList(String openid) throws WxErrorException;

  /**
   * 创建卡券.
   *
   * @param cardCreateMessage 请求
   * @return result
   * @throws WxErrorException 异常
   */
  WxMpCardCreateResult createCard(WxMpCardCreateRequest cardCreateMessage) throws WxErrorException;

  /**
   * 创建卡券二维码.
   *
   * @param cardId   卡券编号
   * @param outerStr 二维码标识
   * @return WxMpCardQrcodeCreateResult
   * @throws WxErrorException 异常
   */
  WxMpCardQrcodeCreateResult createQrcodeCard(String cardId, String outerStr) throws WxErrorException;

  /**
   * 创建卡券二维码.
   *
   * @param cardId    卡券编号
   * @param outerStr  二维码标识
   * @param expiresIn 指定二维码的有效时间，范围是60 ~ 1800秒。不填默认为365天有效
   * @return WxMpCardQrcodeCreateResult
   * @throws WxErrorException 异常
   */
  WxMpCardQrcodeCreateResult createQrcodeCard(String cardId, String outerStr, int expiresIn) throws WxErrorException;

  /**
   * 创建卡券二维码.
   *
   * @param cardId       卡券编号
   * @param outerStr     用户首次领卡时，会通过 领取事件推送 给商户； 对于会员卡的二维码，用户每次扫码打开会员卡后点击任何url，会将该值拼入url中，方便开发者定位扫码来源
   * @param expiresIn    指定二维码的有效时间，范围是60 ~ 1800秒。不填默认为365天有效
   * @param isUniqueCode 指定下发二维码，生成的二维码随机分配一个code，领取后不可再次扫描。填写true或false。默认false，注意填写该字段时，卡券须通过审核且库存不为0。
   * @param code         卡券Code码,use_custom_code字段为true的卡券必须填写，非自定义code和导入code模式的卡券不必填写。
   * @param openid       指定领取者的openid，只有该用户能领取。bind_openid字段为true的卡券必须填写，非指定openid不必填写。
   * @return WxMpCardQrcodeCreateResult
   * @throws WxErrorException 异常
   */
  WxMpCardQrcodeCreateResult createQrcodeCard(String cardId, String outerStr, int expiresIn, String openid,
                                              String code, boolean isUniqueCode) throws WxErrorException;

  /**
   * 创建卡券货架.
   *
   * @param createRequest 货架创建参数
   * @return WxMpCardLandingPageCreateResult
   * @throws WxErrorException 异常
   */
  WxMpCardLandingPageCreateResult createLandingPage(WxMpCardLandingPageCreateRequest createRequest)
    throws WxErrorException;

  /**
   * 将用户的卡券设置为失效状态.
   * 详见:https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1451025272&anchor=9
   *
   * @param cardId 卡券编号
   * @param code   用户会员卡号
   * @param reason 设置为失效的原因
   * @return result
   * @throws WxErrorException 异常
   */
  String unavailableCardCode(String cardId, String code, String reason) throws WxErrorException;

  /**
   * 删除卡券接口.
   *
   * @param cardId 卡券id
   * @return 删除结果
   * @throws WxErrorException 异常
   */
  WxMpCardDeleteResult deleteCard(String cardId) throws WxErrorException;


  /**
   * 导入自定义code(仅对自定义code商户)
   *
   * @param cardId   卡券id
   * @param codeList 需导入微信卡券后台的自定义code，上限为100个。
   */
  WxMpCardCodeDepositResult cardCodeDeposit(String cardId, List<String> codeList) throws WxErrorException;

  /**
   * 查询导入code数目接口
   *
   * @param cardId 卡券id
   */
  WxMpCardCodeDepositCountResult cardCodeDepositCount(String cardId) throws WxErrorException;


  /**
   * 核查code接口
   *
   * @param cardId   卡券id
   * @param codeList 已经微信卡券后台的自定义code，上限为100个
   */
  WxMpCardCodeCheckcodeResult cardCodeCheckcode(String cardId, List<String> codeList) throws WxErrorException;

  /**
   * 图文消息群发卡券获取内嵌html
   *
   * @param cardId 卡券id
   */
  WxMpCardMpnewsGethtmlResult cardMpnewsGethtml(String cardId) throws WxErrorException;


  /**
   * 修改库存接口
   * https://developers.weixin.qq.com/doc/offiaccount/Cards_and_Offer/Managing_Coupons_Vouchers_and_Cards.html#5
   *
   * @param cardId      卡券ID
   * @param changeValue 库存变更值，负值为减少库存
   */
  void cardModifyStock(String cardId, Integer changeValue) throws WxErrorException;


  /**
   * 更改Code接口
   * https://developers.weixin.qq.com/doc/offiaccount/Cards_and_Offer/Managing_Coupons_Vouchers_and_Cards.html#6
   *
   * @param cardId  卡券ID
   * @param oldCode 需变更的Code码
   * @param newCode 变更后的有效Code码
   */
  void cardCodeUpdate(String cardId, String oldCode, String newCode) throws WxErrorException;

  /**
   * 设置买单接口
   * https://developers.weixin.qq.com/doc/offiaccount/Cards_and_Offer/Create_a_Coupon_Voucher_or_Card.html#12
   *
   * @param cardId 卡券ID
   * @param isOpen 是否开启买单功能，填true/false
   */
  void cardPaycellSet(String cardId, Boolean isOpen) throws WxErrorException;

  /**
   * 设置自助核销
   * https://developers.weixin.qq.com/doc/offiaccount/Cards_and_Offer/Create_a_Coupon_Voucher_or_Card.html#14
   *
   * @param cardId           卡券ID
   * @param isOpen           是否开启自助核销功能
   * @param needVerifyCod    用户核销时是否需要输入验证码， 填true/false， 默认为false
   * @param needRemarkAmount 用户核销时是否需要备注核销金额， 填true/false， 默认为false
   */
  void cardSelfConsumeCellSet(String cardId, Boolean isOpen,
                              Boolean needVerifyCod, Boolean needRemarkAmount) throws WxErrorException;

}
