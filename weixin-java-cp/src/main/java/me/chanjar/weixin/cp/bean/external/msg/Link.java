package me.chanjar.weixin.cp.bean.external.msg;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 图文消息.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-08-16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Link implements Serializable {
  private static final long serialVersionUID = -8041816740881163875L;

  @SerializedName("title")
  private String title;

  @SerializedName("picurl")
  private String picUrl;

  @SerializedName("desc")
  private String desc;

  @SerializedName("url")
  private String url;
}
