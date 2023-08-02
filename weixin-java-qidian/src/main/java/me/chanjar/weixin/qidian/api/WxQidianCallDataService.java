package me.chanjar.weixin.qidian.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.qidian.bean.call.GetSwitchBoardListResponse;

/**
 * 通话数据相关操作接口.
 *
 * @author alegria
 */
public interface WxQidianCallDataService {
  public GetSwitchBoardListResponse getSwitchBoardList() throws WxErrorException;
}
