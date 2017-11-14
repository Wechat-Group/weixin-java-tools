package me.chanjar.weixin.mp.bean.shake;

import com.google.gson.JsonObject;
import lombok.Data;

import java.io.Serializable;

@Data
public class WxMpShakeAroundRelationSearchQuery implements Serializable {
  private int type;
  private Integer pageId;
  private Integer begin;
  private Integer count;
  private WxMpDeviceIdentifier deviceIdentifier;
  public String toJsonString(){
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("type", type);
   switch (type){
     case 1:
       jsonObject.add("device_identifier", deviceIdentifier.toJsonObject());
       break;
     case 2:
       jsonObject.addProperty("page_id", pageId);
       jsonObject.addProperty("begin", begin);
       jsonObject.addProperty("count", count);
       break;
     default:
       throw new IllegalArgumentException("type error");
   }
   return jsonObject.toString();
  }
}
