package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpUser;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 用户管理接口
 *  Created by BinaryWang on 2017/6/24.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxCpUserService {
  /**
   * <pre>
   *   用在二次验证的时候
   *   企业在员工验证成功后，调用本方法告诉企业号平台该员工关注成功。
   * </pre>
   *
   * @param userId 用户id
   */
  void authenticate(String userId) throws WxErrorException;

  /**
   * <pre>
   * 获取部门成员(详情)
   *
   * http://qydev.weixin.qq.com/wiki/index.php?title=管理成员#.E8.8E.B7.E5.8F.96.E9.83.A8.E9.97.A8.E6.88.90.E5.91.98.28.E8.AF.A6.E6.83.85.29
   * </pre>
   *
   * @param departId   必填。部门id
   * @param fetchChild 非必填。1/0：是否递归获取子部门下面的成员
   * @param status     非必填。0获取全部员工，1获取已关注成员列表，2获取禁用成员列表，4获取未关注成员列表。status可叠加
   */
  List<WxCpUser> listByDepartment(Integer departId, Boolean fetchChild, Integer status) throws WxErrorException;

  /**
   * <pre>
   * 获取部门成员
   *
   * http://qydev.weixin.qq.com/wiki/index.php?title=管理成员#.E8.8E.B7.E5.8F.96.E9.83.A8.E9.97.A8.E6.88.90.E5.91.98
   * </pre>
   *
   * @param departId   必填。部门id
   * @param fetchChild 非必填。1/0：是否递归获取子部门下面的成员
   * @param status     非必填。0获取全部员工，1获取已关注成员列表，2获取禁用成员列表，4获取未关注成员列表。status可叠加
   */
  List<WxCpUser> listSimpleByDepartment(Integer departId, Boolean fetchChild, Integer status) throws WxErrorException;

  /**
   * 新建用户
   *
   * @param user 用户对象
   */
  void create(WxCpUser user) throws WxErrorException;

  /**
   * 更新用户
   *
   * @param user 用户对象
   */
  void update(WxCpUser user) throws WxErrorException;

  /**
   * <pre>
   * 删除用户/批量删除成员
   * http://qydev.weixin.qq.com/wiki/index.php?title=管理成员#.E6.89.B9.E9.87.8F.E5.88.A0.E9.99.A4.E6.88.90.E5.91.98
   * </pre>
   *
   * @param userIds 员工UserID列表。对应管理端的帐号
   */
  void delete(String... userIds) throws WxErrorException;

  /**
   * 获取用户
   *
   * @param userid 用户id
   */
  WxCpUser getById(String userid) throws WxErrorException;

  /**
   * <pre>
   * 邀请成员关注
   * http://qydev.weixin.qq.com/wiki/index.php?title=管理成员#.E9.82.80.E8.AF.B7.E6.88.90.E5.91.98.E5.85.B3.E6.B3.A8
   * </pre>
   *
   * @param userId     用户的userid
   * @param inviteTips 推送到微信上的提示语（只有认证号可以使用）。当使用微信推送时，该字段默认为“请关注XXX企业号”，邮件邀请时，该字段无效。
   * @return 1:微信邀请 2.邮件邀请
   * @deprecated api obsoleted. 邀请关注的功能微信企业号已经下线了，
   * 详细请参考该链接点击查看 https://qy.weixin.qq.com/cgi-bin/homepagenotify?action=get&id=46
   */
  @Deprecated
  int invite(String userId, String inviteTips) throws WxErrorException;

  /**
   * <pre>
   *  userid转openid.
   *  该接口使用场景为微信支付、微信红包和企业转账。
   *
   * 在使用微信支付的功能时，需要自行将企业微信的userid转成openid。
   * 在使用微信红包功能时，需要将应用id和userid转成appid和openid才能使用。
   * 注：需要成员使用微信登录企业微信或者关注微信插件才能转成openid
   *
   * 文档地址：https://work.weixin.qq.com/api/doc#11279
   * </pre>
   *
   * @param userId  企业内的成员id
   * @param agentId 非必填，整型，仅用于发红包。其它场景该参数不要填，如微信支付、企业转账、电子发票
   * @return map对象，可能包含以下值：
   * - openid	企业微信成员userid对应的openid，若有传参agentid，则是针对该agentid的openid。否则是针对企业微信corpid的openid
   * - appid	应用的appid，若请求包中不包含agentid则不返回appid。该appid在使用微信红包时会用到
   */
  Map<String, String> userId2Openid(String userId, Integer agentId) throws WxErrorException;

  /**
   * <pre>
   * openid转userid
   *
   * 该接口主要应用于使用微信支付、微信红包和企业转账之后的结果查询。
   * 开发者需要知道某个结果事件的openid对应企业微信内成员的信息时，可以通过调用该接口进行转换查询。
   * 权限说明：
   * 管理组需对openid对应的企业微信成员有查看权限。
   *
   * 文档地址：https://work.weixin.qq.com/api/doc#11279
   * </pre>
   *
   * @param openid 在使用微信支付、微信红包和企业转账之后，返回结果的openid
   * @return userid 该openid在企业微信对应的成员userid
   */
  String openid2UserId(String openid) throws WxErrorException;
}
