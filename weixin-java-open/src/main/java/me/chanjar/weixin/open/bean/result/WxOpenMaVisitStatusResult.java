package me.chanjar.weixin.open.bean.result;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 小程序服务状态
 *
 * @author <a href="https://github.com/vostro2013">vostro2013</a>
 * @date 2023/06/06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WxOpenMaVisitStatusResult extends WxOpenResult {
  private Integer status;

  public static WxOpenMaVisitStatusResult fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WxOpenMaVisitStatusResult.class);
  }
}