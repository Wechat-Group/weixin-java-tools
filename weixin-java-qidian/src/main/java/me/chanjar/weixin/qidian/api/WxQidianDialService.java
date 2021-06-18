package me.chanjar.weixin.qidian.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.qidian.bean.dial.IVRDialRequest;
import me.chanjar.weixin.qidian.bean.dial.IVRDialResponse;
import me.chanjar.weixin.qidian.bean.dial.IVRListResponse;

/**
 * 基础话务相关操作接口.
 *
 * @author alegria
 */
public interface WxQidianDialService {
  IVRDialResponse ivrDial(IVRDialRequest ivrDial) throws WxErrorException;

  IVRListResponse getIVRList() throws WxErrorException;

}
