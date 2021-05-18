package me.chanjar.weixin.mp.bean.guide;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.bean.ToJson;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * 小程序素材信息
 *
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * @date 2021/5/12/012
 */
@Data
public class WxMpGuideCardMaterialInfo implements ToJson, Serializable {
  private static final long serialVersionUID = -3165724834271407258L;

  /**
   * 卡片名字
   */
  @SerializedName("title")
  private String title;

  /**
   * 小程序appid
   */
  @SerializedName("appid")
  private String appId;

  /**
   * 路径
   */
  @SerializedName("path")
  private String path;

  /**
   * 图片链接
   */
  @SerializedName("picurl")
  private String picUrl;

  /**
   * 图片id
   */
  @SerializedName("master_id")
  private Long masterId;

  /**
   * 图片id
   */
  @SerializedName("slave_id")
  private Long slaveId;

  @Override
  public String toJson() {
    return WxGsonBuilder.create().toJson(this);
  }

  public static WxMpGuideCardMaterialInfo fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpGuideCardMaterialInfo.class);
  }
}
