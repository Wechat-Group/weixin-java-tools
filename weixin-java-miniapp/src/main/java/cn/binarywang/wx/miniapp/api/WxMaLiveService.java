package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.WxMaLiveInfo;
import cn.binarywang.wx.miniapp.bean.WxMaLiveResult;
import me.chanjar.weixin.common.error.WxErrorException;

import java.util.List;

/**
 * <pre>
 *  直播相关操作接口.
 *  Created by yjwang on 2020/4/5.
 * </pre>
 *
 * @author <a href="https://github.com/yjwang3300300">yjwang</a>
 */
public interface WxMaLiveService {
  String GET_LIVE_INFO = "https://api.weixin.qq.com/wxa/business/getliveinfo";
  String CREATE_ROOM = "https://api.weixin.qq.com/wxaapi/broadcast/room/create";
  String ADD_GOODS = "https://api.weixin.qq.com/wxaapi/broadcast/room/addgoods";

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
  Integer createRoom(WxMaLiveInfo.RoomInfo roomInfo) throws WxErrorException;

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
}
