package me.chanjar.weixin.mp.bean.guide;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 顾问分组内顾问信息
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * @date 2021/5/8/008
 */
@Data
public class WxMpGuideGroupInfoList implements Serializable {
  private static final long serialVersionUID = 7037631524066068497L;

  /**
   * 分组顾问总数量
   */
  @SerializedName("total_num")
  private Integer totalNum;

  /**
   * 分组顾问列表
   */
  @SerializedName("guide_list")
  private List<WxMpGuideGroupInfo> list;

  public static WxMpGuideGroupInfoList fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpGuideGroupInfoList.class);
  }
}
