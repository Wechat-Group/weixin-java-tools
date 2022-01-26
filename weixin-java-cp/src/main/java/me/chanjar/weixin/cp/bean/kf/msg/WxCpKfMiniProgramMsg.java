package me.chanjar.weixin.cp.bean.kf.msg;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author leiin
 * @date 2022/1/26 6:22 下午
 */
@NoArgsConstructor
@Data
public class WxCpKfMiniProgramMsg {
  /**
   * 参数：appid
   * 是否必须：是
   * 类型：string
   * 说明：小程序appid
   */
  @SerializedName("appid")
  private String appId;
  /**
   * 参数：title
   * 是否必须：否
   * 类型：string
   * 说明：小程序消息标题，最多64个字节，超过会自动截断
   */
  @SerializedName("title")
  private String title;
  /**
   * 参数：thumb_media_id
   * 是否必须：是
   * 类型：string
   * 说明：小程序消息封面的mediaid，封面图建议尺寸为520*416
   */
  @SerializedName("thumb_media_id")
  private String thumbMediaId;
  /**
   * 参数：pagepath
   * 是否必须：是
   * 类型：string
   * 说明：点击消息卡片后进入的小程序页面路径。注意路径要以.html为后缀，否则在微信中打开会提示找不到页面
   */
  @SerializedName("pagepath")
  private String pagePath;
}
