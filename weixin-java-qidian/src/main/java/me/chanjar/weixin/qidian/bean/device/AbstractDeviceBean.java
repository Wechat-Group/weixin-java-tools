package me.chanjar.weixin.qidian.bean.device;

import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * 设备抽象类.
 *
 * @author keungtung
 * @date 14/12/2016
 */
public abstract class AbstractDeviceBean implements Serializable {
  private static final long serialVersionUID = 4359729626772515385L;

  public String toJson() {
    return WxGsonBuilder.create().toJson(this);
  }
}
