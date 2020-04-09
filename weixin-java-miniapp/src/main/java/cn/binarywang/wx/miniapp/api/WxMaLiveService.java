package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.WxMaGetLiveInfo;
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
  String GET_LIVE_INFO = "http://api.weixin.qq.com/wxa/business/getliveinfo";

  /**
   * 获取直播房间列表.（分页）
   *
   * @param start 起始拉取房间，start = 0 表示从第 1 个房间开始拉取
   * @param limit 每次拉取的个数上限，不要设置过大，建议 100 以内
   * @return .
   * @throws WxErrorException .
   */
  WxMaGetLiveInfo getLiveInfo(Integer start, Integer limit) throws WxErrorException;

  /**
   * 获取所有直播间信息（没有分页直接获取全部）
   * @return
   * @throws WxErrorException
   */
  List<WxMaGetLiveInfo.RoomInfo> getLiveinfos() throws WxErrorException;

  /**
   *
   * 获取直播房间回放数据信息.
   *
   * @param action 获取回放
   * @param room_id 直播间   id
   * @param start 起始拉取视频，start =   0   表示从第    1   个视频片段开始拉取
   * @param limit 每次拉取的个数上限，不要设置过大，建议  100 以内
   * @return
   * @throws WxErrorException
   */
  WxMaGetLiveInfo getLiveReplay(String action, Integer room_id, Integer start, Integer limit) throws WxErrorException;

  /**
   *
   * 获取直播房间回放数据信息.
   *
   *  获取回放 （默认：get_replay）
   * @param room_id 直播间   id
   * @param start 起始拉取视频，start =   0   表示从第    1   个视频片段开始拉取
   * @param limit 每次拉取的个数上限，不要设置过大，建议  100 以内
   * @return
   * @throws WxErrorException
   */
  WxMaGetLiveInfo getLiveReplay(Integer room_id, Integer start, Integer limit) throws WxErrorException;

}
