package cn.binarywang.wx.miniapp.bean.promoter.response;

import cn.binarywang.wx.miniapp.bean.WxMaBaseResponse;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author zhuangzibin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxMaPromotionGetShareMaterialResponse extends WxMaBaseResponse implements Serializable {

  private static final long serialVersionUID = -7085856752639339737L;

  /*
    {
      "share_path": "xxxxx",
      "qrcode": "xxxxx",
      "tag": "xxxxx",
      "errcode": 0,
      "errmsg": "OK"
    }
   */

  /**
   * 带参path
   */
  @SerializedName("share_path")
  private String sharePath;

  /**
   * 小程序码（图片base64）
   */
  @SerializedName("qrcode")
  private String qrcode;

  /**
   * 短链
   */
  @SerializedName("tag")
  private String tag;

}
