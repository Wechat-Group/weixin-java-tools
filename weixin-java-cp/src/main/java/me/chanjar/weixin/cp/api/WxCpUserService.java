package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpUser;

import java.util.List;

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
}
