package me.chanjar.weixin.qidian.bean.result;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import me.chanjar.weixin.qidian.util.json.WxMpGsonBuilder;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 主体变更迁移用户 openid 返回.
 *
 * @author 007gzs
 */
@Data
public class WxMpChangeOpenid implements Serializable {
  private static final long serialVersionUID = -8132023284876534743L;
  private String oriOpenid;
  private String newOpenid;
  private String errMsg;
  public static WxMpChangeOpenid fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpChangeOpenid.class);
  }
  public static List<WxMpChangeOpenid> fromJsonList(String json) {
    Type collectionType = new TypeToken<List<WxMpChangeOpenid>>() {
    }.getType();
    Gson gson = WxMpGsonBuilder.create();
    JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
    return gson.fromJson(jsonObject.get("result_list"), collectionType);
  }
}
