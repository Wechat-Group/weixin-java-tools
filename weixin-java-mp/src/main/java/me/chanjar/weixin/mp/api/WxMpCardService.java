package me.chanjar.weixin.mp.api;

import me.chanjar.weixin.common.bean.WxCardApiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.card.WxMpCardLandingPageCreateRequest;
import me.chanjar.weixin.mp.bean.card.WxMpCardLandingPageCreateResult;
import me.chanjar.weixin.mp.bean.card.WxMpCardQrcodeCreateResult;
import me.chanjar.weixin.mp.bean.result.WxMpCardResult;

/**
 * 卡券相关接口
 *
 * @author YuJian(mgcnrx11@hotmail.com) on 01/11/2016
 * @author yuanqixun 2018-08-29
 */
public interface WxMpCardService {
  String CARD_GET = "https://api.weixin.qq.com/card/get";
  String CARD_GET_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=wx_card";
  String CARD_CODE_DECRYPT = "https://api.weixin.qq.com/card/code/decrypt";
  String CARD_CODE_GET = "https://api.weixin.qq.com/card/code/get";
  String CARD_CODE_CONSUME = "https://api.weixin.qq.com/card/code/consume";
  String CARD_CODE_MARK = "https://api.weixin.qq.com/card/code/mark";
  String CARD_TEST_WHITELIST = "https://api.weixin.qq.com/card/testwhitelist/set";
  String CARD_QRCODE_CREAET = "https://api.weixin.qq.com/card/qrcode/create";
  String CARD_LANDING_PAGE_CREAET = "https://api.weixin.qq.com/card/landingpage/create";

  /**
   * 得到WxMpService
   */
  WxMpService getWxMpService();

  /**
   * 获得卡券api_ticket，不强制刷新卡券api_ticket
   *
   * @return 卡券api_ticket
   * @see #getCardApiTicket(boolean)
   */
  String getCardApiTicket() throws WxErrorException;

  /**
   * <pre>
   * 获得卡券api_ticket
   * 获得时会检查卡券apiToken是否过期，如果过期了，那么就刷新一下，否则就什么都不干
   *
   * 详情请见：http://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html#.E9.99.84.E5.BD.954-.E5.8D.A1.E5.88.B8.E6.89.A9.E5.B1.95.E5.AD.97.E6.AE.B5.E5.8F.8A.E7.AD.BE.E5.90.8D.E7.94.9F.E6.88.90.E7.AE.97.E6.B3.95
   * </pre>
   *
   * @param forceRefresh 强制刷新
   * @return 卡券api_ticket
   * @throws WxErrorException
   */
  String getCardApiTicket(boolean forceRefresh) throws WxErrorException;

  /**
   * <pre>
   * 创建调用卡券api时所需要的签名
   *
   * 详情请见：http://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html#.E9.99.84.E5.BD
   * .954-.E5.8D.A1.E5.88.B8.E6.89.A9.E5.B1.95.E5.AD.97.E6.AE.B5.E5.8F.8A.E7.AD.BE.E5.90.8D.E7.94
   * .9F.E6.88.90.E7.AE.97.E6.B3.95
   * </pre>
   *
   * @param optionalSignParam 参与签名的参数数组。
   *                          可以为下列字段：app_id, card_id, card_type, code, openid, location_id
   *                          </br>注意：当做wx.chooseCard调用时，必须传入app_id参与签名，否则会造成签名失败导致拉取卡券列表为空
   * @return 卡券Api签名对象
   */
  WxCardApiSignature createCardApiSignature(String... optionalSignParam) throws
    WxErrorException;

  /**
   * 卡券Code解码
   *
   * @param encryptCode 加密Code，通过JSSDK的chooseCard接口获得
   * @return 解密后的Code
   */
  String decryptCardCode(String encryptCode) throws WxErrorException;

  /**
   * 卡券Code查询
   *
   * @param cardId       卡券ID代表一类卡券
   * @param code         单张卡券的唯一标准
   * @param checkConsume 是否校验code核销状态，填入true和false时的code异常状态返回数据不同
   * @return WxMpCardResult对象
   */
  WxMpCardResult queryCardCode(String cardId, String code, boolean checkConsume)
    throws WxErrorException;

  /**
   * 卡券Code核销。核销失败会抛出异常
   *
   * @param code 单张卡券的唯一标准
   * @return 调用返回的JSON字符串。
   * <br>可用 com.google.gson.JsonParser#parse 等方法直接取JSON串中的errcode等信息。
   */
  String consumeCardCode(String code) throws WxErrorException;

  /**
   * 卡券Code核销。核销失败会抛出异常
   *
   * @param code   单张卡券的唯一标准
   * @param cardId 当自定义Code卡券时需要传入card_id
   * @return 调用返回的JSON字符串。
   * <br>可用 com.google.gson.JsonParser#parse 等方法直接取JSON串中的errcode等信息。
   */
  String consumeCardCode(String code, String cardId) throws WxErrorException;

  /**
   * 卡券Mark接口。
   * 开发者在帮助消费者核销卡券之前，必须帮助先将此code（卡券串码）与一个openid绑定（即mark住），
   * 才能进一步调用核销接口，否则报错。
   *
   * @param code   卡券的code码
   * @param cardId 卡券的ID
   * @param openId 用券用户的openid
   * @param isMark 是否要mark（占用）这个code，填写true或者false，表示占用或解除占用
   */
  void markCardCode(String code, String cardId, String openId, boolean isMark) throws
    WxErrorException;

  /**
   * 查看卡券详情接口
   * 详见 https://mp.weixin.qq.com/wiki/14/8dd77aeaee85f922db5f8aa6386d385e.html#.E6.9F.A5.E7.9C.8B.E5.8D.A1.E5.88.B8.E8.AF.A6.E6.83.85
   *
   * @param cardId 卡券的ID
   * @return 返回的卡券详情JSON字符串
   * <br> [注] 由于返回的JSON格式过于复杂，难以定义其对应格式的Bean并且难以维护，因此只返回String格式的JSON串。
   * <br> 可由 com.google.gson.JsonParser#parse 等方法直接取JSON串中的某个字段。
   */
  String getCardDetail(String cardId) throws WxErrorException;

  /**
   * 添加测试白名单
   * @param openid 用户的openid
   * @return
   */
  String addTestWhiteList(String openid) throws WxErrorException;

  /**
   * 创建卡券二维码
   * @param cardId 卡券编号
   * @param outerStr 二维码标识
   * @return WxMpCardQrcodeCreateResult
   */
  WxMpCardQrcodeCreateResult createQrcodeCard(String cardId, String outerStr) throws WxErrorException;

  /**
   * 创建卡券货架
   * @param createRequest 货架创建参数
   * @return
   * @throws WxErrorException
   */
  WxMpCardLandingPageCreateResult createLandingPage(WxMpCardLandingPageCreateRequest createRequest) throws WxErrorException;
}
