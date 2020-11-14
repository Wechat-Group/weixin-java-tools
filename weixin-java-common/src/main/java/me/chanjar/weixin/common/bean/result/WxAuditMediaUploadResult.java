package me.chanjar.weixin.common.bean.result;

import java.io.Serializable;

import lombok.Data;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

/**
 * 小程序 提审素材上传接口
 *
 * @author yangyh22
 * @since 2020/11/14
 */
@Data
public class WxAuditMediaUploadResult implements Serializable {

  private static final long serialVersionUID = 1L;

  private String type;
  private String mediaid;

  public static WxAuditMediaUploadResult fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxAuditMediaUploadResult.class);
  }

  @Override
  public String toString() {
    return WxGsonBuilder.create().toJson(this);
  }

}
