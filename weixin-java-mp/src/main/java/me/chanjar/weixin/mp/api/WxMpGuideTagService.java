package me.chanjar.weixin.mp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.guide.WxMpGuideBuyerResp;
import me.chanjar.weixin.mp.bean.guide.WxMpGuideTagInfo;

import java.util.List;

/**
 * 微信导购助手（现在叫对话能力）标签相关接口.
 *
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * @date 2021/5/13/013
 */
public interface WxMpGuideTagService {

  /**
   * 新建标签类型
   * 最多 4 类标签类型，50 个可选值，所有的标签可选值不能有相等重复的值。
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/newguidetagoption?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/tag-account/shopping-guide.newGuideTagOption.html
   * </pre>
   *
   * @param tagName 标签类型的名字
   * @param values  标签可选值列表，可选值不能为空值，所有的标签可选值不能有相等重复的值
   * @throws WxErrorException 。
   */
  void newGuideTagOption(String tagName, List<String> values) throws WxErrorException;

  /**
   * 删除指定标签类型
   * 此操作会更新所有相关客户的标签信息，存在延迟。
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/delguidetagoption?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/tag-account/shopping-guide.delguidetagoption.html
   * </pre>
   *
   * @param tagName 标签类型的名字
   * @throws WxErrorException 。
   */
  void delGuideTagOption(String tagName) throws WxErrorException;

  /**
   * 为标签添加可选值
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/addguidetagoption?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/tag-account/shopping-guide.addGuideTagOption.html
   * </pre>
   *
   * @param tagName 标签类型的名字
   * @param values  标签可选值列表，可选值不能为空值，所有的标签可选值不能有相等重复的值
   * @throws WxErrorException 。
   */
  void addGuideTagOption(String tagName, List<String> values) throws WxErrorException;

  /**
   * 获取标签和可选值
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/getguidetagoption?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/tag-account/shopping-guide.getGuideTagOption.html
   * </pre>
   *
   * @return 标签信息列表
   */
  List<WxMpGuideTagInfo> getGuideTagOption() throws WxErrorException;

  /**
   * 为客户设置标签（批量）
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/addguidebuyertag?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/tag-account/shopping-guide.addGuideBuyerTag.html
   * </pre>
   *
   * @param account     顾问微信号（guide_account和guide_openid二选一）
   * @param openid      顾问openid或者unionid（guide_account和guide_openid二选一）
   * @param value       标签的可选值，该值必须在标签的可选值集合中
   * @param userOpenIds 客户列表，不超过200
   * @return 客户列表添加结果
   * @throws WxErrorException .
   */
  List<WxMpGuideBuyerResp> addGuideBuyerTag(String account, String openid, String value, List<String> userOpenIds) throws WxErrorException;

  /**
   * 为客户设置标签（单个）
   *
   * @param account    顾问微信号（guide_account和guide_openid二选一）
   * @param openid     顾问openid或者unionid（guide_account和guide_openid二选一）
   * @param value      标签的可选值，该值必须在标签的可选值集合中
   * @param userOpenid 用户openid
   * @throws WxErrorException .
   */
  void addGuideBuyerTag(String account, String openid, String value, String userOpenid) throws WxErrorException;

  /**
   * 查询客户标签
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/getguidebuyertag?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/tag-account/shopping-guide.getGuideBuyerTag.html
   * </pre>
   * <p>
   * 踩坑记录（2021/5/12）：这里不只是返回标签值
   * 如果该客户设置了自定义信息也会同样返回在标签数组的末尾
   * 未设置则只返回客户标签列表
   * 为此坑我添加一个参数是否排除客户自定义信息
   *
   * @param account    顾问微信号（guide_account和guide_openid二选一）
   * @param openid     顾问openid或者unionid（guide_account和guide_openid二选一）
   * @param userOpenid 用户openid
   * @param isExclude  是否排除客户自定义信息
   * @return 标签值列表
   * @throws WxErrorException 。
   */
  List<String> getGuideBuyerTag(String account, String openid, String userOpenid, Boolean isExclude) throws WxErrorException;

  /**
   * 根据标签值筛选客户
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/queryguidebuyerbytag?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/tag-account/shopping-guide.queryGuideBuyerByTag.html
   * </pre>
   * <p>
   * 踩坑记录（2021/5/12）： 不传递pushCount参数会返回-1
   * 传递0查询所有 （推荐传递0）
   * 当pushCount > 0 该条件查询逻辑有问题
   * 目前发现：传递1可以查询出可发次数为4次的用户，而传递4是查询不出来的。
   * <p>
   * 注意：该查询是查询所有条件均符合的  例如：查询A标签的客户  假如客户标签为A,B两个 将无法查询到该客户
   *
   * @param account   顾问微信号（guide_account和guide_openid二选一）
   * @param openid    顾问openid或者unionid（guide_account和guide_openid二选一）
   * @param pushCount 本月还可主动发消息次数 （建议传递0查询）
   * @param value     标签值集合，该值必须在标签可选值集合中
   * @return 客户openid集合
   * @throws WxErrorException 。
   */
  List<String> queryGuideBuyerByTag(String account, String openid, Integer pushCount, List<String> value) throws WxErrorException;

  /**
   * 删除客户标签(批量)
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/delguidebuyertag?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/tag-account/shopping-guide.delGuideBuyerTag.html
   * </pre>
   *
   * @param account     顾问微信号（guide_account和guide_openid二选一）
   * @param openid      顾问openid或者unionid（guide_account和guide_openid二选一）
   * @param value       标签的可选值，该值必须在标签的可选值集合中
   * @param userOpenIds 客户列表，不超过200
   * @return 客户列表处理结果
   * @throws WxErrorException。
   */
  List<WxMpGuideBuyerResp> delGuideBuyerTag(String account, String openid, String value, List<String> userOpenIds) throws WxErrorException;

  /**
   * 删除客户标签(单个)
   *
   * @param account    顾问微信号（guide_account和guide_openid二选一）
   * @param openid     顾问openid或者unionid（guide_account和guide_openid二选一）
   * @param value      标签的可选值，该值必须在标签的可选值集合中
   * @param userOpenid 用户openid
   * @throws WxErrorException .
   */
  void delGuideBuyerTag(String account, String openid, String value, String userOpenid) throws WxErrorException;

  /**
   * 设置自定义客户信息
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/addguidebuyerdisplaytag?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/tag-account/shopping-guide.addGuideBuyerDisplayTag.html
   * </pre>
   *
   * @param account    顾问微信号（guide_account和guide_openid二选一）
   * @param openid     顾问openid或者unionid（guide_account和guide_openid二选一）
   * @param userOpenid 用户openid
   * @param msgList    自定义客户信息，全量更新，调用时传所有信息
   * @throws WxErrorException .
   */
  void addGuideBuyerDisplayTag(String account, String openid, String userOpenid, List<String> msgList) throws WxErrorException;

  /**
   * 获取自定义客户信息
   *
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/guide/getguidebuyerdisplaytag?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Shopping_Guide/tag-account/shopping-guide.getGuideBuyerDisplayTag.html
   * </pre>
   *
   * @param account    顾问微信号（guide_account和guide_openid二选一）
   * @param openid     顾问openid或者unionid（guide_account和guide_openid二选一）
   * @param userOpenid 用户openid
   * @return 自定义客户信息列表
   * @throws WxErrorException 。
   */
  List<String> getGuideBuyerDisplayTag(String account, String openid, String userOpenid) throws WxErrorException;

}
