package me.chanjar.weixin.qidian.bean.shake;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.Data;

import java.util.Collection;

@Data
public class WxMpShakeAroundDeviceBindPageQuery {
  private WxMpDeviceIdentifier deviceIdentifier;
  private Collection<Integer> pageIds;
  public String toJsonString(){
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("device_identifier", deviceIdentifier.toJsonObject());
    JsonArray jsonArray = new JsonArray();
    for(Integer pageid: pageIds){
      jsonArray.add(pageid);
    }
    jsonObject.add("page_ids", jsonArray);
    return jsonObject.toString();
  }
}
