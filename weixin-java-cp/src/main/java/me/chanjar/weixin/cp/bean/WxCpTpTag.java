package me.chanjar.weixin.cp.bean;

import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 *
 * @author zhangq <zhangq002@gmail.com>
 * @since 2021-02-14 16:15 16:15
 */
public class WxCpTpTag extends WxCpTag {
  private static final long serialVersionUID = 7485287679462507128L;

  public static WxCpTpTag deserialize(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTpTag.class);
  }
}
