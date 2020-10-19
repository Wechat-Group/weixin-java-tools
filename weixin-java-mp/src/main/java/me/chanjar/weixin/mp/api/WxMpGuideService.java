package me.chanjar.weixin.mp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.guide.WxMpGuideInfo;
import me.chanjar.weixin.mp.bean.guide.WxMpGuideList;

/**
 * 微信导购助手（现在叫对话能力）接口.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020 -10-06
 */
public interface WxMpGuideService {
  /**
   * 为服务号添加顾问
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/addguideacct?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/guide-account/shopping-guide.addGuideAcct.html
   * </pre>
   *
   * @param account    顾问微信号（guide_account和guide_openid二选一，若同时请求，默认为guide_account）
   * @param openid     顾问openid或者unionid（guide_account和guide_openid二选一）
   * @param headImgUrl 顾问头像，头像url只能用《上传图文消息内的图片获取URL》 me.chanjar.weixin.mp.api.impl.WxMpMaterialServiceImpl#mediaImgUpload(java.io.File)
   * @param nickName   顾问昵称
   * @throws WxErrorException .
   */
  void addGuide(String account, String openid, String headImgUrl, String nickName) throws WxErrorException;

  /**
   * 为服务号添加顾问
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/addguideacct?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/guide-account/shopping-guide.addGuideAcct.html
   * </pre>
   *
   * @param guideInfo 顾问信息
   * @throws WxErrorException .
   */
  void addGuide(WxMpGuideInfo guideInfo) throws WxErrorException;

  /**
   * 修改顾问的昵称或头像
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/updateguideacct?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/guide-account/shopping-guide.updateGuideAcct.html
   * </pre>
   *
   * @param guideInfo 顾问信息
   * @throws WxErrorException .
   */
  void updateGuide(WxMpGuideInfo guideInfo) throws WxErrorException;

  /**
   * 获取顾问信息
   *
   * <pre>
   * 请求地址：  POST https://api.weixin.qq.com/cgi-bin/guide/getguideacct?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/guide-account/shopping-guide.getGuideAcct.html
   * </pre>
   *
   * @param account 顾问微信号（guide_account和guide_openid二选一，若同时请求，默认为guide_account）
   * @param openid  顾问openid或者unionid（guide_account和guide_openid二选一）
   * @return 顾问信息
   * @throws WxErrorException .
   */
  WxMpGuideInfo getGuide(String account, String openid) throws WxErrorException;

  /**
   * 删除顾问
   *
   * <pre>
   * 请求地址：  POST https://api.weixin.qq.com/cgi-bin/guide/delguideacct?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/guide-account/shopping-guide.delGuideAcct.html
   * </pre>
   *
   * @param account 顾问微信号（guide_account和guide_openid二选一，若同时请求，默认为guide_account）
   * @param openid  顾问openid或者unionid（guide_account和guide_openid二选一）
   * @throws WxErrorException .
   */
  void delGuide(String account, String openid) throws WxErrorException;

  /**
   * 获取服务号顾问列表
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/getguideacctlist?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/guide-account/shopping-guide.getGuideAcctList.html
   * </pre>
   *
   * @param page 分页页数，从0开始
   * @param num  每页数量
   * @return 顾问信息列表
   * @throws WxErrorException .
   */
  WxMpGuideList listGuide(int page, int num) throws WxErrorException;
}
