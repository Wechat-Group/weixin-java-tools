package me.chanjar.weixin.mp.bean.guide;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 顾问列表.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-10-07
 */
@Data
public class WxMpGuideList implements Serializable {
  private static final long serialVersionUID = 144044550239346216L;

  /**
   * 顾问总数量
   */
  @SerializedName("total_num")
  private Integer totalNum;

  /**
   * 顾问列表
   */
  private List<WxMpGuideInfo> list;

  public static WxMpGuideList fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpGuideList.class);
  }
}
