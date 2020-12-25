package me.chanjar.weixin.qidian.api.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.qidian.api.WxQidianDialService;
import me.chanjar.weixin.qidian.api.WxQidianService;
import me.chanjar.weixin.qidian.bean.dial.IVRDialRequest;
import me.chanjar.weixin.qidian.bean.dial.IVRDialResponse;
import me.chanjar.weixin.qidian.bean.dial.IVRListResponse;

import static me.chanjar.weixin.qidian.enums.WxMpApiUrl.Dial.GET_IVR_LIST;
import static me.chanjar.weixin.qidian.enums.WxMpApiUrl.Dial.IVR_DIAL;

/**
 * Created by Binary Wang on 2016/7/21.
 *
 * @author Binary Wang
 */
@Slf4j
@RequiredArgsConstructor
public class WxQidianDialServiceImpl implements WxQidianDialService {
  private final WxQidianService wxQidianService;

  @Override
  public IVRDialResponse ivrDial(IVRDialRequest ivrDial) throws WxErrorException {
    String json = ivrDial.toJson();

    log.debug("IVR外呼：{}", json);

    String result = this.wxQidianService.post(IVR_DIAL, json);
    log.debug("创建菜单：{},结果：{}", json, result);

    return IVRDialResponse.fromJson(result);
  }

  @Override
  public IVRListResponse getIVRList() throws WxErrorException {
    String result = this.wxQidianService.get(GET_IVR_LIST, null);
    return IVRListResponse.fromJson(result);
  }

}
