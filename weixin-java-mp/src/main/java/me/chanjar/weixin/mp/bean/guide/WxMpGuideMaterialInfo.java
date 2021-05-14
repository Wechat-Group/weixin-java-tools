package me.chanjar.weixin.mp.bean.guide;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;
import me.chanjar.weixin.common.bean.ToJson;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * 素材信息
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * @date 2021/5/13/013
 */

@Data
@Builder
public class WxMpGuideMaterialInfo implements ToJson, Serializable {
  private static final long serialVersionUID = -6201520701655588983L;

  /**
   * 素材类型,1.文本,3.图片,49.小程序卡片
   */
  @SerializedName("type")
  private int type;

  /**
   * 图片类型素材或者卡片类型素材的封面，只能用《素材管理获取media_id》（注意目前只能用临时素材的media_id）
   */
  @SerializedName("media_id")
  private String mediaId;

  /**
   * 小程序卡片标题,最多35字
   */
  @SerializedName("title")
  private String title;

  /**
   * 小程序卡片路径
   */
  @SerializedName("path")
  private String path;

  /**
   * 小程序卡片appid,需要关联到公众号
   */
  @SerializedName("appid")
  private String appId;

  /**
   * 文本类型素材的内容,不超过300字节
   */
  @SerializedName("word")
  private String word;

  @Override
  public String toJson() {
    return WxGsonBuilder.create().toJson(this);
  }

  public static WxMpGuideMaterialInfo fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpGuideMaterialInfo.class);
  }
}
