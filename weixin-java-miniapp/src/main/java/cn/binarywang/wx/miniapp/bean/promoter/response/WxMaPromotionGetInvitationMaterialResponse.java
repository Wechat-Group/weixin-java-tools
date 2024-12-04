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
public class WxMaPromotionGetInvitationMaterialResponse extends WxMaBaseResponse implements Serializable {

  private static final long serialVersionUID = 7833175570601030853L;

  /*
    {
    "poster": "xxxxx",
    "qrcode": "xxxxx",
    "tag": "xxxxx",
    "errcode": 0,
    "errmsg": "OK"
    }
   */

  /**
   * 海报（图片base64）
   */
  @SerializedName("poster")
  private String poster;

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
