package cn.binarywang.wx.miniapp.api;

import com.google.gson.JsonArray;
import me.chanjar.weixin.common.error.WxErrorException;


/**
 * 【小程序直播】成员管理接口.
 * https://developers.weixin.qq.com/miniprogram/dev/framework/liveplayer/role-manage.html
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2021 -02-15
 */
public interface WxMaLiveMemberService {
  /**
   * 1.设置成员角色
   * 调用此接口设置小程序直播成员的管理员、运营者和主播角色
   * 调用额度：10000次/一天
   * 请求URL ： https://api.weixin.qq.com/wxaapi/broadcast/role/addrole?access_token=
   *
   * @param username 用户的微信号
   * @param role     设置用户的角色，取值[1-管理员，2-主播，3-运营者]，设置超级管理员将无效
   * @return the string
   * @throws WxErrorException the wx error exception
   */
  String addRole(String username, int role) throws WxErrorException;

  /**
   * 2.解除成员角色
   * 调用此接口移除小程序直播成员的管理员、运营者和主播角色
   * 调用额度：10000次/一天
   * 请求URL：https://api.weixin.qq.com/wxaapi/broadcast/role/deleterole?access_token=
   *
   * @param username 用户的微信号
   * @param role     设置用户的角色，取值[1-管理员，2-主播，3-运营者]，设置超级管理员将无效
   * @return the string
   * @throws WxErrorException the wx error exception
   */
  String deleteRole(String username, int role) throws WxErrorException;

  /**
   * 3.查询成员列表
   * 调用此接口查询小程序直播成员列表
   * 调用额度：10000次/一天
   * 请求URL：https://api.weixin.qq.com/wxaapi/broadcast/role/getrolelist?access_token=
   *
   * @param role    查询的用户角色，取值 [-1-所有成员， 0-超级管理员，1-管理员，2-主播，3-运营者]，默认-1
   * @param offset  起始偏移量, 默认0
   * @param limit   查询个数，最大30，默认10
   * @param keyword 搜索的微信号或昵称，不传则返回全部
   * @return . json array
   * @throws WxErrorException the wx error exception
   */
  JsonArray listByRole(Integer role, Integer offset, Integer limit, String keyword) throws WxErrorException;
}
