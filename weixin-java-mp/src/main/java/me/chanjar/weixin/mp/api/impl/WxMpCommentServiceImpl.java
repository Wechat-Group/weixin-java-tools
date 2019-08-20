package me.chanjar.weixin.mp.api.impl;

import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpCommentService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.enums.WxMpApiUrl;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2019-06-16
 */
@RequiredArgsConstructor
public class WxMpCommentServiceImpl implements WxMpCommentService {
  private final WxMpService wxMpService;

  @Override
  public void open(Integer msgDataId, Integer index) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("msg_data_id", msgDataId);
    if (index != null) {
      json.addProperty("index", index);
    }
    this.wxMpService.post(WxMpApiUrl.Comment.OPEN, json.toString());
  }
}
