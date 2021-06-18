package me.chanjar.weixin.mp.bean.guide;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.bean.ToJson;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 添加群发任务返回值
 *
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * @date 2021/5/13/013
 */
@Data
public class WxMpGuideMassed implements ToJson, Serializable {
  private static final long serialVersionUID = 7049976499427665050L;

  @SerializedName("task_id")
  private Long taskId;

  @SerializedName("openid")
  private List<String> list;

  @Override
  public String toJson() {
    return WxGsonBuilder.create().toJson(this);
  }

  public static <T> WxMpGuideMassed fromJson(T json) {
    if (json instanceof String) {
      return WxGsonBuilder.create().fromJson((String) json, WxMpGuideMassed.class);
    } else if (json instanceof JsonElement) {
      return WxGsonBuilder.create().fromJson((JsonElement) json, WxMpGuideMassed.class);
    }
    return null;
  }
}
