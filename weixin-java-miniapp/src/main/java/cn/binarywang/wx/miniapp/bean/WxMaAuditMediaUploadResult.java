package cn.binarywang.wx.miniapp.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * 小程序 提审素材上传接口
 *
 * @author yangyh22
 * @since 2020/11/14
 */
@Data
public class WxMaAuditMediaUploadResult implements Serializable {
  private static final long serialVersionUID = 1L;

  private String type;

  @SerializedName("mediaid")
  private String mediaId;

  public static WxMaAuditMediaUploadResult fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMaAuditMediaUploadResult.class);
  }

  @Override
  public String toString() {
    return WxGsonBuilder.create().toJson(this);
  }

}
