package me.chanjar.weixin.cp.bean.external.msg;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 图文消息.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-08-16
 */
@Data
public class Link implements Serializable {
  private static final long serialVersionUID = -8041816740881163875L;
  private String title;
  @SerializedName("picurl")
  private String picUrl;
  private String desc;
  private String url;
  @SerializedName("media_id")
  private String mediaId;
}
