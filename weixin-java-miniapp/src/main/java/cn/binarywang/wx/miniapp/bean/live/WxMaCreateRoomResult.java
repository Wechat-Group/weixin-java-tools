package cn.binarywang.wx.miniapp.bean.live;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 创建直播间接口返回.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-11-29
 */
@Data
public class WxMaCreateRoomResult implements Serializable {
  private static final long serialVersionUID = -335928442728127170L;

  /**
   * "小程序直播" 小程序码
   * 当主播微信号没有在 “小程序直播“ 小程序实名认证 返回该字段
   */
  @SerializedName("qrcode_url")
  private String qrcodeUrl;

  /**
   * 房间ID
   */
  @SerializedName("roomId")
  private Integer roomId;
}
