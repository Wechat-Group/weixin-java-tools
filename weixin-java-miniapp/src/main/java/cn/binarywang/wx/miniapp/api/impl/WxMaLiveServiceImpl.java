package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaLiveService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaLiveInfo;
import cn.binarywang.wx.miniapp.util.json.WxMaGsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *  Created by yjwang on 2020/4/5.
 * </pre>
 *
 * @author <a href="https://github.com/yjwang3300300">yjwang</a>
 */
@AllArgsConstructor
public class WxMaLiveServiceImpl implements WxMaLiveService {
  final Logger log = LoggerFactory.getLogger(this.getClass());
  private static final JsonParser JSON_PARSER = new JsonParser();
  private WxMaService wxMaService;

  @Override
  public Integer createRoom(WxMaLiveInfo.RoomInfo roomInfo) throws WxErrorException {
    // fixme 请求头需要设置 content-type:application/json;charset=utf-8
    // 问题：https://developers.weixin.qq.com/community/develop/doc/000a84e22d434002317afed845b800?highLine=%25E5%2588%259B%25E5%25BB%25BA%25E7%259B%25B4%25E6%2592%25AD%25E9%2597%25B4%25E6%258E%25A5%25E5%258F%25A3

    String responseContent = this.wxMaService.post(CREATE_ROOM, WxMaGsonBuilder.create().toJson(roomInfo));
    JsonObject jsonObject = JSON_PARSER.parse(responseContent).getAsJsonObject();
    if (jsonObject.get("errcode").getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return jsonObject.get("roomId").getAsInt();
  }

  @Override
  public WxMaLiveInfo getLiveInfo(Integer start, Integer limit) throws WxErrorException {
    JsonObject jsonObject = getJsonObject(start, limit, null);
    return WxMaLiveInfo.fromJson(jsonObject.toString());
  }

  @Override
  public List<WxMaLiveInfo.RoomInfo> getLiveinfos() throws WxErrorException {
    List<WxMaLiveInfo.RoomInfo> results = new ArrayList<>();
    Integer start = 0;
    Integer limit = 80;
    Integer tatal = 0;
    WxMaLiveInfo liveInfo = null;
    do {
      if (tatal != 0 && tatal <= start) {
        break;
      }
      liveInfo = getLiveInfo(start, limit);
      if (liveInfo == null) {
        return null;
      }
      results.addAll(liveInfo.getRoomInfos());
      tatal = liveInfo.getTotal();
      start = results.size();
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    } while (results.size() <= tatal);
    return results;
  }

  @Override
  public WxMaLiveInfo getLiveReplay(String action, Integer room_id, Integer start, Integer limit) throws WxErrorException {
    Map<String, Object> map = new HashMap(4);
    map.put("action", action);
    map.put("room_id", room_id);
    JsonObject jsonObject = getJsonObject(start, limit, map);
    return WxMaLiveInfo.fromJson(jsonObject.toString());
  }

  @Override
  public WxMaLiveInfo getLiveReplay(Integer room_id, Integer start, Integer limit) throws WxErrorException {
    return getLiveReplay("get_replay", room_id, start, limit);
  }

  @Override
  public boolean addGoodsToRoom(Integer roomId, List<Integer> goodsIds) throws WxErrorException {
    Map<String, Object> map = new HashMap<>(2);
    map.put("roomId", roomId);
    map.put("ids", goodsIds);
    String responseContent = this.wxMaService.post(ADD_GOODS, WxMaGsonBuilder.create().toJson(map));
    JsonObject jsonObject = JSON_PARSER.parse(responseContent).getAsJsonObject();
    if (jsonObject.get("errcode").getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return true;
  }

  /**
   * 包装一下
   *
   * @param start
   * @param limit
   * @param map
   * @return
   * @throws WxErrorException
   */
  private JsonObject getJsonObject(Integer start, Integer limit, Map<String, Object> map) throws WxErrorException {
    if (map == null) {
      map = new HashMap(2);
    }
    map.put("start", start);
    map.put("limit", limit);
    String responseContent = wxMaService.post(GET_LIVE_INFO, WxMaGsonBuilder.create().toJson(map));
    JsonObject jsonObject = JSON_PARSER.parse(responseContent).getAsJsonObject();
    if (jsonObject.get("errcode").getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return jsonObject;
  }
}
