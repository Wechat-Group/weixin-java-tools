package me.chanjar.weixin.cp.bean;

import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * 企业微信第三方开发-增加标签成员成员api响应体
 *
 * @author zhangq <zhangq002@gmail.com>
 * @since 2021/2/14 16:44
 */
public class WxCpTpTagAddOrRemoveUsersResult extends WxCpTagAddOrRemoveUsersResult {
  private static final long serialVersionUID = 3490401800490702052L;

  public static WxCpTpTagAddOrRemoveUsersResult deserialize(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTpTagAddOrRemoveUsersResult.class);
  }
}
