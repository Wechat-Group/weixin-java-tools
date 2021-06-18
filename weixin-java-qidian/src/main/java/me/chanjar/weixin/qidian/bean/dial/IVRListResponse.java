package me.chanjar.weixin.qidian.bean.dial;

import lombok.Data;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.qidian.bean.common.QidianResponse;

import java.util.List;

@Data
public class IVRListResponse extends QidianResponse {
  private List<Ivr> node;

  public static IVRListResponse fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, IVRListResponse.class);
  }
}
