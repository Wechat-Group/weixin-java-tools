package me.chanjar.weixin.common.bean.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * @author Daniel Qian
 */
@Data
public class WxMediaUploadResult implements Serializable {
  private static final long serialVersionUID = 330834334738622341L;

  @JsonProperty(value = "url")
  private String url;

  @JsonProperty(value = "type")
  private String type;

  @JsonProperty(value = "media_id")
  private String mediaId;

  @JsonProperty(value = "thumb_media_id")
  private String thumbMediaId;

  @JsonProperty(value = "created_at")
  private long createdAt;

  public static WxMediaUploadResult fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMediaUploadResult.class);
  }

  @Override
  public String toString() {
    return WxGsonBuilder.create().toJson(this);
  }

}
