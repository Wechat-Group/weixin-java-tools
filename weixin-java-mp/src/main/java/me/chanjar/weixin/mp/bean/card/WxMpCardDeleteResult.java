package me.chanjar.weixin.mp.bean.card;

import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

/**
 * @description 删除卡券结果
 * @author: fanxl
 * @date: 2019/1/22 0022 10:24
 */
public class WxMpCardDeleteResult extends BaseWxMpCardResult {

  public static WxMpCardDeleteResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpCardDeleteResult.class);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

}
