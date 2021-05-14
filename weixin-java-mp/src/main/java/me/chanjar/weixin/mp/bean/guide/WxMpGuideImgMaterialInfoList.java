package me.chanjar.weixin.mp.bean.guide;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 图片素材列表
 *
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * @date 2021/5/12/012
 */
@Data
public class WxMpGuideImgMaterialInfoList implements Serializable {
  private static final long serialVersionUID = 8876840664010690223L;

  /**
   * 图片素材总数
   */
  @SerializedName("total_num")
  private Integer totalNum;

  /**
   * 图片素材列表
   */
  @SerializedName("model_list")
  private List<WxMpGuideImgMaterialInfo> list;

  public static WxMpGuideImgMaterialInfoList fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpGuideImgMaterialInfoList.class);
  }
}
