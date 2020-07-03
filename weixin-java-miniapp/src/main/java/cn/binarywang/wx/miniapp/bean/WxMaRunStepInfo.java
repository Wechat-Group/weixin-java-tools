package cn.binarywang.wx.miniapp.bean;

import java.io.Serializable;
import java.util.List;

import cn.binarywang.wx.miniapp.util.json.WxMaGsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import me.chanjar.weixin.common.util.json.GsonParser;

/**
 * 微信运动步数信息.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
public class WxMaRunStepInfo implements Serializable {

  private static final long serialVersionUID = -7496372171398607044L;

  /**
   * 时间戳，表示数据对应的时间.
   */
  private Long timestamp;

  /**
   * 微信运动步数.
   */
  private Integer step;

  public static List<WxMaRunStepInfo> fromJson(String json) {
    JsonObject jsonObject = GsonParser.parse(json);
    return WxMaGsonBuilder.create().fromJson(jsonObject.get("stepInfoList").toString(),
      new TypeToken<List<WxMaRunStepInfo>>() {
      }.getType());
  }
}
