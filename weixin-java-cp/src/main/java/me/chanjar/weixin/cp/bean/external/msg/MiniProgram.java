package me.chanjar.weixin.cp.bean.external.msg;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 小程序消息.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a> created on  2020-08-16
 */
@Data
@Accessors(chain = true)
public class MiniProgram implements Serializable {
  private static final long serialVersionUID = 4242074162638170679L;

  private String title;
  @SerializedName("pic_media_id")
  private String picMediaId;
  private String appid;
  private String page;
}
