package me.chanjar.weixin.mp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.guide.*;

import java.util.List;

/**
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * @date 2021/5/13/013
 */
public interface WxMpGuideBuyerService {
  /**
   * 为顾问分配客户(批量)
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/addguidebuyerrelation?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/buyer-account/shopping-guide.addGuideBuyerRelation.html
   * </pre>
   *
   * @param account 顾问微信号（guide_account和guide_openid二选一，若同时请求，默认为guide_account）
   * @param openid  顾问openid或者unionid（guide_account和guide_openid二选一）
   * @param infos   客户列表
   * @return 客户列表添加结果
   * @throws WxErrorException .
   */
  List<WxMpGuideBuyerResp> addGuideBuyerRelation(String account, String openid, List<WxMpAddGuideBuyerInfo> infos) throws WxErrorException;

  /**
   * 为顾问分配客户(单个)
   *
   * @param account    顾问微信号（guide_account和guide_openid二选一，若同时请求，默认为guide_account）
   * @param openid     顾问openid或者unionid（guide_account和guide_openid二选一）
   * @param userOpenid 用户openid
   * @param nickname   用户昵称
   * @throws WxErrorException .
   */
  void addGuideBuyerRelation(String account, String openid, String userOpenid, String nickname) throws WxErrorException;

  /**
   * 为顾问移除客户(批量)
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/delguidebuyerrelation?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/buyer-account/shopping-guide.delGuideBuyerRelation.html
   * </pre>
   *
   * @param account      顾问微信号（guide_account和guide_openid二选一，若同时请求，默认为guide_account）
   * @param openid       顾问openid或者unionid（guide_account和guide_openid二选一）
   * @param buyerOpenIds 客户openid列表，不超过200
   * @return 客户列表移除结果
   */
  List<WxMpGuideBuyerResp> delGuideBuyerRelation(String account, String openid, List<String> buyerOpenIds) throws WxErrorException;

  /**
   * 为顾问移除客户(单个)
   *
   * @param account    顾问微信号（guide_account和guide_openid二选一，若同时请求，默认为guide_account）
   * @param openid     顾问openid或者unionid（guide_account和guide_openid二选一）
   * @param userOpenid 用户openid
   * @throws WxErrorException .
   */
  void delGuideBuyerRelation(String account, String openid, String userOpenid) throws WxErrorException;

  /**
   * 获取顾问的客户列表
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/getguidebuyerrelationlist?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/buyer-account/shopping-guide.getGuideBuyerRelationList.html
   * </pre>
   *
   * @param account 顾问微信号（guide_account和guide_openid二选一，若同时请求，默认为guide_account）
   * @param openid  顾问openid或者unionid（guide_account和guide_openid二选一）
   * @param page    分页页数，从0开始，用于组内顾问分页获取
   * @param num     每页数量
   * @return 顾问的客户列表
   * @throws WxErrorException .
   */
  WxMpGuideBuyerInfoList getGuideBuyerRelationList(String account, String openid, int page, int num) throws WxErrorException;

  /**
   * 为客户更换顾问(批量)
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/rebindguideacctforbuyer?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/buyer-account/shopping-guide.rebindGuideAcctForBuyer.html
   * </pre>
   *
   * @param oldAccount   原顾问微信号（old_guide_account和new_guide_account配套使用）
   * @param oldOpenid    原顾问openid或者unionid（old_guide_openid和new_guide_openid配套使用）
   * @param account      新顾问微信号（new_guide_account和new_guide_openid二选一）
   * @param openid       新顾问openid或者unionid（new_guide_account和new_guide_openid二选一）
   * @param buyerOpenIds 客户列表，不超过200
   * @return 客户列表换绑结果
   * @throws WxErrorException .
   */
  List<WxMpGuideBuyerResp> rebindGuideAcctForBuyer(String oldAccount, String oldOpenid, String account, String openid, List<String> buyerOpenIds) throws WxErrorException;

  /**
   * 为客户更换顾问(单个)
   *
   * @param oldAccount 原顾问微信号（old_guide_account和new_guide_account配套使用）
   * @param oldOpenid  原顾问openid或者unionid（old_guide_openid和new_guide_openid配套使用）
   * @param account    新顾问微信号（new_guide_account和new_guide_openid二选一）
   * @param openid     新顾问openid或者unionid（new_guide_account和new_guide_openid二选一）
   * @param userOpenid 用户openid
   * @throws WxErrorException 。
   */
  void rebindGuideAcctForBuyer(String oldAccount, String oldOpenid, String account, String openid, String userOpenid) throws WxErrorException;

  /**
   * 修改客户昵称
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/updateguidebuyerrelation?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/buyer-account/shopping-guide.updateGuideBuyerRelation.html
   * </pre>
   *
   * @param account    顾问微信号（guide_account和guide_openid二选一，若同时请求，默认为guide_account）
   * @param openid     顾问openid或者unionid（guide_account和guide_openid二选一）
   * @param userOpenid 客户openid
   * @param nickname   客户昵称
   * @throws WxErrorException .
   */
  void updateGuideBuyerRelation(String account, String openid, String userOpenid, String nickname) throws WxErrorException;

  /**
   * 查询客户所属顾问
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/getguidebuyerrelationbybuyer?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/buyer-account/shopping-guide.getGuideBuyerRelationByBuyer.html
   * </pre>
   *
   * @param openid 客户openid
   * @return 客户顾问关系信息
   * @throws WxErrorException .
   */
  WxMpGuideBuyerRelation getGuideBuyerRelationByBuyer(String openid) throws WxErrorException;

  /**
   * 查询指定顾问和客户的关系
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/getguidebuyerrelation?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/buyer-account/shopping-guide.getGuideBuyerRelation.html
   * </pre>
   *
   * @param account    顾问微信号（guide_account和guide_openid二选一，若同时请求，默认为guide_account）
   * @param openid     顾问openid或者unionid（guide_account和guide_openid二选一）
   * @param userOpenid 客户openid
   * @return 客户信息
   * @throws WxErrorException .
   */
  WxMpGuideBuyerInfo getGuideBuyerRelation(String account, String openid, String userOpenid) throws WxErrorException;
}
