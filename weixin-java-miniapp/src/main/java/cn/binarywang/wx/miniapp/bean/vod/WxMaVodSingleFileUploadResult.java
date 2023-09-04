package cn.binarywang.wx.miniapp.bean.vod;

import cn.binarywang.wx.miniapp.bean.WxMaBaseResponse;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;


@Data
public class WxMaVodSingleFileUploadResult extends WxMaBaseResponse implements Serializable {
  private static final long serialVersionUID = 7495157056049312108L;

  @SerializedName("media_id")
  private Integer mediaId;

  public static WxMaVodSingleFileUploadResult fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMaVodSingleFileUploadResult.class);
  }

  @Override
  public String toString() {
    return WxGsonBuilder.create().toJson(this);
  }

}
