package me.chanjar.weixin.open.bean.minishop;

import com.google.gson.JsonObject;
import lombok.Data;

import java.io.Serializable;

@Data
public class MinishopPicFile implements Serializable {


  private String mediaId;

  private String payMediaId;

  public JsonObject toJsonObject() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("media_id", mediaId);
    jsonObject.addProperty("pay_media_id", payMediaId);
    return jsonObject;
  }
}
