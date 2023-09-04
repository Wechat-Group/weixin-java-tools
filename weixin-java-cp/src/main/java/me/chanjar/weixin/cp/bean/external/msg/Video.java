package me.chanjar.weixin.cp.bean.external.msg;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 视频消息
 *
 * @author pg  created on  2021-6-21
 */
@Data
@Accessors(chain = true)
public class Video implements Serializable {
  private static final long serialVersionUID = -6048642921382867138L;
  @SerializedName("media_id")
  private String mediaId;
  @SerializedName("thumb_media_id")
  private String thumbMediaId;
}
