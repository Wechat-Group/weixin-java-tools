package me.chanjar.weixin.cp.bean.kf.msg;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author leiin
 * @date 2022/1/26 5:33 下午
 */
@NoArgsConstructor
@Data
public class WxCpKfLinkMsg {

  /**
   * 参数：title
   * 是否必须：是
   * 类型：string
   * 说明：标题，不超过128个字节，超过会自动截断
   */
  @SerializedName("title")
  private String title;
  /**
   * 参数：desc
   * 是否必须：否
   * 类型：string
   * 说明：描述，不超过512个字节，超过会自动截断
   */
  @SerializedName("desc")
  private String desc;
  /**
   * 参数：url
   * 是否必须：是
   * 类型：string
   * 说明：点击后跳转的链接。 最长2048字节，请确保包含了协议头(http/https)
   */
  @SerializedName("url")
  private String url;
  /**
   * 参数：thumb_media_id
   * 是否必须：是
   * 类型：string
   * 说明：发送消息参数，缩略图的media_id, 可以通过素材管理接口获得。此处thumb_media_id即上传接口返回的media_id
   */
  @SerializedName("thumb_media_id")
  private String thumb_media_id;

  /**
   * 返回消息参数
   */
  @SerializedName("pic_url")
  private String picUrl;
}
