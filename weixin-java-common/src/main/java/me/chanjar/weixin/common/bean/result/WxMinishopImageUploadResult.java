package me.chanjar.weixin.common.bean.result;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Data;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;

@Data
public class WxMinishopImageUploadResult  implements Serializable {
  private static final long serialVersionUID = 330834334738622332L;

  private String errcode;
  private String errmsg;


  private WxMinishopPicFileResult picFile;


  public static WxMinishopImageUploadResult fromJson(String json) {
    JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
    WxMinishopImageUploadResult result = new WxMinishopImageUploadResult();
    result.setErrcode(jsonObject.get("errcode").getAsNumber().toString());
    if (result.getErrcode().equals("0")) {
      WxMinishopPicFileResult picFileResult = new WxMinishopPicFileResult();
      JsonObject picObject = jsonObject.get("pic_file").getAsJsonObject();
      picFileResult.setMediaId(picObject.get("media_id").getAsString());
      picFileResult.setPayMediaId(picObject.get("pay_media_id").getAsString());
      result.setPicFile(picFileResult);

    }
    return result;
  }

  @Override
  public String toString() {
    return WxGsonBuilder.create().toJson(this);
  }
}
