/**
 *
 */
package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

import java.io.Serializable;

/**
 * @author lfj
 */
@Data
public class WxOpenWxaAddTemplateResult implements Serializable {

  private static final long serialVersionUID = -4265339517906157137L;

  @SerializedName("template_id")
  private String templateId;

  public static WxOpenWxaAddTemplateResult fromJson(String json) {
    return WxOpenGsonBuilder.create().fromJson(json, WxOpenWxaAddTemplateResult.class);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.INSTANCE.create().toJson(this);
  }

}
