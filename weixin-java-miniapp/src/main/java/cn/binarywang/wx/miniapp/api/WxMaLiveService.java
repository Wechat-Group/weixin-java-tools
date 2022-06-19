package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.live.*;
import me.chanjar.weixin.common.error.WxErrorException;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 *  直播相关操作接口.
 *  Created by yjwang on 2020/4/5.
 * </pre>
 *
 * @author <a href="https://github.com/yjwang3300300">yjwang</a>
 */
public interface WxMaLiveService {

  /**
   * 创建直播间
   * <pre>
   * 调用此接口创建直播间，创建成功后将在直播间列表展示，调用额度：10000次/一天
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/framework/liveplayer/studio-api.html#1
   * http请求方式：POST https://api.weixin.qq.com/wxaapi/broadcast/room/create?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param roomInfo 直播间信息
   * @return .
   * @throws WxErrorException .
   */
  WxMaCreateRoomResult createRoom(WxMaLiveRoomInfo roomInfo) throws WxErrorException;

  /**
   * 删除直播间
   * <pre>
   * 调用额度：10000次/一天
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/framework/liveplayer/studio-api.html#5
   * http请求方式：POST https://api.weixin.qq.com/wxaapi/broadcast/room/deleteroom?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param roomId 直播间id
   * @return .
   * @throws WxErrorException .
   */
  boolean deleteRoom(Integer roomId) throws WxErrorException;

  /**
   * 编辑直播间
   * <pre>
   * 调用此接口编辑直播间，调用额度：10000次/一天
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/framework/liveplayer/studio-api.html#6
   * http请求方式：POST https://api.weixin.qq.com/wxaapi/broadcast/room/editroom?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param roomInfo 直播间信息
   * @return .
   * @throws WxErrorException .
   */
  boolean editRoom(WxMaLiveRoomInfo roomInfo) throws WxErrorException;

  /**
   * 获取直播间推流地址
   * <pre>
   * 调用额度：10000次/一天
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/framework/liveplayer/studio-api.html#7
   * http请求方式：GET https://api.weixin.qq.com/wxaapi/broadcast/room/getpushurl?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param roomId 直播间id
   * @return .
   * @throws WxErrorException .
   */
  String getPushUrl(Integer roomId) throws WxErrorException;

  /**
   * 获取直播间分享二维码
   * <pre>
   * 调用额度：10000次/一天
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/framework/liveplayer/studio-api.html#8
   * http请求方式：GET https://api.weixin.qq.com/wxaapi/broadcast/room/getsharedcode?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param roomId 直播间id
   * @return .
   * @throws WxErrorException .
   */
  String getSharedCode(Integer roomId, String params) throws WxErrorException;

  /**
   * 获取直播房间列表.（分页）
   *
   * @param start 起始拉取房间，start = 0 表示从第 1 个房间开始拉取
   * @param limit 每次拉取的个数上限，不要设置过大，建议 100 以内
   * @return .
   * @throws WxErrorException .
   */
  WxMaLiveResult getLiveInfo(Integer start, Integer limit) throws WxErrorException;

  /**
   * 获取所有直播间信息（没有分页直接获取全部）
   *
   * @return .
   * @throws WxErrorException .
   */
  List<WxMaLiveResult.RoomInfo> getLiveInfos() throws WxErrorException;

  /**
   * 获取直播房间回放数据信息.
   *
   * @param action 获取回放
   * @param roomId 直播间   id
   * @param start  起始拉取视频，start =   0   表示从第    1   个视频片段开始拉取
   * @param limit  每次拉取的个数上限，不要设置过大，建议  100 以内
   * @return .
   * @throws WxErrorException .
   */
  WxMaLiveResult getLiveReplay(String action, Integer roomId, Integer start, Integer limit) throws WxErrorException;

  /**
   * 获取直播房间回放数据信息.
   * <p>
   * 获取回放 （默认：get_replay）
   *
   * @param roomId 直播间   id
   * @param start  起始拉取视频，start =   0   表示从第    1   个视频片段开始拉取
   * @param limit  每次拉取的个数上限，不要设置过大，建议  100 以内
   * @return .
   * @throws WxErrorException .
   */
  WxMaLiveResult getLiveReplay(Integer roomId, Integer start, Integer limit) throws WxErrorException;

  /**
   * 直播间导入商品
   * <p>
   * 调用接口往指定直播间导入已入库的商品
   * 调用频率
   * 调用额度：10000次/一天
   * <p>
   * http请求方式：POST https://api.weixin.qq.com/wxaapi/broadcast/room/addgoods?access_token=ACCESS_TOKEN
   * <pre>
   * @param roomId 房间ID
   * @param goodsIds 数组列表，可传入多个，里面填写 商品 ID
   * @return 导入商品是否成功
   * @throws WxErrorException .
   */
  boolean addGoodsToRoom(Integer roomId, List<Integer> goodsIds) throws WxErrorException;

  /**
   * 添加管理直播间小助手
   * <p>
   * 调用接口往指定直播间添加管理直播间小助手
   * 调用频率
   * 调用额度：10000次/一天
   * <p>
   * http请求方式：POST https://api.weixin.qq.com/wxaapi/broadcast/room/addassistant?access_token=ACCESS_TOKEN
   * <pre>
   * @param roomId 房间ID
   * @param users 数组列表，可传入多个，"users": [{"username":"testwechat","nickname":"testnick"}]
   * @return 添加管理直播间小助手是否成功
   * @throws WxErrorException .
   */
  boolean addAssistant(Integer roomId, List<WxMaLiveAssistantInfo> users) throws WxErrorException;

  /**
   * 修改直播间小助手昵称
   * <p>
   * 调用接口修改直播间小助手昵称
   * 调用频率
   * 调用额度：10000次/一天
   * <p>
   * http请求方式：POST https://api.weixin.qq.com/wxaapi/broadcast/room/modifyassistant?access_token=ACCESS_TOKEN
   * <pre>
   * @param roomId 房间ID
   * @param username 小助手微信号
   * @param nickname 小助手直播间昵称
   * @return 修改小助手昵称是否成功
   * @throws WxErrorException .
   */
  boolean modifyAssistant(Integer roomId, String username, String nickname) throws WxErrorException;

  /**
   * 删除直播间小助手
   * <p>
   * 删除直播间小助手
   * 调用频率
   * 调用额度：10000次/一天
   * <p>
   * http请求方式：POST https://api.weixin.qq.com/wxaapi/broadcast/room/removeassistant?access_token=ACCESS_TOKEN
   * <pre>
   * @param roomId 房间ID
   * @param username 小助手微信号
   * @return 删除小助手昵称是否成功
   * @throws WxErrorException .
   */
  boolean removeAssistant(Integer roomId, String username) throws WxErrorException;

  /**
   * 查询直播间小助手
   * <p>
   * 查询直播间小助手
   * 调用频率
   * 调用额度：10000次/一天
   * <p>
   * http请求方式：POST https://api.weixin.qq.com/wxaapi/broadcast/room/getassistantlist?access_token=ACCESS_TOKEN
   * <pre>
   * @param roomId 房间ID
   * @return 小助手列表
   * @throws WxErrorException .
   */
  List<WxMaAssistantResult.Assistant> getAssistantList(Integer roomId) throws WxErrorException;

  /**
   * 添加主播副号
   * <p>
   * 调用接口添加主播副号
   * <p>
   * 调用额度：10000次/一天
   * <p>
   * http请求方式：POST https://api.weixin.qq.com/wxaapi/broadcast/room/addsubanchor?access_token=ACCESS_TOKEN
   * <pre>
   * @param roomId 房间ID
   * @param username 用户微信号
   * @return 是否成功
   * @throws WxErrorException .
   */
  boolean addSubanchor(Integer roomId, String username) throws WxErrorException;

  /**
   * 修改主播副号
   * <p>
   * 调用接口修改主播副号
   * <p>
   * 调用频率: 10000次/一天
   * <p>
   * http请求方式：POST https://api.weixin.qq.com/wxaapi/broadcast/room/modifyassistant?access_token=ACCESS_TOKEN
   * <pre>
   * @param roomId 房间ID
   * @param username 小助手微信号
   * @param username 用户微信号
   * @return 是否成功
   * @throws WxErrorException .
   */
  boolean modifySubanchor(Integer roomId, String username) throws WxErrorException;

  /**
   * 删除主播副号
   * <p>
   * 调用频率: 10000次/一天
   * <p>
   * http请求方式：POST https://api.weixin.qq.com/wxaapi/broadcast/room/deletesubanchor?access_token=ACCESS_TOKEN
   * <pre>
   * @param roomId 房间ID
   * @return 是否成功
   * @throws WxErrorException .
   */
  boolean deleteSubanchor(Integer roomId) throws WxErrorException;

  /**
   * 获取主播副号
   * <p>
   * 调用额度：10000次/一天
   * <p>
   * http请求方式：GET https://api.weixin.qq.com/wxaapi/broadcast/room/getsubanchor?access_token=ACCESS_TOKEN
   * </pre>
   * @param roomId 直播间id
   * @return .
   * @throws WxErrorException .
   */
  String getSubanchor(Integer roomId) throws WxErrorException;

  /**
   * 开启/关闭直播间官方收录
   * <p>
   * 调用额度：10000次/一天
   * <p>
   * http请求方式：POST https://api.weixin.qq.com/wxaapi/broadcast/room/updatefeedpublic?access_token=ACCESS_TOKEN
   * <pre>
   * @param roomId 房间ID
   * @param isFeedsPublic 是否开启官方收录 【1: 开启，0：关闭】
   * @return 是否成功
   * @throws WxErrorException .
   */
  boolean updatefeedpublic(Integer roomId, Integer isFeedsPublic) throws WxErrorException;

  /**
   * 开启/关闭回放功能
   * <p>
   * 调用额度：10000次/一天
   * <p>
   * http请求方式：POST https://api.weixin.qq.com/wxaapi/broadcast/room/updatereplay?access_token=ACCESS_TOKEN
   * <pre>
   * @param roomId 房间ID
   * @param closeReplay 是否关闭回放 【0：开启，1：关闭】
   * @return 是否成功
   * @throws WxErrorException .
   */
  boolean updatereplay(Integer roomId, Integer closeReplay) throws WxErrorException;

  /**
   * 开启/关闭客服功能
   * <p>
   * 调用额度：10000次/一天
   * <p>
   * http请求方式：POST https://api.weixin.qq.com/wxaapi/broadcast/room/updatekf?access_token=ACCESS_TOKEN
   * <pre>
   * @param roomId 房间ID
   * @param closeKf 是否关闭客服 【0：开启，1：关闭】
   * @return 是否成功
   * @throws WxErrorException .
   */
  boolean updatekf(Integer roomId, Integer closeKf) throws WxErrorException;

  /**
   * 开启/关闭直播间全局禁言
   * <p>
   * 调用额度：10000次/一天
   * <p>
   * http请求方式：POST https://api.weixin.qq.com/wxaapi/broadcast/room/updatecomment?access_token=ACCESS_TOKEN
   * <pre>
   * @param roomId 房间ID
   * @param banComment 1-禁言，0-取消禁言
   * @return 是否成功
   * @throws WxErrorException .
   */
  boolean updatecomment(Integer roomId, Integer banComment) throws WxErrorException;

  /**
   * 上下架商品
   * <p>
   * 调用额度：10000次/一天
   * <p>
   * http请求方式：POST https://api.weixin.qq.com/wxaapi/broadcast/goods/onsale?access_token=ACCESS_TOKEN
   * <pre>
   * @param roomId 房间ID
   * @param goodsId 商品ID
   * @param onSale 上下架 【0：下架，1：上架】
   * @return 是否成功
   * @throws WxErrorException .
   */
  boolean onsale(Integer roomId, Integer goodsId, Integer onSale) throws WxErrorException;

  /**
   * 删除直播间商品
   * <p>
   * 调用额度：10000次/一天
   * <p>
   * http请求方式：POST https://api.weixin.qq.com/wxaapi/broadcast/goods/deleteInRoom?access_token=ACCESS_TOKEN
   * <pre>
   * @param roomId 房间ID
   * @param goodsId 商品ID
   * @return 是否成功
   * @throws WxErrorException .
   */
  boolean deleteInRoom(Integer roomId, Integer goodsId) throws WxErrorException;

  /**
   * 推送商品
   * <p>
   * 调用额度：10000次/一天
   * <p>
   * http请求方式：POST https://api.weixin.qq.com/wxaapi/broadcast/goods/push?access_token=ACCESS_TOKEN
   * <pre>
   * @param roomId 房间ID
   * @param goodsId 商品ID
   * @return 是否成功
   * @throws WxErrorException .
   */
  boolean push(Integer roomId, Integer goodsId) throws WxErrorException;

  /**
   * 直播间商品排序
   * <p>
   * 调用额度：10000次/一天
   * <p>
   * http请求方式：POST https://api.weixin.qq.com/wxaapi/broadcast/goods/sort?access_token=ACCESS_TOKEN
   * <pre>
   * @param roomId 房间ID
   * @param goods 商品ID列表, 例如: [{"goodsId":"123"}, {"goodsId":"234"}]
   * @return 是否成功
   * @throws WxErrorException .
   */
  boolean sort(Integer roomId, List<Map<String,String>> goods) throws WxErrorException;

  /**
   * 下载商品讲解视频
   * <p>
   * 调用额度：10000次/一天
   * <p>
   * http请求方式：POST https://api.weixin.qq.com/wxaapi/broadcast/goods/getVideo?access_token=ACCESS_TOKEN
   * </pre>
   * @param roomId 直播间id
   * @param goodsId 商品ID
   * @return .
   * @throws WxErrorException .
   */
  String getVideo(Integer roomId, Integer goodsId) throws WxErrorException;
}
