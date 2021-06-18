package me.chanjar.weixin.mp.bean.guide;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 文字素材信息列表
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * @date 2021/5/12/012
 */
@Data
public class WxMpGuideWordMaterialInfoList implements Serializable {
  private static final long serialVersionUID = 6891519244712898267L;

  /**
   * 文字素材总数
   */
  @SerializedName("total_num")
  private Integer totalNum;

  /**
   * 文字素材列表
   */
  @SerializedName("word_list")
  private List<WxMpGuideWordMaterialInfo> list;

  public static WxMpGuideWordMaterialInfoList fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpGuideWordMaterialInfoList.class);
  }
}
