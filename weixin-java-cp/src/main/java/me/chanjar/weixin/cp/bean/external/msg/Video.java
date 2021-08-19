package me.chanjar.weixin.cp.bean.external.msg;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 视频消息
 *
 * @author pg
 * @date 2021-6-21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video implements Serializable {
  private static final long serialVersionUID = -6048642921382867138L;

  @SerializedName("media_id")
  private String mediaId;
}
